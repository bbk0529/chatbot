package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import driver.Driver_Player;
import driver.Driver_Profile;
import driver.Driver_schedule;
import model.GameVO;
import parser.IntentParser;
import parser.PlayerData;
import parser.PlayerParser;
import test.DateTest;

public class Coordinator extends AbstractController{

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String msg=request.getParameter("msg");
		System.out.println("Coordinator / handleRequestInternal" + msg);
		Map <String, String> map = new HashMap<> ();
		mav.addObject("message",msg + " Treated in Coordinator");
		mav.setViewName("/WEB-INF/views/chat.jsp");
		return mav;
		/*
		if (msg.contains("이번주")){ 
			DateTest dateTest = new DateTest();
			String [] period = dateTest.thisWeek();
			for(String a : period) out.println(a + "<br/>"); 
		}
		
		if(msg.contains("프로필")){
			PlayerData playerData = new PlayerData();
			Driver_Player driver_Player = new Driver_Player();
			Driver_Profile driver_Profile = new Driver_Profile();
			JSONObject jObject = new JSONObject();
			List <String []> list = new ArrayList<>();	
			
			//검색 타겟으로 할 선수이름 찾아냄. "(target)"  프로필 
			String name = msg.substring(0, msg.indexOf("프로필")).trim();
			
			PlayerParser playerParser = new PlayerParser();
			list = playerParser.parser(name); // 이름을 name.txt에서 검색해서 선수 LIST RETURN 
			if (list.size()==1) {
				
				out.println("<br/><img src='http://platform-static-files.s3.amazonaws.com/premierleague/photos/players/40x40/" + list.get(0)[2] + ".png ' width='100px' ><br/>");
				out.println(list.get(0)[1] + " " +  list.get(0)[5] );
				out.println(list.get(0)[4] +  " " + list.get(0)[3] + "<br/>");
				
				map=driver_Profile.profileDetail(list.get(0)[0]);
				for (String keymap : map.keySet() ) {
					out.println(map.get(keymap) + " ");
				}
				
			map=driver_Player.playerDetail(list.get(0)[0]);
			out.println("<div class='row'>");
			out.println("<div class='col-sm-10'>");
			out.println("<table class='table' style='color:white;' align='center'><tr>");
			int count=0;
			for (String keymap : map.keySet() ) {
				count++;
				out.println("<td><b>"+  keymap  +"</b></td><td style='color:white;text-align:right'>" +  map.get(keymap) + "</td>");
				out.println(count%4 == 0 ? "</tr><tr>" : ""); 
			}
			out.println("</tr></table>");
			out.println("</div>");
			out.println("</div>");
			} else {
				out.println("검색어로 확인된 선수가 2명 이상 있습니다.<br/>");
				for (int i=0; i<list.size(); i++){
					out.println(list.get(i)[1] + "<br/>");
				}
			}
		}
		
		if (msg.contains("경기")) {
			IntentParser intentParser = new IntentParser();
			String yearmonth []= intentParser.crawlerParser(msg);
			Driver_schedule ds = new Driver_schedule();
			List<GameVO> list = new ArrayList<>();
			list=ds.crawling(yearmonth[0], yearmonth[1]);
			out.println("<div class='row'>");
			out.println("<div class='col-sm-10'>");
			out.println("<table class='table' style='color:white;' align='center'><tr>");
			
			
			out.println("<th>Date</th><th>Time</th><th>Stadium</th><th>Home</th><th>score</th><th> vs </th> <th>score</th><th>Away</th></tr>");
			for(GameVO a : list) {
				out.println("<tr><td>" + a.getGame_date() + "</td><td>"+ a.getGameStartTime() + "</td><td>" + a.getStadium() + "</td><td>" + a.getHomeTeamName()   
				+ "</td><td>" + a.getHomeTeamScore()   + "</td><td> - </td><td> " + a.getAwayTeamScore()  + "</td><td> " + a.getAwayTeamName() + "</tr>");
			}
			out.println("</table>");
			out.println("</div>");
			out.println("</div>");
		}
	}
	
	out.println("적당한 대답을 찾지 못했습니다. 다시 질문해주세요");*/
	
	/* try {
		
		intentParser.tokenParser(returnedMessage);
		out.println(intentParser.detailParser());
		
	} catch(Exception e)	{
		out.println("적당한 대답을 찾지 못했습니다. 다시 질문해주세요");
	} */
}}
