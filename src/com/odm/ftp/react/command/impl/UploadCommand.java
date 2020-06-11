package com.odm.ftp.react.command.impl;


import com.odm.ftp.entity.User;
import com.odm.ftp.base.BaseCommand;
import com.odm.ftp.utils.AccountUtil;

import java.io.*;
import java.net.Socket;
import java.util.Calendar;
import java.util.Objects;

public class UploadCommand extends BaseCommand {

    /**
     * @Author DMingO
     * @Description �����ļ��ϴ�ָ�� UPLOAD
     * @Date  2020/6/11 15:17
     * @Param [content, writer, user]
     * @return void
     **/
    @Override
    public void execute(String content, BufferedWriter writer, User user) {
        File file = new File(AccountUtil.getRootPath());
        try{
            writer.write("150 Binary data connection\r\n");
            writer.flush();
            File oldFileName = new File(AccountUtil.getRootPath()+"/"+content);
            String name = AccountUtil.getRootPath()+content;
            for (String item: Objects.requireNonNull(file.list())){
                item = AccountUtil.getRootPath()+item;
                //��ȡ��ͬ�ļ���
                if (item.equals(name)){
                    //���ļ����ĳ�ʱ���
                    File newFileName = new File(AccountUtil.getRootPath()+ Calendar.getInstance().getTimeInMillis()+"."+item.substring(item.lastIndexOf(".")+1));
                    if(oldFileName.renameTo(newFileName)) {
                        System.out.println(oldFileName + "successfully renameTo  " + newFileName);
                    }else {
                        System.out.println(oldFileName + "failed to renameTo  " + newFileName);
                    }

                }
            }
            RandomAccessFile inFile = new
                    RandomAccessFile(oldFileName,"rw");
            //��ʼ�ϴ�
            Socket tempSocket = new Socket(user.getIpAddress(),user.getPort());
            InputStream inSocket
                    = tempSocket.getInputStream();
            byte[] byteBuffer = new byte[1024];
            int length;

            while((length =inSocket.read(byteBuffer) )!= -1){
                inFile.write(byteBuffer, 0, length);
            }
            System.out.println("�ļ�������ϣ�");
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
