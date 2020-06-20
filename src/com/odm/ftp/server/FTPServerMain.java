package com.odm.ftp.server;

import com.odm.ftp.base.BaseServer;
import com.odm.ftp.react.ClientHandler;
import com.odm.ftp.utils.AccountManager;
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
public class FTPServerMain extends BaseServer {
	
	private ServerSocket serverSocket;

	private FTPServerMain(int port){
		try {
			serverSocket = new ServerSocket(port);
			System.out.println(serverSocket);

		} catch (IOException e) {
			e.printStackTrace();
		}
		//�˷���Ϊ��ʼ�����Խ�����ʺ�����
		AccountManager.initAccount();
	}

	@Override
	protected void listen() {
			try {
				System.out.println("-------------------Start Listen-------------------");
				Socket socket = serverSocket.accept();
				ClientHandler connection = new ClientHandler(socket);
				ThreadUtil.getThreadPool().execute(connection);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	/*
	 * @Author DMingO
	 * @Description ���� FTP �����
	 * @Date  2020/6/19 17:17
	 * @Param [args]
	 * @return void
	 **/
	public static void main(String[] args) {
		//Ĭ�϶˿�Ϊ 21 �������ֿ���ʧ�ܣ�������ϵͳ�ط��Ѿ�ռ����21�˿�
		FTPServerMain ftpServer = new FTPServerMain(21);
		ftpServer.listen();
	}
	
}
