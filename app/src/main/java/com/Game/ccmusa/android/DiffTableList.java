package com.Game.ccmusa.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DiffTableList extends Activity {
    android.widget.ListView ListView;
    String EasyName;
    String MedName;
    String HardName;
    String sEasyName;
    String sMedName;
    String sHardName;
    String Name;
    int BUFFER_SIZE = 50;
    String readLine;
    int level;
    Context ctx;
    String name;
    String sname;
    int cnt = 0;
    int i = 0;
    int ret = 0;
    String fileType;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listview);

        MedName = "med";
        sMedName = "smed.txt";

        ctx = getApplication();
        level = Main.level;

        ListView = (ListView) findViewById(R.id.user_list);

        String fileType;
        if (Main.setcn == false)
        {
            fileType = "UTF-8";
            Name = MedName;
        }
        else
        {
            // 簡體字
            fileType = "Unicode";
            Name = sMedName;
        }
        //  super.onCreate(savedInstanceState);
        Main.list = new ArrayList<String>();
        // 是否有完過這個遊戲 0.  沒有完過 1. 有錯 2. 全對

        if (Main.setcn == false) {
            // 繁體
            i = 0;

            cnt = 100;
            for (i = 0; i < cnt ; i++) {
                name = "m" + i + ".txt";
                //  why name is null  here
                if (name != null) {
                    sname = name;
              //      Log.d("cfh", "sname = " + sname);
                    try {
                        InputStream inputStream = ctx.
                                openFileInput(sname);
                        if (inputStream != null) {

                            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, fileType), BUFFER_SIZE);
                            String str = "";
                            while ((str = br.readLine()) != null) {
                                //  Log.d("cfh", "str = " + str);
                                ret = Integer.parseInt(str);
                                // 1.   全對
                                if (ret == 1) {
                                    //   Log.d("cfh", "1. ret = " + ret + " " + sname);
                                    Main.list.add("1");
                                } else {
                                    // 2. 有錯
                                    if (ret == 2) {
                                        // Log.d("cfh", "2. ret = " + ret + " " + sname);
                                        Main.list.add("2");
                                    }
                                }
                            }
                            br.close();
                        }  // if NULL

                    } catch (IOException e) {
                        Main.list.add("0");
                        //  Log.e("cfh", "File write failed: " + e.toString());
                    } // try
                }
            }
        }
        else
        {
            // 簡體字
            fileType = "UTF-8";
            // fileType = "Unicode";
            cnt = 100;
            for (i = 0; i < cnt; i++)
            {
                sname = "sm" + i + ".txt";
                try {
                    InputStream inputStream = ctx.openFileInput(sname);
                    //   Log.d("HSIEH","Path = "+ ctx.getFileStreamPath(sname));
                    if (inputStream != null) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,fileType), BUFFER_SIZE);
                        String str = "";
                        // /data/user/0/com.Game.ccmusa.android/files/se0.txt
                        Log.d("cfh","Path = "+ ctx.getFileStreamPath(sname));
                        while ((str = br.readLine()) != null) {
                            Log.d("cfh", "簡體字 str = " + str);

                            ret = Integer.parseInt(str);
                            //    ret = Integer.toString(str)
                            // 1. 有錯

                            if (ret == 1) {
                                //   Log.d("cfh", "1. 簡體字 ret = " + ret + " " + sname);
                                Main.list.add("1");
                            } else {
                                // 2. 全對
                                if (ret == 2) {
                                    //  Log.d("cfh", "2. ret = " + ret + " " + sname);
                                    Main.list.add("2");
                                }
                            }
                        }
                        br.close();
                    }

                } catch (IOException e) {
                    Main.list.add("0");
                    //  Log.e("cfh", "File write failed: " + e.toString());
                }
            }  // for loop here
        }

        //  Log.d("cfh", "Main.list.size() " + Main.list.size());
        ArrayList DataList = getListData();
        Main.L2Text = false;
        Main.L3Text = false;
        ListView.setAdapter(new CustomListAdapter(this, DataList));
        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                // Log.d("cfh", "bef position = " + position);
                Main.filenumber = position;  // ? need add number here
                Log.d("cfh", "aft position = " + position);
                ListItem user = (ListItem) ListView.getItemAtPosition(position);
                Intent n = new Intent(ctx, DifficultGames.class);
                startActivity(n);
                //    continueButton.setEnabled(false);

            }
        });

    }
    private ArrayList getListData()
    {
        ArrayList<ListItem> results = new ArrayList<>(401);

        String fileType;
        if (Main.setcn == false) {
            fileType = "UTF-8";
            // Log.d("cfh", "214 UTF-8");
        }
        else {
            fileType = "Unicode";
            // Log.d("cfh", "218 Unicode");
        }
        Log.d("cfh", "open file name " + Name);
        try
        {
            InputStream input1 = getAssets().open(Name);  // Name easy.txt
            BufferedReader br = new BufferedReader(new InputStreamReader(input1,
                    fileType), BUFFER_SIZE);
            try
            {
                while ((readLine = br.readLine()) != null)
                {
                    ListItem user = new ListItem();
                    if (i == 0)
                    {
                        if (Main.setcn == false)
                        {
                            user.setName(readLine.substring(1));
                        }
                        else
                        {
                            user.setName(readLine);
                        }
                    }
                    else
                    {
                        user.setName(readLine);
                    }
                    results.add(user);
                    i++;
                }
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return results;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //  Log.d("CFH", "GameTableList" +  " NewBtn = " + Main.GameBtn);
        Intent main = new Intent(ctx, GameLevel.class);
        startActivity(main);
        finish();
        return;
    }


}
