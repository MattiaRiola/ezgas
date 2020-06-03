# USAGE: this script assumes that the admin is already logged in and is in the home page
# Also, a dummy gas station to be modified is assumed to exist


# enter the admin page
doubleClick("1590764890940.png") # doubleClick is used since click() doesn't seems to work with this kind of buttons

wait(1) # wait that the page has loaded before scrolling
#scroll down to reach the edit buttons of the gas station
wheel(Button.WHEEL_UP, 40)
wait(1) # wait after a scroll to improve accuracy

# pick the right gas station to be edited
findAll("1590767326284.png")
editButtons = SCREEN.getLastMatches()
sortedEditButtons = sorted(editButtons, key=lambda m:m.y)
i = 0
editButton = 0

for edit in sortedEditButtons:
    if i == 0: #pick the first edit button
        editButton = edit
    i = i + 1    

doubleClick(editButton)

# modify the gas station
click(Pattern("1590768497931.png").targetOffset(-64,0))
click(Pattern("1590768535369.png").targetOffset(-28,0))
click(Pattern("1590768564641.png").targetOffset(-40,0))

#submit the modifications
doubleClick("1590768757214.png")