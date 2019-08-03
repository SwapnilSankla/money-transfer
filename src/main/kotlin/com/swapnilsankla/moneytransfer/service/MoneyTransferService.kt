package com.swapnilsankla.moneytransfer.service

import com.swapnilsankla.moneytransfer.model.Account

class MoneyTransferService {
    fun transfer(from: Account, to: Account, amount: Double): Boolean {
        from.debit(amount)
        to.credit(amount)
        return true
    }
}


