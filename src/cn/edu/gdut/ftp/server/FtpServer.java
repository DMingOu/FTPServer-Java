package cn.edu.gdut.ftp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import cn.edu.gdut.ftp.utils.AccountUtil;
import cn.edu.gdut.ftp.utils.ThreadUtil;

/**
 * ��ʼ��ftp�����������ܿͻ��˵�����
 * @author
 *
 */
public class FtpServer {
	
	private ServerSocket serverSocket;

	private FtpServer(int port){
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
		FtpServer ftpServer = new FtpServer(21);
		ftpServer.listen();
	}
	
}
