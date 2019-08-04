package com.swapnilsankla.moneytransfer.model

data class TransactionRequest(val fromAccountNumber: String,
                              val toAccountNumber: String,
                              val amount: Double)
