package com.swapnilsankla.moneytransfer.service

import com.swapnilsankla.moneytransfer.model.Account
import com.swapnilsankla.moneytransfer.repository.AccountRepository
import javax.inject.Singleton

@Singleton
class MoneyTransferService(private val accountRepository: AccountRepository) {
    @Synchronized
    fun transfer(fromAccountNumber: String, toAccountNumber: String, amount: Double): Boolean {
        val fromAccount = accountRepository.find(fromAccountNumber)
        val toAccount = accountRepository.find(toAccountNumber)
        return fromAccount?.let { toAccount?.let { transfer(fromAccount, toAccount, amount) } } ?: false
    }

    private fun transfer(from: Account, to: Account, amount: Double): Boolean {
        if (from.sufficientBalanceToDebit(amount)) {
            from.debit(amount)
            to.credit(amount)
            return true
        }
        return false
    }
}


