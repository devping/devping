<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.google.android.gcm.server.*" %>
<%!
    public String getMessageID()
    {
        String str = String.valueOf(Math.random() % 100 + 1); //메시지 고유 ID
        return str;
    }
%>
<%
    request.setCharacterEncoding("UTF-8");
    String MESSAGE_ID = getMessageID(); //메시지 고유 ID
    String regi_title = request.getParameter("regi_title"); // 제목
    String regi_msg = request.getParameter("regi_msg"); // 메시지
    String regid   = request.getParameter("user_id");  // 대상 아이디
    String user_key = request.getParameter("user_key");
    String simpleApiKey = user_key; // 관리자쪽 API 키
    String gcmURL = "https://android.googleapis.com/gcm/send";  //푸쉬를 요청할 구글 주소

    boolean SHOW_ON_IDLE = true; //기기가 활성화 상태일때 보여줄것인지
    int LIVE_TIME =3; //기기가 비활성화 상태일때 GCM가 메시지를 유효화하는 시간
    int RETRY = 5; //메시지 전송실패시 재시도 횟수
    try{
        out.println("id :[ " + regid + "]<br>");
        Sender sender = new Sender(simpleApiKey);
        Message message = new Message.Builder()
                .collapseKey(getMessageID())
                .delayWhileIdle(SHOW_ON_IDLE)
                .timeToLive(LIVE_TIME)
                .addData("msg",regi_msg)
                .addData("title",regi_title)
                .build();
        out.println("msg :[" + message.toString() + "]<br>");
        sender.send(message, regid, RETRY);
        out.println("sending ok2");
    }catch (Exception e) {
        out.println("error" + e.getMessage());
    }finally{
        out.println("finish");
    }
%>
