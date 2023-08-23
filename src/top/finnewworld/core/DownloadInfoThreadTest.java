package top.finnewworld.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DownloadInfoThreadTest {

    @Test
    void run() {
        DownloadInfoThread downloadInfoThread = new DownloadInfoThread(1000);
        downloadInfoThread.run();
        downloadInfoThread.preSize = 100;
        downloadInfoThread.downSize = 50;
        downloadInfoThread.finishedSize = 200;
        System.out.println();
        System.out.println(downloadInfoThread.downSize+ " " + downloadInfoThread.preSize + " " + downloadInfoThread.finishedSize);
    }
}