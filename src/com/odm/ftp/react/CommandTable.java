package com.odm.ftp.react;

/**
 * @ClassName: CommandTable
 * @Auther: DMingO
 * @Date: 2020/6/20 12:49
 * @Description:
 */
public final class CommandTable {

    private CommandTable(){
        throw new IllegalStateException("禁止创建CommandTable对象");
    }


    public static final String USER = "USER";
    public static final String PASSWORD = "PASS";
    public static final String LIST = "LIST";
    public static final String UPLOAD = "STOR";
    public static final String DOWNLOAD = "RETR";
    public static final String PORT = "PORT";
    public static final String QUIT = "QUIT";
    public static final String MAKE_DIR = "XMKD";
}
