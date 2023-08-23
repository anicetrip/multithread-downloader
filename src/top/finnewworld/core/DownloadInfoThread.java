package top.finnewworld.core;

import top.finnewworld.constant.Constant;

/**
 * download thread
 *
 * @author 21001596_ZHENGTAO YIN
 * @date 2023/08/22 21:13
 **/
public class DownloadInfoThread implements Runnable{
    private long httpFileContentLength;
    //本地已下载文件大小，不是太确定为啥这边不用long,这个可以用来断点续传
    public double finishedSize;

    //本次累计下载的大小，用来计算已经下载的内容
    public volatile long downSize;

    //前一次下载大小
    public long preSize;

    public DownloadInfoThread(long httpFileContentLength) {
        this.httpFileContentLength = httpFileContentLength;
    }

    @Override
    public void run() {
        String httpFileSize = String.format("%.2f", httpFileContentLength / Constant.MB);
        int speed = (int)((downSize - preSize) / 1024d);
        preSize = downSize;
        double remainSize = (httpFileContentLength - finishedSize - downSize / 1024)/Constant.MB;
        String remainTime = String.format("%.1f", remainSize * 1024 * 1024 / speed);
        if ("Infinity".equalsIgnoreCase(remainTime)) {
            remainTime = "-";
        }
        String currentFileSize = String.format("%.2f",(downSize - finishedSize)/1024d / Constant.MB);

        String downloadInfo = String.format("httpFileContentLength:%s,downSize:%s,已下载%smb/%smb，速度%skb，,剩余大小%s,剩余时间%ss",httpFileContentLength,downSize,currentFileSize,httpFileSize,speed,remainSize,remainTime);
//        String downloadInfo = "downSize:" + downSize + " " + "preSize:" + preSize + " speed:" + speed;
        System.out.print("\r");
        System.out.print(downloadInfo);

    }
}
 