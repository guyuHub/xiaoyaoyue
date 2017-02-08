package cn.com.doit.mvc.formatAndconverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.core.convert.converter.Converter;

public class Converter_StringToDate implements Converter<String,java.util.Date> {
	private static final String date_formatter="yyyy-MM-dd";
	public java.util.Date convert(String source) {
		System.out.println("----this is in Converter_StringToDate-----");
		SimpleDateFormat sb=new SimpleDateFormat(date_formatter);	
		try {
			return sb.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}}
}
