package com.swapnilsankla.moneytransfer.service

import com.swapnilsankla.moneytransfer.model.Account
import com.swapnilsankla.moneytransfer.repository.AccountRepository

class MoneyTransferService(private val accountRepository: AccountRepository) {
    fun transfer(fromAccountNumber: String, toAccountNumber: String, amount: Double): Boolean {
        val fromAccount = accountRepository.find(fromAccountNumber)
        val toAccount = accountRepository.find(toAccountNumber)
        return transfer(fromAccount, toAccount, amount)
    }

    private fun transfer(from: Account, to: Account, amount: Double): Boolean {
        if (from.balance >= amount) {
            from.debit(amount)
            to.credit(amount)
            return true
        }
        return false
    }
}


