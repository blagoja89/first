package by.julia.bankservice.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateUtil {
	private static final String PATTERN_DATE_WITH_TIME = "dd MM yyyy HH:mm:ss";
	private static final String PATTERN_TIME = "HH:mm";
	private static final String PATTERN_DATE = "yyyy-MM-dd";
	
	public static String getDateWithTime(Date date){
		return new SimpleDateFormat(PATTERN_DATE_WITH_TIME).format(date);
	}

	public static String getTime(Date date){
		return new SimpleDateFormat(PATTERN_TIME).format(date);
	}
	
	public static Date parseToTime(String date){
		try {
			return new SimpleDateFormat(PATTERN_TIME).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date parseToDate(String date) throws ParseException{
			return new SimpleDateFormat(PATTERN_DATE).parse(date);
	}


	public static Date concatDate(Date date, List<Date> times){
		return concatDate(date, concatDate(times));
	}
	

	public static Date concatDate(Date date, Date date2){
		GregorianCalendar worldTour = new GregorianCalendar();
		worldTour.setTime(date);
		GregorianCalendar worldTour1 = new GregorianCalendar();
		worldTour1.setTime(date2);
		worldTour.add(GregorianCalendar.HOUR, worldTour1.get(GregorianCalendar.HOUR));
		worldTour.add(GregorianCalendar.MINUTE, worldTour1.get(GregorianCalendar.MINUTE));
		worldTour.add(GregorianCalendar.SECOND, worldTour1.get(GregorianCalendar.SECOND));
		return worldTour.getTime();
	}

	public static Date concatDate(List<Date> times){
		GregorianCalendar worldTour = new GregorianCalendar();
		worldTour.setTime(times.get(0));
		for (int i = 1; i < times.size(); i++) {				
			GregorianCalendar worldTour1 = new GregorianCalendar();
			worldTour1.setTime(times.get(i));
			worldTour.add(GregorianCalendar.HOUR, worldTour1.get(GregorianCalendar.HOUR));
			worldTour.add(GregorianCalendar.MINUTE, worldTour1.get(GregorianCalendar.MINUTE));
			worldTour.add(GregorianCalendar.SECOND, worldTour1.get(GregorianCalendar.SECOND));
		}
		return worldTour.getTime();
	}
}
