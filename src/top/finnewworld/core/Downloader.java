package top.finnewworld.core;

import top.finnewworld.constant.Constant;
import top.finnewworld.util.FileUtils;
import top.finnewworld.util.HttpUtils;
import top.finnewworld.util.LogUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 下载类
 *
 * @author 21001596_ZHENGTAO YIN
 * @date 2023/08/21 12:38
 **/
public class Downloader {

    public ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    public void download(String url) {
        String httpFileName = HttpUtils.getHttpFileName(url);
        httpFileName = Constant.Path + httpFileName;

        File f = new File(Constant.Path);
        boolean mkdirs = f.mkdirs();

        long localFileLength = FileUtils.getFileConetentLength(httpFileName);



        HttpURLConnection httpUrlConnection = null;
        DownloadInfoThread downloadInfoThread = null;
        try {
            httpUrlConnection = HttpUtils.getHttpUrlConnection(url);
            int contentLength = httpUrlConnection.getContentLength();
            if(localFileLength >= contentLength) {
                LogUtils.info("File {} has been downloaded",httpFileName);
                return;
            }

            //Create download info
            downloadInfoThread = new DownloadInfoThread(contentLength);
            scheduledExecutorService.scheduleAtFixedRate(downloadInfoThread,1,1, TimeUnit.SECONDS);


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
            byte[] buffer = new byte[Constant.BYTE_SIZE];
            while ((len = bis.read()) != -1){
                downloadInfoThread.downSize += len;
                bos.write(buffer,0,len);
            }
        } catch (IOException e) {
            LogUtils.error("地址有误");
        } catch (Exception e) {
            LogUtils.error("下载失败");
        } finally {
            System.out.print("/r");
            LogUtils.info("下载完成");
            if (httpUrlConnection != null) {
                httpUrlConnection.disconnect();
            }
            scheduledExecutorService.shutdownNow();
        }
    }
}
 