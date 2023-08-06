package kr.co.kalpa.demo.springkotlinjunit.datasource.mock

import kr.co.kalpa.demo.springkotlinjunit.datasource.BankDataSource
import kr.co.kalpa.demo.springkotlinjunit.model.Bank
import org.springframework.stereotype.Repository
import java.util.NoSuchElementException

@Repository("mock")
class MockBankDataSource : BankDataSource {
    val banks = mutableListOf<Bank>(
        Bank("1111", 10.12, 10),
        Bank("2222", 13.32, 20),
        Bank("3333", 20.42, 30)
    )

    override fun retrieveBanks(): Collection<Bank> = banks
    override fun retrieveBanks(accountNumber: String): Bank = banks.firstOrNull(){
        it.accountNumber == accountNumber
    } ?: throw NoSuchElementException("$accountNumber not found")

}