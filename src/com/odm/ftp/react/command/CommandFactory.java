package com.odm.ftp.react.command;

import com.odm.ftp.base.BaseCommand;
import com.odm.ftp.react.command.impl.*;

/**
 * @ClassName: CommandFactory
 * @Auther: DMingO
 * @Date: 2020/6/11 09:15
 * @Description: ��ʼ��ftp�����������ܿͻ��˵�����
 */
public class CommandFactory {

	/**
	 * @Author DMingO
	 * @Description ����ָ�����ַ��ض�Ӧ��ָ��
	 * @Date  2020/6/11 09:40
	 * @Param [name] ָ���������
	 * @return com.odm.ftp.base.BaseCommand ָ����ʵ��
	 **/
	public static BaseCommand parseCommand(String name) {
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
				command = new ListCommand();
				break;
			//�洢���ϴ���FTP������
			case "STOR" :
        	    command = new UploadCommand();
        	    break;
        	//���Բ���
			case "RETR" :
				command = new RetryCommand();
				break;
			case "PORT" :
				command = new PortCommand();
				break;
			//�˳�����FTP������
			case "QUIT" :
				command = new QuitCommand();
				break;
			default:
				break;
		}
		return command;
	}
}
