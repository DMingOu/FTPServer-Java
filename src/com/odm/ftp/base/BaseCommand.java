package com.odm.ftp.base;

import com.odm.ftp.entity.User;

import java.io.BufferedWriter;

/**
 * @ClassName: BaseCommand
 * @Auther: DMingO
 * @Date: 2020/6/11 08:50
 * @Description: ָ��������
 */
public abstract class BaseCommand {

	/**
	 * @Author DMingO
	 * @Description  ִ��ָ��ľ������
	 * @Date  2020/6/11
	 * @Param [content, writer, userInfo]
	 * @return void
	 **/
	public abstract void execute(String content, BufferedWriter writer, User userInfo);

}
