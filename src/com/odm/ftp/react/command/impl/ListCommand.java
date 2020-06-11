package com.odm.ftp.react.command.impl;

import com.odm.ftp.entity.User;
import com.odm.ftp.base.BaseCommand;
import com.odm.ftp.utils.AccountUtil;
import com.odm.ftp.utils.FileUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ListCommand extends BaseCommand {


	/**
	 * @Author DMingO
	 * @Description 列表指令
	 * @Date  2020/6/11 12:04
	 * @Param [content, writer, userInfo]
	 * @return void
	 **/
	@Override
	public void execute(String content, BufferedWriter writer, User userInfo) {
		File file = new File(AccountUtil.getRootPath());
		if (!file.isDirectory()) {
			try {
				writer.write("返回码  210 文件不存在\r\n");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			//拼接文件目录字符串 StringBuffer是线程安全的，而StringBuilder 是线程不安全的
			StringBuffer dirList = new StringBuffer();
			int count = 1;
			for(String item:file.list()){
				File itemFile = new File(file+File.separator+item);
				String size = FileUtil.getFileSize(itemFile);
				if (size.equals("")) {
					size = "dir";
				}else{
					size += "	file";
				}
				dirList.append(count+"	"+item+"	"+size);
				dirList.append("\r\n");
				count++;
			}
			try {
				//告知客户端：服务器向另外一个端口发送数据
				writer.write("150 open ascii mode...\r\n");
				writer.flush();
				//与客户端发来的ip和端口号连接,自身端口设置为20
				Socket socket = new Socket(userInfo.getIpAddress(), userInfo.getPort(),null,20);
				System.out.println(socket.getLocalPort());
				BufferedWriter portWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"GBK"));
				portWriter.write(dirList.toString());
				portWriter.flush();
				socket.close();
				writer.write("返回码  200 传输完毕...\r\n");
				writer.flush();
				System.out.println(dirList.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
