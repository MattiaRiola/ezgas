# Project Dashboard

The dashboard collects the key measures about the project.
To be used to control the project, and compare with others. These measures will not be used to graduate the project. <br>
We consider two phases: <br>
-New development: From project start (april 13) to delivery of version 1 fixed (june 9)  <br>
-Maintenance: implementation of CR4 and CR7 (june 9 to end)   <br>
Report effort figures from the timesheet or timesheetCR document, compute size from the source code.

note: Size is calculated by cloc (https://github.com/AlDanial/cloc)

## New development 
| Measure| Value |
|---|---|
|effort E (report here effort in person hours, for New development, from timesheet)  | 257 personhours|
|size S (report here size in LOC of all code written, excluding test cases)  |1610|
|productivity = S/E | 1610/257 = 6.26 |
|defects after release D (number of defects found running official acceptance tests and fixed in CR0) | 30  |
|defect density = D/S| 30/1610 = 0.018 |
| effort for non-quality ENQ (effort for CR0, or effort to fix defects found running official acceptance tests, from timesheetCR) | 19 |
| effort for non quality, relative = ENQ / E | 19/257 = 0.074 |

## Maintenance

| Measure | Value|
|---|---|
| size S_CR4 = only lines added for CR4 = total size with CR4 - S | 22 |
| actual effort (from timesheetCR) AE_CR4 |  3  |
| productivity P_CR4 = S_CR4/ EA_CR4 | 22/3 = 7.3 |
| estimated effort (from estimationCR) EE_CR4 | 6 |
|estimation accuracy CR4 = EE_CR4/AE_CR4  | 6/3 = 2 |
|||
| size S_CR7 =only lines added for CR7 = total size with CR7 - S |  35  |
| actual effort (from timesheetCR) AE_CR7 | 4 |
| productivity P_CR7 = S_CR7/ AE_CR7 | 35/4 = 8.75 |
| estimated effort (from estimationCR) EE_CR7 | 4 |
|estimation accuracy CR7 = EE_CR7/AE_CR7  | 4/4 = 1 |