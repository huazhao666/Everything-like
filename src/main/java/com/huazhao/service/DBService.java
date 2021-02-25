package com.huazhao.service;

import com.huazhao.dao.InitDAO;

/**
 * Created with Intellij IDEA
 * Description:
 * User : 花朝
 * Date : 2021-02-24
 * Time : 12:42
 */
public class DBService {
    private final InitDAO initDAO = new InitDAO();
    public void init() {
        initDAO.init();
    }
}
