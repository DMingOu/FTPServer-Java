package com.odm.ftp.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: Md5Util
 * @Auther: DMingO
 * @Date: 2020/6/17 12:50
 * @Description: MD5���ܽ��ܹ�����
 */
public class Md5Util {

	private static StringBuffer stringBuffer = new StringBuffer();

	/**
	 * @Author DMingO
	 * @Description ��ָ���ַ�������md5�㷨ȥ����
	 * @Date  2020/6/20 12:33
	 * @Param [psd] ��Ҫ���ܵ�����
	 * @return java.lang.String ����md5�㷨���ܺ���ַ���
	 **/
	public  static String encode(String psd) {
		//�����StringBuffer�е�����
		if(stringBuffer == null){
			stringBuffer = new StringBuffer();
		} else{
			stringBuffer.delete(0 , stringBuffer.length());
		}
		try {
			//ָ�������㷨����
			MessageDigest digest = MessageDigest.getInstance("MD5");
			//����Ҫ���ܵ��ַ�����ת����byte���͵�����,Ȼ����������ϣ����
			byte[] byteArray = digest.digest(psd.getBytes());
			//ѭ������bs,Ȼ����������32λ�ַ��� + ƴ���ַ���
			for (byte b : byteArray) {
				int i = b & 0xff;
				//int���͵�i��Ҫת����16�����ַ�
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
	 * @Description  ���ܽ����㷨 ִ��һ�μ��ܣ����ν���
	 * @Date  2020/6/20 12:32
	 * @Param [md5Str]
	 * @return java.lang.String  ���ܺ���ַ���
	 **/
	public static String decode(String md5Str){

		char[] a = md5Str.toCharArray();
		for (int i = 0; i < a.length; i++){
			a[i] = (char) (a[i] ^ 't');
		}
		return new String(a);

	}
}
