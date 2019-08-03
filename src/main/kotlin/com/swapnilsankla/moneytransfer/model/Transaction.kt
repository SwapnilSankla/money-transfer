package com.swapnilsankla.moneytransfer.model

data class Transaction(val fromAccountNumber: String,
                       val toAccountNumber: String,
                       val amount: Double)
