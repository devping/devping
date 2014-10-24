package org.jbug.devping.interfaces.adapter;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nadal on 2014. 9. 30..
 */
public class GoogleAPIAdapter {

    public GoogleTokenDto getTokens(String code) {
        RestTemplate rt = new RestTemplate();
//        rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//        rt.getMessageConverters().add(new StringHttpMessageConverter());

        // Prepare acceptable media type
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);

        String authUrl = "https://accounts.google.com/o/oauth2/token";
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("code", code);
        map.add("client_id", "298688116141-tomf8f4jkunlankbd4c55nu9bp83h963.apps.googleusercontent.com");
        map.add("client_secret", "iGEoPkecoD4Gu1RTjNKAoxFl");
        map.add("redirect_uri", "https://test.devping.com:8443/oauth2callback");
        map.add("grant_type", "authorization_code");

        // Prepare header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<MultiValueMap<String,String>> httpEntity = new HttpEntity<MultiValueMap<String,String>>(map, headers);
        ResponseEntity<GoogleTokenDto> resp = rt.exchange(authUrl, HttpMethod.POST, httpEntity, GoogleTokenDto.class);


//        User returns = rt.postForObject(uri, u, User.class, vars);
//        return dailyImageDtos;
//
//        ResponseEntity<String> responseEntity = rt.postForEntity(
//                authUrl, map, String.class);
//        System.out.println(responseEntity);

//        HttpHeaders headers = responseEntity.getHeaders();
//        String cookie = headers.get("Set-Cookie").get(0);
//        System.out.println(cookie);
//
//        String[] cookieEntries = cookie.split(";");
//        String jSessionId = null;
//        for (String cookieEntry : cookieEntries) {
//            cookieEntry = cookieEntry.trim();
//            if (cookieEntry.startsWith(JSESSIONID)) {
//                jSessionId = cookieEntry.split("=")[1];
//            }
//        }

//        String url = "http://localhost:8080/organizeme/api/v1/users/check";
//        HttpHeaders requestHeaders = new HttpHeaders();
//        requestHeaders.add("Cookie", JSESSIONID + "=" + jSessionId);
//        HttpEntity<String> requestEntity = new HttpEntity<>(null,
//                requestHeaders);
//        ResponseEntity<User> responseEntityForUser = rt.exchange(url,
//                HttpMethod.GET, requestEntity, User.class);
//        System.out.println(responseEntityForUser);
//
//        User user = responseEntityForUser.getBody();
//        System.out.println(user);


            return resp.getBody();
    }

}

