package com.odm.ftp.react.command.factory;

import com.odm.ftp.base.BaseCommand;
import com.odm.ftp.react.command.impl.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: CommandFactory
 * @Auther: DMingO
 * @Date: 2020/6/11 09:15
 * @Description: 初始化ftp服务器，接受客户端的连接
 */
public class CommandFactory {

	public static Set<String> CommandSet = new HashSet<>();

	private CommandFactory(){
		throw new IllegalStateException("不可初始化CommandFactory实例");
	}


	/**
	 * @Author DMingO
	 * @Description 根据指令名字返回对应的指令
	 * @Date  2020/6/11 09:40
	 * @Param [name] 指令具体名字
	 * @return com.odm.ftp.base.BaseCommand 指令类实例
	 **/
	public static BaseCommand handleCommand(String name) {
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
				command = new DirCommand();
				break;
			//存储，上传到FTP服务器  put指令
			case "STOR" :
        	    command = new UploadCommand();
        	    break;
        	//从服务器目录下载文件到客户端当前目录 操作  get指令
			case "RETR" :
				command = new DownloadCommand();
				break;
			case "PORT" :
				command = new PortCommand();
				break;
			//退出连接FTP服务器
			case "QUIT" :
				command = new QuitCommand();
				break;
			//在服务器目录上创建文件夹 mkdir file
			case "XMKD" :
				break;
			default:
				break;
		}
		return command;
	}
}
