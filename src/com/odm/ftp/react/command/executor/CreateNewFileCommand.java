package com.odm.ftp.react.command.executor;

import com.odm.ftp.base.BaseCommand;
import com.odm.ftp.entity.Result;
import com.odm.ftp.entity.User;
import com.odm.ftp.utils.AccountManager;
import com.odm.ftp.utils.FileUtil;
import com.odm.ftp.utils.LogUtil;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * @ClassName: CreateNewFileCommand
 * @Auther: DMingO
 * @Date: 2020/6/20 14:23
 * @Description:
 */
public class CreateNewFileCommand extends BaseCommand {

    private CreateNewFileCommand() {
    }
    //��̬�ڲ��ഴ����̬���ⲿ��ʵ��
    private static class CreateNewFileCommandInstance {
        private static final CreateNewFileCommand INSTANCE = new CreateNewFileCommand();
    }

    public static CreateNewFileCommand getInstance(){
        return CreateNewFileCommandInstance.INSTANCE;
    }

    @Override
    public synchronized void execute(String content, BufferedWriter writer, User user) {
        LogUtil.info("�������ļ���  " + content);
        try {
            //�����½��ļ���
            Result result = FileUtil.createDir(AccountManager.getRootPath() + content);
            System.out.println(result.toString());
            //�����ļ���ʧ�ܣ���ӡԭ��
            if(result.getCode() != 250){
                LogUtil.error(result.getMsg());
            }
            //���ؿͻ�����Ϣ
            writer.write(result.getCode() + " "+result.getMsg() + "\r\n");
            writer.flush();
        }catch (IOException e){
            LogUtil.error(e);
            e.printStackTrace();
        }



    }


}
