package top.finnewworld.util;

import top.finnewworld.constant.Constant;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HttpUtilsTest {
    static String url = Constant.testUrl;
    @org.junit.jupiter.api.Test
    void getHttpUrlConnection() {
        try {
            HttpURLConnection httpUrlConnection = HttpUtils.getHttpUrlConnection(url);
            System.out.println(httpUrlConnection.getResponseCode());
            InputStream inputStream = httpUrlConnection.getInputStream();
//            System.out.println(Arrays.toString(inputStream.readAllBytes()));
        } catch (IOException | KeyManagementException | NoSuchProviderException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void getHttpFileName() {
        String httpFileName = HttpUtils.getHttpFileName(url);
        assertEquals(httpFileName,"242701311.epub");
    }
}