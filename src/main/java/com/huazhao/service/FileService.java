package com.huazhao.service;

import com.huazhao.dao.DeleteDao;
import com.huazhao.dao.QueryDAO;
import com.huazhao.dao.SaveDao;
import com.huazhao.model.FileMeta;
import com.huazhao.util.ListUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with Intellij IDEA
 * Description:
 * User : 花朝
 * Date : 2021-02-24
 * Time : 12:42
 */
public class FileService {
    private final SaveDao saveDao = new SaveDao();
    private final QueryDAO queryDAO = new QueryDAO();
    private final DeleteDao deleteDao = new DeleteDao();
    /*private void saveFileList (List<FileMeta> fileList){
        saveDAO.save(fileList);
    }
    private void deleteFileList (List<FileMeta> fileList) {
        List<Integer> idList = fileList.stream().map(FileMeta::getId).collect(Collectors.toList());
        deleteDAO.delete(idList);
    }*/
    private void saveFileList(List<FileMeta> fileList){
        saveDao.save(fileList);
    }
    private void deleteFileList(List<FileMeta> fileList){
        List<Integer> idList = fileList.stream().map(FileMeta::getId).collect(Collectors.toList());
        deleteDao.delete(idList);
    }
    public void service(String path,List<FileMeta> scanResultList){
        List<FileMeta> queryResultList = queryDAO.queryByPath(path);
        List<FileMeta> ds = ListUtil.differenceSet(queryResultList,scanResultList);
        deleteFileList(ds);
        List<FileMeta> ss = ListUtil.differenceSet(scanResultList,queryResultList);
        saveFileList(ss);
    }
    public List<FileMeta> queryName(String keyword){
        return queryDAO.query(keyword);
    }

}
