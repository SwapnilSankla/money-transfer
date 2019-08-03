package com.swapnilsankla.moneytransfer.controller

import com.swapnilsankla.moneytransfer.model.Transaction
import com.swapnilsankla.moneytransfer.service.MoneyTransferService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/transaction")
class MoneyTransactionController(private val moneyTransferService: MoneyTransferService) {
    @Post("/")
    fun transfer(@Body transaction: Transaction): HttpResponse<Transaction> {
        val transferred = moneyTransferService.transfer(
                fromAccountNumber = transaction.fromAccountNumber,
                toAccountNumber = transaction.toAccountNumber,
                amount = transaction.amount
        )
        if (transferred) {
            return HttpResponse.ok(transaction)
        }
        return HttpResponse.unprocessableEntity()
    }
}