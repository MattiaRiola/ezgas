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
|other|-|Invalid|passing not int like uniqueidentifier parameter not well formed input| T1('0E984725-C51C-4BF4-9960-E1C80E27ABA0wrong')-> Error|
|srring|-|Invalid|passing not int like string as input parameter not well formed input	| T2("A")-> Error|
||-|Invalid|passing not int like float as input parameter not well formed input| T3(2.1)-> Error|
|int|minint|valid|passing minint that is not valid as input parameter |T4(minint)-> minint|
||minint+1|valid|passing minint+1 that is not valid as input parameter|T5(minint+1) -> minint+1|
||-1|valid|passing -1 that is not valid as input parameter|T6(-1)-> -1|
||0|valid|passing 0 as input parameter|T7(0)-> 0|
||10|valid|passing 10 as input parameter|T8(10)-> 10|
||maxint|valid|passing maxint as input parameter|T9(maxint)-> maxint|
||maxint-1|valid|passing maxint-1 as input parameter|T10(maxint-1)-> maxint-1|






 ### **Class *User* - method *getUserId()***


**Combination of predicates**:

| Type of parameter | Value of parameter | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|
|other|-|Invalid|it is noy possibe to set uniqueidentifier instead of int it is not well formed parameter| T1 {setUserId(‘0E984725-C51C-4BF4-9960-E1C80E27ABA0wrong’);-> Error}|
|string|-|Invalid|it is noy possibe to set string instead of int it is not well formed parameter| T2 {setUserId("A");-> Error}|
||-|Invalid|it is noy possibe to set float instead of int it is not well formed parameter|T3 {setUserId(2.1);-> Error}|
|int|minint|valid|set minint value as input parameter|T4 {setUserId(minint);getUserId()-> minint}|
||minint+1|valid|set minint+1 value as input parameter|T5 {setUserId(minint+1);getUserId()-> minint+1}|
||-1|valid|set -1  as input parameter|T6 {setUserId(-1);getUserId(); ->-1}|
||0|valid|set 0  as input parameter|T7 {setUserId(0);getUserId() -> 0}|
||10|valid|set 10  as input parameter|T8 {setUserId(10);getUserId() -> 10}|
||maxint|valid|set maxint value as input parameter|T9 {setUserId(maxint);getUserId() -> maxint}|
||maxint-1|valid|set maxint-1 value as input parameter|T10 {setUserId(maxint-1);getUserId() -> maxint-1}|





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


