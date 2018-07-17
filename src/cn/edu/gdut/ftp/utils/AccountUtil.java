package cn.edu.gdut.ftp.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class AccountUtil {
	

	static String fileName = "server.xml";
	static String rootDir = "E:\\计算机网络实验\\计算机网络课程设计"+File.separator;//服务器位置，用于存储文件
	static HashMap<String, String> users = new HashMap<String, String>();

	public static void initAccount() {
		File file = new File(System.getProperty("user.dir")+"/bin/"+fileName);
        SAXBuilder builder = new SAXBuilder();
        users.put("admin", "202cb962ac59075b964b07152d234b70");


	}
	
	public static String getRootPath() {
		return rootDir;
	}
	
	public static boolean hasUsername(String username) {
		if (users.get(username)!=null) {
			return true;
		}else{
			return false;
		}
	}

	public static String getPassword(String userName) {
		return users.get(userName);
	}
}
