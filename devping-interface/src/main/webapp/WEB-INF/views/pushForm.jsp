<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
    <TITLE> New Document </TITLE>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</HEAD>

<BODY>
</br>
</br>
<h1>안드로이드 GCM 푸시 테스트</h1>
<h3>메세지 : 보내고 싶은 내용</h3>
<h3>GCM API 발급키 : 구글 프로젝트 생성 후 발급받은 키</h3>
<h3>메세지 : 보내고 싶은 내용</h3>
</br>
<form action="/pushToMobile" method="post">
    메세지</br><input type="text" name="message"/></br>
    GCM API 발급키</br><input type="text" name="apiKey"/></br>
    안드로이드폰 GCM 등록아이디</br><input type="text" name="deviceId" value=""/></br>
    </br>
    <input type="submit" value="send"/>
</form>
</BODY>
</HTML>
