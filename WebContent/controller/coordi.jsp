<%@page import="answer.AnswerProfile"%>
<%@page import="test.DateTest"%>
<%@page import="answer.AnswerSchedule"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/plain; charset=UTF-8" pageEncoding="UTF-8"%>
<%

// 현재 왓슨 계정이 없어져서 임시로 주석처리함. 
	/* WatsonConnection wc = new WatsonConnection();
	String returnedMessage= wc.service(request, response, msg); 
	try {
		intentParser.tokenParser(returnedMessage);
		out.println(intentParser.detailParser());
	} catch(Exception e) {} 
 */
	String msg=request.getParameter("msg");
	try {
		if (msg.contains("이번주")){ 
			DateTest dateTest = new DateTest();
			String [] period = dateTest.thisWeek();
			for(String a : period) out.println(a + "<br/>"); 
			return;
		}
		if(msg.contains("프로필")){
			out.println(new AnswerProfile().answer(msg));
			return;
		}
		if (msg.contains("경기")) {
			out.println( new AnswerSchedule().answer(msg));
			return;
		}
	} catch (Exception e) {	
		out.println(e.getMessage());
	}
	out.println("다시 질문해주세요. 사용법은 아래를 참조하세요. <br/> 1. 선수프로필조회 : [player_name] 프로필 ex) 루니 프로필을 알고 싶어요. Heung-Min 프로필을 알려줘 <br/> 2. 경기결과(일정)조회 : [year]년 [month]월 경기 ex) 2017년 1월 경기를 알고 싶어요. ");
%>