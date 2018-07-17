package cn.edu.gdut.ftp.command.impl;

import java.io.BufferedWriter;
import java.io.IOException;

import cn.edu.gdut.ftp.bean.UserInfo;
import cn.edu.gdut.ftp.command.BaseCommand;

public class QuitCommand implements BaseCommand{

	/**
	 * Àë¿ªftp·þÎñ
	 * @param datas
	 * @param writer
	 * @param userInfo
	 */
	@Override
	public void executeCommand(String datas, BufferedWriter writer, UserInfo userInfo) {
		try {
			writer.write("221 GoodBye\r\n");
			writer.flush();
			userInfo.getSocket().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
