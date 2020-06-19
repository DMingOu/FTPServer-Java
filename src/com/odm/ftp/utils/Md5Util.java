package com.odm.ftp.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Md5Util {

	private static StringBuffer stringBuffer = new StringBuffer();

	/**给指定字符串按照md5算法去加密
	 * @param psd	需要加密的密码
	 * @return 根据md5算法加密后的字符串
	 */
	public  static String encoder(String psd) {
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
}
