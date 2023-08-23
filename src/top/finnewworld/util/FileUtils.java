package top.finnewworld.util;

import java.io.File;

/**
 * 文件操作工具类
 *
 * @author 21001596_ZHENGTAO YIN
 * @date 2023/08/23 12:19
 **/
public class FileUtils {
    /**
     * get current file size
     * @author 21001596_ZHENGTAO YIN
     * @date 12:21 2023/8/23
     * @param path  
     * @return long
     **/
    public static long getFileConetentLength(String path){
        File file = new File(path);
        return file.exists() && file.isFile() ? file.length(): 0;


    }
}
 