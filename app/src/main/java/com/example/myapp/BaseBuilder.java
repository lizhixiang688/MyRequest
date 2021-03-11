package com.example.myapp;

import android.os.Handler;

import java.util.HashMap;

public abstract class BaseBuilder {
    public abstract void buildurl(String url);
    public abstract void buildparams(HashMap<String,Long> params);
    public abstract void buildhandler(Handler handler);
    public abstract NetRequest create();
}
