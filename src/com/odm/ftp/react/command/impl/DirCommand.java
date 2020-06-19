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
 * @Description: չʾ�ļ��б���ָ�� LIST
 */
public class DirCommand extends BaseCommand {

	/**
	 * @Author DMingO
	 * @Description չʾ�б�ָ��
	 * @Date  2020/6/11 12:04
	 * @Param [content, writer, user]
	 * @return void
	 **/
	@Override
	public void execute(String content, BufferedWriter writer, User user) {
		File file = new File(AccountManager.getRootPath());
		//�ж��ļ����Ƿ����
		if (! file.isDirectory()) {
			try {
				writer.write("210 �ļ��в�����\r\n");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			//ƴ���ļ�Ŀ¼�ַ��� StringBuffer���̰߳�ȫ�ģ���StringBuilder ���̲߳���ȫ��
			StringBuffer dirList = new StringBuffer();
			dirList.append("����ΪԶ���ļ�Ŀ¼��չʾ�� ��").append("\r\n");
			dirList.append("���").append("					")
					.append("�ļ���").append("					")
					.append("�ļ�����/�ļ���С").append("					")
					.append("\r\n");
			//�ļ����
			int index = 1;
			for(String item: Objects.requireNonNull(file.list())){
				File itemFile = new File(file + File.separator + item);
				String size = FileUtil.getFileSize(itemFile);
				dirList.append(index).append("	").append(item).append("	");
				if (size.equals("")) {
					//����Ϊ�ļ���
					dirList.append("    ").append("�ļ���");
				}else{
					//�ļ���С + �ļ�
					dirList.append("�����ļ�").append("     ").append(size);
				}
				dirList.append("\r\n\n");
				//���+1
				index++;
			}
			try {
				//��֪�ͻ��ˣ�������������һ���˿ڷ�������
				writer.write("150 open ascii mode...\r\n");
				writer.flush();
				//��ͻ��˷�����ip�Ͷ˿ں�����,����˿�����Ϊ20
				Socket socket = new Socket(user.getIpAddress(), user.getPort(),null,20);
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
