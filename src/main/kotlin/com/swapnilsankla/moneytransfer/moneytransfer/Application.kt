package com.swapnilsankla.moneytransfer.moneytransfer

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("com.swapnilsankla")
                .mainClass(Application.javaClass)
                .start()
    }
}