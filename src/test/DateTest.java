package test;

import java.text.ParseException; 
import java.text.SimpleDateFormat; 
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTest {
	 
		public String[] thisWeek() {
		    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd"); 
		    
		    Date date = new Date();
		    System.out.println(date);
		    Calendar c1 = Calendar.getInstance();
		    String today = simpleDateFormat.format(c1.getTime());
		    c1.setTime(date); 
		    System.out.println("오늘   날짜 : " + today); 
		      
		
		    c1.add(Calendar.DATE, 1 - c1.get(Calendar.DAY_OF_WEEK)); 
		    String startDate =simpleDateFormat.format(c1.getTime());
		    System.out.println("첫번째 요일(일요일) 날짜 : " +    startDate); 
		    
		    c1.setTime(date); 
		    
		    c1.add(Calendar.DATE, 7 - c1.get(Calendar.DAY_OF_WEEK)); 
		    String endDate = simpleDateFormat.format(c1.getTime());
		    System.out.println("마지막 요일(토요일) 날짜 : " + endDate); 
		      
		   
		     	
		    String [] boundary = {startDate, endDate};
		    return boundary;
		}
}
