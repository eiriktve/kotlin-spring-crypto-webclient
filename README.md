# Cryptocurrency API

API which populates a db with data from CoinGecko, and serves crypto data such as listing of coins,
current prices, trends etc available through different endpoints.

## Technologies / features
* Kotlin with Java 17 JVM
* Spring Boot 3
* Spring JDBC CRUD with H2
* Webclient/Webflux
* Error handling with advice
* kotlinx serialization

## How to get started
The database is initially empty, but can be populated through an endpoint. Call this resource before calling any of 
the other services. **/api/db/populate**

### Other endpoints
**GET a specific currency (i.e., btc)**: /api/crypto/currency/btc \
**GET All currencies:** /api/crypto/currency

## Running the application
``mvn spring-boot:run`` 

Alternatively run in a docker container: \
````docker build -t kotlin-spring-crypto .```` \
````docker run -p 8080:8080 kotlin-spring-crypto````

Configured to run with the **dev** profile as default, see application-dev.yml for config.
