package com.odm.ftp.react.command.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import com.odm.ftp.entity.User;
import com.odm.ftp.base.BaseCommand;
import com.odm.ftp.utils.AccountManager;
import com.odm.ftp.utils.LogUtil;

/**
 * @ClassName: LoginCommand
 * @Auther: DMingO
 * @Date: 2020/6/11 15:48
 * @Description: 处理用户登录指令 USER
 */
public class LoginCommand extends BaseCommand {

	private BufferedReader reader;

	@Override
	public void execute(String userName, BufferedWriter writer, User user) {
/*		if (AccountUtil.hasUsername(userName)) {
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
			LogUtil.warn("此账号未存在记录 ： "+userName);
			try {
				//此用户未注册，返回501
				writer.write("501\n\r\n");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/

		//如果 用户名 有记录的说明是登录操作，需要验证密码
		if(AccountManager.hasUsername(userName)){
			LogUtil.info(userName+ "   -----系统已有记录");
			try {
				writer.write("331 , Please continue to enter your password\r\n");
				writer.flush();
				user.setUsername(userName);
				//reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else{
			//用户名 没有记录，说明是 注册操作，需要获取新注册用户的密码
			LogUtil.info(userName + "   -----系统没有记录，判断是新用户");
			try {
				//此处返回客户端信息，需有状态码开头，否则出现登录失败
				writer.write("331 , Welcome New User! Please set up a password for your account\r\n");
				writer.flush();
				user.setUsername(userName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	}
	

}
