# Cyber Security Project - Helsinki University CompSci

## Site Functionality
- Register
- Login
- Edit user details
- See all messages
- Post a message

## Flaws
### A1: Injection
#### Problem
SQL injection is possible in the login screen.
#### Example
``` example code ```
#### Solution
Sanitise the input instead of running it directly in the database.

### A2: Broken Authentication
#### Problem
Allows password reset with knowledge-based answers.
#### Example
#### Solution

### A3: Sensitive Data Exposure
#### Problem
#### Example
#### Solution
Passwords are stored in plain text.

### A5: Broken Access Control
#### Problem
#### Example
#### Solution
Unauthorized content can be accessed with direct GET requests.

### A7: Cross-Site Scripting (XSS)
#### Problem
#### Example
#### Solution
Malicious JS script can be inserted into the page.

### A9: Using Components with Known Vulnerabilities
#### Problem
#### Example
#### Solution
Old Java Spring has various vulnerabilities.


