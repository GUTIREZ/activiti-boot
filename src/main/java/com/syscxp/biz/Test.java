package com.syscxp.biz;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;

import javax.servlet.http.Cookie;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-09-29.
 * @Description: .
 */
public class Test {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://192.168.212.197:3001/login");
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection connection = null;
            if (urlConnection instanceof HttpURLConnection) {
                connection = (HttpURLConnection) urlConnection;
            } else {
                System.out.println("请输入 URL 地址");
                return;
            }
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            String urlString = "";
            String current;
            while ((current = in.readLine()) != null) {
                urlString += current;
            }
            System.out.println(urlString);

            String cookie = connection.getHeaderField("Set-Cookie");
            System.out.println(cookie);

            String authenticity_token = "AVKGu2DB7wmAa0qg9T5WDSwvA8ysfndp5My3hbzEyssJRmzNuXETiubf8TzKTwL2ib1xX6QKc8MwwtCyNq+8aQ==";

            String values = String.format("{\n" +
                    "    \"utf8\":\"✓\",\n" +
                    "    \"authenticity_token\":%s,\n" +
                    "    \"back_url\":\"http://192.168.200.180/redmine/\",\n" +
                    "    \"username\":\"jint\",\n" +
                    "    \"password\":\"123456\",\n" +
                    "    \"login\":\"登录 »\"\n" +
                    "}",authenticity_token);

            System.out.println(values);

            HttpHeaders headers;

            connection.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
//            connection.setRequestProperty();
//            connection.setRequestProperty();
//            connection.setRequestProperty();
//            connection.setRequestProperty();
//            connection.setRequestProperty();


//            headers = {"Accept":"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
//                    "Content-Type":"application/x-www-form-urlencoded",
//                    "Accept-Encoding":"gzip, deflate",
//                    "Accept-Language":"zh-CN,zh;q=0.8",
//                    "Host":"192.168.200.180",
//                    "Upgrade-Insecure-Requests":"1",
//                    "Connection":"Keep-Alive","Referer":"http://192.168.200.180/redmine/",
//                    "User-Agent":"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36",
//                    "Cookie":Cookie}

            //pattern = re.compile(u'<input type="hidden" name="authenticity_token" value=\"(.*?)\" />',re.S)
//            String s = "<input type=\"hidden\" name=\"authenticity_token\" value=\"(.*?)\" />";
//            System.out.println(s);
//            Pattern p = Pattern.compile(s);
//            Matcher matcher = p.matcher(urlString);
//
//            System.out.println(matcher.group(1));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
