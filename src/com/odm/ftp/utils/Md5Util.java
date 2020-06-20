package com.odm.ftp.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: Md5Util
 * @Auther: DMingO
 * @Date: 2020/6/17 12:50
 * @Description: MD5加密解密工具类
 */
public class Md5Util {

	private static StringBuffer stringBuffer = new StringBuffer();

	/**
	 * @Author DMingO
	 * @Description 给指定字符串按照md5算法去加密
	 * @Date  2020/6/20 12:33
	 * @Param [psd] 需要加密的密码
	 * @return java.lang.String 根据md5算法加密后的字符串
	 **/
	public  static String encode(String psd) {
		//先清空StringBuffer中的内容
		if(stringBuffer == null){
			stringBuffer = new StringBuffer();
		} else{
			stringBuffer.delete(0 , stringBuffer.length());
		}
		try {
			//指定加密算法类型
			MessageDigest digest = MessageDigest.getInstance("MD5");
			//将需要加密的字符串中转换成byte类型的数组,然后进行随机哈希过程
			byte[] byteArray = digest.digest(psd.getBytes());
			//循环遍历bs,然后让其生成32位字符串 + 拼接字符串
			for (byte b : byteArray) {
				int i = b & 0xff;
				//int类型的i需要转换成16机制字符
				String hexString = Integer.toHexString(i);
				if(hexString.length()<2){
					hexString = "0"+hexString;
				}
				stringBuffer.append(hexString);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return stringBuffer.toString();
	}


	/**
	 * @Author DMingO
	 * @Description  加密解密算法 执行一次加密，两次解密
	 * @Date  2020/6/20 12:32
	 * @Param [md5Str]
	 * @return java.lang.String  解密后的字符串
	 **/
	public static String decode(String md5Str){

		char[] a = md5Str.toCharArray();
		for (int i = 0; i < a.length; i++){
			a[i] = (char) (a[i] ^ 't');
		}
		return new String(a);

	}
}
