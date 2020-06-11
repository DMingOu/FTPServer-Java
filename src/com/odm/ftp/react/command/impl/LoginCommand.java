package com.odm.ftp.react.command.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import com.odm.ftp.entity.User;
import com.odm.ftp.base.BaseCommand;
import com.odm.ftp.utils.AccountUtil;

/**
 * @Author DMingO
 * @Description �����¼ָ��  USER
 * @Date  2020/6/11 15:07
 * @Param
 * @return
 **/
public class LoginCommand extends BaseCommand {

	private BufferedReader reader;

	@Override
	public void execute(String userName, BufferedWriter writer, User user) {
		if (AccountUtil.hasUsername(userName)) {
			System.out.println("���˺��Ѵ��ڼ�¼ : " + userName);
			try {
				writer.write("331 , Please continue to enter your password\r\n");
				writer.flush();
				user.setUsername(userName);
				//reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("���˺�δ���ڼ�¼ �� "+userName);
			try {
				//���û�δע�ᣬ����501
				writer.write("501\n\r\n");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

}
