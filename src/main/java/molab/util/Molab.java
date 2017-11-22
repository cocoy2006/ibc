package molab.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Molab {

	private static Logger log = LoggerFactory.getLogger(Molab.class);
	
	public static String rename(String src, String pureName) {
		if(src != null && src.lastIndexOf(".") > -1) {
			return pureName + src.substring(src.lastIndexOf("."));
		}
		return pureName; 
	}

	public static int parseInt(long now) {
		return (int) (now / 1000);
	}

	public static String parseDate(int time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		return format.format(new Date(time * 1000l));
	}
		
	public static int parseTime(String time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			return (int) (format.parse(time).getTime() / 1000);
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		return 0;
	}
	
	public static int parseDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return (int) (format.parse(date).getTime() / 1000);
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		return 0;
	}

	public static float rescale(double d) {
		return rescale(d, 2);
	}

	public static float rescale(double d, int scale) {
		float f = (float) d;
		return rescale(f, scale);
	}

	public static float rescale(float f) {
		return rescale(f, 2);
	}

	public static float rescale(float f, int scale) {
		String pattern = "0.";
		for (int i = 0; i < scale; i++) {
			pattern += "#";
		}
		DecimalFormat format = new DecimalFormat(pattern);
		return Float.valueOf(format.format(f));
	}
}
