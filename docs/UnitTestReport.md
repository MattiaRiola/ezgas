# Unit Testing Documentation

Authors: Group 21

Date: 16/05/2020

Version: 1

# Contents

- [Black Box Unit Tests](#black-box-unit-tests)




- [White Box Unit Tests](#white-box-unit-tests)


# Black Box Unit Tests

    <Define here criteria, predicates and the combination of predicates for each function of each class.
    Define test cases to cover all equivalence classes and boundary conditions.
    In the table, report the description of the black box test case and (traceability) the correspondence with the JUnit test case writing the 
    class and method name that contains the test case>
    <JUnit test classes must be in src/test/java/it/polito/ezgas   You find here, and you can use,  class EZGasApplicationTests.java that is executed before 
    the set up of all Spring components
    >

 # Unit Testing Documentation

Authors: Group 21

Date: 16/05/2020

Version: 1

# Contents

- [Black Box Unit Tests](#black-box-unit-tests)
- [White Box Unit Tests](#white-box-unit-tests)


# Black Box Unit Tests

    <Define here criteria, predicates and the combination of predicates for each function of each class.
    Define test cases to cover all equivalence classes and boundary conditions.
    In the table, report the description of the black box test case and (traceability) the correspondence with the JUnit test case writing the 
    class and method name that contains the test case>
    <JUnit test classes must be in src/test/java/it/polito/ezgas   You find here, and you can use,  class EZGasApplicationTests.java that is executed before 
    the set up of all Spring components
    >

 ### **Class *User* - method *setUserId()***



**Criteria for method *setUserId()*:**

 - Type of parameter
 - Value of parameter


**Predicates for method *setUserId()*:**

| Criteria | Predicate |
| -------- | --------- |
|Type of parameter        |int  |
|                         |string|
|                         |other|
|Value of parameter       |[minint,-1]|
|                         |[0,maxint]|





**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| Value of parameter | minint, minint+1, -1, 0, maxint-1, maxint |



**Combination of predicates**:


| Type of parameter | Value of parameter | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|
|string|-|Invalid|T1('0E984725-C51C-4BF4-9960-E1C80E27ABA0wrong')-> Error|it.polito.ezgas.entity.User.tc1|
|srring|-|Invalid|T2("A")-> Error	| it.polito.ezgas.entity.User.tc2|
||-|Invalid|T3(2.1)-> Error| it.polito.ezgas.entity.User.tc3|
|int|minint|valid|T4(minint)-> minint |it.polito.ezgas.entity.User.tc4|
||minint+1|valid|T5(minint+1) -> minint+1|it.polito.ezgas.entity.User.tc5|
||-1|valid|T6(-1)-> -1|it.polito.ezgas.entity.User.tc6|
||0|valid|T7(0)-> 0|it.polito.ezgas.entity.User.tc7|
||10|valid|T8(10)-> 10|it.polito.ezgas.entity.User.tc8|
||maxint|valid|T9(maxint)-> maxint|it.polito.ezgas.entity.User.tc9|
||maxint-1|valid|T10(maxint-1)-> maxint-1|it.polito.ezgas.entity.User.tc10|






 ### **Class *User* - method *getUserId()***


**Combination of predicates**:

| Type of parameter | Value of parameter | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|
|string|-|Invalid|T1 {setUserId(‘0E984725-C51C-4BF4-9960-E1C80E27ABA0wrong’);-> Error}| it.polito.ezgas.entity.User.tc1|
|string|-|Invalid|T2 {setUserId("A");-> Error}| it.polito.ezgas.entity.User.tc2|
||-|Invalid|T3 {setUserId(2.1);-> Error}|it.polito.ezgas.entity.User.tc3|
|int|minint|valid|T4 {setUserId(minint);getUserId()-> minint}|it.polito.ezgas.entity.User.tc4|
||minint+1|valid|T5 {setUserId(minint+1);getUserId()-> minint+1}|it.polito.ezgas.entity.User.tc5|
||-1|valid|T6 {setUserId(-1);getUserId(); ->-1}|it.polito.ezgas.entity.User.tc6|
||0|valid|T7 {setUserId(0);getUserId() -> 0}|	it.polito.ezgas.entity.User.tc7|
||10|valid|T8 {setUserId(10);getUserId() -> 10}|it.polito.ezgas.entity.User.tc8|
||maxint|valid|T9 {setUserId(maxint);getUserId() -> maxint}|it.polito.ezgas.entity.User.tc9|
||maxint-1|valid|T10 {setUserId(maxint-1);getUserId() -> maxint-1}|it.polito.ezgas.entity.User.tc10|


### **Class *GasStation* - method *getGasStationId()***


**Combination of predicates**:

| Type of parameter | Value of parameter | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|
|string|-|Invalid|T1 {setGasStationId(‘0E984725-C51C-4BF4-9960-E1C80E27ABA0wrong’);-> Error}| it.polito.ezgas.entity.GasStation.tc1|
|string|-|Invalid|T2 {setGasStationId("G");-> Error}|it.polito.ezgas.entity.GasStation.tc2 |
||-|Invalid|T3 {setGasStationId(9.1);-> Error}|it.polito.ezgas.entity.GasStation.tc3|
|int|minint|valid|T4 {setGasStationId(minint);getGasStationId()-> minint}|it.polito.ezgas.entity.GasStation.tc4|
||minint+1|valid|T5 {setGasStationId(minint+1);getGasStationId()-> minint+1}|it.polito.ezgas.entity.GasStation.tc5|
||-1|valid|T6 {setGasStationId(-1);getGasStationId(); ->-1}|it.polito.ezgas.entity.GasStation.tc6|
||0|valid|T7 {setGasStationId(0);getGasStationId() -> 0}|it.polito.ezgas.entity.GasStation.tc7|
||10|valid|T8 {setGasStationId(10);getGasStationId() -> 10}|it.polito.ezgas.entity.GasStation.tc8|
||maxint|valid|T9 {setGasStationId(maxint);getGasStationId() -> maxint}|it.polito.ezgas.entity.GasStation.tc9|
||maxint-1|valid|T10 {setGasStationId(maxint-1);getGasStationId() -> maxint-1}|it.polito.ezgas.entity.GasStation.tc10|


### **Class *Haversine* - method *testDistanceTest()***

note that the computations are based on :
[sismogrammi.com/calcolo-distanze](https://www.sismogrammi.com/calcolo-distanze.php)


**Combination of predicates**:

| Type of parameter | Value of parameter | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|
|string|-|Invalid|T1 {testDistanceTest(‘0E984725-C51C-4BF4-9960-E1C80E27ABA0wrong’,‘631c400c-b535-48ab-97e6-4e57e7001eff’,‘3794a41e-30af-4858-96d5-637852c57ec8’,‘30b3b77c-8b16-4379-ba82-857ddae9f347’);-> Error}| it.polito.ezgas.service.impl.Haversine.tc1|
|string|-|Invalid|T2 {testDistanceTest("lat1", "lon1", "lat2", "lon2");-> Error}| it.polito.ezgas.service.impl.Haversine.tc2|
|double|-|valid|T3 {testDistanceTest(0,0,90,180);testDistanceTest() -> 10007.543 *1000 }| it.polito.ezgas.service.impl.Haversine.tc3|
|double|-|valid|T4 {testDistanceTest(-25-30,-26,35);testDistanceTest() -> 513.947 *1000  }| it.polito.ezgas.service.impl.Haversine.tc4|
|double|-|valid|T5 {testDistanceTest(-25.45,-30,-26.03,-35);testDistanceTest() -> 504.910 *1000  }| it.polito.ezgas.service.impl.Haversine.tc5|
|double|-|valid|T6 {testDistanceTest(380,-390,380.02,-391);testDistanceTest() -> 104.506*1000  }|it.polito.ezgas.service.impl.Haversine.tc6 |
|double|-|Invalid|T7 {testDistanceTest(360,360,360,360);testDistanceTest() -> -1  }| it.polito.ezgas.service.impl.Haversine.tc7|
|double|-|Invalid|T8 {testDistanceTest(-360,-360,-360,-360);testDistanceTest() -> -1  }| it.polito.ezgas.service.impl.Haversine.tc8|
# White Box Unit Tests

### Test cases definition
    
    <JUnit test classes must be in src/test/java/it/polito/ezgas>
    <Report here all the created JUnit test cases, and the units/classes under test >
    <For traceability write the class and method name that contains the test case>


| Unit name | JUnit test case |
|--|--|
|||
|||
||||

### Code coverage report

    <Add here the screenshot report of the statement and branch coverage obtained using
    the Eclemma tool. >


### Loop coverage analysis

    <Identify significant loops in the units and reports the test cases
    developed to cover zero, one or multiple iterations >

|Unit name | Loop rows | Number of iterations | JUnit test case |
|---|---|---|---|
|||||
|||||
||||||







**Combination of predicates**:


| Criteria 1 | Criteria 2 | ... | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|
|||||||
|||||||
|||||||
|||||||
|||||||




# White Box Unit Tests

### Test cases definition
    
    <JUnit test classes must be in src/test/java/it/polito/ezgas>
    <Report here all the created JUnit test cases, and the units/classes under test >
    <For traceability write the class and method name that contains the test case>


| Unit name | JUnit test case |
|--|--|
|||
|||
||||

### Code coverage report

    <Add here the screenshot report of the statement and branch coverage obtained using
    the Eclemma tool. >


### Loop coverage analysis

    <Identify significant loops in the units and reports the test cases
    developed to cover zero, one or multiple iterations >

|Unit name | Loop rows | Number of iterations | JUnit test case |
|---|---|---|---|
|||||
|||||
||||||


