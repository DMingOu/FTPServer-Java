package cn.edu.gdut.ftp.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import cn.edu.gdut.ftp.bean.UserInfo;
import cn.edu.gdut.ftp.command.BaseCommand;
import cn.edu.gdut.ftp.command.CommanFactory;

public class ClientConnection implements Runnable {

	private Socket socket;
	private BufferedWriter writer;
	private BufferedReader reader;

	public ClientConnection(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"GBK"));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"GBK"));

			// Ϊ����command������Ϣ
			UserInfo userInfo = new UserInfo();
			userInfo.setSocket(socket);
			// ��һ�ν�������
			writer.write("220\r\n");
			writer.flush();
			reader.readLine();
			writer.write("Please enter your account! \r\n");
			writer.flush();

			// ���ܿͻ��˷�������Ϣ
			while (true) {

				if (!socket.isClosed()) {
					String result = null;
					try {
						result = reader.readLine();
					} catch (Exception e) {
						System.out.println("Client forcibly closed the server! ");
					}
					System.out.println(result);// ��ӡ���ͻ��˷��͵�����
					if (result != null && result != "") {
						String[] datas = result.split(" ");
						BaseCommand command = CommanFactory.parseCommand(datas[0]);
						if (command != null) {
							// ���ͻ��˷���������ֻ������û�к��������ʱ��
							if (datas.length == 1) {
								command.executeCommand("", writer, userInfo);
							} else {
								command.executeCommand(datas[1], writer, userInfo);
							}
						} else {
							writer.write("There is no such command! ");
							writer.flush();
						}


					} else {
						// ��ʱ�ͻ���ǿ�йر�������
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
