package top.finnewworld;

import top.finnewworld.core.Downloader;
import top.finnewworld.util.LogUtils;

import java.util.Scanner;

/**
 * 主类
 *
 * @author 21001596_ZHENGTAO YIN
 * @date 2023/08/20 19:55
 **/
public class Main {
    public static void main(String[] args) {
        String url = "https://video.dashucun.com/dsc/09.mp4";
//        if (args == null || args.length == 0) {
//            do {
//                LogUtils.info("请输入下载链接");
//                Scanner scanner = new Scanner(System.in);
//                url = scanner.next();
//            } while (url == null);
//        } else {
//            url = args[0];
//        }
        LogUtils.info("下载地址为{}",url);
        Downloader downloader = new Downloader();
        downloader.download(url);
    }

}
 