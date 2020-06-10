package com.odm.ftp.react.command.impl;


import com.odm.ftp.bean.User;
import com.odm.ftp.base.BaseCommand;
import com.odm.ftp.utils.AccountUtil;

import java.io.*;
import java.net.Socket;
import java.util.Calendar;

/**
 * �ļ�����
 */
public class RetryCommand extends BaseCommand {


	@Override
	public void execute(String content, BufferedWriter writer, User userInfo) {
		File file = new File(AccountUtil.getRootPath()+File.separator+content);
		File rootFile = new File(System.getProperty("user.home")+File.separator);
		File lastFileName = new File(System.getProperty("user.home")+File.separator+content);
		String name = System.getProperty("user.home")+"\\"+content;
		if (file.exists()) {
			for (String item:rootFile.list()){
				item = System.getProperty("user.home")+"\\"+item;
				//��ȡ��ͬ�ļ���
				if (item.equals(name)){
					//���ļ����ĳ�ʱ���
					File temp = new File(System.getProperty("user.home")+"\\"+ Calendar.getInstance().getTimeInMillis()+"."+item.substring(item.lastIndexOf(".")+1));
					lastFileName.renameTo(temp);

				}
			}
			try {
				writer.write("150 open ascii mode...\r\n");
				writer.flush();

				//��ʼ����
				Socket socket = new Socket(userInfo.getIp(),userInfo.getPort(),null,20);
				OutputStream outputStream = socket.getOutputStream();
				FileInputStream inputStream = new FileInputStream(file);
				int length = 0;
				byte[] buff = new byte[1024];
				while((length = inputStream.read(buff))!=-1){
					outputStream.write(buff, 0, length);
				}
				//�������
				inputStream.close();
				outputStream.close();
				socket.close();
				
				writer.write("220 transfer complete...\r\n");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else{
			try {
				writer.write("220  The file is not exist!\r\n");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
