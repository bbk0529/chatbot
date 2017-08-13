package parser;

import java.sql.SQLException;
import java.util.StringTokenizer;

public class IntentParser {
	
	String intent = null;
	String intent_detail = null;
	String intent_fixture = null;
	String target = null;
		
	public void tokenParser(String returnedMessage){
		
		returnedMessage=returnedMessage.replace("[", "");
		returnedMessage=returnedMessage.replace("]", "");
		// [intent_player|profile|Son Heung-Min]
		StringTokenizer st = new StringTokenizer(returnedMessage, "|");  
		intent = st.nextToken();
	  	intent_detail = st.nextToken();
	  	target = st.nextToken();
	  	System.out.println("TOKENED INTENT :" + intent);
	  	System.out.println("TOKENED INTENT_DETAIL : "+ intent_detail);
	  	System.out.println("TOKENED PLAYER_NAME : "+ target); 
	}
	
	public String crawlerParser(String msg) {
		System.out.println(msg.indexOf("년"));
		System.out.println(msg.indexOf("월"));
		int guide_year = msg.indexOf("년");
		int guide_month = msg.indexOf("월");
		
		String year = msg.substring(guide_year-4,guide_year);
		String month = msg.substring(guide_month-2, guide_month);
		System.out.println(msg);
		System.out.println(year + " " + month);
		return year+month;
		//return null;
	}
	
	public String detailParser() throws ClassNotFoundException, SQLException{
		if (intent.equals("intent_team"))	return new DetailParserTeam(intent_detail, target).parsing();			
		if (intent.equals("intent_player")) return new DetailParserPlayer(intent_detail, target).parsing();
		if (intent.equals("intent_fixture")) return new DetailParserFixture(intent_detail, target).parsing();
		return null; 
	}
		
}
