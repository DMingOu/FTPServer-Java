package com.odm.ftp.react;

import com.odm.ftp.entity.User;
import com.odm.ftp.base.BaseCommand;
import com.odm.ftp.react.command.CommandFactory;

import java.io.*;
import java.net.Socket;

/**
 * @ClassName: ClientConnection
 * @Auther: DMingO
 * @Date: 2020/6/11 08:45
 * @Description: 客户端连接交互类
 */
public class ClientConnection implements Runnable {

	private Socket socket;

	public ClientConnection(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "GBK"));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "GBK"));

			User userInfo = new User();
			userInfo.setSocket(socket);
			writer.write("返回码  200\r\n");
			writer.flush();
			reader.readLine();
			writer.write("请输入你的账号名 \r\n");
			writer.flush();

			while (true) {

				if (!socket.isClosed()) {
					String result = null;
					try {
						result = reader.readLine();
					} catch (Exception e) {
						System.out.println("客户端强制关闭了与服务器的连接 ");
					}
					System.out.println(result);
					if (result != null && !result.equals("")) {
						String[] content = result.split(" ");
						BaseCommand command = CommandFactory.parseCommand(content[0]);
						if (command != null) {
							//
							if (content.length == 1) {
								command.execute("", writer, userInfo);
							} else {
								command.execute(content[1], writer, userInfo);
							}
						} else {
							writer.write("当前输入指令不在指令列表中，请输入正确的指令");
							writer.flush();
						}


					} else {
						//
						reader.close();
						writer.close();
						socket.close();
						break;
					}

				} else {

					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
