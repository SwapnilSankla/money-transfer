package com.swapnilsankla.moneytransfer.service

import com.swapnilsankla.moneytransfer.model.Account
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MoneyTransferServiceTest {
    @Test
    fun `should be able to transfer money from 1 account to another account`() {
        val moneyTransferService = MoneyTransferService()

        val transferred = moneyTransferService.transfer(
                from = Account("1234", 10000.0),
                to = Account("4321", 5000.0),
                amount = 100.0
        )

        Assertions.assertTrue(transferred)
    }

    @Test
    fun `on successful transfer the amount should be debited from sender's account`() {
        val moneyTransferService = MoneyTransferService()
        val from = Account("1234", 10000.0)
        moneyTransferService.transfer(
                from = from,
                to = Account("4321", 5000.0),
                amount = 100.0
        )
        Assertions.assertEquals(9900.0, from.balance)
    }

    @Test
    fun `on successful transfer the amount should be credit to receiver's account`() {
        val moneyTransferService = MoneyTransferService()
        val to = Account("4321", 5000.0)
        moneyTransferService.transfer(
                from = Account("1234", 10000.0),
                to = to,
                amount = 100.0
        )
        Assertions.assertEquals(5100.0, to.balance)
    }

    @Test
    fun `should be able to transfer money from 1 account to another account only if account has sufficient balance`() {
        val moneyTransferService = MoneyTransferService()

        val transferred = moneyTransferService.transfer(
                from = Account("1234", 1000.0),
                to = Account("4321", 5000.0),
                amount = 2000.0
        )

        Assertions.assertFalse(transferred)
    }

    @Test
    fun `on unsuccessful transfer the amount should not be debited from sender's account`() {
        val moneyTransferService = MoneyTransferService()
        val from = Account("1234", 10000.0)
        moneyTransferService.transfer(
                from = from,
                to = Account("4321", 5000.0),
                amount = 11100.0
        )
        Assertions.assertEquals(10000.0, from.balance)
    }

    @Test
    fun `on unsuccessful transfer the amount should be credit to receiver's account`() {
        val moneyTransferService = MoneyTransferService()
        val to = Account("4321", 5000.0)
        moneyTransferService.transfer(
                from = Account("1234", 10000.0),
                to = to,
                amount = 11100.0
        )
        Assertions.assertEquals(5000.0, to.balance)
    }
}