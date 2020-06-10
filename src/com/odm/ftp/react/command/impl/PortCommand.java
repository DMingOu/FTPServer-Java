package com.odm.ftp.react.command.impl;


import com.odm.ftp.bean.User;
import com.odm.ftp.base.BaseCommand;

import java.io.BufferedWriter;
import java.io.IOException;

public class PortCommand extends BaseCommand {

	/**
	 * ¶Ë¿ÚºÅ¡¢ip´¦Àí
	 * @param content
	 * @param writer
	 * @param userInfo
	 */
	@Override
	public void execute(String content, BufferedWriter writer, User userInfo) {
		String[] result = content.split(",");
		String ip = result[0]+"."+result[1]+"."+result[2]+"."+result[3];
		System.out.println("ip:"+ip);
		int port = Integer.parseInt(result[4])*256+Integer.parseInt(result[5]);
		System.out.println("port:"+port);
		try {
			writer.write("200 Accept the id and port\r\n");
			writer.flush();
			userInfo.setIp(ip);
			userInfo.setPort(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
