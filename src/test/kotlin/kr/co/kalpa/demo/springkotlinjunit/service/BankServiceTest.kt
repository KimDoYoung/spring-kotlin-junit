package kr.co.kalpa.demo.springkotlinjunit.service

import io.mockk.mockk
import io.mockk.verify
import kr.co.kalpa.demo.springkotlinjunit.datasource.BankDataSource
import org.junit.jupiter.api.Test

internal class BankServiceTest {

    private val dataSource: BankDataSource = mockk(relaxed = true)

    private val bankService = BankService(dataSource)

    @Test
    fun `call getBanks`() {
        //when
        bankService.getBanks()

        //then
        verify(exactly = 1) { dataSource.retrieveBanks()  }
    }
}