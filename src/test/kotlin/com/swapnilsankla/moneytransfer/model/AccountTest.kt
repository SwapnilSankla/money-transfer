package com.swapnilsankla.moneytransfer.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AccountTest {

    @Test
    fun debit() {
        val account = Account("1234", 10000.0)
        account.credit(500.0)
        assertEquals(10500.0, account.balance)
    }

    @Test
    fun credit() {
        val account = Account("1234", 10000.0)
        account.debit(500.0)
        assertEquals(9500.0, account.balance)
    }

    @Test
    fun `sufficientBalanceToDebit should return true if balance greater than given amount`() {
        val account = Account("1234", 10000.0)
        assertTrue(account.sufficientBalanceToDebit(5000.0))
    }

    @Test
    fun `sufficientBalanceToDebit should return false if balance less than given amount`() {
        val account = Account("1234", 10000.0)
        assertFalse(account.sufficientBalanceToDebit(50000.0))
    }
}