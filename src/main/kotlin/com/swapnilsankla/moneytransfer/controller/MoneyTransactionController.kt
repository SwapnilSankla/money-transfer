package com.swapnilsankla.moneytransfer.controller

import com.swapnilsankla.moneytransfer.model.Transaction
import com.swapnilsankla.moneytransfer.model.TransactionRequest
import com.swapnilsankla.moneytransfer.service.MoneyTransferService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/transaction")
class MoneyTransactionController(private val moneyTransferService: MoneyTransferService) {
    @Post("/")
    fun transfer(@Body transactionRequest: TransactionRequest): HttpResponse<Transaction> {
        val transaction = moneyTransferService.transfer(
                fromAccountNumber = transactionRequest.fromAccountNumber,
                toAccountNumber = transactionRequest.toAccountNumber,
                amount = transactionRequest.amount
        )
        if (transaction != null) {
            return HttpResponse.ok(transaction)
        }
        return HttpResponse.unprocessableEntity()
    }
}