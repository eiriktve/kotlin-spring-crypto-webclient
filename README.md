# Cryptocurrency API

API which utilizes the CoinGecko API to retrieve various information about crypto, such as listing of coins, current prices, trends etc.

## Technologies
* Kotlin with Java 11 JVM
* Spring Boot
* Spring boot web
* JDBC CRUD with H2
* Webflux for remote calls

## How to get started
The database is initially empty, but can be populated through an endpoint. Call this resource before calling any of the other services. **{server}/api/db/populate**