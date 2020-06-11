package com.odm.ftp.react.command.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import com.odm.ftp.entity.User;
import com.odm.ftp.base.BaseCommand;
import com.odm.ftp.utils.AccountUtil;

public class LoginCommand extends BaseCommand {

	private BufferedReader reader;

	@Override
	public void execute(String userName, BufferedWriter writer, User userInfo) {
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
