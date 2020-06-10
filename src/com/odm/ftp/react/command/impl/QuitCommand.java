package com.odm.ftp.react.command.impl;


import com.odm.ftp.bean.User;
import com.odm.ftp.base.BaseCommand;

import java.io.BufferedWriter;
import java.io.IOException;

public class QuitCommand extends BaseCommand {

	/**
	 * Àë¿ªftp·þÎñ
	 * @param content
	 * @param writer
	 * @param userInfo
	 */
	@Override
	public void execute(String content, BufferedWriter writer, User userInfo) {
		try {
			writer.write("221 GoodBye\r\n");
			writer.flush();
			userInfo.getSocket().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
