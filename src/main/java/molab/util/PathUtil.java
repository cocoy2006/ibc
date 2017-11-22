package molab.util;

import java.io.File;

public class PathUtil {

	public static String upload(String name) {
		return classes() + "/static/upload/" + name;
	}
	
	private static String classes() {
		return new File(PathUtil.class.getResource("/").getFile()).getAbsolutePath();
	}
}
