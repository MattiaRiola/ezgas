# USAGE: run the script as anonymous user from the main page

# go to the login page
doubleClick("1590749036953.png") # use doubleClick as click seems to have some bugs with kind of buttons

#insert login data
email = wait(Pattern("1590749567038.png").similar(0.91), 2) # wait two seconds for the image to appear 
email.click()
email.paste("leo@leo.it") # use paste to insert '@' character

click(Pattern("1590749868617.png").similar(0.88)) # remove any possible suggestion that covers the password 
password = wait(Pattern("1590749785111.png").similar(0.81), 1)
password.click()
password.type("leo")

# perform the login

click("1590749993843.png")

# go the the update profile page
updateProfile = wait("1590750181111.png", 2)
updateProfile.doubleClick()

# perform the update

name = wait("1590750660846.png", 2)
name.doubleClick() # select the text to be changed
name.type("Leonardo")
click(Pattern("1590749868617.png").similar(0.88))
email = wait("1590751191207.png", 2)
email.click() #select the box
email.doubleClick() #select all the text
email.paste("leonardo@leonardo.com")

click(Pattern("1590749868617.png").similar(0.88))

password = wait("1590764249948.png", 2)
password.click()
password.type("Leonardo")

# look for the submit button and submit the data
submit = find("1590764299492.png")
submit.click()
