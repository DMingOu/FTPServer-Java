package com.odm.ftp.base;

import com.odm.ftp.bean.User;

import java.io.BufferedWriter;

public abstract class BaseCommand {

	public abstract void execute(String content, BufferedWriter writer, User userInfo);

}
