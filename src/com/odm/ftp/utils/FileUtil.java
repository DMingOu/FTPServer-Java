package com.odm.ftp.utils;

import com.odm.ftp.entity.Result;

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
	 * @Description 获取单个文件/文件夹的大小
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

	/**
	 * @Author DMingO
	 * @Description 创建文件夹
	 * @Date  2020/6/20 14:10
	 * @Param [destDirName]
	 * @return boolean
	 **/
	public static Result createDir(String destDirName) {
		File dir = new File(destDirName);
		LogUtil.info("创建文件夹  "+ destDirName);
		Result result = new Result();
		if (dir.exists()) {
			result.setCode(553);
			result.setMsg("创建目录  " + destDirName + " 失败 Failed，原因：目标目录已经存在 ");
			return result;
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		//创建目录
		if (dir.mkdirs()) {
			result.setCode(250);
			result.setMsg("创建目录  " + destDirName + " 成功！ Success");
		} else {
			result.setCode(504);
			result.setMsg("创建目录 " + destDirName + " 失败！ Failed");
		}
		return result;
	}


}
