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
    //静态内部类创建静态的外部类实例
    private static class CreateNewFileCommandInstance {
        private static final CreateNewFileCommand INSTANCE = new CreateNewFileCommand();
    }

    public static CreateNewFileCommand getInstance(){
        return CreateNewFileCommandInstance.INSTANCE;
    }

    @Override
    public synchronized void execute(String content, BufferedWriter writer, User user) {
        LogUtil.info("创建新文件夹  " + content);
        try {
            //操作新建文件夹
            Result result = FileUtil.createDir(AccountManager.getRootPath() + content);
            System.out.println(result.toString());
            //创建文件夹失败，打印原因
            if(result.getCode() != 250){
                LogUtil.error(result.getMsg());
            }
            //返回客户端信息
            writer.write(result.getCode() + " "+result.getMsg() + "\r\n");
            writer.flush();
        }catch (IOException e){
            LogUtil.error(e);
            e.printStackTrace();
        }



    }


}
