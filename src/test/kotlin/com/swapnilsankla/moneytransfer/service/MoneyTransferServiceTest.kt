package com.swapnilsankla.moneytransfer.service

import com.swapnilsankla.moneytransfer.model.Account
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MoneyTransferServiceTest {
    @Test
    fun `should be able to transfer money from 1 account to another account`() {
        val moneyTransferService = MoneyTransferService()

        val transferred = moneyTransferService.transfer(Account("1234",10000.0), Account("4321", 5000.0), 100.0)

        Assertions.assertTrue(transferred)
    }
}