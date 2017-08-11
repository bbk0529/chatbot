<%@page import="parser.Sofi_JSONParser_Schedule"%>
<%@page import="crawler.CrawlerSchedule"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%
	String msg=request.getParameter("msg");
	out.println("나" + msg);
	
	
	String rawData = null;
	//String urlstr = "http://sports.news.naver.com/wfootball/schedule/index.nhn?year=2017&month=08&category=epl";
	String year="2015";
	String month="09";
	String urlstr = "http://sports.news.naver.com/wfootball/schedule/index.nhn?year="+year+"&month="+month+"&category=epl";

	CrawlerSchedule crawer = new CrawlerSchedule();
	crawer.setUrlstr(urlstr);
	crawer.sofi_WebReader();
	rawData = crawer.getRawData();

	Sofi_JSONParser_Schedule parser = new Sofi_JSONParser_Schedule();
	parser.do_parsing(rawData);

%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel= "stylesheet" type="text/css" href="board.css">
	<link rel="shortcut icon" href="/favicon.ico" />
	<title>BOARD</title>	
</head>
<body>
	<%=year%>
	<%=month%>
	<%=urlstr%>
	<%=rawData%>
</body>
</html>
