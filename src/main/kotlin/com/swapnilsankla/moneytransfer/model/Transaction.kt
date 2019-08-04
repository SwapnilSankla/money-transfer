package com.swapnilsankla.moneytransfer.model

data class Transaction(val id: String,
                       val fromAccountNumber: String,
                       val toAccountNumber: String,
                       val amount: Double)
