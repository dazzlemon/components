<%-- 
    Document   : index
    Created on : May 12, 2022, 4:21:19 PM
    Author     : dazzlemon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="insert" method="post">
            <table>
                <tr>
                    <th>id</th>
                    <td><input
                        type="int"
                        name="id"
                        placeholder="id"
                    ></td>
                </tr>
                <tr>
                    <th>name</th>
                    <td><input
                        type="text"
                        name="name"
                        placeholder="name"
                    ></td>
                </tr>
                <tr>
                    <th>quantity</th>
                    <td><input
                        type="text"
                        name="quantity"
                        placeholder="quantity"
                    ></td>
                </tr>
                <tr>
                    <th>priceInUsdCents</th>
                    <td><input
                        type="text"
                        name="priceInUsdCents"
                        placeholder="priceInUsdCents"
                    ></td>
                </tr>
            </table>
            <button type="submit">Submit</button>
        </form>
    </body>
</html>