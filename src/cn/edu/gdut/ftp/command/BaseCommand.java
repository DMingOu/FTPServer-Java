package cn.edu.gdut.ftp.command;

import java.io.BufferedWriter;

import cn.edu.gdut.ftp.bean.UserInfo;

public interface BaseCommand {

	public void executeCommand(String datas, BufferedWriter writer, UserInfo userInfo);
}
