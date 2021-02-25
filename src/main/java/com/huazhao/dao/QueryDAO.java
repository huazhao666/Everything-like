package com.huazhao.dao;

import com.huazhao.model.FileMeta;
import com.huazhao.util.DBUtil;
import com.huazhao.util.LogUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with Intellij IDEA
 * Description:
 * User : 花朝
 * Date : 2021-02-24
 * Time : 12:41
 */
public class QueryDAO {
    public List<FileMeta> query(String keyword) {
        try {
            Connection c = DBUtil.getConnection();
            String sql = "SELECT id, name, pinyin, pinyin_first, path, is_directory, size, "+
                    "last_modified  FROM file_meta WHERE name like ? or pinyin like ? or pinyin_first like ? ";
            try (PreparedStatement ps = c.prepareStatement(sql)){
                ps.setString(1,"%" + keyword + "%");
                ps.setString(2,"%" + keyword + "%");
                ps.setString(3,"%" + keyword + "%");

                LogUtil.log("执行 SQL: %s, %s", sql, keyword);
                List<FileMeta> result = new ArrayList<>();
                try (ResultSet rs = ps.executeQuery()){
                    while (rs.next()){
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String pinyin = rs.getString("pinyin");
                        String pinyinFirst = rs.getString("pinyin_first");
                        String path = rs.getString("path");
                        boolean directory = rs.getBoolean("is_directory");
                        long length = rs.getLong("size");
                        long lastModified = rs.getLong("last_modified");
                        FileMeta meta = new FileMeta(id,name,pinyin,pinyinFirst,path,directory,length,lastModified);
                        result.add(meta);
                    }
                    LogUtil.log("一共查询出 %d 个文件", result.size());
                    return result;
                }
            }
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }
    public List<FileMeta> queryByPath(String searchPath) {
        try {
            Connection c = DBUtil.getConnection();
            String sql = "SELECT id, name, pinyin, pinyin_first, path, is_directory, size, "+
                    "last_modified  FROM file_meta WHERE path =  ? ";
            try (PreparedStatement ps = c.prepareStatement(sql)){
                ps.setString(1,"%" + searchPath + "%");

                LogUtil.log("执行 SQL: %s, %s", sql, searchPath);
                List<FileMeta> result = new ArrayList<>();
                try (ResultSet rs = ps.executeQuery()){
                    while (rs.next()){
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String pinyin = rs.getString("pinyin");
                        String pinyinFirst = rs.getString("pinyin_first");
                        String path = rs.getString("path");
                        boolean directory = rs.getBoolean("is_directory");
                        long length = rs.getLong("size");
                        long lastModified = rs.getLong("last_modified");
                        FileMeta meta = new FileMeta(id,name,pinyin,pinyinFirst,path,directory,length,lastModified);
                        result.add(meta);
                    }
                    LogUtil.log("一共查询出 %d 个文件", result.size());
                    return result;
                }
            }
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }
}
