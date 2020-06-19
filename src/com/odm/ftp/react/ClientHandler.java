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
 * @Description: �ͻ������ӽ�����
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
			writer.write("������  200\r\n");
			writer.flush();
			reader.readLine();
			writer.write("����������˺��� \r\n");
			writer.flush();

			while (true) {
				if (! socket.isClosed()) {
					String result = null;
					try {
						result = reader.readLine();
					} catch (Exception e) {
						System.out.println("�ͻ���ǿ�ƹر���������������� ");
					}
					System.out.println("���յ��ͻ��˵���Ϣ  " + result);

					if (result != null && !result.equals("")) {
						String[] content = result.split(" ");
						LogUtil.info("��ǰָ��:  " + content[0] );
						BaseCommand command = CommandFactory.handleCommand(content[0]);
						if (command != null) {
							//ִ�ж�Ӧ��ָ�����
							if (content.length == 1) {
								command.execute("", writer, user);
							} else {
								command.execute(content[1], writer, user);
							}
						} else {
							LogUtil.error("�û���¼������� 500 ");
							writer.write("500 Wrong Command��");
							writer.flush();
						}
					} else {
						//�ر���Դ
						reader.close();
						writer.close();
						socket.close();
						break;
					}

				} else {
					//socket�ر�������ʧЧ���˳�ѭ��
					LogUtil.warn("socket�ѹرգ����ӹر�");
					break;
				}
			}

		} catch (IOException e) {
			LogUtil.error(e.getCause());
			e.printStackTrace();
		}

	}

}
