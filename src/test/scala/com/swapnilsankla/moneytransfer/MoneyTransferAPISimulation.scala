package com.swapnilsankla.moneytransfer

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class MoneyTransferAPISimulation extends Simulation {
  val httpConf = http.baseUrl("http://localhost:8080")
    .acceptHeader("application/json")

  val scn = scenario("Basic Simulation")
    .exec(http("request_1")
      .post("/transaction")
      .body(StringBody(
      """{
          "fromAccountNumber": "1234",
          "toAccountNumber": "4321",
          "amount": 1.0
       }"""))
      .asJson
      .check(status.is(201)))

  setUp(scn.inject(atOnceUsers(1000))).protocols(httpConf)
}
