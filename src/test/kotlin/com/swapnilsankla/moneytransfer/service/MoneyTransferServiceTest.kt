package com.swapnilsankla.moneytransfer.service

import com.swapnilsankla.moneytransfer.model.Account
import com.swapnilsankla.moneytransfer.model.Transaction
import com.swapnilsankla.moneytransfer.repository.AccountRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MoneyTransferServiceTest {
    @Test
    fun `should be able to transfer money from 1 account to another account`() {
        val from = Account("1234", 10000.0)
        val to = Account("4321", 5000.0)
        val moneyTransferService = MoneyTransferService(DummyAccountRepository(listOf(from, to)))

        val transaction: Transaction? = moneyTransferService.transfer(
                fromAccountNumber = "1234",
                toAccountNumber = "4321",
                amount = 100.0
        )

        assertNotNull(transaction)
    }

    @Test
    fun `should be able to transfer money from 1 account to another account and return correct transaction response`() {
        val from = Account("1234", 10000.0)
        val to = Account("4321", 5000.0)
        val moneyTransferService = MoneyTransferService(DummyAccountRepository(listOf(from, to)))

        val transaction: Transaction? = moneyTransferService.transfer(
                fromAccountNumber = "1234",
                toAccountNumber = "4321",
                amount = 100.0
        )

        assertEquals("1234", transaction!!.fromAccountNumber)
        assertEquals("4321", transaction.toAccountNumber)
        assertEquals(100.0, transaction.amount)
    }

    @Test
    fun `on successful transfer the amount should be debited from sender's account`() {
        val from = Account("1234", 10000.0)
        val to = Account("4321", 5000.0)
        val moneyTransferService = MoneyTransferService(DummyAccountRepository(listOf(from, to)))

        moneyTransferService.transfer(
                fromAccountNumber = "1234",
                toAccountNumber = "4321",
                amount = 100.0
        )
        assertEquals(9900.0, from.balance)
    }

    @Test
    fun `on successful transfer the amount should be credit to receiver's account`() {
        val from = Account("1234", 10000.0)
        val to = Account("4321", 5000.0)
        val moneyTransferService = MoneyTransferService(DummyAccountRepository(listOf(from, to)))

        moneyTransferService.transfer(
                fromAccountNumber = "1234",
                toAccountNumber = "4321",
                amount = 100.0
        )
        assertEquals(5100.0, to.balance)
    }

    @Test
    fun `should be able to transfer money from 1 account to another account only if account has sufficient balance`() {
        val from = Account("1234", 1000.0)
        val to = Account("4321", 5000.0)
        val moneyTransferService = MoneyTransferService(DummyAccountRepository(listOf(from, to)))

        val transaction = moneyTransferService.transfer(
                fromAccountNumber = "1234",
                toAccountNumber = "4321",
                amount = 2000.0
        )

        assertNull(transaction)
    }

    @Test
    fun `on unsuccessful transfer the amount should not be debited from sender's account`() {
        val from = Account("1234", 10000.0)
        val to = Account("4321", 5000.0)
        val moneyTransferService = MoneyTransferService(DummyAccountRepository(listOf(from, to)))
        moneyTransferService.transfer(
                fromAccountNumber = "1234",
                toAccountNumber = "4321",
                amount = 11100.0
        )
        assertEquals(10000.0, from.balance)
    }

    @Test
    fun `on unsuccessful transfer the amount should be credit to receiver's account`() {
        val from = Account("1234", 10000.0)
        val to = Account("4321", 5000.0)
        val moneyTransferService = MoneyTransferService(DummyAccountRepository(listOf(from, to)))
        moneyTransferService.transfer(
                fromAccountNumber = "1234",
                toAccountNumber = "4321",
                amount = 11100.0
        )
        assertEquals(5000.0, to.balance)
    }

    @Test
    fun `transaction should fail if sender's accountNumber not found`() {
        val from = Account("8657", 10000.0)
        val to = Account("4321", 5000.0)
        val moneyTransferService = MoneyTransferService(DummyAccountRepository(listOf(from, to)))

        val transaction = moneyTransferService.transfer(
                fromAccountNumber = "1234",
                toAccountNumber = "4321",
                amount = 2000.0
        )

        assertNull(transaction)
    }

    @Test
    fun `transaction should fail if receiver's accountNumber not found`() {
        val from = Account("1234", 10000.0)
        val to = Account("8657", 5000.0)
        val moneyTransferService = MoneyTransferService(DummyAccountRepository(listOf(from, to)))

        val transaction = moneyTransferService.transfer(
                fromAccountNumber = "1234",
                toAccountNumber = "4321",
                amount = 2000.0
        )

        assertNull(transaction)
    }
}

class DummyAccountRepository(private val accounts: List<Account>): AccountRepository {
    override fun find(accountNumber: String) = accounts.find { acc -> acc.id == accountNumber }
}
