package com.odm.ftp.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


/**
 * @ClassName: AccountManager
 * @Auther: DMingO
 * @Date: 2020/6/19 18:50
 * @Description: �˻�������
 */
public class AccountManager {
	

	static String fileName = "server.xml";
	static String rootDir = "C:"+File.separator;//Զ���ļ�Ŀ¼
	//���ش洢�û������û�����(MD5)
	static HashMap<String, String> accountMap = new HashMap<>();

	public static void initAccount() {
		File file = new File(System.getProperty("user.dir")+"/bin/"+fileName);
		//��ʼ��������
		//admin 123
        accountMap.put("admin", "202cb962ac59075b964b07152d234b70");
        //test1 123456
        accountMap.put("test1", "e10adc3949ba59abbe56e057f20f883e");
	}
	
	public static String getRootPath() {
		return rootDir;
	}
	
	public static boolean hasUsername(String username) {
		return accountMap.get(username) != null;
	}

	public static String getPassword(String userName) {
		return accountMap.get(userName);
	}

	/**
	 * @Author DMingO
	 * @Description �����û��˺�����
	 * @Date  2020/6/19 23:39
	 * @Param [userName, password]
	 * @return boolean
	 **/
	public static boolean replenishAccount(String userName ,String password){
		if(hasUsername(userName)){
			return false;
		}
		if(accountMap == null)
			return false;
		accountMap.put(userName , password);
		return true;
	}
}
