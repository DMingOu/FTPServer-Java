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
 * @Description: 初始化ftp服务器，接受客户端的连接
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
		//此方法为初始化可以接入的帐号密码
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
	 * @Description 启动 FTP 服务端
	 * @Date  2020/6/19 17:17
	 * @Param [args]
	 * @return void
	 **/
	public static void main(String[] args) {
		//默认端口为 21 ，若出现开启失败，可能是系统地方已经占用了21端口
		FTPServerMain ftpServer = new FTPServerMain(21);
		ftpServer.listen();
	}
	
}
