# Cyber Security Project - Helsinki University CompSci

Find the [final report here](report.txt), though this Readme contains mostly the same information, though in brief.

Uses the course template. Clone, then open and run in NetBeans, and [access locally](http://localhost:8080).

## Site Functionality
- See all messages
- Register
- Login
- Change password
- Post a message

## Flaws
### A1: Injection
#### Problem
SQL injection is possible in the register screen.
#### Example
**If you test this, you'll need to restart the app as there's not much the app can do with a broken db.**

Running the below injection in the Month field will cause the database to drop the entire Post table. Any other SQL command could also be ran, e.g. changing all the passwords.

```aa'); DROP TABLE Post; UPDATE User SET month = 'lol' WHERE ('kek' = 'bur```
#### Solution
Sanitise the input instead of running it directly in to the database.

### A2: Broken Authentication
#### Problem
Allows password reset with knowledge-based answers.
#### Example
Choose one of the usernames from the message lists, then follow the Forget password? link and start testing months until you find a match. *All our default users happened to be born in January.*

In real life, finding out the birth month is often trivial through e.g. Google search.
#### Solution
Remove any knowledge-based reset, and require e.g. email confirmation for resetting the password.

### A3: Sensitive Data Exposure
#### Problem
Passwords are stored in plain text.
#### Example
Any unauthorised access to the database would mean all the usernames and passwords are instantly available to the attacker - these may also be in use on other sites.
#### Solution
Use encryption! Java Spring uses BCrypt be default, don't mess with it like on this site, this [UltimateEncoder](src/main/java/sec/project/config/UltimateEncoder.java) simply isn't up to its name.

### A5: Broken Access Control
#### Problem
Unauthorised content, such as each user's personal page (where you can also change their password!) can be accessed with direct GET requests.
#### Example
[Modify admin's password](http://localhost:8080/edit/admin)
#### Solution
Add authorisation to each request as needed.

### A7: Cross-Site Scripting (XSS)
#### Problem
Malicious JS script can be inserted into the page via making a post.
#### Example
Typing the below into [a post](http://localhost:8080/post) will cause the script to always be run on every browser accessing the default page. This time the script is harmless enough, but could e.g. be used to send cookie info to the attacker.

```<script> alert("owned"); </script> lol```
#### Solution
Sanitise the input to be shown on the site.
