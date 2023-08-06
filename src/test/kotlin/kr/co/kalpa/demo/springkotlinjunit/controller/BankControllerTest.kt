package kr.co.kalpa.demo.springkotlinjunit.controller

import com.fasterxml.jackson.databind.ObjectMapper
import kr.co.kalpa.demo.springkotlinjunit.model.Bank
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
){
    private val baseUrl = "/api/banks"

    @Nested
    @DisplayName("Get /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBanks {

        private val baseUrl = "/api/banks"

        @Test
        fun `get all banks`(){
            //when then
            mockMvc.get(baseUrl)
                .andDo { println() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].accountNumber"){ value("1111")}
                }
        }
    }

    @Nested
    @DisplayName("Get /api/banks/{accountNumber}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBank {

        @Test
        fun `get one data`(){
            //when then
            val accountNumber = "1111"
            mockMvc.get("$baseUrl/$accountNumber")
                .andDo { println() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.trust"){ value(10.12)}
                    jsonPath("$.transactionFee"){value(10)}
                }
        }
        @Test
        fun `get one data but NOT FOUND`(){
            //when then
            val accountNumber = "not_exists_account_number"
            mockMvc.get("$baseUrl/$accountNumber")
                .andDo { println() }
                .andExpect {
                    status { isNotFound() }
                }
        }
    }
    @Nested
    @DisplayName("Post /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostNewBank {
        @Test
        fun `should add the new bank`(){
            //given
            val newBank = Bank("acc123", 31.45, 2)

            //when
            val performPost = mockMvc.post(baseUrl){
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(newBank)
            }
            //then
            performPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(newBank))
                    }
                }
            mockMvc.get("$baseUrl/${newBank.accountNumber}")
                .andDo { print() }
                .andExpect {
                    content { json(objectMapper.writeValueAsString(newBank)) }
                }
        }
        @Test
        fun `should return BAD_REQUEST if bank with given account number already exists`(){
            //given
            val invalidBank = Bank("1111", 34.12, 12)
            //when
            val performPost = mockMvc.post(baseUrl){
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }
            //then
            performPost
                .andDo { print() }
                .andExpect { status{ isBadRequest()} }

        }
    }
    @Nested
    @DisplayName("Patch /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PatchExistingBank {
        @Test
        fun `should update an existing bank`(){
            //given
            val updateBank = Bank("1111", 1.0, 1)
            //when
            val performPatchBank = mockMvc.patch(baseUrl){
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updateBank)
            }
            //then
            performPatchBank
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        content { json(objectMapper.writeValueAsString(updateBank)) }
                    }
                }
            mockMvc.get("$baseUrl/${updateBank.accountNumber}")
                .andExpect { content { json(objectMapper.writeValueAsString(updateBank)) } }
        }
        @Test
        fun `should bad request if existing bank`(){
            //given
            val not_exist_bank = Bank("not_exist_bank", 32.4,1)
            //when
            val performUpdate = mockMvc.patch(baseUrl){
                contentType= MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(not_exist_bank)
            }
            //then
            performUpdate
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }
    }
    @Nested
    @DisplayName("DELETE /api/banks/{accountNumber}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class DeleteExistingBank {

        @Test
        fun `should delete the bank with given account number`(){
            //given
            val accountNumber = "1111"
            //when then
            mockMvc.delete("$baseUrl/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status{ isNoContent() }
                }
            mockMvc.get("$baseUrl/$accountNumber")
                .andExpect { status {isNotFound()} }
        }

    }

}