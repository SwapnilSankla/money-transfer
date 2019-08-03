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
    fun `should be able to transfer money from 1 account to another account only if account has sufficient balance`() {
        val moneyTransferService = MoneyTransferService()

        val transferred = moneyTransferService.transfer(
                from = Account("1234", 1000.0),
                to = Account("4321", 5000.0),
                amount = 2000.0
        )

        Assertions.assertFalse(transferred)
    }
}