package com.odm.ftp.react.command.executor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import com.odm.ftp.entity.User;
import com.odm.ftp.base.BaseCommand;
import com.odm.ftp.utils.AccountManager;
import com.odm.ftp.utils.LogUtil;

/**
 * @ClassName: LoginCommand
 * @Auther: DMingO
 * @Date: 2020/6/11 15:48
 * @Description: �����û���¼ָ�� USER
 */
public class UserCommand extends BaseCommand {

	private BufferedReader reader;

	@Override
	public synchronized void execute(String userName, BufferedWriter writer, User user) {

		//��� �û��� �м�¼��˵���ǵ�¼��������Ҫ��֤����
		if(AccountManager.hasUsername(userName)){
			LogUtil.info(userName+ "   -----ϵͳ���м�¼");
			try {
				writer.write("331 , Please continue to enter your password\r\n");
				writer.flush();
				user.setUsername(userName);
				//reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else{
			//�û��� û�м�¼��˵���� ע���������Ҫ��ȡ��ע���û�������
			LogUtil.info(userName + "   -----ϵͳû�м�¼���ж������û�");
			try {
				//�˴����ؿͻ�����Ϣ������״̬�뿪ͷ��������ֵ�¼ʧ��
				writer.write("331 , Welcome New User! Please set up a password for your account\r\n");
				writer.flush();
				user.setUsername(userName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	}
	

}