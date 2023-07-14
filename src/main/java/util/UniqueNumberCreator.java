package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UniqueNumberCreator {
	
	public static String getNumber() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HHmmss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}
	
	private UniqueNumberCreator() {
		throw new IllegalStateException("UniqueNumberCreator class");
	}
	
}
