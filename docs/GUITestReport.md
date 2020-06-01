# GUI  Testing Documentation 

Authors: Group 21

Date: 30/05/2020

Version: 1

# GUI testing

This part of the document reports about testing at the GUI level. Tests are end to end, so they should cover the Use Cases, and corresponding scenarios.

## Coverage of Scenarios and FR

```
<Complete this table (from IntegrationApiTestReport.md) with the column on the right. In the GUI Test column, report the name of the .py  file with the test case you created.>
```

### 

| Scenario ID | Functional Requirements covered | GUI Test(s) | 
| ---------- | ------------------------------- | ----------- | 
|UC1 - Create User Account|FR1.1| GUItests.gt-uc10.1.sikuli.gt-uc1|             
|UC2 - Modify user account| FR1.1 | GUItests.gt-uc10.1.sikuli.gt-uc2 |             
|UC3 - Delete user account| FR1.2 | GUItests.gt-uc10.1.sikuli.gt-uc3 |            
|UC4 - Create Gas Station| FR3.1 | GUItests.gt-uc10.1.sikuli.gt-uc4|      
|UC5 - Modify Gas Station information| FR3.1 |GUItests.gt-uc10.1.sikuli.gt-uc5 |      
|UC6 - Delete Gas Station| FR3.2 | GUItests.gt-uc10.1.sikuli.gt-uc6 |     
|UC7 - Report fuel price for a gas station| FR5.1, FR5.2 | GUItests.gt-uc10.1.sikuli.gt-uc7|  
|UC8 - Obtain price of fuel for gas stations in a certain geographic area| FR4.2, FR4.5 | GUItests.gt-uc10.1.sikuli.gt-uc8| 
|UC9 - Update trust level of price list|FR5 | - | 
|UC10 - Evaluate price| FR5.3 |GUItests.gt-uc10.1.sikuli.gt-uc10.1                           GUItests.gt-uc10.1.sikuli.gt-uc10.2|
           


# REST  API  Testing

This part of the document reports about testing the REST APIs of the back end. The REST APIs are implemented by classes in the Controller package of the back end. 
Tests should cover each function of classes in the Controller package

## Coverage of Controller methods


<Report in this table the test cases defined to cover all methods in Controller classes >

| class.method name | Functional Requirements covered |REST  API Test(s) | 
| ----------- | ------------------------------- | ----------- | 
|UserControllerTest.testSaveUser|FR1.1| src.test.java.it.polito.ezgas.controllertests.UserControllerTest|     
|UserControllerTest.testDeleteUser|FR1.2| src.test.java.it.polito.ezgas.controllertests.UserControllerTest|            
|UserControllerTest.testIncreaseUserReputation|FR5.3| src.test.java.it.polito.ezgas.controllertests.UserControllerTest|              
|UserControllerTest.testDecreaseUserReputation|FR5.3| src.test.java.it.polito.ezgas.controllertests.UserControllerTest|            
|GasStationControllerTest.TestSaveGasStation|FR3.1| src.test.java.it.polito.ezgas.controllertests.GasStationControllerTest|            
|GasStationControllerTest.TestDelateGasStation|FR3.2| src.test.java.it.polito.ezgas.controllertests.GasStationControllerTest| 
|GasStationControllerTest.TTestSetReport|FR4.3| src.test.java.it.polito.ezgas.controllertests.GasStationControllerTest| 
 