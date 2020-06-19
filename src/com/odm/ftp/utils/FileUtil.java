package com.odm.ftp.utils;

import java.io.File;
import java.text.DecimalFormat;

/**
 * @ClassName: FileUtil
 * @Auther: DMingO
 * @Date: 2020/6/19 18:50
 * @Description: 文件工具类，处理文件
 */
public class FileUtil {

	/**
	 * @Author DMingO
	 * @Description //TODO
	 * @Date  2020/6/19 19:06
	 * @Param [file] 单个文件
	 * @return 文件具体大小 根据大小返回不同的后缀
	 **/
	public static String getFileSize(File file) {
		String size = "";
		if (file.exists() && file.isFile()) {
			long fileS = file.length();
			DecimalFormat df = new DecimalFormat("#.00");
		if (fileS < 1024) {
			size = df.format((double) fileS) + "B";
		} else if (fileS < 1024 * 1024) {
			size = df.format((double) fileS / 1024) + "KB";
		} else if (fileS < 1024 * 1024 * 1024) {
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
