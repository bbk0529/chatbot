<%@page import="driver.Driver_Profile"%>
<%@page import="test.DateTest"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="driver.Driver_Player"%>
<%@page import="parser.PlayerData"%>
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
	// 현재 왓슨 계정이 없어져서 임시로 주석처리함. 
	/* WatsonConnection wc = new WatsonConnection();
	String returnedMessage= wc.service(request, response, msg); */
	
	Map <String, String> map = new HashMap<>();
	
	DateTest dateTest = new DateTest();
	String [] period = dateTest.thisWeek();
	for(String a : period) out.println(a + "<br/>"); 
	
	
	if(msg.contains("프로필")){
		PlayerData playerData = new PlayerData();
		Driver_Player driver_Player = new Driver_Player();
		Driver_Profile driver_Profile = new Driver_Profile();
		JSONObject jObject = new JSONObject();
	
		String name = msg.substring(0, msg.indexOf("프로필")).trim();
		jObject = playerData.search(name);
		
		
		map=driver_Profile.profileDetail(jObject);
		for (String keymap : map.keySet() ) {
			out.println("<b>" + keymap + "</b> " + map.get(keymap) + "<br/>");
		}
		
		map=driver_Player.playerDetail(jObject);
		out.println("<img src='http://platform-static-files.s3.amazonaws.com/premierleague/photos/players/40x40/" + jObject.get("pic") + "' width='100px' >");
		out.println(jObject.get("name")+"선수는 " + jObject.get("nationality") +  "출신의 " + jObject.get("position") + "입니다 <br/>" );
		for (String keymap : map.keySet() ) {
			out.println(keymap + " " + map.get(keymap) + "\t");
		}
		
		return;
	}
	
	
	if (msg.contains("경기")) {
		IntentParser intentParser = new IntentParser();
		String yearmonth = intentParser.crawlerParser(msg);
		Driver_schedule ds = new Driver_schedule();
		List<GameVO> list = new ArrayList<>();
		list=ds.crawling(yearmonth.substring(0,4), yearmonth.substring(4));
		for(GameVO a : list) {
			out.println(a.toString() + "<br/>");
		}
		return;
	}
	
	
	/* try {
		
		intentParser.tokenParser(returnedMessage);
		out.println(intentParser.detailParser());
		
	} catch(Exception e)	{
		out.println("적당한 대답을 찾지 못했습니다. 다시 질문해주세요");
	} */
			
	
%>