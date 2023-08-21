package top.finnewworld.core;

import top.finnewworld.constant.Constant;
import top.finnewworld.util.HttpUtils;
import top.finnewworld.util.LogUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * 下载类
 *
 * @author 21001596_ZHENGTAO YIN
 * @date 2023/08/21 12:38
 **/
public class Downloader {
    public void download(String url) {
        String httpFileName = HttpUtils.getHttpFileName(url);
        httpFileName = Constant.Path + httpFileName;

        File f = new File(Constant.Path);
        boolean mkdirs = f.mkdirs();

        HttpURLConnection httpUrlConnection = null;
        try {
            httpUrlConnection = HttpUtils.getHttpUrlConnection(url);
        } catch (IOException | NoSuchAlgorithmException | NoSuchProviderException | KeyManagementException e) {
            e.printStackTrace();
        }

        try (
                InputStream input = httpUrlConnection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(input);
                FileOutputStream fos = new FileOutputStream(httpFileName);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
        ) {
            int len = -1;
            while ((len = bis.read()) != -1){
                bos.write(len);
            }
        } catch (IOException e) {
            LogUtils.error("地址有误");
        } catch (Exception e) {
            LogUtils.error("下载失败");
        } finally {
            if (httpUrlConnection != null) {
                httpUrlConnection.disconnect();
            }
        }
    }
}
 