package kr.co.kalpa.demo.springkotlinjunit.controller

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
    val mockMvc: MockMvc
){

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

        private val baseUrl = "/api/banks"

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

}