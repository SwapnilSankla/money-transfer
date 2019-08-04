package com.swapnilsankla.moneytransfer.service

import com.swapnilsankla.moneytransfer.model.Account
import com.swapnilsankla.moneytransfer.model.Transaction
import com.swapnilsankla.moneytransfer.repository.AccountRepository
import java.util.*
import javax.inject.Singleton

@Singleton
class MoneyTransferService(private val accountRepository: AccountRepository) {
    @Synchronized
    fun transfer(fromAccountNumber: String, toAccountNumber: String, amount: Double): Transaction? {
        val fromAccount = accountRepository.find(fromAccountNumber)
        val toAccount = accountRepository.find(toAccountNumber)
        return fromAccount?.let { toAccount?.let { transfer(fromAccount, toAccount, amount) } }
    }

    private fun transfer(from: Account, to: Account, amount: Double): Transaction? {
        if (from.sufficientBalanceToDebit(amount)) {
            from.debit(amount)
            to.credit(amount)
            return Transaction(
                    id = UUID.randomUUID().toString(),
                    fromAccountNumber = from.id,
                    toAccountNumber = to.id,
                    amount = amount
            )
        }
        return null
    }
}


