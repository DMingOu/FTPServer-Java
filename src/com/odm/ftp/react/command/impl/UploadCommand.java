package com.odm.ftp.react.command.impl;


import com.odm.ftp.entity.User;
import com.odm.ftp.base.BaseCommand;
import com.odm.ftp.utils.AccountUtil;

import java.io.*;
import java.net.Socket;
import java.util.Calendar;

public class UploadCommand extends BaseCommand {

    /**
     * �ļ��ϴ�
     * @param content
     * @param writer
     * @param userInfo
     */
    @Override
    public void execute(String content, BufferedWriter writer, User userInfo) {
        File file = new File(AccountUtil.getRootPath());
        try{
            writer.write("150 Binary data connection\r\n");
            writer.flush();
            File lastFileName = new File(AccountUtil.getRootPath()+"/"+content);
            String name = AccountUtil.getRootPath()+content;
            for (String item:file.list()){
                item = AccountUtil.getRootPath()+item;
                //��ȡ��ͬ�ļ���
                if (item.equals(name)){
                    //���ļ����ĳ�ʱ���
                    File temp = new File(AccountUtil.getRootPath()+ Calendar.getInstance().getTimeInMillis()+"."+item.substring(item.lastIndexOf(".")+1));
                    lastFileName.renameTo(temp);
                    System.out.println(lastFileName);

                }
            }
            RandomAccessFile inFile = new
                    RandomAccessFile(lastFileName,"rw");
            //��ʼ�ϴ�
            Socket tempSocket = new Socket(userInfo.getIpAddress(),userInfo.getPort());
            InputStream inSocket
                    = tempSocket.getInputStream();
            byte byteBuffer[] = new byte[1024];
            int length;

            while((length =inSocket.read(byteBuffer) )!= -1){
                inFile.write(byteBuffer, 0, length);
            }
            System.out.println("transfer complete");
            inFile.close();
            inSocket.close();
            tempSocket.close();
            //�������

            writer.write("226 transfer complete\r\n");
            writer.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
