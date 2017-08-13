package crawler;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrawlerPlayer {
	
	private String current_Line;
	private Map<String, String> rawData=null;
	private String urlstr=null;
	
	
	List <String> list = new ArrayList<>();
	Map <String, String> map = new HashMap<>();
	String divider1="<span class=\"stat\">";
	
	public Map<String, String> webReader(){
		try {
			StringBuffer sb = new StringBuffer();
			URL url = new URL(urlstr);		
			BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream()));
			while((current_Line=bf.readLine())!=null){
			   //	System.out.println(current_Line);  
				if (current_Line.contains(divider1)) { 
			 		int start = current_Line.indexOf(divider1) + divider1.length();
			 		int last = current_Line.lastIndexOf("<span class=");
			 		if (start<last && (last-start)<15) {
			 			String key = current_Line.substring(start, last).trim();
			 			String value = bf.readLine().trim();
			 			System.out.println(key + " : " + value);
			 			map.put(key, value);
			 		}
				}
		    } // while
		bf.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		//rawData = rawData.substring(16,rawData.length()-1);
		//System.out.println(rawData);
		return map;
	}
	
	public String getCurrent_Line() {
		return current_Line;
	}
	public void setCurrent_Line(String current_Line) {
		this.current_Line = current_Line;
	}
	public Map<String, String> getRawData() {
		return rawData;
	}
	public void setRawData(Map<String, String> rawData) {
		this.rawData = rawData;
	}
	public String getUrlstr() {
		return urlstr;
	}
	public void setUrlstr(String urlstr) {
		this.urlstr = urlstr;
	} 
}