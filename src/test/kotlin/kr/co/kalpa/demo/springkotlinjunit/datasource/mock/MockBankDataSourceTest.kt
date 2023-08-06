package kr.co.kalpa.demo.springkotlinjunit.datasource.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MockBankDataSourceTest{
    private val mockBankDataSource = MockBankDataSource()

    @Test
    fun `should banks exists`(){
        val banks = mockBankDataSource.retrieveBanks()
        assertThat(banks.size).isGreaterThanOrEqualTo(3)
    }

    @Test
    fun `bank data test`(){
        val banks = mockBankDataSource.retrieveBanks()
        assertThat(banks).allMatch{ it.accountNumber.isNotEmpty()}
        assertThat(banks).anyMatch{ it.trust != 0.0 }
    }

}