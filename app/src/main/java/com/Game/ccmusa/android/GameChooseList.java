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

public class GameChooseList extends Activity
{
    GridView chooseGV;
    String name;
    String sname;
    int cnt = 0;
    int i = 0;
    int ret = 0;
    String fileType;
    Context ctx;
    int BUFFER_SIZE = 50;
    int n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamechooselist);
        ctx = getApplication();
        Main.list = new ArrayList<String>();

        // 是否有完過這個遊戲 0.  沒有完過 1. 有錯 2. 全對
        Main.myArrayCh = new ArrayList<String>();
        Main.myArrayChInf = new ArrayList<String>();
        Main.choosereadjson = 0;
        Main.choosebackPressed = 0;

        fileType = "UTF-8";
        for (i = 0; i < 30 ; i++)
        {
            if (Main.setcn == false)
                name = "ce" + i + ".txt";
            else
                name = "cse" + i + ".txt";

            if (name != null)
            {
                sname = name;
                   //  Log.d("cfh", "open to see Error sname = " + sname);
                    try {
                        InputStream inputStream = ctx.
                                openFileInput(sname);
                        if (inputStream != null) {

                            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, fileType), BUFFER_SIZE);
                            String str = "";
                            while ((str = br.readLine()) != null) {
                                //  Log.d("cfh", "str = " + str);
                                ret = Integer.parseInt(str);
                           //     Log.d("cfh", "began ret = " + ret + " " + sname);
                                // 1.   全對
                                if (ret == 1)
                                {

                                 //   Log.d("cfh", "1. ret = " + ret + " " + sname);
                                    Main.list.add("1");
                                    Main.myArrayCh.add("1");
                                    Main.myArrayChInf.add("1");
                                    Main.saveindx = i;
                                }
                                else
                                {
                                    // 2. 有錯
                                    if (ret == 2)
                                    {
                                   //     Log.d("cfh", "2. ret = " + ret + " " + sname);
                                        Main.list.add("2");
                                        Main.myArrayCh.add("1");
                                        Main.myArrayChInf.add("2");
                                        Main.saveindx = i;
                                        Main.saveerr = ret;
                                    }
                                    else
                                    {
                                   //     Log.d("cfh", "3. ret = " + ret + " " + sname);
                                        Main.myArrayCh.add("1");
                                        Main.myArrayChInf.add("3");
                                        Main.saveerr = ret;
                                        Main.saveindx = i;
                                    }
                                }
                            }
                            br.close();
                        }
                        // if NULL

                    }
                    catch (IOException e)
                    {
                        if (i == 0 )
                        {
                            Main.list.add("0");
                            Main.myArrayCh.add("1");
                            Main.myArrayChInf.add("0");
                         //   Log.e("cfh", "File write failed: " + e.toString());
                        }
                        else
                        {
                            n =  i - 1;
                         //   Log.d("cfh", "n  = " + n + " i " + i + " sidx " + Main.saveindx);
                            if ( n == Main.saveindx)
                            {
                                if ( Main.saveerr == 3)
                                {
                                  //  Log.e("cfh", "File index =  " + i + " n = " + n);
                                    Main.myArrayCh.add("1");
                                    Main.myArrayChInf.add("0");
                                }
                                else
                                {
                                    if (Main.saveerr == 2)
                                    {
                                        Main.myArrayCh.add("1");
                                        Main.myArrayChInf.add("0");
                                    }
                                    else
                                    {
                                      //  Log.e("cfh", "error  =  " + Main.saveerr);
                                        Main.myArrayCh.add("0");
                                    }
                                }
                            }
                            else
                            {
                               // Log.e("cfh", "n = " + n );
                                Main.myArrayCh.add("0");

                            }
                        }
                    } // try
                }
        }  // for loop 繁體

        chooseGV = findViewById(R.id.idchoose);
        final ArrayList<ChooseModel> chooseModelArrayList = new ArrayList<ChooseModel>();

        chooseModelArrayList.add(new ChooseModel("0", R.drawable.choose, R.drawable.num01,R.mipmap.err));
        chooseModelArrayList.add(new ChooseModel("1", R.drawable.choose, R.drawable.num02,R.mipmap.err));
        chooseModelArrayList.add(new ChooseModel("2", R.drawable.choose, R.drawable.num03,R.mipmap.err));
        chooseModelArrayList.add(new ChooseModel("3", R.drawable.choose, R.drawable.num04,R.mipmap.err));
        chooseModelArrayList.add(new ChooseModel("4", R.drawable.choose, R.drawable.num05,R.mipmap.err));
        chooseModelArrayList.add(new ChooseModel("5", R.drawable.choose, R.drawable.num06,R.mipmap.err));
        chooseModelArrayList.add(new ChooseModel("6", R.drawable.choose, R.drawable.num07,R.mipmap.err));
        chooseModelArrayList.add(new ChooseModel("7", R.drawable.choose, R.drawable.num08,R.mipmap.err));
        chooseModelArrayList.add(new ChooseModel("8", R.drawable.choose, R.drawable.num09,R.mipmap.err));
        chooseModelArrayList.add(new ChooseModel("9", R.drawable.choose, R.drawable.num10,R.mipmap.err));
        chooseModelArrayList.add(new ChooseModel("10", R.drawable.choose, R.drawable.num11,R.mipmap.err));
        chooseModelArrayList.add(new ChooseModel("11", R.drawable.choose, R.drawable.num12,R.mipmap.err));
        chooseModelArrayList.add(new ChooseModel("12", R.drawable.choose, R.drawable.num13,R.mipmap.err));
        chooseModelArrayList.add(new ChooseModel("13", R.drawable.choose, R.drawable.num14,R.mipmap.err));
        chooseModelArrayList.add(new ChooseModel("14", R.drawable.choose, R.drawable.num15,R.mipmap.err));
        chooseModelArrayList.add(new ChooseModel("15", R.drawable.choose, R.drawable.num16,R.mipmap.err));
        chooseModelArrayList.add(new ChooseModel("16", R.drawable.choose, R.drawable.num17,R.mipmap.err));
        chooseModelArrayList.add(new ChooseModel("17", R.drawable.choose, R.drawable.num18,R.mipmap.err));
        chooseModelArrayList.add(new ChooseModel("18", R.drawable.choose, R.drawable.num19,R.mipmap.err));
        chooseModelArrayList.add(new ChooseModel("19", R.drawable.choose, R.drawable.num20,R.mipmap.err));
        chooseModelArrayList.add(new ChooseModel("20", R.drawable.choose, R.drawable.num21,R.mipmap.err));
        chooseModelArrayList.add(new ChooseModel("21", R.drawable.choose, R.drawable.num22,R.mipmap.err));
        chooseModelArrayList.add(new ChooseModel("22", R.drawable.choose, R.drawable.num23,R.mipmap.err));
        chooseModelArrayList.add(new ChooseModel("23", R.drawable.choose, R.drawable.num24,R.mipmap.err));
        chooseModelArrayList.add(new ChooseModel("24", R.drawable.choose, R.drawable.num25,R.mipmap.err));
        chooseModelArrayList.add(new ChooseModel("25", R.drawable.choose, R.drawable.num26,R.mipmap.err));
        chooseModelArrayList.add(new ChooseModel("26", R.drawable.choose, R.drawable.num27,R.mipmap.err));
        chooseModelArrayList.add(new ChooseModel("27", R.drawable.choose, R.drawable.num28,R.mipmap.err));
        chooseModelArrayList.add(new ChooseModel("28", R.drawable.choose, R.drawable.num29,R.mipmap.err));
        chooseModelArrayList.add(new ChooseModel("29", R.drawable.choose, R.drawable.num30,R.mipmap.err));

        ChooseGVAdapter adapter = new ChooseGVAdapter(this, chooseModelArrayList);
        chooseGV.setAdapter(adapter);
        chooseGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {
                Main.cnum = position;
                if  (Main.myArrayCh.get(position) == "1" )
                {
                    Intent intent = new Intent(GameChooseList.this, ChooseGridList.class);
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
