package com.odm.ftp.react.command;

import com.odm.ftp.base.BaseCommand;
import com.odm.ftp.react.command.impl.*;

public class CommandFactory {

	public static BaseCommand parseCommand(String name) {
		BaseCommand command  = null;
		switch(name){
			//处理用户登录状态
			case "USER" :
				command = new LoginCommand();
				break;
			//处理用户的密码
			case "PASS" :
				command = new PassCommand();
				break;
			case "LIST" :
				command = new ListCommand();
				break;
			//存储，上传到FTP服务器
			case "STOR" :
        	    command = new UploadCommand();
        	    break;
        	//重试操作
			case "RETR" :
				command = new RetryCommand();
				break;
			case "PORT" :
				command = new PortCommand();
				break;
			//退出连接FTP服务器
			case "QUIT" :
				command = new QuitCommand();
				break;
			default:
				break;
		}
		return command;
	}
}
