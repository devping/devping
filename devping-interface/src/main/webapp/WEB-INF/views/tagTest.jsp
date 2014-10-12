<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: jhouse
  Date: 9/27/14
  Time: 9:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tag Test Page</title>
</head>

<body>
<h1>Tag Test Page</h1>

<h3>Login</h3>
<form method="post" action="./loginUpdateTag">
    <table>
        <tr>
            <td>ID</td>
            <td><input type="text" name="id" value="ljhiyh"/></td>
        </tr>
        <tr>
            <td>Tag</td>
            <td><input type="text" name="tagList" value="jboss java weblogic websphere 웹로직 웹스피어"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="login"/>
            </td>
        </tr>
    </table>
</form>

<h3>Logout</h3>
<form method="post" action="./logoutUpdateTag">
    <table>
        <tr>
            <td>ID</td>
            <td><input type="text" name="id"/></td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" value="logout"/>
            </td>
        </tr>
    </table>
</form>

<h3>Search Tag with Prefix</h3>
<form method="post" action="./searchTagWithPrefix">
    <table>
        <tr>
            <td>Prefix</td>
            <td><input type="text" name="prefix"/></td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" value="Search"/>
            </td>
        </tr>
    </table>
</form>

<h3>Find People With the TagList</h3>
<form method="post" action="./findPeopleWithTagList">
    <table>
        <tr>
            <td>Tag</td>
            <td><input type="text" name="tagList"/></td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" value="Find"/>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
