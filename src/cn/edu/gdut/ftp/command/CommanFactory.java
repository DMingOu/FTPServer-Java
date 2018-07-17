package cn.edu.gdut.ftp.command;

import cn.edu.gdut.ftp.command.impl.*;

public class CommanFactory {

	public static BaseCommand parseCommand(String name) {
		BaseCommand command  = null;
		switch(name){
		case "USER" :
			command = new UserCommmand();
			break;
		case "PASS" :
			command = new PassCommand();
			break;
		case "RETR" :
			command = new RetrCommand();
			break;
		case "PORT" :
			command = new PortCommand();
			break;
		case "LIST" :
			command = new ListCommand();
			break;
		case "STOR" :
            command = new UploadCommand();
            break;
		case "QUIT" :
			command = new QuitCommand();
			break;
		}
		return command;		
	}
}
