package com.odm.ftp.utils;

import com.odm.ftp.entity.Result;

import java.io.File;
import java.text.DecimalFormat;

/**
 * @ClassName: FileUtil
 * @Auther: DMingO
 * @Date: 2020/6/19 18:50
 * @Description: �ļ������࣬�����ļ�
 */
public class FileUtil {

	/**
	 * @Author DMingO
	 * @Description ��ȡ�����ļ�/�ļ��еĴ�С
	 * @Date  2020/6/19 19:06
	 * @Param [file] �����ļ�
	 * @return �ļ������С ���ݴ�С���ز�ͬ�ĺ�׺
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
	 * @Description �����ļ���
	 * @Date  2020/6/20 14:10
	 * @Param [destDirName]
	 * @return boolean
	 **/
	public static Result createDir(String destDirName) {
		File dir = new File(destDirName);
		LogUtil.info("�����ļ���  "+ destDirName);
		Result result = new Result();
		if (dir.exists()) {
			result.setCode(553);
			result.setMsg("����Ŀ¼  " + destDirName + " ʧ�� Failed��ԭ��Ŀ��Ŀ¼�Ѿ����� ");
			return result;
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		//����Ŀ¼
		if (dir.mkdirs()) {
			result.setCode(250);
			result.setMsg("����Ŀ¼  " + destDirName + " �ɹ��� Success");
		} else {
			result.setCode(504);
			result.setMsg("����Ŀ¼ " + destDirName + " ʧ�ܣ� Failed");
		}
		return result;
	}


}
