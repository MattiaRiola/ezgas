click("1590924059585.png")
wait(1)
find("1591004597390.png")
region = exists("1591006616475.png",1)

if region:
    region.click("1591006638263.png")
    popup("Test Success!","test")
else:
    popup("User not found!","test")

















