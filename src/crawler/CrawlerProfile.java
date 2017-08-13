package crawler;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrawlerProfile {
	
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
				//System.out.println(current_Line);
				String key = "Birth";
				String divider1 = "<div class=\"info\">";
				if (current_Line.contains(key)) {
					System.out.println("CrawlerProfile / webReader / Birthday checked");
					current_Line = bf.readLine();
					System.out.println(divider1.length());
					String value = current_Line.substring(current_Line.indexOf(divider1)+divider1.length(),current_Line.indexOf("</div>"));
					System.out.println(key + value);
					map.put(key, value);
				}
				
				key = "<div class=\"label\">Height</div>";
				if (current_Line.contains(key)) {
					System.out.println("CrawlerProfile / webReader / Height checked");
					current_Line = bf.readLine();
					
					String value = current_Line.substring(current_Line.indexOf(divider1)+divider1.length(),current_Line.indexOf("</div>"));
					System.out.println(key + value);
					map.put("Height", value);
				}
				
				key = "<div class=\"label\">Weight</div>";
				if (current_Line.contains(key)) {
					System.out.println("CrawlerProfile / webReader / Weight checked");
					current_Line = bf.readLine();
					String value = current_Line.substring(current_Line.indexOf(divider1)+divider1.length(),current_Line.indexOf("</div>"));
					System.out.println(key + value);
					map.put("Weight", value);
				}
				} // while
		bf.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
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