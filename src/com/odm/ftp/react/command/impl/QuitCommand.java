package com.odm.ftp.react.command.impl;
import com.odm.ftp.entity.User;
import com.odm.ftp.base.BaseCommand;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * @ClassName: QuitCommand
 * @Auther: DMingO
 * @Date: 2020/6/11 15:48
 * @Description: 处理退出指令 QUIT
 */
public class QuitCommand extends BaseCommand {

	/**
	 * @Author DMingO
	 * @Description  断开与FTP服务器建立的连接
	 * @Date  2020/6/11 15:13
	 * @Param [content, writer, user]
	 * @return void
	 **/
	@Override
	public void execute(String content, BufferedWriter writer, User user) {
		try {
			writer.write("221 Disconnect from the FTP-Server\r\n");
			writer.flush();
			user.getSocket().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
