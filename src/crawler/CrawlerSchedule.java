package crawler;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class CrawlerSchedule {
	
	private String current_Line;
	private String rawData=null;
	private String urlstr=null;
	
	public String sofi_WebReader(){
		try {
			URL url = new URL(urlstr);		
			BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream()));
			while((current_Line=bf.readLine())!=null){
			   	  if (current_Line.contains("monthlyScheduleModel")) { 
			 		 rawData=current_Line.trim();		
			  	  } // if
		    } // while
		bf.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		rawData = rawData.substring(0, rawData.length()-1);
		rawData = rawData.replace("monthlyScheduleModel","\"monthlyScheduleModel\"");
		rawData = "{"+ rawData + "}";
		System.out.println("CrawlerSchedule / sofi_WebReader / Start ");
		System.out.println(rawData);
		System.out.println("CrawlerSchedule / sofi_WebReader / End ");
		return rawData.trim();
	}

	public String getCurrent_Line() {
		return current_Line;
	}

	public void setCurrent_Line(String current_Line) {
		this.current_Line = current_Line;
	}

	public String getRawData() {
		return rawData;
	}

	public void setRawData(String rawData) {
		this.rawData = rawData;
	}

	public String getUrlstr() {
		return urlstr;
	}

	public void setUrlstr(String urlstr) {
		this.urlstr = urlstr;
	}
	
	
}