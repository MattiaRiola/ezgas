click("1590932987096.png")
wait(1)
type(Key.PAGE_DOWN*3)
wait("1591007190504.png",1)
find("1591006840468.png")


region = exists("1591006855216.png",1)

if region:
    region.click("1591006879616.png")
    popup("Test Success!","test")
else:
    popup("Gas Station not found!","test")

