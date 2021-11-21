package com.example.demo.utility;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeZoneConversion {
	private static final String SQL_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private String zoneName;
	
	public TimeZoneConversion(String zoneName) {
		super();
		this.zoneName = zoneName;
	}

	public String convertDateTimeToUserTimeZone(String dateTime, String format) {
		LocalDateTime ldt = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(format));
		ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
		return zdt.withZoneSameInstant(ZoneId.of(zoneName)).format(DateTimeFormatter.ofPattern(format));
	}
	
	public String convertDateTimeToServerTimeZone(String dateTime, String format) {
		LocalDateTime ldt = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(format));
		ZonedDateTime zdt = ldt.atZone(ZoneId.of(zoneName));
		return zdt.withZoneSameInstant(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern(format));
	}
	
	public long convertServerMillisToUserMillis(long millis) {
		LocalDateTime ldtServer = LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault());
		String serverTime = ldtServer.format(DateTimeFormatter.ofPattern(SQL_DATE_TIME_FORMAT));
		
		String userTime = convertDateTimeToUserTimeZone(serverTime, SQL_DATE_TIME_FORMAT);
		LocalDateTime ldtUser = LocalDateTime.parse(userTime, DateTimeFormatter.ofPattern(SQL_DATE_TIME_FORMAT));
		
		ZonedDateTime zdt = ldtUser.atZone(ZoneId.systemDefault());

		return zdt.toInstant().toEpochMilli();
	}
	
	public long convertUserMillisToServerMillis(long millis) {
		LocalDateTime ldtUser = LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault());
		String userTime = ldtUser.format(DateTimeFormatter.ofPattern(SQL_DATE_TIME_FORMAT));
		
		String serverTime = convertDateTimeToServerTimeZone(userTime, SQL_DATE_TIME_FORMAT);
		LocalDateTime ldtServer = LocalDateTime.parse(serverTime, DateTimeFormatter.ofPattern(SQL_DATE_TIME_FORMAT));
		
		ZonedDateTime zdt = ldtServer.atZone(ZoneId.systemDefault());
		
		return zdt.toInstant().toEpochMilli();
	}
	
	public static void main(String[] args) {
		TimeZoneConversion tzc = new TimeZoneConversion("Asia/Kolkata");
		String dateTime = "2021-01-15 15:30:25";
		String convertedTime = tzc.convertDateTimeToUserTimeZone(dateTime, "yyyy-MM-dd HH:mm:ss");
		
		System.err.println(dateTime);
		System.err.println("server to user    " + convertedTime);
		
		convertedTime = tzc.convertDateTimeToServerTimeZone(convertedTime, "yyyy-MM-dd HH:mm:ss");
		System.err.println("user to server    " + convertedTime);
		
		long serverMillis = 1610724625000l;
		long userMillis = tzc.convertServerMillisToUserMillis(serverMillis);
		
		System.err.println("server to user    " + userMillis);
		
		serverMillis = tzc.convertUserMillisToServerMillis(userMillis);
		System.err.println("user to server    " + serverMillis);
		
		// One liner version to get zoned date time 
		ZonedDateTime zdt = ZonedDateTime.of(
				LocalDateTime.parse("2021-01-15 21:00:25", DateTimeFormatter.ofPattern(SQL_DATE_TIME_FORMAT)), 
				ZoneId.of("Asia/Kolkata")
				);
		System.err.println(zdt.plusHours(2).withZoneSameInstant(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern(SQL_DATE_TIME_FORMAT)));
	}
}
