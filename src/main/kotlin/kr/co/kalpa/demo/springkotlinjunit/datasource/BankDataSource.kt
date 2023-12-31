package kr.co.kalpa.demo.springkotlinjunit.datasource

import kr.co.kalpa.demo.springkotlinjunit.model.Bank

interface BankDataSource {
    fun retrieveBanks(): Collection<Bank>
    fun retrieveBanks(accountNumber: String) : Bank
    fun  insertBank(bank: Bank): Bank
    fun updateBank(bank: Bank): Bank
    fun deleteBank(accountNumber: String)
}