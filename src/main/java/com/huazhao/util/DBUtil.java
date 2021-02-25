package com.huazhao.util;

import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created with Intellij IDEA
 * Description:
 * User : 花朝
 * Date : 2021-02-24
 * Time : 12:43
 */
public class DBUtil {
    private static volatile  DataSource dataSource = null;

    private static void initDataSource() {
        SQLiteDataSource sqLiteDataSource = new SQLiteDataSource();
        String url = "jdbc:sqlite://" + getDatabasePath();
        sqLiteDataSource.setUrl(url);
        dataSource = sqLiteDataSource;
    }

    private static String getDatabasePath() {
        try{
            String classPath = DBUtil.class.getProtectionDomain()
                    .getCodeSource().getLocation().getFile();
            File classDir = new File(URLDecoder.decode(classPath,"UTF-8"));
            String path = classDir.getParent() + File.separator + "everything-like.db";
            LogUtil.log("数据路径为：%s",path);
            return path;
        }catch (UnsupportedEncodingException e){
            throw new RuntimeException(e);
        }
    }

    public static Connection initConnection(){
        if(dataSource == null){
            synchronized (DBUtil.class){
                if(dataSource == null){
                    initDataSource();
                }
            }
        }
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
    }
    private static final ThreadLocal<Connection> connectionThreadLocal =
            ThreadLocal.withInitial(DBUtil::initConnection);

    public static Connection getConnection(){

        return connectionThreadLocal.get();
    }



}
