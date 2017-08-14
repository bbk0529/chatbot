package answer;

import java.util.ArrayList;
import java.util.List;

import driver.Driver_schedule;
import model.GameVO;
import parser.IntentParser;

public class AnswerSchedule {
	public String answer(String msg) throws Exception {
		IntentParser intentParser = new IntentParser();
		Driver_schedule ds = new Driver_schedule();
		List<GameVO> list = new ArrayList<>();
		
		String yearmonth[] = intentParser.crawlerParser(msg);
		list = ds.crawling(yearmonth[0], yearmonth[1]);
		
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(yearmonth[0]+ "년 "+ yearmonth[1] + "월 경기 일정을 아래와 같이 알려 드립니다.");
		
		
		stringBuffer.append("<div class='row'><div class='col-sm-10'><table class='table' style='color:white;' align='center'><tr>");
		stringBuffer.append("<th>Date</th><th>Time</th><th>Stadium</th><th>Home</th><th>score</th><th> vs </th> <th>score</th><th>Away</th></tr>");
		for (GameVO a : list) {
			stringBuffer.append("<tr><td>" + a.getGame_date() + "</td><td>" + a.getGameStartTime() + "</td><td>"
					+ a.getStadium() + "</td><td>" + a.getHomeTeamName() + "</td><td>" + a.getHomeTeamScore()
					+ "</td><td> - </td><td> " + a.getAwayTeamScore() + "</td><td> " + a.getAwayTeamName() + "</tr>");
		}
		stringBuffer.append("</table></div></div>");
		return stringBuffer.toString();
	}
}
