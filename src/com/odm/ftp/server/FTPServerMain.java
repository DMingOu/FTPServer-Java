package com.odm.ftp.server;

import com.odm.ftp.react.ClientConnection;
import com.odm.ftp.utils.AccountUtil;
import com.odm.ftp.utils.ThreadUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName: FTPServerMain
 * @Auther: DMingO
 * @Date: 2020/6/11 08:50
 * @Description: ��ʼ��ftp�����������ܿͻ��˵�����
 */
public class FTPServerMain {
	
	private ServerSocket serverSocket;

	private FTPServerMain(int port){
		try {
			serverSocket = new ServerSocket(port);
			System.out.println(serverSocket);

		} catch (IOException e) {
			e.printStackTrace();
		}
		//�˷���Ϊ��ʼ�����Խ�����ʺ�����
		AccountUtil.initAccount();
	}
	
	private void listen() {
		try {
			while(true){
				Socket socket = serverSocket.accept();
				ClientConnection connection = new ClientConnection(socket);
				ThreadUtil.getThreadPool().execute(connection);
			}
		} catch (IOException e) {
    			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		FTPServerMain ftpServer = new FTPServerMain(21);
		ftpServer.listen();
	}
	
}
