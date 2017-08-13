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


// text 파일 만드는 Crawling 기계역할
public class JSonPretreatment {
	JSONObject jObject = new JSONObject();
	JSONParser jsonParser = new JSONParser();
	JSONArray playerSet = new JSONArray();
	
	String filePath = this.getClass().getResource("").getPath();
	
	
	Map<String, Integer> map = new HashMap <> ();
	
	
	public static void main(String[] args) throws IOException {
		JSonPretreatment jsp = new JSonPretreatment();
	/*	jsp.read();
		jsp.write();
		jsp.search(3);*/
		jsp.read2();
		
	}
	
	public void write() {
		System.out.println(jObject.toJSONString());
	}
	
	public void search(int name) {
		System.out.println(playerSet.get(name));
		System.out.println("map index : " + map.get("Tammy Abraham"));
		System.out.println(playerSet.get(map.get("Tammy Abraham")));
	}

	
		
	public void read2() throws IOException{
		String input = "check.txt";
		FileReader fileReader = new FileReader(filePath + input);
		BufferedReader br= new BufferedReader(fileReader);
		
		String output = "result.txt";
		FileWriter fileWriter = new FileWriter(filePath + output);
		BufferedWriter bw= new BufferedWriter(fileWriter);
		
		
		while(br.ready()){
			String currentLine=br.readLine();
			if (currentLine.contains("href=")){
				String a [] = currentLine.split("/");
				System.out.println(a[4]);
				System.out.println(a[5]);
				System.out.println("http://www.premierleague.com/players/" + a[4] + "/" +a[5] );
				//bw.write(a[4]+"\n");
				//bw.write(a[5]+"\n");
				
				//bw.write("//www.premierleague.com/players/" + a[4] + "/" + a[5]);
			}
			
			if (currentLine.contains("src=")){
				String a [] = currentLine.split("\"");
				System.out.println("http:" + a[1]);
				System.out.println();
				System.out.println();
				//bw.write(a[1]+"\n");
			} 
			
		}
		br.close();
		
		//bw.close();
	}
	
	
	public void read()throws IOException {
		String filename = "test.txt";
		FileReader fileReader = new FileReader(filePath + filename);
		BufferedReader br= new BufferedReader(fileReader);
		int idxnum=0;
		while(br.ready()){ 
			JSONObject player = new JSONObject();
			String cline=br.readLine();
			
			StringTokenizer st = new StringTokenizer(cline, "\t");
			String header[] = {"name","position","nationality"};
			player.put("index", idxnum);
			for(int i=0;st.hasMoreTokens();i++){
				player.put(header[i], st.nextToken().trim());
			}
		playerSet.add(player);
		map.put((String) player.get("name"), idxnum);
		idxnum++;
		}
		jObject.put("player", playerSet);
	}
}
