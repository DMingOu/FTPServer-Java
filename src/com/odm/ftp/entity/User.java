package com.odm.ftp.entity;
import java.net.Socket;

/**
 * @ClassName: User
 * @Auther: DMingO
 * @Date: 2020/6/11 08:55
 * @Description: 用户实体数据类
 */
public class User {

	//用户密码
	private String password;
	//用户名
	private String username;
	//用户的端口
	private int port;

	private Socket socket;
	//用户的Ip地址
	private String ipAddress;

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}
