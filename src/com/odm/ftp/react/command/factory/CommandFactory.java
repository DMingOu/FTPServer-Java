package com.odm.ftp.react.command.factory;

import com.odm.ftp.base.BaseCommand;
import com.odm.ftp.react.command.executor.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: CommandFactory
 * @Auther: DMingO
 * @Date: 2020/6/14 09:15
 * @Description: 返回对应的Command对象
 */
public class CommandFactory {

	//存储已经使用过的指令对象
	public static Map<String , BaseCommand> commandMap = new HashMap<>();

	private CommandFactory(){
		throw new IllegalStateException("不可初始化CommandFactory实例");
	}


	/**
	 * @Author DMingO
	 * @Description 根据指令名字返回对应的指令
	 * @Date  2020/6/11 09:40
	 * @Param [command_name] 指令具体名字
	 * @return com.odm.ftp.base.BaseCommand 指令类实例
	 **/
	public static BaseCommand handleCommand(String command_name) {
		BaseCommand command  = null;
		if(commandMap.containsKey(command_name)){
			command = commandMap.get(command_name);
		}else {
			switch(command_name){
				//处理用户登录状态
				case CommandTable.USER :
					command = new LoginCommand();
					break;
				//处理用户的密码
				case CommandTable.PASSWORD  :
					command = new PassCommand();
					break;
				//处理端口命令
				case CommandTable.PORT :
					command = new PortCommand();
					break;
				//展示文件目录
				case CommandTable.LIST :
					command = new DirCommand();
					break;
				//存储，上传到FTP服务器  put指令
				case CommandTable.UPLOAD :
					command = new UploadCommand();
					break;
				//从服务器目录下载文件到客户端当前目录 操作  get指令
				case CommandTable.DOWNLOAD :
					command = new DownloadCommand();
					break;
				//在服务器目录上创建文件夹 mkdir file
				case CommandTable.MAKE_DIR :
					command = new CreateNewFileCommand();
					break;
				default:
					break;
				//退出连接FTP服务器
				case CommandTable.QUIT :
					command = new QuitCommand();
					break;

			}
			//将初次创建的command存储，给下一次使用
			if(command != null){
				commandMap.put(command_name,command);
			}

		}
		
		//返回 对应的 command
		return command;
	}
}
