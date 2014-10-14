<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
    <TITLE> New Document </TITLE>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</HEAD>

<BODY>
제목, 메시지, 등록아이디, 키 순으로 입력</br>
<form action="/gcmSend" method="post">
    제목</br><input type="text" name="regi_title"/></br>
    메시지</br><input type="text" name="regi_msg"/></br>
    발급키</br><input type="text" name="user_key"/></br>
    등록아이디</br><input type="text" name="user_id" value=""/></br>
    <input type="submit" value="send"/>
</form>
</BODY>
</HTML>
