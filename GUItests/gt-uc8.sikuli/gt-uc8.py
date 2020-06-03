# USAGE: the start page must be the home

proximity = find(Pattern("1590827161831.png").targetOffset(72,0))
proximity.doubleClick() # Set the focus
proximity.type("Corso Orbassan") # trigger the autocomplete

wait(1.5) # wait for autocomplete to appear
# highlight the first option
keyDown(Key.DOWN)
wait(0.3)
keyUp(Key.DOWN)
wait(0.3)
# select the first option
keyDown(Key.ENTER)
wait(0.3)
keyUp(Key.ENTER)
gasolineType = find(Pattern("1590829108051.png").targetOffset(58,0)) # open the gasoline filter menu
gasolineType.click() #select a filter

wait(0.3) # wait the filters to appear
# highlight the first option
keyDown(Key.DOWN)
wait(0.3)
keyUp(Key.DOWN)
wait(0.3)
# select the first option
keyDown(Key.ENTER)
wait(0.3)
keyUp(Key.ENTER)

# search the gas stations matching the fiters
searchButton = find("1590830187262.png")
searchButton.click()

wheel(Button.WHEEL_UP, 20) # show the results