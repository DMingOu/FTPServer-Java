package com.odm.ftp.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Md5Util {

	private static StringBuffer stringBuffer = new StringBuffer();

	/**��ָ���ַ�������md5�㷨ȥ����
	 * @param psd	��Ҫ���ܵ�����
	 * @return ����md5�㷨���ܺ���ַ���
	 */
	public  static String encoder(String psd) {
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
}
