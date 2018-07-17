package cn.edu.gdut.ftp.command.impl;

import java.io.BufferedWriter;
import java.io.IOException;

import cn.edu.gdut.ftp.bean.UserInfo;
import cn.edu.gdut.ftp.command.BaseCommand;

public class PortCommand implements BaseCommand{

	/**
	 * ¶Ë¿ÚºÅ¡¢ip´¦Àí
	 * @param datas
	 * @param writer
	 * @param userInfo
	 */
	@Override
	public void executeCommand(String datas, BufferedWriter writer,UserInfo userInfo) {
		String[] result = datas.split(",");
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
