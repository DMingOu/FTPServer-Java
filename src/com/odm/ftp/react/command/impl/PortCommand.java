package com.odm.ftp.react.command.impl;


import com.odm.ftp.entity.User;
import com.odm.ftp.base.BaseCommand;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * @ClassName: PortCommand
 * @Auther: DMingO
 * @Date: 2020/6/11 15:45
 * @Description: ����˿�ָ�� PORT
 */
public class PortCommand extends BaseCommand {

	/**
	 * @Author DMingO
	 * @Description �˿ںš�ip����
	 * @Date  2020/6/11 9:58
	 * @Param [content, writer, user ]  content�� ���յ����Կͻ��˵���Ϣ
	 * @return void
	 **/
	@Override
	public void execute(String content, BufferedWriter writer, User user) {
		//������յ����Կͻ��˵���Ϣ
		String[] result = content.split(",");

		String ipAddress = result[0]+"."+result[1]+"."+result[2]+"."+result[3];
		//��ȡ�����IP��ַ
		System.out.println(user.getUsername() +  "  Ip:  " + ipAddress);
		//��ȡ����Ķ˿�
		int port = Integer.parseInt(result[4])*256+Integer.parseInt(result[5]);
		System.out.println(user.getUsername() +  "  port: " + port);
		try {
			user.setIpAddress(ipAddress);
			user.setPort(port);
			//�˴�д�����Ϊ200 Accept the id and port �� 200������ᵼ���޷��������ܿͻ��˵���Ϣ
			writer.write("200 Accept the id and port\r\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
