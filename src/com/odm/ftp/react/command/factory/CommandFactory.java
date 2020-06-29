package com.odm.ftp.react.command.factory;

import com.odm.ftp.base.BaseCommand;
import com.odm.ftp.react.command.executor.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: CommandFactory
 * @Auther: DMingO
 * @Date: 2020/6/14 09:15
 * @Description: ���ض�Ӧ��Command����
 */
public final class CommandFactory {

	//�洢�Ѿ�ʹ�ù���ָ��ʵ��
	public static Map<String , BaseCommand> commandMap = new HashMap<>();

	private CommandFactory(){
		throw new IllegalStateException("���ɳ�ʼ��CommandFactoryʵ��");
	}

	/**
	 * @Author DMingO
	 * @Description ����ָ�����ַ��ض�Ӧ��ָ��
	 * @Date  2020/6/11 09:40
	 * @Param [command_name] ָ���������
	 * @return com.odm.ftp.base.BaseCommand ָ����ʵ��
	 **/
	public static BaseCommand handleCommand(String command_name) {
		BaseCommand command  = null;
		if(commandMap.containsKey(command_name)){
			command = commandMap.get(command_name);
		}else {
			switch(command_name){
				//�����û���¼״̬
				case CommandTable.USER :
					command = new UserCommand();
					break;
				//�����û�������
				case CommandTable.PASSWORD  :
					command = new PassCommand();
					break;
				//����˿�����
				case CommandTable.PORT :
					command = new PortCommand();
					break;
				//չʾ�ļ�Ŀ¼
				case CommandTable.LIST :
					command = new DirCommand();
					break;
				//�洢���ϴ���FTP������  putָ��
				case CommandTable.UPLOAD :
					command =  UploadCommand.getInstance();
					break;
				//�ӷ�����Ŀ¼�����ļ����ͻ��˵�ǰĿ¼ ����  getָ��
				case CommandTable.DOWNLOAD :
					command =  DownloadCommand.getInstance();
					break;
				//�ڷ�����Ŀ¼�ϴ����ļ��� mkdir file
				case CommandTable.MAKE_DIR :
					command =  CreateNewFileCommand.getInstance();
					break;
				default:
					break;
				//�˳�����FTP������
				case CommandTable.QUIT :
					command = new QuitCommand();
					break;

			}
			//�����δ�����command�洢������һ��ʹ��
			if(command != null){
				commandMap.put(command_name,command);
			}

		}
		
		//���� ��Ӧ�� command
		return command;
	}
}
