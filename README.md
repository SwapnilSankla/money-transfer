# money-transfer

This repository contains a simple service to transfer money among accounts. Following are the details.

1. Language: Kotlin
2. Dependency manager: Gradle
3. API framework: Micronaut
4. Test framework: JUnit
5. Performance test framework: Gatling

## Commands

1. Build: `gradle clean build`
2. Run: `java -jar build/libs/money-transfer-0.1.jar`
3. Run performance test: 
    1. `java -jar build/libs/money-transfer-0.1.jar &`
    2. `gradle loadTest`

## Limitations

1. Accounts are hardcoded in the InMemoryAccountRepository
2. For running performance test, application needs to be started manually.