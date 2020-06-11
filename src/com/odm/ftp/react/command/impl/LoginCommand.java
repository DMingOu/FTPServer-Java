package com.odm.ftp.react.command.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import com.odm.ftp.entity.User;
import com.odm.ftp.base.BaseCommand;
import com.odm.ftp.utils.AccountUtil;

/**
 * @Author DMingO
 * @Description 处理登录指令  USER
 * @Date  2020/6/11 15:07
 * @Param
 * @return
 **/
public class LoginCommand extends BaseCommand {

	private BufferedReader reader;

	@Override
	public void execute(String userName, BufferedWriter writer, User user) {
		if (AccountUtil.hasUsername(userName)) {
			System.out.println("此账号已存在记录 : " + userName);
			try {
				writer.write("331 , Please continue to enter your password\r\n");
				writer.flush();
				user.setUsername(userName);
				//reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("此账号未存在记录 ： "+userName);
			try {
				//此用户未注册，返回501
				writer.write("501\n\r\n");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

}
