package top.finnewworld.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import top.finnewworld.constant.Constant;

import static org.junit.jupiter.api.Assertions.*;

class DownloaderTest {
    Downloader downloader;
    @BeforeEach
    void beforeAll(){
        downloader = new Downloader();
    }


    @Test
    void download() {
        downloader.download(Constant.testUrl);
    }
}