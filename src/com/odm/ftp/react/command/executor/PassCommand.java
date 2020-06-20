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
 * @Description: 处理用户关于密码的指令
 */
public class PassCommand extends BaseCommand {

	/**
	 * @Author DMingO
	 * @Description  密码验证
	 * @Date  2020/6/11 12:00
	 * @Param [passWord, writer, user]
	 * @return void
	 **/
	@Override
	public void execute(String passWord, BufferedWriter writer, User user) {
		if(passWord == null || writer == null || user== null){
			LogUtil.error("PassCommand ------execute()方法输入参数出现null ");
			return;
		}
		//根据用户名判断此用户为老用户 / 新用户
		//老用户，将密码转换 MD5 后，和记录中做对比
		if(AccountManager.hasUsername(user.getUsername())){
			String passWord2Md5 = Md5Util.encode(passWord);
			//密码与记录相符，密码正确，登录成功
			if(passWord2Md5.equals(AccountManager.getPassword(user.getUsername()))){
				LogUtil.info("用户 "+user.getUsername() + "  密码正确,登录成功");
				try {
					//230前面不能有其他字符否则会触发系统消息 登录失败。
					writer.write("230 , Your passWord is passed!Welcome to use FTP-Server!\r\n");
					writer.flush();
					//user对象更新密码
					user.setPassword(passWord);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//密码不符合记录，密码错误，登录失败
			else{
				try {
					writer.write("530 Your passWord isn't passed , please input the correct password !\r\n");
					writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		//新用户，将密码转换 MD5 后，存储进来
		if(! AccountManager.hasUsername(user.getUsername())){
			String passWord2Md5 = Md5Util.encode(passWord);
			//将新用户,用户民和密码(MD5)存入账户管理类
			if(AccountManager.replenishAccount(user.getUsername() , passWord2Md5)){
				LogUtil.info("用户 "+user.getUsername() + "  注册通过");
			}
			try {
				//230前面不能有其他字符否则会触发系统消息 登录失败。
				writer.write("230 ,Your account has been approved! Welcome to use FTP-Server!\r\n");
				writer.flush();
				//user对象更新密码
				user.setPassword(passWord);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
