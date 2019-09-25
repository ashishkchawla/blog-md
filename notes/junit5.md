#Junit Platform in general
Junit Platform is serves as foundation for launching testing frameworks on JVM. It also defines [TestingEngine API](https://junit.org/junit5/docs/current/api/org/junit/platform/engine/TestEngine.html) for developing testing framework that runs on the platform.

#TDD Using Junit 5

As per JUnit documentation:
> JUnit 5 = JUnit PLatform + JUnit Jupiter + JUnit Vintage

**JUnit Juniper** is the combination of the new programming model and extension model for writing tests and extensions in JUnit 5

**JUnit Vintage** provides TestEngine for running JUnit3 and JUnit4 based tests.

##Support
JUnit 5 requires Java 8 or higher at runtime.

##Benefits

* Less time debugging
* Code proven to be correct and meet requirements
* Faster time to market
* Built-in set of regression tests, when adding new functionalities, or changing existing functionalities

##Annotations
Annotation | Summary
----------|----------
@TestFactory| denotes a method that is a test factory for dynamic tests
@DisplayName|  defines custom display name for a test class or a test method
@Nested | denotes that the annotated class is a nested, non-static test class
@Tag | declares tags for filtering tests
@ExtendWith | it is used to register extensions declaratively. Extensions registered declaratively via @ExtendWith will be executed in the order in which they are declared in the source code
@BeforeEach | denotes that the annotated method will be executed before each test method (previously @Before)
@AfterEach | denotes that the annotated method will be executed after each test method (previously @After)
@BeforeAll | denotes that the annotated method will be executed before all test methods in the current class (previously @BeforeClass)
@AfterAll | denotes that the annotated method will be executed after all test methods in the current class (previously @AfterClass)
@Disable | it is used to disable a test class or method (previously @Ignore)
@Timeout | Used to fail a test, test factory, test template, or lifecycle method if its execution exceeds a given duration. 

##Assertions
Assertion methods| Description | Junit 4 counterpart
---|---|---
assertThrows| Tests for a particular exception being thrown| @Test(expected=)
assertTimeout| timeout condition| @Test(timeout=1)
assertAll| group assertion| -NA-

## Test Cases with Spring Boot

###Support:
* @SpringBootTest: Combined with Spring Extension annotation, loads full application context in the test and manages the whole context as test runs.
* @MockMvc: For testing controllers. Tests using WebRequests and validates the response body.
* @MockBean: Mock Spring managed beans that will get autowired into context. And then configuring its behaviour.
###Third party extensions for managing backend resources
* DBUnit: pre-populate database using sql file, then cleans up database post the test.
* Custom extension for MongoDB: Prepopulate MongoDB from JSON file and then cleans up after the test.
* WireMock: Simulate responses from third party API.

## POM dependency

~~~

<!--Junit 5 begins.-->
<dependency>
			<groupId>org.junit.jupiter</groupId>
			<artifactId>junit-jupiter-engine</artifactId>
			<version>5.5.1</version>
		</dependency>
		<dependency>
			<groupId>org.junit.jupiter</groupId>
			<artifactId>junit-jupiter-api</artifactId>
			<version>5.5.1</version>
		</dependency>
		<dependency>
			<groupId>org.junit.jupiter</groupId>
			<artifactId>junit-jupiter-params</artifactId>
			<version>5.5.1</version>
		</dependency>
<!--JUnit 5 ends.-->

<!--Mockito extension begins.-->
<dependency>
			<groupId>org.mockito</groupId>
			<artifactId>mockito-junit-jupiter</artifactId>
			<version>3.0.0</version>
		</dependency>

<!--Mockito extension ends.-->


<!--Sure fire plugin: used by maven during test phase, to generate reports in two different file formats: XML and PlainText.
Reports are generated in: ${basedir}/target/surefire-reports/TEST-*.xml. -->

<plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>${maven-surefire-plugin.version}</version>
        <configuration>
          <includes>
            <include>**/*Test.java</include>
            <include>**/*Spec.java</include>
          </includes>
        </configuration>
      </plugin>


~~~


##Writing Test Class


