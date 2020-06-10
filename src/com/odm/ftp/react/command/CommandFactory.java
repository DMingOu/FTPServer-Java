package com.odm.ftp.react.command;

import com.odm.ftp.base.BaseCommand;
import com.odm.ftp.react.command.impl.*;

public class CommandFactory {

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
