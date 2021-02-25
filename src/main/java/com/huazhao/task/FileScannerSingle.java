package com.huazhao.task;

import com.huazhao.model.FileMeta;
import com.huazhao.service.FileService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with Intellij IDEA
 * Description:
 * User : 花朝
 * Date : 2021-02-24
 * Time : 12:40
 */
public class FileScannerSingle {
    private final FileService fileService = new FileService();
    public void scan(File root) {

        scanDir(root);
    }

    private void scanDir(File root) {
        if(!root.isDirectory()){
            return;
        }
        File[] children = root.listFiles();
        if(children == null){
            return;
        }
        List<FileMeta> scanResultList = new ArrayList<>();
        for(File child : children){
            scanDir(child);
            if(child.isFile()){
                scanResultList.add(new FileMeta(child));
            }
        }
        fileService.service(root.getAbsolutePath(),scanResultList);
    }
}
