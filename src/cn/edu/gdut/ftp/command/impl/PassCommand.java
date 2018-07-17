package cn.edu.gdut.ftp.command.impl;

import java.io.BufferedWriter;
import java.io.IOException;

import cn.edu.gdut.ftp.bean.UserInfo;
import cn.edu.gdut.ftp.command.BaseCommand;
import cn.edu.gdut.ftp.utils.AccountUtil;
import cn.edu.gdut.ftp.utils.Md5Util;

public class PassCommand implements BaseCommand{

	/**
	 * 密码验证
	 * @param passWord
	 * @param writer
	 * @param userInfo
	 */
	@Override
	public void executeCommand(String passWord, BufferedWriter writer, UserInfo userInfo) {
		String passWord2Md5 = Md5Util.encoder(passWord);
		if (passWord2Md5.equals(AccountUtil.getPassword(userInfo.getUsername()))) {
			System.out.println("密码正确"+passWord + ",md5密码："+passWord2Md5);
			try {
				writer.write("230 PWD is right!Welcome!\r\n");
				writer.flush();
				userInfo.setPassword(passWord2Md5);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				writer.write("530 Wrong PWD!\r\n");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
