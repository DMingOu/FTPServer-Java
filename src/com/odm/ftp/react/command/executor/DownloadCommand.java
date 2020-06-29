package com.odm.ftp.react.command.executor;
import com.odm.ftp.entity.User;
import com.odm.ftp.base.BaseCommand;
import com.odm.ftp.utils.AccountManager;

import java.io.*;
import java.net.Socket;
import java.util.Calendar;
import java.util.Objects;


/**
 * @ClassName: DownloadCommand
 * @Auther: DMingO
 * @Date: 2020/6/11 15:48md
 * @Description: �����û������ļ�ָ�� RETR
 */
public class DownloadCommand extends BaseCommand {

	private DownloadCommand() {
	}
	//��̬�ڲ��ഴ����̬���ⲿ��ʵ��
	private static class DownloadCommandInstance {
		private static final DownloadCommand INSTANCE = new DownloadCommand();
	}

	public static DownloadCommand getInstance(){
		return DownloadCommandInstance.INSTANCE;
	}


	@Override
	public synchronized void execute(String content, BufferedWriter writer, User user) {
		File file = new File(AccountManager.getRootPath()+File.separator+content);
		File rootFile = new File(System.getProperty("user.home")+File.separator);
		File oldFileName = new File(System.getProperty("user.home")+File.separator+content);
		String name = System.getProperty("user.home")+"\\"+content;
		if (file.exists()) {
			for (String item: Objects.requireNonNull(rootFile.list())){
				item = System.getProperty("user.home")+"\\"+item;
				//��ȡ��ͬ�ļ���
				if (item.equals(name)){
					//���ļ���Ϊʱ���
					File newFileName = new File(System.getProperty("user.home")+"\\"+ Calendar.getInstance().getTimeInMillis()+"."+item.substring(item.lastIndexOf(".")+1));
					if(oldFileName.renameTo(newFileName)) {
						System.out.println(oldFileName + "successfully renameTo  " + newFileName);
					}else {
						System.out.println(oldFileName + "failed to renameTo  " + newFileName);
					}
				}
			}
			try {
				writer.write("150 open ascii mode...\r\n");
				writer.flush();
				//��ʼ����
				Socket socket = new Socket(user.getIpAddress(),user.getPort(),null,20);
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
				
				writer.write("200 transfer complete...\r\n");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else{
			try {
				writer.write("550  The file is not exist!\r\n");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
