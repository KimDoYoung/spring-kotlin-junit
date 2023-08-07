package kr.co.kalpa.demo.springkotlinjunit.service

import kr.co.kalpa.demo.springkotlinjunit.datasource.BankDataSource
import kr.co.kalpa.demo.springkotlinjunit.model.Bank
import org.springframework.stereotype.Service

@Service
class BankService(private val bankDataSource: BankDataSource) {
    fun getBanks(): Collection<Bank> = bankDataSource.retrieveBanks()
    fun getBank(accountNumber: String) : Bank = bankDataSource.retrieveBanks(accountNumber)
    fun addBank(bank: Bank) : Bank =  bankDataSource.insertBank(bank)
    fun update(bank: Bank): Bank = bankDataSource.updateBank(bank)
    fun deleteBank(accountNumber: String) =  bankDataSource.deleteBank(accountNumber)
}