package com.odm.ftp.react;

import com.odm.ftp.entity.User;
import com.odm.ftp.base.BaseCommand;
import com.odm.ftp.react.command.factory.CommandFactory;
import com.odm.ftp.utils.LogUtil;

import java.io.*;
import java.net.Socket;

/**
 * @ClassName: ClientHandler
 * @Auther: DMingO
 * @Date: 2020/6/11 08:45
 * @Description: 客户端连接交互类
 */
public class ClientHandler implements Runnable {

	private Socket socket;

	public ClientHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "GBK"));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "GBK"));

			User user = new User();
			user.setSocket(socket);
			writer.write("返回码  200\r\n");
			writer.flush();
			reader.readLine();
			writer.write("请输入你的账号名 \r\n");
			writer.flush();

			while (true) {
				if (! socket.isClosed()) {
					String result = null;
					try {
						result = reader.readLine();
					} catch (Exception e) {
						System.out.println("客户端强制关闭了与服务器的连接 ");
					}
					System.out.println("接收到客户端的信息  " + result);

					if (result != null && !result.equals("")) {
						String[] content = result.split(" ");
						LogUtil.info("当前指令:  " + content[0] );
						BaseCommand command = CommandFactory.handleCommand(content[0]);
						if (command != null) {
							//执行对应的指令操作
							if (content.length == 1) {
								command.execute("", writer, user);
							} else {
								command.execute(content[1], writer, user);
							}
						} else {
							LogUtil.error("用户登录密码错误 500 ");
							writer.write("500 Wrong Command！");
							writer.flush();
						}
					} else {
						//关闭资源
						reader.close();
						writer.close();
						socket.close();
						break;
					}

				} else {
					//socket关闭则连接失效，退出循环
					LogUtil.warn("socket已关闭，连接关闭");
					break;
				}
			}

		} catch (IOException e) {
			LogUtil.error(e.getCause());
			e.printStackTrace();
		}

	}

}
