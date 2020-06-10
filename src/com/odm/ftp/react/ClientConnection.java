package com.odm.ftp.react;

import com.odm.ftp.bean.User;
import com.odm.ftp.base.BaseCommand;
import com.odm.ftp.react.command.CommandFactory;

import java.io.*;
import java.net.Socket;

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
			writer.write("220\r\n");
			writer.flush();
			reader.readLine();
			writer.write("Please enter your account! \r\n");
			writer.flush();

			while (true) {

				if (!socket.isClosed()) {
					String result = null;
					try {
						result = reader.readLine();
					} catch (Exception e) {
						System.out.println("Client forcibly closed the server! ");
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
							writer.write("There is no such command! ");
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
