package com.odm.ftp.react.command.impl;


import com.odm.ftp.entity.User;
import com.odm.ftp.base.BaseCommand;
import com.odm.ftp.utils.AccountUtil;
import com.odm.ftp.utils.Md5Util;

import java.io.BufferedWriter;
import java.io.IOException;

public class PassCommand extends BaseCommand {

	/**
	 * @Author DMingO
	 * @Description  密码验证
	 * @Date  2020/6/11 12:00
	 * @Param [passWord, writer, user]
	 * @return void
	 **/
	@Override
	public void execute(String passWord, BufferedWriter writer, User user) {
		String passWord2Md5 = Md5Util.encoder(passWord);
		System.out.println("passWord2Md5 :  " + passWord2Md5);
		System.out.println("util 存储密码 : " + AccountUtil.getPassword(user.getUsername()));

		if (passWord2Md5.equals(AccountUtil.getPassword(user.getUsername())  ) ||
			(AccountUtil.hasUsername(user.getUsername()) && passWord.equals(AccountUtil.getPassword(user.getUsername())))
		) {
			System.out.println("密码正确"+passWord + ",对应md5密码："+passWord2Md5);
			try {
				//230前面不能有其他字符否则会触发系统消息 登录失败。
				writer.write("230 , Your passWord is passed!Welcome to use FTP-Server!\r\n");
				writer.flush();
				user.setPassword(passWord2Md5);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				writer.write("530 Your passWord isn't passed , please input the correct password !\r\n");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
