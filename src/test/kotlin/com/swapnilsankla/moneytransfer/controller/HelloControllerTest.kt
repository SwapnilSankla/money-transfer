package com.swapnilsankla.moneytransfer.controller

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxStreamingHttpClient
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

@MicronautTest
class HelloControllerTest {

    @Test
    fun `hello controller test`() {
        val embeddedServer : EmbeddedServer = ApplicationContext.run(EmbeddedServer::class.java)
        val httpClient= RxStreamingHttpClient.create(embeddedServer.url)
        val response = httpClient.toBlocking().exchange("/hello", String::class.java)
        Assertions.assertEquals(HttpStatus.OK, response.status)
        Assertions.assertEquals("Hello World !!!", response.body())
    }
}
