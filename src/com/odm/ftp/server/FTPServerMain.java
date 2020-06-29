package com.odm.ftp.server;

import com.odm.ftp.base.BaseServer;
import com.odm.ftp.react.ClientHandler;
import com.odm.ftp.utils.AccountManager;
import com.odm.ftp.utils.LogUtil;
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
			LogUtil.info(serverSocket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//�˷���Ϊ��ʼ�����Խ�����ʺ�����
		AccountManager.initAccount();
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

	@Override
	protected void listen() {
        System.out.println("----------------------Start Listen---------------------");
		int clientIndex  = 1;
		//ѭ�����ն���ͻ��˵���������
		while (true){
			Socket socket = null;
			try {
				socket = serverSocket.accept();
				System.out.println("��������   " + clientIndex + "  ���ͻ��˳������ӣ�����");
				ClientHandler connection = new ClientHandler(socket);
				ThreadUtil.getThreadPool().execute(connection);
				clientIndex++;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	
}
