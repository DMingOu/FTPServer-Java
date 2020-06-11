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
     * @Description 处理文件上传指令 UPLOAD
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
                //获取相同文件名
                if (item.equals(name)){
                    //将文件名改成时间戳
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
            //开始上传
            Socket tempSocket = new Socket(user.getIpAddress(),user.getPort());
            InputStream inSocket
                    = tempSocket.getInputStream();
            byte[] byteBuffer = new byte[1024];
            int length;

            while((length =inSocket.read(byteBuffer) )!= -1){
                inFile.write(byteBuffer, 0, length);
            }
            System.out.println("文件传输完毕！");
            inFile.close();
            inSocket.close();
            tempSocket.close();
            //传输结束
            writer.write("226 transfer complete\r\n");
            writer.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
