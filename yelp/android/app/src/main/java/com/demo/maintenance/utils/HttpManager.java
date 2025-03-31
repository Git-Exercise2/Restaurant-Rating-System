package com.demo.maintenance.utils;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.demo.maintenance.contant.Config;
import com.demo.maintenance.contant.Global;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpManager {

    private static OkHttpClient client;

    public static void requestToken(){
        if (client == null) {
            client = new OkHttpClient();
        }
        try{
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("https://aip.baidubce.com/oauth/2.0/token?client_id=" + Config.CLIENT_ID
                            + "&client_secret=" + Config.SECRET_KEY
                            + "&grant_type=client_credentials")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.e("dc", response.body().string());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 请求另外的接口
     *
     * @param url 另外的 url 地址
     */
    public static void get(String url, NetworkCallBack networkCallBack) {
        if (client == null) {
            client = new OkHttpClient();
        }
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if (networkCallBack != null) {
                    networkCallBack.onError(e.getLocalizedMessage());
                    networkCallBack.onFinal();
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                if (response.body() == null || !response.isSuccessful()) {
                    if (networkCallBack != null) {
                        networkCallBack.onError(null);
                        networkCallBack.onFinal();
                    }
                    return;
                }
                try {
                    if (networkCallBack != null) {
                        networkCallBack.onSuccess(response.body().string());
                    }
                } catch (IOException e) {
                    networkCallBack.onError(null);
                }
                if (networkCallBack != null) {
                    networkCallBack.onFinal();
                }
            }
        });
    }

    public static void postJson(String url, Map<String, String> param, NetworkCallBack networkCallBack) {
        if (client == null) {
            client = new OkHttpClient();
        }
        RequestBody body = null;
        if (param != null && !param.isEmpty()){
            try {
                body = RequestBody.create(MediaType.parse("application/json"), JSON.toJSONString(param));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("token", Global.token)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if (networkCallBack != null) {
                    networkCallBack.onError(e.getLocalizedMessage());
                    networkCallBack.onFinal();
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                if (response.body() == null || !response.isSuccessful()) {
                    if (networkCallBack != null) {
                        networkCallBack.onError(null);
                        networkCallBack.onFinal();
                    }
                    return;
                }
                try {
                    if (networkCallBack != null) {
                        String successInfo = response.body().string();
                        Log.e("monitoring", TextUtils.isEmpty(successInfo) ? "nothing" : successInfo);
                        networkCallBack.onSuccess(successInfo);
                    }
                } catch (Exception e) {
                    networkCallBack.onError(e.getLocalizedMessage());
                }
                if (networkCallBack != null) {
                    networkCallBack.onFinal();
                }
            }
        });
    }

    public static void postForm(String url, Map<String, String> param, NetworkCallBack networkCallBack) {
        if (client == null) {
            client = new OkHttpClient();
        }
        FormBody.Builder builder = new FormBody.Builder();
        if (param != null && !param.isEmpty()){
            for (String key : param.keySet()){
                builder.add(key, param.get(key));
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if (networkCallBack != null) {
                    networkCallBack.onError(e.getLocalizedMessage());
                    networkCallBack.onFinal();
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                if (response.body() == null || !response.isSuccessful()) {
                    if (networkCallBack != null) {
                        networkCallBack.onError(null);
                        networkCallBack.onFinal();
                    }
                    return;
                }
                try {
                    if (networkCallBack != null) {
                        String successInfo = response.body().string();
                        Log.e("monitoring", TextUtils.isEmpty(successInfo) ? "nothing" : successInfo);
                        networkCallBack.onSuccess(successInfo);
                    }
                } catch (Exception e) {
                    networkCallBack.onError(e.getLocalizedMessage());
                }
                if (networkCallBack != null) {
                    networkCallBack.onFinal();
                }
            }
        });
    }

    public static void postFile(String url, String filePath, NetworkCallBack networkCallBack) throws Exception{
        MediaType mediaType = MediaType.parse("application/octet-stream");

        File file = new File(filePath);
        RequestBody requestBody = RequestBody.create(mediaType, file);

        String lastFix = filePath.substring(filePath.lastIndexOf("."));

        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", UUID.randomUUID().toString() + lastFix, requestBody)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(multipartBody)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if (networkCallBack != null) {
                    networkCallBack.onError(e.getLocalizedMessage());
                    networkCallBack.onFinal();
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                if (response.body() == null || !response.isSuccessful()) {
                    if (networkCallBack != null) {
                        networkCallBack.onError(null);
                        networkCallBack.onFinal();
                    }
                    return;
                }
                try {
                    if (networkCallBack != null) {
                        String successInfo = response.body().string();
                        Log.e("monitoring", TextUtils.isEmpty(successInfo) ? "nothing" : successInfo);
                        networkCallBack.onSuccess(successInfo);
                    }
                } catch (Exception e) {
                    networkCallBack.onError(e.getLocalizedMessage());
                }
                if (networkCallBack != null) {
                    networkCallBack.onFinal();
                }
            }
        });
    }

    public abstract static class NetworkCallBack {

        public void onSuccess(String successInfo) {

        }

        public void onError(String errorInfo) {
            Log.e("monitoring", TextUtils.isEmpty(errorInfo) ? "nothing" : errorInfo);
        }

        public void onFinal() {

        }
    }

}
