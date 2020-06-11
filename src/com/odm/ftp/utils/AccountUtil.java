package com.odm.ftp.utils;

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
	static String rootDir = "C:\\Users\\ODM\\Desktop"+File.separator;//服务器位置，用于存储文件
	static HashMap<String, String> users = new HashMap<>();

	public static void initAccount() {
		File file = new File(System.getProperty("user.dir")+"/bin/"+fileName);
        SAXBuilder builder = new SAXBuilder();
        //初始测试密码？
        users.put("admin", "202cb962ac59075b964b07152d234b70");
        users.put("test1", "123456");
	}
	
	public static String getRootPath() {
		return rootDir;
	}
	
	public static boolean hasUsername(String username) {
		return users.get(username) != null;
	}

	public static String getPassword(String userName) {
		return users.get(userName);
	}
}
