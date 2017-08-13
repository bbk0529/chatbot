package driver;

import crawler.Crawler;
import crawler.CrawlerImage;

public class Driver_Image {
	public static void main(String[] args) throws Exception {

		String a = "2017/10/01";
		String b = "2017/10/03";
		a=a.replace("/", "");
		b=b.replace("/", "");
		System.out.println(a + b);
		int q=Integer.parseInt(a);
		int w=Integer.parseInt(b);
		System.out.println(q<w);
		/*	String rawData = null;
		//String url = "https://www.google.co.kr/search?q=손흥민&source=lnms&tbm=isch&sa=X&ved=0ahUKEwjEkJvAwqbVAhXEEpQKHVXcCaQQ_AUICigB&biw=1527&bih=858";
		String url = "http://search.daum.net/search?w=tot&DA=YZR&t__nil_searchbox=btn&sug=&sugo=&q=%EC%86%90%ED%9D%A5%EB%AF%BC";

		Crawler crawler = new Crawler();
		crawler.setUrlstr(url);
		crawler.sofi_WebReader();
		rawData = crawler.getRawData();
*/
		/*Sofi_JSONParser_Team parser = new Sofi_JSONParser_Team();*/
		
	}
}