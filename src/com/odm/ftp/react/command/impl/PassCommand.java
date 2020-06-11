package com.odm.ftp.react.command.impl;


import com.odm.ftp.entity.User;
import com.odm.ftp.base.BaseCommand;
import com.odm.ftp.utils.AccountUtil;
import com.odm.ftp.utils.Md5Util;

import java.io.BufferedWriter;
import java.io.IOException;

public class PassCommand extends BaseCommand {

	/**
	 * √‹¬Î—È÷§
	 * @param passWord
	 * @param writer
	 * @param userInfo
	 */
	@Override
	public void execute(String passWord, BufferedWriter writer, User userInfo) {
		String passWord2Md5 = Md5Util.encoder(passWord);
		System.out.println("passWord2Md5 :  " + passWord2Md5);
		System.out.println("util ¥Ê¥¢√‹¬Î : " + AccountUtil.getPassword(userInfo.getUsername()));

		if (passWord2Md5.equals(AccountUtil.getPassword(userInfo.getUsername())) ||
			(AccountUtil.hasUsername(userInfo.getUsername()) && passWord.equals(AccountUtil.getPassword(userInfo.getUsername())))
		) {
			System.out.println("√‹¬Î’˝»∑"+passWord + ",md5√‹¬Î£∫"+passWord2Md5);
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
