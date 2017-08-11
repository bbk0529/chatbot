package parser;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.TeamVO;

public class Sofi_JSONParser_Team {
	
	public List<TeamVO> do_parsing (String rawData){
	
		List <TeamVO> list = new ArrayList<>() ;
		try {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(rawData);
        JSONArray teaminfoArray = (JSONArray) jsonObject.get("regularTeamRecordList");
       
        for(int i=0; i<teaminfoArray.size(); i++){
        	TeamVO teamVO = new TeamVO();
            JSONObject teamObject = (JSONObject) teaminfoArray.get(i);
            
            teamVO.setTeam_name		((String)teamObject.get("teamName"));         
            teamVO.setLastResult		((String)teamObject.get("lastResult"));       
            teamVO.setTeamCode			((String)teamObject.get("teamCode"));         
            teamVO.setTeamRankingGroup	((String)teamObject.get("teamRankingGroup"));
            
            teamVO.setGainGoal			(((Long)teamObject.get("gainGoal")).intValue());
            teamVO.setGameCount			(((Long)teamObject.get("gameCount")).intValue());
            teamVO.setGainPoint			(((Long)teamObject.get("gainPoint")).intValue());        
            teamVO.setFoul 				(((Long)teamObject.get("foul")).intValue());             
            teamVO.setLost 				(((Long)teamObject.get("lost")).intValue());             
            teamVO.setWon 				(((Long)teamObject.get("won")).intValue());              
            teamVO.setRank 				(((Long)teamObject.get("rank")).intValue());             
            teamVO.setDrawn				(((Long)teamObject.get("drawn")).intValue());             
                     
            list.add(teamVO);
            System.out.println(teamVO);
           
        } 
	}catch (ParseException e) {
	 e.printStackTrace();
	}		
		return list;
		
	}
}
