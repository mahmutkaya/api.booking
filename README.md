# api.booking

- API Automation Testing Using Java, cucumber and restAssured

- Environment and specs: [restful-booker](http://restful-booker.herokuapp.com/apidoc/index.html)

### dependencies:
- [cucumber-java v7.4.1](https://mvnrepository.com/artifact/io.cucumber/cucumber-java)
- [cucumber-junit v7.4.1](https://mvnrepository.com/artifact/io.cucumber/cucumber-junit)
- [rest-assured v5.1.1](https://mvnrepository.com/artifact/io.rest-assured/rest-assured)
- [hamcrest v2.2](https://mvnrepository.com/artifact/org.hamcrest/hamcrest)
- [assertj v3.23.1](https://mvnrepository.com/artifact/org.assertj/assertj-core)
- [gson v2.9.0](https://mvnrepository.com/artifact/com.google.code.gson/gson)
- [maven-failsafe-plugin v3.0.0-M7](https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-failsafe-plugin)

### Setting Up
These instructions will get you a copy of the project up and running on your local machine.

- *clone the repo:*
```shell
git clone https://github.com/mahmutkaya/api.booking.git
```
- *create ```configuration.properties``` file - at the project level*
- *and add the text below into it with replacing your own values from [restful-booker](http://restful-booker.herokuapp.com/apidoc/index.html)*
```properties
base_uri = <api_base_uri>
username = <your_username>
password = <your_password>
```
- *set project sdk as 17*

Running tests from terminal:
```shell
mvn -B verify --file pom.xml
```
Running tests in CI/CD pipeline:
- In this project I used github actions.

- If you want to use same pipeline setup just add CONFIGURATION variables defined inside the workflows/maven.yml
  to your github repository as secret variable.

### About scenarios:
Automated scenarios are:
- Create a booking WITH ALL REQUIRED FIELDS
- Create a booking WITHOUT NAME
- Create a booking WITHOUT LAST NAME
- Create a booking WITHOUT BOOKING DATES

- Get all booking IDs
- Get all booking IDs by filtering them

- Update a booking
- Partially Update a booking
- 
- Delete a booking
