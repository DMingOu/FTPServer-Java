package com.odm.ftp.react.command;

import com.odm.ftp.base.BaseCommand;
import com.odm.ftp.react.command.impl.*;

/**
 * @ClassName: CommandFactory
 * @Auther: DMingO
 * @Date: 2020/6/11 09:15
 * @Description: 初始化ftp服务器，接受客户端的连接
 */
public class CommandFactory {

	/**
	 * @Author DMingO
	 * @Description 根据指令名字返回对应的指令
	 * @Date  2020/6/11 09:40
	 * @Param [name] 指令具体名字
	 * @return com.odm.ftp.base.BaseCommand 指令类实例
	 **/
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
