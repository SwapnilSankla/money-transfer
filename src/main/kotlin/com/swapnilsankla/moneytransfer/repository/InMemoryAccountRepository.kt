package com.swapnilsankla.moneytransfer.repository

import com.swapnilsankla.moneytransfer.model.Account
import javax.inject.Singleton

interface AccountRepository {
    fun find(accountNumber: String): Account?
}

@Singleton
open class InMemoryAccountRepository(private val accounts: MutableList<Account>): AccountRepository {
    init {
        accounts.add(Account("1234", 10000.0))
        accounts.add(Account("4321", 5000.0))
    }

    override fun find(accountNumber: String) = accounts.find { acc -> acc.id == accountNumber }
}