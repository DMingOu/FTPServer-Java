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
	 * @Description �б�ָ��
	 * @Date  2020/6/11 12:04
	 * @Param [content, writer, userInfo]
	 * @return void
	 **/
	@Override
	public void execute(String content, BufferedWriter writer, User userInfo) {
		File file = new File(AccountUtil.getRootPath());
		if (!file.isDirectory()) {
			try {
				writer.write("������  210 �ļ�������\r\n");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			//ƴ���ļ�Ŀ¼�ַ��� StringBuffer���̰߳�ȫ�ģ���StringBuilder ���̲߳���ȫ��
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
				//��֪�ͻ��ˣ�������������һ���˿ڷ�������
				writer.write("150 open ascii mode...\r\n");
				writer.flush();
				//��ͻ��˷�����ip�Ͷ˿ں�����,����˿�����Ϊ20
				Socket socket = new Socket(userInfo.getIpAddress(), userInfo.getPort(),null,20);
				System.out.println(socket.getLocalPort());
				BufferedWriter portWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"GBK"));
				portWriter.write(dirList.toString());
				portWriter.flush();
				socket.close();
				writer.write("������  200 �������...\r\n");
				writer.flush();
				System.out.println(dirList.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
