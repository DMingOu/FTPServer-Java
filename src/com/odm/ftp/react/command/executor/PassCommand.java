package com.odm.ftp.react.command.executor;
import com.odm.ftp.entity.User;
import com.odm.ftp.base.BaseCommand;
import com.odm.ftp.utils.AccountManager;
import com.odm.ftp.utils.LogUtil;
import com.odm.ftp.utils.Md5Util;
import java.io.BufferedWriter;
import java.io.IOException;


/**
 * @ClassName: PassCommand
 * @Auther: DMingO
 * @Date: 2020/6/11 15:48
 * @Description: �����û����������ָ��
 */
public class PassCommand extends BaseCommand {

	/**
	 * @Author DMingO
	 * @Description  ������֤
	 * @Date  2020/6/11 12:00
	 * @Param [passWord, writer, user]
	 * @return void
	 **/
	@Override
	public void execute(String passWord, BufferedWriter writer, User user) {
		if(passWord == null || writer == null || user== null){
			LogUtil.error("PassCommand ------execute()���������������null ");
			return;
		}
		//�����û����жϴ��û�Ϊ���û� / ���û�
		//���û���������ת�� MD5 �󣬺ͼ�¼�����Ա�
		if(AccountManager.hasUsername(user.getUsername())){
			String passWord2Md5 = Md5Util.encode(passWord);
			//�������¼�����������ȷ����¼�ɹ�
			if(passWord2Md5.equals(AccountManager.getPassword(user.getUsername()))){
				LogUtil.info("�û� "+user.getUsername() + "  ������ȷ,��¼�ɹ�");
				try {
					//230ǰ�治���������ַ�����ᴥ��ϵͳ��Ϣ ��¼ʧ�ܡ�
					writer.write("230 , Your passWord is passed!Welcome to use FTP-Server!\r\n");
					writer.flush();
					//user�����������
					user.setPassword(passWord);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//���벻���ϼ�¼��������󣬵�¼ʧ��
			else{
				try {
					writer.write("530 Your passWord isn't passed , please input the correct password !\r\n");
					writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		//���û���������ת�� MD5 �󣬴洢����
		if(! AccountManager.hasUsername(user.getUsername())){
			String passWord2Md5 = Md5Util.encode(passWord);
			//�����û�,�û��������(MD5)�����˻�������
			if(AccountManager.replenishAccount(user.getUsername() , passWord2Md5)){
				LogUtil.info("�û� "+user.getUsername() + "  ע��ͨ��");
			}
			try {
				//230ǰ�治���������ַ�����ᴥ��ϵͳ��Ϣ ��¼ʧ�ܡ�
				writer.write("230 ,Your account has been approved! Welcome to use FTP-Server!\r\n");
				writer.flush();
				//user�����������
				user.setPassword(passWord);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
