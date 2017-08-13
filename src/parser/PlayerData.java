package parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class PlayerData {
	JSONObject jObject = new JSONObject();
	JSONParser jsonParser = new JSONParser();
	JSONArray playerSet = new JSONArray();
	String filePath = this.getClass().getResource("").getPath();
	
	// http:\platform static files.s3.amazonaws.com\premierleague\photos\players\40x40\Pxxxxxxxx.png
	
	private static Map<String, Integer> map = new HashMap <>();
	
	public static void main(String[] args) throws IOException {
		PlayerData jsp = new PlayerData();
		jsp.read();
		jsp.search("Son Heung Min");
	}
	
	public JSONObject search(String name) throws IOException  {
		
		playerSet = new PlayerData().read();
		
		JSONObject player = new JSONObject();
		
		System.out.println("MAP SHOW : " + map.toString());
		System.out.println("playerSet  : " + playerSet.toJSONString());
		player=(JSONObject) playerSet.get(map.get(name));
		System.out.println(player.toJSONString());
		return player;
	}

	
	public JSONArray read() throws IOException {
		String filename = "player.txt";
		FileReader fileReader = new FileReader(filePath + filename);
		BufferedReader br= new BufferedReader(fileReader);
		int idxnum=0;
		String [] header = {"id", "name", "detail", "pic", "position", "nationality"};
		while(br.ready()){ 
			JSONObject player = new JSONObject();
			for(int i=0; i<header.length;i++) { 
				player.put(header[i], br.readLine());
			}
			playerSet.add(player);
			map.put((String) player.get("name"), idxnum);
			idxnum++;
		}
		jObject.put("player", playerSet);
		return playerSet;
	}
}
