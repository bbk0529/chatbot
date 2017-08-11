<%@page import="model.GameVO"%>
<%@page import="driver.Driver_schedule"%>
<%@page import="parser.IntentParser"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="model.PlayerVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="controller.WatsonConnection"%>
<%@ page language="java" contentType="text/plain; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String msg=request.getParameter("msg");
	/* WatsonConnection wc = new WatsonConnection();
	String returnedMessage= wc.service(request, response, msg); */
	
	
	if (msg.contains("경기일정")) {
		IntentParser intentParser = new IntentParser();
		String yearmonth = intentParser.crawlerParser(msg);
		Driver_schedule ds = new Driver_schedule();
		out.println(yearmonth.substring(0,4) +"  " +yearmonth.substring(4)+"<br/>");
		List<GameVO> list = new ArrayList<>();
		list=ds.crawling(yearmonth.substring(0,4), yearmonth.substring(4));
		for(GameVO a : list) {
			out.println(a.toString());
		}
		return;
	}
	/* try {
		
		intentParser.tokenParser(returnedMessage);
		out.println(intentParser.detailParser());
		if (msg.contains("손흥민")) out.println("<img src='./resources/img/son.jpg' width='400px'>");
		if (msg.contains("루니")) out.println("<img src='./resources/img/rooney.jpg' width='400px'>");
		if (msg.contains("아자르")) out.println("<img src='./resources/img/ajar.jpg' width='400px'>");
		if (msg.contains("기성용")) out.println("<img src='./resources/img/ki.jpg' width='400px'>");
		
		
	} catch(Exception e)	{
		out.println("적당한 대답을 찾지 못했습니다. 다시 질문해주세요");
	} */
			
	
%>