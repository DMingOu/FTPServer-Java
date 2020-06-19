package com.odm.ftp.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @ClassName: LogUtil
 * @Auther: DMingO
 * @Date: 2020/6/19 18:50
 * @Description: ��ӡ�ı���
 */
public class LogUtil {

    //��ȡ�Ե�ǰ��Ϊ��������־����
    private static Log log = LogFactory.getLog(LogUtil.class);


    public static void debug(Object content){
        log.debug(content);
    }

    public static void info(Object content){
        log.info(content);
    }
    public static void warn(Object content){
        log.warn(content);
    }
    public static void error(Object content){
        log.error(content);
    }


}