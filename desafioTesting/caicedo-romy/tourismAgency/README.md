# Tourism Application

Bootcamp Quality Challenge. Applications allows to book, consult, and filter Hotels and Flights

## Starting π

Please download the code in order to run the application

You can find all methods here ππΌ: 

Check **/src/main/resources/DesafioTesting.postman_collection.json** to test the application, you will find all requests enumerated with the same numbers of the challenge statement.


### Annotationsπ

According to my interpretation of challenge statement methods to get Flights or Hotels using filters, are limited to four and three parameters in the order mentioned, but those methods could be improved, is an improvement opportunity.

Methods to make booking are soooo long because i have made the filters directlly in Service Class for both cases, and i did't have the chance to improve it π.

The method to get all hotel and all flights, show all even if its reserved or not. 

I didn't make the method to update the state of hotel if its reserved or not, because everytime i exceute a booking i was going to need to change it manually to run application again.

The Flight number was repeated but i take it as identifier so i've changed repository to make flightNumber unique.
### SetUp π§

_Just a note, for Unit Tests i used Mockito  and for the Integral test SpringBoot test, and the version of JUnit is 5, just to let you know π_.



## Execute test βοΈ

_You can create a new JUnit configuration to run all tests and also can use Jacoco to generate report_

Follow this

```
mvn clean test
open target/site/jacoco/index.html
```
Last command will open the report in a web browser.

But just in case i will leave you screenshot of the tests π, please check **/images/**.


## External Archives π¦

All .JSON needed are included in the repository.



### Just to let you know π

Hardest part of the challenge was understanding how to do the tests.





---
β¨οΈ con β€οΈ por [Romy](https://github.com/romycaicedo/repo-test-bootcamp) π