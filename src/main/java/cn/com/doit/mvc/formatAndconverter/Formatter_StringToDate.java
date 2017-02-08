package cn.com.doit.mvc.formatAndconverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class Formatter_StringToDate implements Formatter<Date>{
private static final String date_formatter="yyyy-MM-dd";
	public String print(Date object, Locale locale) {	
		return object.toLocaleString();
	}

	public Date parse(String text, Locale locale) throws ParseException {
		System.out.println("------this is in Formatter_StringToDate-------");
		SimpleDateFormat sb=new SimpleDateFormat(date_formatter);	
		return sb.parse(text);
	}

}
