package com.example.myapp;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


public class Frag1 extends Fragment {
   private TextView textView,textViewjson;
   private Mhandler mhandler=new Mhandler();
   private HashMap<String ,Long> params=new HashMap<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_frag1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        textView=(TextView)getActivity().findViewById(R.id.text_view);
        textViewjson=(TextView)getActivity().findViewById(R.id.text_json);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                params.put("uid",Long.parseLong("4057802562"));

                NetRequest request=NetRequest.getBuilder()
                        .url("http://sandyz.ink:3000/user/playlist")
                        .handler(mhandler)
                        .params(params).postway("POST").build();

                request.sendPostRequest();
            }
        });
        


        super.onViewCreated(view, savedInstanceState);
    }

    private class Mhandler extends Handler{

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String respondata=(String) msg.obj;
            if(!respondata.isEmpty()){
                textViewjson.setText(respondata);
                Toast.makeText(getContext(),"??????????????????",Toast.LENGTH_SHORT).show();
            }
        }
    }
}