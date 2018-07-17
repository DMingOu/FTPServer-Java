package cn.edu.gdut.ftp.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Md5Util {
	
	public static void main(String[] args) {
		System.out.println(encoder("123"));
	}

	/**��ָ���ַ�������md5�㷨ȥ����
	 * @param psd	��Ҫ���ܵ�����
	 * @return 
	 */
	public  static String encoder(String psd) {
		StringBuffer stringBuffer = null;
		try {
			//1,ָ�������㷨����
			MessageDigest digest = MessageDigest.getInstance("MD5");
			//2,����Ҫ���ܵ��ַ�����ת����byte���͵�����,Ȼ����������ϣ����
			byte[] bs = digest.digest(psd.getBytes());
			//3,ѭ������bs,Ȼ����������32λ�ַ���,�̶�д��
			//4,ƴ���ַ�������
			stringBuffer = new StringBuffer();
			for (byte b : bs) {
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
