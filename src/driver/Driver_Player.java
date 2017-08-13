package driver;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;

import crawler.CrawlerPlayer;

public class Driver_Player {
	
	public Map <String, String> playerDetail(JSONObject player) {
		
		String rawData = null;
		String url = "https://www.premierleague.com/players/" + player.get("id") + "/player/stats";
		Map <String, String> map = new HashMap<>();
		CrawlerPlayer crawler = new CrawlerPlayer();
		
		crawler.setUrlstr(url);
		map =crawler.webReader();
		System.out.println("Map Size : " + map.size());
		for (String keymap : map.keySet() ) {
			System.out.println(keymap + " " + map.get(keymap));
		}
		return map;
	}
}