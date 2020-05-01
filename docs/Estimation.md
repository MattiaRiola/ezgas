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
| NC | 15 classes|                             |             
|  A | 100 LOC/class      |                            | 
| S | 1500 LOC | |
| E | 150 person hour  |                                      |   
| C | 4500€ | | 
| Estimated calendar time| 1 week |                    |               


# Estimate by activity decomposition



### 

|         Activity name    | Estimated effort (person hours)   |             
| ----------- | ------------------------------- | 
| Requirement | 33 |
| Design | 90 |
| Coding | 150 |
| Unit testing | 100 |
| Integration testing | 140 |
| System testing | 170 |


###
``` plantuml
@startuml
[Requirements] lasts 1 days

[Design] lasts 3 days
[Design] starts at [Requirements]'s end

[Coding] lasts 5 days
[Coding] starts at [Design]'s end

[Unit testing] lasts 3 days
[Unit testing] starts at [Coding]'s end

[Integration testing] lasts 4 days
[Integration testing] starts at [Unit testing]'s end

[System testing] lasts 6 days
[System testing] starts at [Integration testing]'s end

@enduml
```

