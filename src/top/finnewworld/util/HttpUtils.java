package top.finnewworld.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

/**
 * http工具类
 *
 * @author 21001596_ZHENGTAO YIN
 * @date 2023/08/21 10:35
 **/
public class HttpUtils {
    
    /**
     *
     * @author 21001596_ZHENGTAO YIN
     * @date 10:40 2023/8/21
     * @param url  
     * @return HttpURLConnection
     **/
    public static HttpURLConnection getHttpUrlConnection(String url) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        SSLContext sslcontext = SSLContext.getInstance("SSL");//第一个参数为协议,第二个参数为提供者(可以缺省)
        TrustManager[] tm = {new MyX509TrustManager()};
        sslcontext.init(null, tm, new SecureRandom());
        HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
            public boolean verify(String s, SSLSession sslsession) {
                System.out.println("WARNING: Hostname is not matched for cert.");
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
        HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());



        URL httpUrl = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
        httpURLConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.835.163 Safari/535.1");
        return httpURLConnection;
    }

    /**
     * 获取下载文件名
     * @author 21001596_ZHENGTAO YIN
     * @date 10:53 2023/8/21
     * @param null
     * @return null
     **/
    public static String getHttpFileName(String url){
        int index = url.lastIndexOf("/");
        return url.substring(index + 1);
    }
}
 