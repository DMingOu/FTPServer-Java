package com.odm.ftp.utils;

import java.io.File;
import java.text.DecimalFormat;

public class FileUtil {

	public static String getFileSize(String Path) {
		return getFileSize(new File(Path));
	}

	public static String getFileSize(File file) {
		String size = "";
		if (file.exists() && file.isFile()) {
			long fileS = file.length();
			DecimalFormat df = new DecimalFormat("#.00");
		if (fileS < 1024) {
			size = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			size = df.format((double) fileS / 1024) + "KB";
		} else if (fileS < 1073741824) {
			size = df.format((double) fileS / 1048576) + "MB";
		} else {
			size = df.format((double) fileS / 1073741824) + "GB";
		}
		} else if (file.exists() && file.isDirectory()) {
			size = "";
		} else {
			size = "0BT";
		}
		return size;
	}

}
