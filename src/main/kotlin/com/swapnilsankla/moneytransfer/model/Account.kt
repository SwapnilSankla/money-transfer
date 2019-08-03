package com.swapnilsankla.moneytransfer.model

data class Account(val id: String, var balance: Double) {
    fun debit(amount: Double) {
        balance -= amount
    }

    fun credit(amount: Double) {
        balance += amount
    }
}
