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
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>

<script>

    function ajaxTest() {
        jQuery.ajax({
            type:"POST",
            url:"../cs/pingRequest",
//            url:"../v1/projectName",
            data : pingData,
//            dataType:"JSON", // 옵션이므로 JSON으로 받을게 아니면 안써도 됨
            success : function(data) {
                // 통신이 성공적으로 이루어졌을 때 이 함수를 타게 된다.
                // TODO
                alert("success")
            },
            complete : function(data) {
                // 통신이 실패했어도 완료가 되었을 때 이 함수를 타게 된다.
                // TODO
                alert("Complete")
            },
            error : function(xhr, status, error) {
                alert("에러발생");
            }
        });
    }
//    function createJSON() {
//        jsonObj = [];
//
//        userIdsWithTag=[];
//
//        user1 = {};
//        user1["userId"] = "jooho";
//        user1["nickName"] = "Client1";
//
//        user2 = {};
//        user2["userId"] = "redhat";
//        user2["nickName"] = "Client2";
//
//        userIdsWithTag.push(user1);
//        userIdsWithTag.push(user2);
//
//        jsonObj.push(userIdsWithTag);
//
//        jsonObj.put("userId",$("#PingRequestId").val());
//        jsonObj.put("nickName","트레이닝맨");
//        jsonObj.put("question" , "MQTT 구현하자");
//        jsonObj.put("channelId","ljhiyh/chat/12345");
//        jsonObj.put( "time" , "2014-10-04 13:55:29 +0100");
//
//        jsonString = JSON.stringify(jsonObj);
//
//
//        console.log(jsonString);
//    }

    var pingData= {
                "userIdsWithTag" : [
                    { "userId" : "jooho" , "nickName" : "Client1"},
                    { "userId" : "redhat" , "nickName" : "Client2" }
                ],
                "userId" : "ljhiyh",
                "nickName" : "트레이닝맨",
                "question" : "MQTT 구현하자",
                "channelId" : "ljhiyh/chat/12345",
                "time" : "2014-10-04 13:55:29 +0100"
            };

</script>
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

<h3>Ping 요청</h3>
PingRequester : ljhiyh<br/>
Ping을 받을 사람 : jooho, redhat <br/>

    <table>
        <tr>
            <td colspan="2">
                <input type="button" value="Ping" onclick="javascript:ajaxTest();"/>
            </td>
        </tr>
    </table>
</body>
</html>
