package answer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import driver.Driver_Player;
import driver.Driver_Profile;
import parser.PlayerParser;

public class AnswerProfile {

	public String answer(String msg) throws Exception{
		Driver_Player driver_Player = new Driver_Player();
		Driver_Profile driver_Profile = new Driver_Profile();
		
		List <String []> list = new ArrayList<>();	
		Map <String, String> map = new HashMap<>();
		StringBuffer stringBuffer = new StringBuffer();
		
		//  msg 에서 이름부분만 뽑아냄. 
		String name = msg
				.substring(0, msg.indexOf("프로필"))
				.replace("의","")
				.replace("선수","")
				.trim(); 
		System.out.println("AnswerProfile / answer :" + name);
		//검색 타겟으로 할 선수이름 찾아냄. "(target)"  프로필 // 이름을 name.txt에서 검색해서 선수 LIST RETURN 
		PlayerParser playerParser = new PlayerParser();
		
		list = playerParser.parser(name); 
		
		if (list.size()==1) {
			stringBuffer.append("<img src='http://platform-static-files.s3.amazonaws.com/premierleague/photos/players/40x40/" + list.get(0)[2] + ".png'>");
			stringBuffer.append("<b style='font-size:60px'>" + list.get(0)[1] + "</b></br>" +  list.get(0)[5] );
			stringBuffer.append(" " +list.get(0)[4] +  " " + list.get(0)[3] + " ");
			
			map=driver_Profile.profileDetail(list.get(0));
			for (String keymap : map.keySet() ) {
				stringBuffer.append(map.get(keymap) + " ");
			}
			
			map=driver_Player.playerDetail(list.get(0)[0]);
			int count=0;
			stringBuffer.append("<div class='row'>");
			stringBuffer.append("<div class='col-sm-10'>");
			stringBuffer.append("<table class='table' style='color:white;' align='center'><tr>");
			for (String keymap : map.keySet() ) {
				count++;
				stringBuffer.append("<td><b>"+  keymap  +"</b></td><td style='color:white;text-align:right'>" +  map.get(keymap) + "</td>");
				stringBuffer.append(count%4 == 0 ? "</tr><tr>" : ""); 
			}
			stringBuffer.append("</tr></table>");
			stringBuffer.append("</div>");
			stringBuffer.append("</div>");
		} else {
			for (int i=0; i<list.size(); i++){
				stringBuffer.append(list.get(i)[1] +" " + list.get(i)[5] + "<br/>");
			}
			stringBuffer.append("검색어로 확인된 선수가 " + list.size() + "명 있습니다. 이름을 좀 더 정확히 넣어주세요.<br/>");
		}
		return stringBuffer.toString();
	}
}
