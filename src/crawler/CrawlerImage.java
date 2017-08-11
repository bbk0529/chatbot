package crawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class CrawlerImage {

	private String current_Line;
	private String rawData=null;
	private String urlstr=null;
	
	public String sofi_WebReader(){
		try {
			URL url = new URL(urlstr);		
			BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream()));
			while((current_Line=bf.readLine())!=null){
				System.out.println(current_Line);
			   	  /*if (current_Line.contains("{\"cl\"")) { 
			 		 rawData=current_Line.trim();
			 		 System.out.println("RawData captured in the webpage");
			 		 System.out.println(rawData);
			  	  } // if
*/		    } // while
		bf.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		// rawData = rawData.substring(16,rawData.length()-1);
		/* System.out.println("RawData preprocessed in the webpage");
		System.out.println(rawData);
		return rawData;*/
		return null;
	}
	
	public final String getCurrent_Line() {
		return current_Line;
	}
	public final void setCurrent_Line(String current_Line) {
		this.current_Line = current_Line;
	}
	public final String getRawData() {
		return rawData;
	}
	public final void setRawData(String rawData) {
		this.rawData = rawData;
	}
	public final String getUrlstr() {
		return urlstr;
	}
	public final void setUrlstr(String urlstr) {
		this.urlstr = urlstr;
	} 
}
