package driver;

import java.util.HashMap;
import java.util.Map;
import crawler.CrawlerPlayer;

public class Driver_Player {
	
	public Map <String, String> playerDetail(String id) {
		String url = "https://www.premierleague.com/players/" + id + "/player/stats";
		Map <String, String> map = new HashMap<>();
		CrawlerPlayer crawler = new CrawlerPlayer();
		crawler.setUrlstr(url);
		map =crawler.webReader();
		
		System.out.println("Driver_Player / playerDetail / Map Size : " + map.size());
		for (String keymap : map.keySet() ) {
			System.out.println(keymap + " " + map.get(keymap));
		}
		return map;
	}
}