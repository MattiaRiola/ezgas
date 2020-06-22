# Project Estimation  

Authors: Group 21

Date: 30/04/2020

Version: 1.0

# Contents



- [Estimate by product decomposition](#Estimate-by-product-decomposition)
- [Estimate by activity decomposition](#Estimate-by-activity-decomposition)


# Estimate by product decomposition



### 

| Parameter            | Estimate                        |             
| ----------- | ------------------------------- |  
| NC | 12 classes|                             |             
|  A | 100 LOC/class      |                            | 
| S | 1200 LOC | |
| E | 350 person hour  |                                      |   
| C | 10500€ | | 
| Estimated calendar time| 3 week |                    |               


# Estimate by activity decomposition



### 

|         Activity name    | Estimated effort (person hours)   |             
| ----------- | ------------------------------- | 
| Requirement | 25 |
| Design | 40 |
| Coding | 100 |
| Unit testing | 15 |
| Integration testing | 80 |
| System testing | 90 |


###
``` plantuml
@startuml
[Requirements] lasts 1 days

[Design] lasts 2 days
[Design] starts at [Requirements]'s end

[Coding] lasts 4 days
[Coding] starts at [Design]'s end

[Unit testing] lasts 1 days
[Unit testing] starts at [Coding]'s end

[Integration testing] lasts 3 days
[Integration testing] starts at [Unit testing]'s end

[System testing] lasts 3 days
[System testing] starts at [Integration testing]'s end

@enduml
```

