package cn.edu.gdut.ftp.command.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import cn.edu.gdut.ftp.bean.UserInfo;
import cn.edu.gdut.ftp.command.BaseCommand;
import cn.edu.gdut.ftp.utils.AccountUtil;

public class UserCommmand implements BaseCommand{

	private BufferedReader reader;

	@Override
	public void executeCommand(String userName, BufferedWriter writer, UserInfo userInfo) {
		if (AccountUtil.hasUsername(userName)) {
			System.out.println("The account is exist! "+userName);
			try {
				writer.write("331\r\n");
				writer.flush();
				userInfo.setUsername(userName);
				//reader.readLine();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("The account is not exist! "+userName);
			try {
				writer.write("501\r\n");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

}
