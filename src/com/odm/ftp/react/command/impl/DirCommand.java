package com.odm.ftp.react.command.impl;

import com.odm.ftp.entity.User;
import com.odm.ftp.base.BaseCommand;
import com.odm.ftp.utils.AccountManager;
import com.odm.ftp.utils.FileUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Objects;

/**
 * @ClassName: DirCommand
 * @Auther: DMingO
 * @Date: 2020/6/11 15:45
 * @Description: 展示文件列表处理指令 LIST
 */
public class DirCommand extends BaseCommand {

	/**
	 * @Author DMingO
	 * @Description 展示列表指令
	 * @Date  2020/6/11 12:04
	 * @Param [content, writer, user]
	 * @return void
	 **/
	@Override
	public void execute(String content, BufferedWriter writer, User user) {
		File file = new File(AccountManager.getRootPath());
		//判断文件夹是否存在
		if (! file.isDirectory()) {
			try {
				writer.write("210 文件夹不存在\r\n");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			//拼接文件目录字符串 StringBuffer是线程安全的，而StringBuilder 是线程不安全的
			StringBuffer dirList = new StringBuffer();
			dirList.append("以下为远程文件目录的展示： ↓").append("\r\n");
			dirList.append("序号").append("					")
					.append("文件名").append("					")
					.append("文件类型/文件大小").append("					")
					.append("\r\n");
			//文件序号
			int index = 1;
			for(String item: Objects.requireNonNull(file.list())){
				File itemFile = new File(file + File.separator + item);
				String size = FileUtil.getFileSize(itemFile);
				dirList.append(index).append("	").append(item).append("	");
				if (size.equals("")) {
					//类型为文件夹
					dirList.append("    ").append("文件夹");
				}else{
					//文件大小 + 文件
					dirList.append("具体文件").append("     ").append(size);
				}
				dirList.append("\r\n\n");
				//序号+1
				index++;
			}
			try {
				//告知客户端：服务器向另外一个端口发送数据
				writer.write("150 open ascii mode...\r\n");
				writer.flush();
				//与客户端发来的ip和端口号连接,自身端口设置为20
				Socket socket = new Socket(user.getIpAddress(), user.getPort(),null,20);
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
