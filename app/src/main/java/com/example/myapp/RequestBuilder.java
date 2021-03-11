package com.example.myapp;

import android.os.Handler;

import java.util.HashMap;

public class RequestBuilder extends BaseBuilder{
    private NetRequest request=NetRequest.getInstance();
    @Override
    public void buildurl(String url) {
        request.setMurl(url);
    }

    @Override
    public void buildparams(HashMap<String, Long> params) {
        request.setParams(params);
    }

    @Override
    public void buildhandler(Handler handler) {
        request.setHandler(handler);
    }

    @Override
    public NetRequest create() {
        return request;
    }
}
