package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public List<Fragment>fragmentList =new ArrayList<>();
    public List<String> titlelist=new ArrayList<>();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Viewpageradapter viewpageradapter;

    @Override
    protected void onStart() {
        super.onStart();
        View decorview =getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorview.setSystemUiVisibility(uiOptions);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View decorview =getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorview.setSystemUiVisibility(uiOptions);

        tabLayout=(TabLayout)findViewById(R.id.tablayout);
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        fragmentList.add(new Frag1());
        fragmentList.add(new Frag2());
        titlelist.add("1");
        titlelist.add("2");
        viewpageradapter=new Viewpageradapter(getSupportFragmentManager(),Viewpageradapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewpageradapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText(titlelist.get(0)).setIcon(R.drawable.home_black);
        tabLayout.getTabAt(1).setText(titlelist.get(1)).setIcon(R.drawable.icon_2);


        //可以在网上直接找图片，然后下载的时候在下不同的颜色 可以得到颜色码，用于字体，也可以控制下载图片的大小

       tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {
                int positin=tab.getPosition();
                if(positin==0){
                    tab.setIcon(R.drawable.home_red);
                }
                else if(positin==1){
                    tab.setIcon(R.drawable.icon_green);
                }
           }

           @Override
           public void onTabUnselected(TabLayout.Tab tab) {
               int position =tab.getPosition();
               if(position==0){
                   tab.setIcon(R.drawable.home_black);
               }
               else if(position==1){
                   tab.setIcon(R.drawable.icon_2);
               }
           }

           @Override
           public void onTabReselected(TabLayout.Tab tab) {

           }
       });
    }



    public class Viewpageradapter extends FragmentPagerAdapter{
        public Viewpageradapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titlelist.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
