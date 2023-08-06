package kr.co.kalpa.demo.springkotlinjunit.datasource.mock

import kr.co.kalpa.demo.springkotlinjunit.datasource.BankDataSource
import kr.co.kalpa.demo.springkotlinjunit.model.Bank
import org.springframework.stereotype.Repository
import kotlin.NoSuchElementException

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

    override fun insertBank(bank: Bank): Bank {
        if(banks.any { it.accountNumber == bank.accountNumber}){
            throw IllegalArgumentException("Bank with account ${bank.accountNumber} already exists")
        }
        banks.add(bank)
        return bank
    }

    override fun updateBank(bank: Bank): Bank {
        val currentBank = banks.firstOrNull{ it.accountNumber == bank.accountNumber}
            ?: throw NoSuchElementException("Could not find a bank with account number ${bank.accountNumber}")

        banks.remove(currentBank)
        banks.add(bank)
        return bank
    }

    override fun deleteBank(accountNumber: String) {
        val currentBank = banks.firstOrNull{ it.accountNumber == accountNumber}
            ?: throw NoSuchElementException("Could not find a bank with account number $accountNumber")

        banks.remove(currentBank)
    }

}