<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
</head>
<body>
    <h1>Registration</h1>
    <c:if test="${violations != null}">
        <c:forEach items="${violations}" var="violation">
            <p>${violation}.</p>
        </c:forEach>
    </c:if>
    <form action="${pageContext.request.contextPath}/processcustomer"
          method="post"
          >
        <table>
            <tr>
                <th>First name:</th>
                <td><input type="text"
                           name="firstname"
                           id="firstname"
                           value="${firstname}"
                           ></td>
            </tr>
            <tr>
                <th>Last name:</th>
                <td><input type="text"
                           name="lastname"
                           id="lastname"
                           value="${lastname}"
                           ></td>
            </tr>
            <tr>
                <th>Email:</th>
                <td><input type="text"
                           name="email"
                           id="email"
                           value="${email}"
                           ></td>
            </tr>
            <tr>
                <th></th>
                <td><input type="submit" value="Sign Up"/></td>
            </tr>
        </table>
    </form>
</body>
</html>