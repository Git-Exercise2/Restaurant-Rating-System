package com.demo.maintenance.utils;

import com.alibaba.fastjson.JSON;
import com.baidubce.qianfan.Qianfan;
import com.baidubce.qianfan.model.chat.ChatResponse;
import com.demo.maintenance.bean.ChatMessages;
import com.demo.maintenance.bean.Token;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class QianFanUtils {

    public static final String API_KEY = "EE0m6MuJjAg7I4rnqW4xo12a";
    public static final String SECRET_KEY = "L1BuaVikH6BgsQ5IzfAj5H1iFLaBxnKk";
    public static final String ACCESS_KEY = "fbcf54441396448e8cb95800580980c7";
    public static final String ACCESS_SECRET_KEY = "7b31359bd36e40a681e791a7305e637e";

    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();

    public static String requestData(String content) throws IOException {
        Qianfan qianfan = new Qianfan(ACCESS_KEY, ACCESS_SECRET_KEY);
        ChatResponse resp = qianfan.chatCompletion()
                .model("ERNIE-3.5-8K")
                .addMessage("user", content)
                .execute();
        return resp.getResult();
    }

}
