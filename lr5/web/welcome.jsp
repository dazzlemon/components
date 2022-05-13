<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>User page</title>
    </head>
    <body>
        <h1>Welcome!</h1>
        <h2>Your account info:</h2>
        <p><strong>First name: </strong> ${firstname}</p>
        <p><strong>Last name:  </strong> ${lastname}</p>
        <p><strong>Email:      </strong> ${email}</p>
    </body>
</html>