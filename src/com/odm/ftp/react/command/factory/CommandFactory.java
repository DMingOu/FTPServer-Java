package com.odm.ftp.react.command.factory;

import com.odm.ftp.base.BaseCommand;
import com.odm.ftp.react.command.impl.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: CommandFactory
 * @Auther: DMingO
 * @Date: 2020/6/11 09:15
 * @Description: ��ʼ��ftp�����������ܿͻ��˵�����
 */
public class CommandFactory {

	public static Set<String> CommandSet = new HashSet<>();

	private CommandFactory(){
		throw new IllegalStateException("���ɳ�ʼ��CommandFactoryʵ��");
	}


	/**
	 * @Author DMingO
	 * @Description ����ָ�����ַ��ض�Ӧ��ָ��
	 * @Date  2020/6/11 09:40
	 * @Param [name] ָ���������
	 * @return com.odm.ftp.base.BaseCommand ָ����ʵ��
	 **/
	public static BaseCommand handleCommand(String name) {
		BaseCommand command  = null;
		switch(name){
			//�����û���¼״̬
			case "USER" :
				command = new LoginCommand();
				break;
			//�����û�������
			case "PASS" :
				command = new PassCommand();
				break;
			case "LIST" :
				command = new DirCommand();
				break;
			//�洢���ϴ���FTP������  putָ��
			case "STOR" :
        	    command = new UploadCommand();
        	    break;
        	//�ӷ�����Ŀ¼�����ļ����ͻ��˵�ǰĿ¼ ����  getָ��
			case "RETR" :
				command = new DownloadCommand();
				break;
			case "PORT" :
				command = new PortCommand();
				break;
			//�˳�����FTP������
			case "QUIT" :
				command = new QuitCommand();
				break;
			//�ڷ�����Ŀ¼�ϴ����ļ��� mkdir file
			case "XMKD" :
				break;
			default:
				break;
		}
		return command;
	}
}
