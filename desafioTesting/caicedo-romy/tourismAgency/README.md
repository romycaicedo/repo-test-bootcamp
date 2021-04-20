# Tourism Application

Bootcamp Quality Challenge. Applications allows to book, consult, and filter Hotels and Flights

## Starting 🚀

Please download the code in order to run the application

Check **/src/main/resources/DesafioTesting.postman_collection.json** to test the appilication, you will found all requests enumerated with the same numbers of the challenge statement.


### Annotations📋

According to my interpretation of challenge statement methods to get Flights or Hotels using filters, are limited to four and three parameters in the order mentioned, but those methods could be improved, is an improvement opportunity.

Methods to make booking are soooo long because i have made the filters directlly in Service Class for both cases, and i did't have the chance to improve it 😔.
### SetUp 🔧

_Just a note, for Unit Tests i used Mockito  and for the Integral test SpringBoot test, and the version of JUnit is 5, just to let you know 😁_.



## Execute test ⚙️

_You can create a new JUnit configuration to run all tests and also can use Jacoco to generate report_

Follow this

```
mvn clean test
open target/site/jacoco/index.html
```
Last command will open the report in a web browser.

But just in case i will leave you screenshot of the tests 😉
![Test InteliJJ](/tourismAgency/images/IntelijjTest.png)

![Test Jacoco](/tourismAgency/images/JacocoTest.png)



## External Archives 📦

All .JSON needed are included in the repository.



### Just to let you know 📌

Hardest part of the challenge was understanding how to do the test (I couldn't have done it without the guys on the team).





---
⌨️ con ❤️ por [Romy](https://github.com/romycaicedo/repo-test-bootcamp) 😊