# USAGE: this script assumes that the user is already logged in and that there's a dummy gas station in the db to be modified

# scroll down to see the gas station list
wheel(Button.WHEEL_UP, 20) # scroll to see the gas stations list
wait(1) # wait a second to improve image recognition accuracy

findAll("1590773667840.png") # find all the "New Report buttons"
newReports = SCREEN.getLastMatches()
sortedNewReports = sorted(newReports, key=lambda m:m.y)
i = 0
newReport = 0

# select the right gas station to which the report have to be filled
for nr in sortedNewReports:
    if i == 0: # pick the first New Report button
        newReport = nr
    i = i + 1
    
doubleClick(newReport) # a double click must be performed since it seems that with some buttons just a click doesn't work
wheel(Button.WHEEL_UP, 20) # scroll to see "Add price report form"

# fill and submit the price report
dieselPrice = wait(Pattern("1590773008414.png").similar(0.80).targetOffset(50,0), 1)
dieselPrice.click()
dieselPrice.type("1.2")
emptySpace = find("1590773532918.png") # select a random empty space where to click to remove prices suggestions and let sikuli recognize the forms
emptySpace.click()

gasolinePrice = wait(Pattern("1590773184814.png").similar(0.90).targetOffset(56,0), 1)
gasolinePrice.click()
gasolinePrice.type("1.5")
emptySpace.click()
premiumGasolinePrice = wait(Pattern("1590773253677.png").targetOffset(71,0), 1)
premiumGasolinePrice.click()
premiumGasolinePrice.type("1.7")

doubleClick("1590773316023.png")