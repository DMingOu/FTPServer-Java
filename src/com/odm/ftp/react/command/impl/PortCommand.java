package com.odm.ftp.react.command.impl;


import com.odm.ftp.entity.User;
import com.odm.ftp.base.BaseCommand;

import java.io.BufferedWriter;
import java.io.IOException;

public class PortCommand extends BaseCommand {

	/**
	 * 端口号、ip处理
	 * @param content
	 * @param writer
	 * @param userInfo
	 */
	@Override
	public void execute(String content, BufferedWriter writer, User userInfo) {
		String[] result = content.split(",");
		String ip = result[0]+"."+result[1]+"."+result[2]+"."+result[3];
		System.out.println("ip:  "+ip);
		int port = Integer.parseInt(result[4])*256+Integer.parseInt(result[5]);
		System.out.println("port:"+port);
		try {
			writer.write("返回码 200 ，允许 Ip 和 端口通过\r\n");
			writer.flush();
			userInfo.setIpAddress(ip);
			userInfo.setPort(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
