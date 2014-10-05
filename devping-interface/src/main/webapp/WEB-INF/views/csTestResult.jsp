<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
    <title>Client Server Command Test Page</title>
</head>
<body>
<h1>Client Server Command Test Result Page</h1>

<c:if test="${case eq 'findPeopleWithTag'}">
    <h3>Find People With the TagList</h3>
    <ul>Tag "${tagList}" 을 가지고 있는 사람은 총 ${fn:length(selectedPeople)} 명 입니다.</ul>
    <c:forEach var="selectedPerson" items="${selectedPeople}">
        <li>${selectedPerson}</li>
    </c:forEach>
</c:if>

</body>
</html>
