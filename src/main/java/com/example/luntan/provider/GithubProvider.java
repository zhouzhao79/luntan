package com.example.luntan.provider;


import com.alibaba.fastjson.JSON;
import com.example.luntan.dto.AccessTokenDTO;
import com.example.luntan.dto.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 *GIT授权登录类
 */
@Component //仅仅把类加上到系统上下文  使用时不需要GithubProvider githubProvider=new GithubProvider();
public class GithubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string=response.body().string();
            String[] split = string.split("&");
            String tokens = split[0];
            String[] split1 = tokens.split("=");
            String token = split1[1];
         //   System.out.println(token);
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GitHubUser getUser(String access_token){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+access_token)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string=response.body().string();
           GitHubUser gitHubUser=JSON.parseObject(string,GitHubUser.class);
           return gitHubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

}
