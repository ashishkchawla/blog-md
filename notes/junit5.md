#TDD Using Junit 5

##Benefits

* Less time dbugging
* Code proven to be correct and meet requirements
* Faster time to market
* Built-in set of regression tests, when adding new functionalities, or changing existing functionalities


## Test Cases with Spring Boot

###Support:
* @SpringBootTest: Combined with Spring Extension annotation, loads full application context in the test and manages the whole context as test runs.
* @MockMvc: For testing controllers. Tests using WebRequests and validates the response body.
* @MockBean: Mock Spring managed beans that will get autowired into context. And then configuring its behaviour.
###Third party extensions for managing backend resources
* DBUnit: pre-populate database using sql file, then cleans up database post the test.
* Custom extension for MongoDB: Prepopulate MongoDB from JSON file and then cleans up after the test.
* WireMock: Simulate responses from third party API.




