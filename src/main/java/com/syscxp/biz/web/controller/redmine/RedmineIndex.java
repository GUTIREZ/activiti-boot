package com.syscxp.biz.web.controller.redmine;

import com.alibaba.fastjson.JSON;
import com.syscxp.biz.core.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-09-29.
 * @Description: .
 */

@RestController
@RequestMapping("/redmine")
public class RedmineIndex {
    private static final Logger logger = LoggerFactory.getLogger(RedmineIndex.class);


    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/index")
    public String index() {
        String url = "http://192.168.212.197:3001";

        ResponseEntity<String> indexResp = restTemplate.getForEntity(url, String.class);
        String cookie = StringUtil.substringBefore(indexResp.getHeaders().get("Set-Cookie").get(0).toString(), ";");
        String authToken = StringUtil.substringBefore(StringUtil.substringAfter(indexResp.getBody().toString(), "<meta name=\"csrf-token\" content=\""), "\" />");

        logger.info("cookie: ====>>>>" + cookie);
        logger.info("authToken: ====>>>>" + authToken);

        MultiValueMap<String, String> variables = new LinkedMultiValueMap<>();
        variables.add("utf8", "✓");
        variables.add("authenticity_token", authToken);
        variables.add("back_url", "http://192.168.212.197:3001/");
        variables.add("username", "admin");
        variables.add("password", "password");
        variables.add("login", "登录 »");

        HttpHeaders loginHeader = new HttpHeaders();
        loginHeader.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        loginHeader.setContentLength(500);
        loginHeader.set("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        loginHeader.set("Cookie", cookie);

        loginHeader.set("Content-Type", "application/x-www-form-urlencoded");
        loginHeader.set("Accept-Encoding", "gzip, deflate");
        loginHeader.set("Accept-Language", "zh-CN,zh;q=0.8");
        loginHeader.set("Host", "localhost");
        loginHeader.set("Upgrade-Insecure-Requests", "1");
        loginHeader.set("Connection", "Keep-Alive");
        loginHeader.set("Referer", "http://192.168.212.197:3001");
        loginHeader.set("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

        HttpEntity<MultiValueMap<String, String>> loginEntity = new HttpEntity<>(variables, loginHeader);

        String loginUrl = url + "/login";
        ResponseEntity<String> loginResp = restTemplate.exchange(loginUrl, HttpMethod.POST, loginEntity, String.class);


        cookie = StringUtil.substringBefore(loginResp.getHeaders().get("Set-Cookie").get(0).toString(), ";");
        logger.info("login cookie: ====>>>>" + cookie);

//        String pageUrl = url+"/my/page";
//        loginHeader.set("Cookie", cookie);
//
//        HttpEntity<MultiValueMap<String, String>> pageEntity = new HttpEntity<>(null, loginHeader);
//
//        ResponseEntity<String> pageResp = restTemplate.exchange(loginUrl, HttpMethod.GET, pageEntity, String.class);

        return cookie;
    }

    @GetMapping("/test")
    public void test() {
        String url = "http://192.168.212.197:3001/login";
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        List<MediaType> acceptTpyes = new ArrayList<>();
        acceptTpyes.add(MediaType.TEXT_HTML);
        acceptTpyes.add(MediaType.APPLICATION_XHTML_XML);
        acceptTpyes.add(MediaType.APPLICATION_XML);
        headers.setAccept(acceptTpyes);
//        headers.set("Cookie", "_redmine_session=YkJ4U1h3RWtWUnRCanVBT2k3QktxVHJLMEpaU1hDbVlpMVlIM29RNW8wYjZyTWdIRE5yaUtEM3FUMXRNNHJLNEtnTk5ZYjF5Tmt1NFBrRm9QcitNZERwR0s1MHFZQTM5Y0NrcXVUelhXeDYvQjh3SHpBZW1TNXVnbzljQXlESWRoRjgrSmdDT0hUM1QrNEYrUnFrTEVVNEhFTDk0NXVITVUwMXlPcXZrblZpOGJsdHhCMWk0TXc5ODZDeDZzbTZlSnMxKzl4QU05bHVCanNLTWdFSjNadkZjWXM1WmNKdW5hd1BxaFdxNjk3aGpidmlLTDhtbjBjY241OXErZDFSNGp1TDhlTlQ1aXFYdWhXdUFwNEFET3p1eGVzdTA0ODV2Qk1ZV1hCZ1NjalVQSjJ5S0pOeVJTMzhPNktYWHBVMC8tLVhmWUxRZVBHYUZJUnpsK0FnaW9sM3c9PQ%3D%3D--c2776c5be0cd40d8b447edd29a58b9bbd65542d7");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("username", "admin");
        params.add("password", "password");
        params.add("utf8", "✓");
        params.add("authenticity_token", "UBsGHXE3TDwsMe33y2FB+pcpYrXO1y3o4jIMgVuYg4FoQWK2J4JtZUcUghNiT6NbQx5W3eQ63jG8T2W5LFg0eA==");
        params.add("login", "登录 »");
        params.add("back_url", "http://192.168.212.197:3001/");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        ResponseEntity<String> response = client.exchange(url, HttpMethod.POST, requestEntity, String.class);

        System.out.println(response.getBody());

        /*
        * authenticity_token: s8ZX2RZSh7WUt0ymwsY6km+HSMeyx0Y9aIJLun6DZY3CKA6c88N3Ih7Ju/1BUZ3g7bYQnl38P+vJdVWp26vAYg==
        * */
    }

}
