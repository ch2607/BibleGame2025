package com.Game.ccmusa.android;

import android.app.Activity;
import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

/**
 * Created by ch180005 on 4/21/2015.
 */
public class HelpGame extends AppCompatActivity {
    public static int help = 0;
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       if (Main.setcn == false)
           setContentView(R.layout.help_main);
        else
           setContentView(R.layout.help_main);
        help = 1;


        viewPager = findViewById(R.id.viewpager);

        // setting up the adapter
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        // add the fragments
        if (Main.setcn == false) {
            viewPagerAdapter.add(new Page1(), "首頁");
            viewPagerAdapter.add(new Page2(), "選擇題");
            viewPagerAdapter.add(new Page3(), "是非題");
            viewPagerAdapter.add(new Page4(), "填空題");
            viewPagerAdapter.add(new Page5(), "填空題");
        }
        else
        {
            viewPagerAdapter.add(new Page1(), "首頁");
            viewPagerAdapter.add(new Page2(), "选择题");
            viewPagerAdapter.add(new Page3(), "是非题");
            viewPagerAdapter.add(new Page4(), "填空题");
            viewPagerAdapter.add(new Page5(), "填空題");
        }

        // Set the adapter
        viewPager.setAdapter(viewPagerAdapter);

        // The Page (fragment) titles will be displayed in the
        // tabLayout hence we need to  set the page viewer
        // we use the setupWithViewPager().
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }
}