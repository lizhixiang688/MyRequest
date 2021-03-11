package com.example.myapp;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetRequest {
    private String murl;
    private HashMap<String,Long> params;
    private Handler handler;
    private String way;

    private NetRequest (){}
    private static final NetRequest request=new NetRequest();
    public static NetRequest getInstance(){
        return request;
    }                //单例模式得到NetRequest的实例
    public static NetRequest.Builder getBuilder(){
        return new Builder();
    }    //得到内部类Builder的实例


    public void setMurl(String murl) {
        this.murl = murl;
    }
    public void setParams(HashMap<String, Long> params) {
        this.params = params;
    }
    public void setHandler(Handler handler) {
        this.handler = handler;
    }
    public void setWay(String way) {
        this.way = way;
    }


    public void sendPostRequest(){
        ExecutorService pool= Executors.newFixedThreadPool(2);
        pool.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    String m_url=murl+"?timestamp="+System.currentTimeMillis();
                    URL url=new URL(m_url);
                    HttpURLConnection connection=(HttpURLConnection)url.openConnection();
                    connection.setRequestMethod(way);
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    StringBuilder datatowrite=new StringBuilder();
                    for(String key :params.keySet()){
                        datatowrite.append(key).append("=").append(params.get(key)).append("&");
                    }
                    connection.connect();
                    OutputStream outputStream=connection.getOutputStream();
                    outputStream.write(datatowrite.substring(0,datatowrite.length()-1).getBytes());
                    InputStream in=connection.getInputStream();
                    String respondata =StreamToString(in);
                    Message message=new Message();
                    message.obj=respondata;
                    handler.sendMessage(message);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public static class Builder{
       private NetRequest request=NetRequest.getInstance();

       public Builder url(String url){
           request.setMurl(url);
           return this;
       }

       public Builder handler(Handler handler){
           request.setHandler(handler);
           return this;
       }

       public Builder params(HashMap<String,Long> params){
           request.setParams(params);
           return this;
       }

       public Builder postway(String way){
           request.setWay(way);
           return this;
       }

       public NetRequest build(){
           return request;
       }


    }

    public String StreamToString(InputStream in){
        StringBuilder sb=new StringBuilder();
        String oneline;
        BufferedReader reader=new BufferedReader(new InputStreamReader(in));
        try{
            while((oneline=reader.readLine())!=null){
                sb.append(oneline).append('\n');
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                in.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
