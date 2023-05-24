package com.Game.ccmusa.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GameYandNList extends Activity
{
    GridView yandnGV;
    String name;
    String sname;
    int cnt = 0;
    int i = 0;
    int n = 0;
    int ret = 0;
    String fileType;
    Context ctx;
    int BUFFER_SIZE = 50;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameyandnlist);
        ctx = getApplication();
        Main.list = new ArrayList<String>();
        Main.myArrayYandn = new ArrayList<String>();
        Main.myArrayYandNInf = new ArrayList<String>();
        // 是否有完過這個遊戲 0.  沒有完過 1. 有錯 2. 全對

        Main.yandnreadjson = 0;
        Main.yandnbackPressed = 0;

        fileType = "UTF-8";
        for (i = 0; i < 30 ; i++)
        {
            if (Main.setcn == false)
                name = "ye" + i + ".txt";
            else
                name = "yse" + i + ".txt";
            if (name != null)
            {
                sname = name;
                Log.d("cfh", "sname = " + sname);
                try
                {
                    InputStream inputStream = ctx.
                                openFileInput(sname);
                    if (inputStream != null)
                    {

                        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, fileType), BUFFER_SIZE);
                        String str = "";
                        while ((str = br.readLine()) != null) {
                                //  Log.d("cfh", "str = " + str);
                            ret = Integer.parseInt(str);
                     //           Log.d("cfh", "began ret = " + ret + " " + sname);
                                // 1.   全對
                            if (ret == 1)
                            {
                           //     Log.d("cfh", "1. ret = " + ret + " " + sname);
                                Main.list.add("1");
                                Main.myArrayYandn.add("1");
                                Main.myArrayYandNInf.add("1");
                                Main.saveindx = i;
                            }
                            else
                            {
                                    // 2. 有錯
                                if (ret == 2)
                                {
                              //      Log.d("cfh", "2. ret = " + ret + " " + sname);
                                    Main.list.add("2");
                                    Main.myArrayYandn.add("1");
                                    Main.myArrayYandNInf.add("2");
                                    Main.saveindx = i;
                                    Main.saveerr = ret;
                                }
                                else
                                {
                                //    Log.d("cfh", "3. ret = " + ret + " " + sname);
                                    Main.myArrayYandNInf.add("3");
                                    Main.myArrayYandn.add("1");
                                    Main.saveindx = i;
                                    Main.saveerr = ret;
                                }
                            }
                        }
                            br.close();
                    }  // if NULL

                }
                catch (IOException e)
                {
                    Log.e("cfh", "File write failed: " + sname);
                    if (i == 0 )
                    {
                        Main.list.add("0");
                        Main.myArrayYandn.add("1");
                        Main.myArrayYandNInf.add("0");
                      //  Log.e("cfh", "File write failed: " + e.toString());
                    }
                    else
                    {
                        n =  i - 1;  // ????? i
                      //  Log.d("cfh", "n  = " + n + " i " + i + " sidx " + Main.saveindx);
                        if ( n == Main.saveindx)
                        {
                            if ( Main.saveerr == 3)
                            {
                                   //  Log.e("cfh", "File index =  " + i + " n = " + n);
                                Main.myArrayYandn.add("1");
                                Main.myArrayYandNInf.add("0");
                            }
                            else
                            {
                                if ( Main.saveerr == 2)
                                {
                                    Main.myArrayYandn.add("1");
                                    Main.myArrayYandNInf.add("0");
                                }
                                else
                                {
                                    Log.e("cfh", "error  =  " + Main.saveerr);
                                    Main.myArrayYandn.add("0");
                                }
                            }
                        }
                        else
                        {
                                // Log.e("cfh", "n = " + n );
                            Main.myArrayYandn.add("0");

                        }
                    }
                } // try
            }
        }  // for loop 繁體



        yandnGV = findViewById(R.id.idchoose);
        final ArrayList<YandNModel> yandnModelArrayList = new ArrayList<YandNModel>();

        yandnModelArrayList.add(new YandNModel("0", R.drawable.yandn, R.drawable.num01,R.mipmap.err));
        yandnModelArrayList.add(new YandNModel("1", R.drawable.yandn, R.drawable.num02,R.mipmap.err));
        yandnModelArrayList.add(new YandNModel("2", R.drawable.yandn, R.drawable.num03,R.mipmap.err));
        yandnModelArrayList.add(new YandNModel("3", R.drawable.yandn, R.drawable.num04,R.mipmap.err));
        yandnModelArrayList.add(new YandNModel("4", R.drawable.yandn, R.drawable.num05,R.mipmap.err));
        yandnModelArrayList.add(new YandNModel("5", R.drawable.yandn, R.drawable.num06,R.mipmap.err));
        yandnModelArrayList.add(new YandNModel("6", R.drawable.yandn, R.drawable.num07,R.mipmap.err));
        yandnModelArrayList.add(new YandNModel("7", R.drawable.yandn, R.drawable.num08,R.mipmap.err));
        yandnModelArrayList.add(new YandNModel("8", R.drawable.yandn, R.drawable.num09,R.mipmap.err));
        yandnModelArrayList.add(new YandNModel("9", R.drawable.yandn, R.drawable.num10,R.mipmap.err));
        yandnModelArrayList.add(new YandNModel("10", R.drawable.yandn, R.drawable.num11,R.mipmap.err));
        yandnModelArrayList.add(new YandNModel("11", R.drawable.yandn, R.drawable.num12,R.mipmap.err));
        yandnModelArrayList.add(new YandNModel("12", R.drawable.yandn, R.drawable.num13,R.mipmap.err));
        yandnModelArrayList.add(new YandNModel("13", R.drawable.yandn, R.drawable.num14,R.mipmap.err));
        yandnModelArrayList.add(new YandNModel("14", R.drawable.yandn, R.drawable.num15,R.mipmap.err));
        yandnModelArrayList.add(new YandNModel("15", R.drawable.yandn, R.drawable.num16,R.mipmap.err));
        yandnModelArrayList.add(new YandNModel("16", R.drawable.yandn, R.drawable.num17,R.mipmap.err));
        yandnModelArrayList.add(new YandNModel("17", R.drawable.yandn, R.drawable.num18,R.mipmap.err));
        yandnModelArrayList.add(new YandNModel("18", R.drawable.yandn, R.drawable.num19,R.mipmap.err));
        yandnModelArrayList.add(new YandNModel("19", R.drawable.yandn, R.drawable.num20,R.mipmap.err));
        yandnModelArrayList.add(new YandNModel("20", R.drawable.yandn, R.drawable.num21,R.mipmap.err));
        yandnModelArrayList.add(new YandNModel("21", R.drawable.yandn, R.drawable.num22,R.mipmap.err));
        yandnModelArrayList.add(new YandNModel("22", R.drawable.yandn, R.drawable.num23,R.mipmap.err));
        yandnModelArrayList.add(new YandNModel("23", R.drawable.yandn, R.drawable.num24,R.mipmap.err));
        yandnModelArrayList.add(new YandNModel("24", R.drawable.yandn, R.drawable.num25,R.mipmap.err));
        yandnModelArrayList.add(new YandNModel("25", R.drawable.yandn, R.drawable.num26,R.mipmap.err));
        yandnModelArrayList.add(new YandNModel("26", R.drawable.yandn, R.drawable.num27,R.mipmap.err));
        yandnModelArrayList.add(new YandNModel("27", R.drawable.yandn, R.drawable.num28,R.mipmap.err));
        yandnModelArrayList.add(new YandNModel("28", R.drawable.yandn, R.drawable.num29,R.mipmap.err));
        yandnModelArrayList.add(new YandNModel("29", R.drawable.yandn, R.drawable.num30,R.mipmap.err));

        YandNGVAdapter adapter = new YandNGVAdapter(this, yandnModelArrayList);
        yandnGV.setAdapter(adapter);
        yandnGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Main.ynum = position;
                if  (Main.myArrayYandn.get(position) == "1" )
                {
                    Intent intent = new Intent(GameYandNList.this, YandNGridList.class);
                    //  intent.putExtra("image", chooseModelArrayList[position]); // put image data in Intent
                    startActivity(intent); // start Intent
                }

            }
        });
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
