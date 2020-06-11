package com.odm.ftp.react.command.impl;


import com.odm.ftp.entity.User;
import com.odm.ftp.base.BaseCommand;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * @ClassName: PortCommand
 * @Auther: DMingO
 * @Date: 2020/6/11 15:45
 * @Description: 处理端口指令 PORT
 */
public class PortCommand extends BaseCommand {

	/**
	 * @Author DMingO
	 * @Description 端口号、ip处理
	 * @Date  2020/6/11 9:58
	 * @Param [content, writer, user ]  content： 接收的来自客户端的信息
	 * @return void
	 **/
	@Override
	public void execute(String content, BufferedWriter writer, User user) {
		//处理接收的来自客户端的信息
		String[] result = content.split(",");

		String ipAddress = result[0]+"."+result[1]+"."+result[2]+"."+result[3];
		//获取请求的IP地址
		System.out.println(user.getUsername() +  "  Ip:  " + ipAddress);
		//获取请求的端口
		int port = Integer.parseInt(result[4])*256+Integer.parseInt(result[5]);
		System.out.println(user.getUsername() +  "  port: " + port);
		try {
			user.setIpAddress(ipAddress);
			user.setPort(port);
			//此处写入必须为200 Accept the id and port 或 200，否则会导致无法继续接受客户端的信息
			writer.write("200 Accept the id and port\r\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
