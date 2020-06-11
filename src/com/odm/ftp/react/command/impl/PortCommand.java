package com.odm.ftp.react.command.impl;


import com.odm.ftp.entity.User;
import com.odm.ftp.base.BaseCommand;

import java.io.BufferedWriter;
import java.io.IOException;

public class PortCommand extends BaseCommand {

	/**
	 * @Author DMingO
	 * @Description 端口号、ip处理
	 * @Date  2020/6/11 9:58
	 * @Param [content, writer, userInfo]
	 * @return void
	 **/
	@Override
	public void execute(String content, BufferedWriter writer, User userInfo) {
		String[] result = content.split(",");
		String ip = result[0]+"."+result[1]+"."+result[2]+"."+result[3];
		System.out.println("ip:  "+ip);
		int port = Integer.parseInt(result[4])*256+Integer.parseInt(result[5]);
		System.out.println("port:"+port);
		try {
			//此处写入必须为200 Accept the id and port 或 200，否则会导致无法继续接受客户端的信息
			writer.write("200 Accept the id and port\r\n");
			writer.flush();
			userInfo.setIpAddress(ip);
			userInfo.setPort(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
