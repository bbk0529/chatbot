package parser;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.GameVO;
public class Sofi_JSONParser_Schedule {
	
	public List<GameVO> do_parsing(String rawData) throws ParseException, java.text.ParseException{
		List <GameVO> list = new ArrayList<>() ;
		
		JSONParser jsonParser			 = new JSONParser();
		JSONObject object  				= (JSONObject) jsonParser.parse(rawData);
		JSONObject monthlyScheduleModel = (JSONObject) object.get("monthlyScheduleModel");
		JSONObject dailyScheduleListMap = (JSONObject) monthlyScheduleModel.get("dailyScheduleListMap");
		
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt((String) monthlyScheduleModel.get("year")), Integer.parseInt((String)monthlyScheduleModel.get("month")), 1);
		int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println(endDay);
		
		String yearMonth=(String) monthlyScheduleModel.get("year") + monthlyScheduleModel.get("month");
		for (int k=1;k<=endDay;k++){ 
			
			String date = yearMonth + (k >=10 ? k : "0"+k);	
			JSONArray schDetail = (JSONArray) dailyScheduleListMap.get(date);
			
			date = (String) monthlyScheduleModel.get("year") +"/" +monthlyScheduleModel.get("month")+"/"+(k >=10 ? k : "0"+k);
			if(schDetail!=null) {
				for (int i=0; i<schDetail.size();i++){
	
					GameVO gameVO = new GameVO();
					JSONObject detail = (JSONObject) schDetail.get(i);
					gameVO.setGame_date(date);
					gameVO.setGameStartTime((String)detail.get("gameStartTime"));
					gameVO.setHomeTeamName((String)detail.get("homeTeamName"));
					gameVO.setAwayTeamName((String)detail.get("awayTeamName"));
					gameVO.setHomeTeamScore((String)detail.get("homeTeamScore"));
					gameVO.setAwayTeamScore((String)detail.get("awayTeamScore"));
					gameVO.setStadium((String)detail.get("stadium"));
					list.add(gameVO);
				}
			}
		}
		return list;
	}
}

