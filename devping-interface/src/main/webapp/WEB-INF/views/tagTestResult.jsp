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
    <title>Tag Test Page</title>
</head>
<body>
<h1>Tag Test Result Page</h1>

<c:if test="${case eq 'loginUpdateTag' || case eq 'logoutUpdateTag'}">
    <h3>Update Tag Test</h3>
    <ul>J로 시작하는 Tag</ul>
    <c:forEach var="tagWithJ" items="${tagListWithJ}">
        <li>${tagWithJ}</li>
    </c:forEach>

    <ul>W로 시작하는 Tag</ul>
    <c:forEach var="tagWithW" items="${tagListWithW}">
        <li>${tagWithW}</li>
    </c:forEach>


    <ul>웹로 시작하는 Tag</ul>
    <c:forEach var="tagWithKorean" items="${tagListWithKorean}">
        <li>${tagWithKorean}</li>
    </c:forEach>


    "java" tag를 가진 로그인된 사람
    <c:forEach var="person" items="${peopleWithTag}">
        <li>${person}</li>
    </c:forEach>

</c:if>
<c:if test="${case eq 'searchTagWithPrefix'}">
    <h3>Search Tag with Prefix</h3>
    <ul>${prefix} 로 시작하는 Tag</ul>
    <c:forEach var="tagWithPrefix" items="${tagListWithPrefix}">
        <li>${tagWithPrefix}</li>
    </c:forEach>
</c:if>

<c:if test="${case eq 'findPeopleWithTagList'}">
    <h3>Find People With the Tag</h3>
    <ul>Tag "${tagList}" 을 가지고 있는 사람은 총 ${fn:length(peopleWithTagList)} 명 입니다.</ul>
    <c:forEach var="personWithTag" items="${peopleWithTagList}">
        <li>${personWithTag}</li>
    </c:forEach>
</c:if>
<c:if test="${case eq 'findPeopleWithTag'}">
    <h3>Find People With the TagList</h3>
    <ul>Tag "${tagList}" 을 가지고 있는 사람은 총 ${fn:length(selectedPeople)} 명 입니다.</ul>
    <c:forEach var="selectedPerson" items="${selectedPeople}">
        <li>${selectedPerson}</li>
    </c:forEach>
</c:if>

</body>
</html>
