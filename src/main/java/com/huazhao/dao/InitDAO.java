package com.huazhao.dao;


import com.huazhao.util.DBUtil;
import com.huazhao.util.LogUtil;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Scanner;

/**
 * Created with Intellij IDEA
 * Description:
 * User : 花朝
 * Date : 2021-02-24
 * Time : 12:41
 */
public class InitDAO {
    private String[] getSQLs() {
        try(InputStream is = InitDAO.class.getClassLoader().getResourceAsStream("init.sql")) {
            StringBuffer sb = new StringBuffer();
            Scanner sc = new Scanner(is,"UTF-8");
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                sb.append(line);
            }
            return sb.toString().split(";");
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }


    public void init() {
        try {
            Connection c = DBUtil.getConnection();
            String[] sqls = getSQLs();
            for(String sql : sqls){
                LogUtil.log("执行Sql：" + sql);
                try(PreparedStatement ps = c.prepareStatement(sql)) {
                    if(sql.toUpperCase().startsWith("SELECT")){
                        try(ResultSet rs = ps.executeQuery()){
                            ResultSetMetaData metaData = rs.getMetaData();
                            int columnCount = metaData.getColumnCount();
                            int rowCount = 0;
                            while (rs.next()){
                                StringBuffer sb = new StringBuffer("|");
                                for(int i = 1;i <= columnCount;i++){
                                    String str = rs.getString(i);
                                    sb.append(str).append("|");
                                }
                                LogUtil.log(sb.toString());
                                rowCount++;
                            }
                            LogUtil.log("查询出 %d 行",rowCount);
                        }
                    }else {
                        int i = ps.executeUpdate();
                        LogUtil.log("收到影响的行一共有 %d 行: ", i);
                    }
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        InitDAO dao = new InitDAO();
        dao.init();
    }
}
