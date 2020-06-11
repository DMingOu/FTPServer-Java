package com.odm.ftp.react.command.impl;


import com.odm.ftp.entity.User;
import com.odm.ftp.base.BaseCommand;
import com.odm.ftp.utils.AccountUtil;
import com.odm.ftp.utils.Md5Util;

import java.io.BufferedWriter;
import java.io.IOException;

public class PassCommand extends BaseCommand {

	/**
	 *
	 * @param passWord
	 * @param writer
	 * @param userInfo
	 */
	/**
	 * @Author DMingO
	 * @Description  ������֤
	 * @Date  2020/6/11 12:00
	 * @Param [passWord, writer, userInfo]
	 * @return void
	 **/
	@Override
	public void execute(String passWord, BufferedWriter writer, User userInfo) {
		String passWord2Md5 = Md5Util.encoder(passWord);
		System.out.println("passWord2Md5 :  " + passWord2Md5);
		System.out.println("util �洢���� : " + AccountUtil.getPassword(userInfo.getUsername()));

		if (passWord2Md5.equals(AccountUtil.getPassword(userInfo.getUsername())  ) ||
			(AccountUtil.hasUsername(userInfo.getUsername()) && passWord.equals(AccountUtil.getPassword(userInfo.getUsername())))
		) {
			System.out.println("������ȷ"+passWord + ",��Ӧmd5���룺"+passWord2Md5);
			try {
				//230ǰ�治���������ַ�����ᴥ��ϵͳ��Ϣ ��¼ʧ�ܡ�
				writer.write("230 , Your passWord is passed!Welcome to use FTP-Server!\r\n");
				writer.flush();
				userInfo.setPassword(passWord2Md5);
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
