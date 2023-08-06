package kr.co.kalpa.demo.springkotlinjunit.service

import kr.co.kalpa.demo.springkotlinjunit.datasource.BankDataSource
import kr.co.kalpa.demo.springkotlinjunit.model.Bank
import org.springframework.stereotype.Service

@Service
class BankService(private val bankDataSource: BankDataSource) {
    fun getBanks(): Collection<Bank> = bankDataSource.retrieveBanks()
    fun getBank(accountNumber: String) = bankDataSource.retrieveBanks(accountNumber)

}