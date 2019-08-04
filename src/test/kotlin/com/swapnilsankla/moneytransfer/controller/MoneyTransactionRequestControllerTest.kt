package com.swapnilsankla.moneytransfer.controller

import com.swapnilsankla.moneytransfer.model.TransactionRequest
import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxStreamingHttpClient
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

@MicronautTest
class MoneyTransactionRequestControllerTest {

    @Test
    fun success_case() {
        val embeddedServer = ApplicationContext.run(EmbeddedServer::class.java)
        val httpClient = RxStreamingHttpClient.create(embeddedServer.url)
        val request = TransactionRequest("1234", "4321", 1000.0)
        val response = httpClient.toBlocking().exchange(HttpRequest.POST("/transaction", request), TransactionRequest::class.java)
        assertEquals(HttpStatus.OK, response.status)
        assertEquals(request, response.body())
    }

    @Test
    fun failure_case() {
        val embeddedServer = ApplicationContext.run(EmbeddedServer::class.java)
        val httpClient = RxStreamingHttpClient.create(embeddedServer.url)
        val request = TransactionRequest("123", "4321", 1000.0)

        assertThrows(HttpClientResponseException::class.java) {
            httpClient.toBlocking().exchange(HttpRequest.POST("/transaction", request), TransactionRequest::class.java)
        }
    }
}