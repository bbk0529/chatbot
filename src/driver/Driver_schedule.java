package driver;

import java.sql.SQLException;
import java.util.List;

import org.json.simple.parser.ParseException;

import crawler.CrawlerSchedule;
import model.GameVO;
import parser.Sofi_JSONParser_Schedule;

public class Driver_schedule {
	/*public static void main(String[] args) throws ParseException, ClassNotFoundException, SQLException, java.text.ParseException {
	*/	
		public List<GameVO> crawling (String year, String month) throws Exception {
			
		String rawData = null;
		/*String year="2018";
		String month="08";*/
		String urlstr = "http://sports.news.naver.com/wfootball/schedule/index.nhn?year="+year+"&month="+month+"&category=epl";

		CrawlerSchedule crawer = new CrawlerSchedule();
		crawer.setUrlstr(urlstr);
		crawer.sofi_WebReader();
		rawData = crawer.getRawData();
			
		Sofi_JSONParser_Schedule parser = new Sofi_JSONParser_Schedule();
		List<GameVO> list = parser.do_parsing(rawData);
		return list;
	}
}