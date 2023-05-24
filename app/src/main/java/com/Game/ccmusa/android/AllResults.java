package com.Game.ccmusa.android;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AllResults  extends Activity {
    public static int easy = 0;
    private static final String TAG = "CFH";

    String name;
    String sname;
    int i = 0;
    int ret = 0;
    int level;
    Context mContext;
    int BUFFER_SIZE = 50;
    String fileType;
    String tile;
    String easyover;
    String easyok;
    String easyerr;
    String easytotal;
    String yeasyover;
    String yeasyok;
    String yeasyerr;
    String yeasytotal;

    String heasyover;
    String heasyok;
    String heasyerr;
    String heasytotal;

    public ImageView Imgok, ImgErr, ImgAll;

    int ok = 0;
    int err = 0;
    int over = 0;
    int total = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allresults);


        mContext = getApplicationContext();
        fileType = "UTF-8";
        level = Main.level;

        // 選擇題
        TextView etxtok = (TextView)findViewById(R.id.textOk);
        TextView etxterr = (TextView)findViewById(R.id.textError);
        TextView etxtover = (TextView)findViewById(R.id.textErrAll);
        TextView etxttotal = (TextView)findViewById(R.id.textTotal);
        TextView etxtTile = (TextView)findViewById(R.id.textTitle);

        // 是非題
        TextView mtxtok = (TextView)findViewById(R.id.mtextOk);
        TextView mtxterr = (TextView)findViewById(R.id.mtextError);
        TextView mtxtover = (TextView)findViewById(R.id.mtextErrAll);
        TextView mtxttotal = (TextView)findViewById(R.id.mtextTotal);
        TextView mtxtTile = (TextView)findViewById(R.id.mtextTitle);

       // 填空题
        TextView htxtok = (TextView)findViewById(R.id.htextOk);
        TextView htxterr = (TextView)findViewById(R.id.htextError);
        TextView htxtover = (TextView)findViewById(R.id.htextover);
        TextView htxttotal = (TextView)findViewById(R.id.htextTotal);
        TextView htxtTile = (TextView)findViewById(R.id.htextTile);
        ReadData(1, etxtok, etxterr, etxtover, etxttotal, etxtTile );
        ReadData(2, mtxtok, mtxterr, mtxtover, mtxttotal, mtxtTile );
        ReadData(3, htxtok, htxterr, htxtover, htxttotal, htxtTile );
    }
    private void ReadData(int level,
                          TextView txtok,
                          TextView txterr,
                          TextView txtover,
                          TextView txtotal,
                          TextView txtTile)
    {
        //
        // 繁體中文 ---選擇題
        //
        if (Main.setcn == false)
        {
            if (level == 1)
            {
                int cnt = 29;

                tile = "選擇題";
                for (i = 0; i < cnt; i++)
                {
                    name = "ce" + i + ".txt";
                    sname = name;
                    try
                    {
                        InputStream inputStream = mContext.openFileInput(sname);
                        Log.d("cfh", "name = " + sname);
                        if (inputStream != null)
                        {
                            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, fileType),
                                    BUFFER_SIZE);
                            String str = "";
                            while ((str = br.readLine()) != null)
                            {
                                ret = Integer.parseInt(str);
                                //     Log.d("cfh", "e = " + ret + " name = " + sname);
                                if (ret == 1)
                                {
                                    ok++;
                                }
                                else
                                {
                                    if (ret == 2)
                                    {
                                        err++;
                                    }
                                    else
                                    {
                                        over++;
                                    }
                                }
                            }
                            br.close();
                        }

                    }
                    catch (IOException e)
                    {
                        Log.d("cfh", "name err = " + sname);
                        total++;
                    }
                }
            }
            else
            {
                if (level == 2)
                {

                    tile = "是非題";
                    int cnt = 29;

                    for (i = 0; i < cnt; i++)
                    {
                        name = "ye" + i + ".txt";
                        sname = name;
                        try
                        {
                            InputStream inputStream = mContext.openFileInput(sname);
                            if (inputStream != null)
                            {
                                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, fileType),
                                        BUFFER_SIZE);
                                String str = "";
                                while ((str = br.readLine()) != null)
                                {
                                    ret = Integer.parseInt(str);
                                    //     Log.d("cfh", "e = " + ret + " name = " + sname);
                                    if (ret == 1)
                                    {
                                        ok++;
                                    }
                                    else
                                    {
                                        if (ret == 2)
                                        {
                                            err++;
                                        }
                                        else
                                        {
                                            over++;
                                        }
                                    }
                                }
                                br.close();
                            }

                        }
                        catch (IOException e)
                        {
                            total++;
                        }
                    }
                }
                else
                {

                    tile = "填空題";
                    int cnt = 400;

                    for (i = 0; i < cnt; i++)
                    {
                        name = "h" + i + ".txt";
                        sname = name;
                        try
                        {
                            InputStream inputStream = mContext.openFileInput(sname);
                            if (inputStream != null)
                            {
                                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, fileType),
                                        BUFFER_SIZE);
                                String str = "";
                                while ((str = br.readLine()) != null)
                                {
                                    ret = Integer.parseInt(str);
                                    //     Log.d("cfh", "e = " + ret + " name = " + sname);
                                    if (ret == 1)
                                    {
                                        ok++;
                                    }
                                    else
                                    {
                                        if (ret == 2)
                                        {
                                            err++;
                                        }
                                        else
                                        {
                                            over++;
                                        }
                                    }
                                }
                                br.close();
                            }

                        }
                        catch (IOException e)
                        {
                            total++;
                        }
                    }
                }
            }
        }
        else {
            //
            //  简体中文 ---选择题
            //
            if (level == 1) {
                int cnt = 29;
                name = "sce" + i + ".txt";
                tile = "选择题";
                for (i = 0; i < cnt; i++) {
                    sname = name;
                    try {
                        InputStream inputStream = mContext.openFileInput(sname);
                        if (inputStream != null) {
                            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, fileType),
                                    BUFFER_SIZE);
                            String str = "";
                            while ((str = br.readLine()) != null) {
                                ret = Integer.parseInt(str);
                                //     Log.d("cfh", "e = " + ret + " name = " + sname);
                                if (ret == 1) {
                                    ok++;
                                } else {
                                    if (ret == 2) {
                                        err++;
                                    } else {
                                        over++;
                                    }
                                }
                            }
                            br.close();
                        }

                    } catch (IOException e) {
                        total++;
                    }
                }
            } else {
                if (level == 2) {
                    name = "sye" + i + ".txt";
                    tile = "是非题";
                    int cnt = 29;

                    for (i = 0; i < cnt; i++) {
                        sname = name;
                        try {
                            InputStream inputStream = mContext.openFileInput(sname);
                            if (inputStream != null) {
                                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, fileType),
                                        BUFFER_SIZE);
                                String str = "";
                                while ((str = br.readLine()) != null) {
                                    ret = Integer.parseInt(str);
                                    //     Log.d("cfh", "e = " + ret + " name = " + sname);
                                    if (ret == 1) {
                                        ok++;
                                    } else {
                                        if (ret == 2) {
                                            err++;
                                        } else {
                                            over++;
                                        }
                                    }
                                }
                                br.close();
                            }

                        } catch (IOException e) {
                            total++;
                        }
                    }
                } else {
                    name = "sh" + i + ".txt";
                    tile = "@string/easy_label";
                    int cnt = 400;
                    name = "h" + i + ".txt";
                    tile = "填空题";
                    for (i = 0; i < cnt; i++) {
                        sname = name;
                        try {
                            InputStream inputStream = mContext.openFileInput(sname);
                            if (inputStream != null) {
                                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, fileType),
                                        BUFFER_SIZE);
                                String str = "";
                                while ((str = br.readLine()) != null) {
                                    ret = Integer.parseInt(str);
                                    //     Log.d("cfh", "e = " + ret + " name = " + sname);
                                    if (ret == 1) {
                                        ok++;
                                    } else {
                                        if (ret == 2) {
                                            err++;
                                        } else {
                                            over++;
                                        }
                                    }
                                }
                                br.close();
                            }

                        } catch (IOException e) {
                            total++;
                        }
                    }
                }

            }

        }

        if (Main.setcn == false)
        {
            easyok = "全部答對經節:" + ok;
            easyerr = "答錯經節:" + err;
            easyover = "答錯四個字經節" + over;
            easytotal = "尚未完的經節：" + total;
        }
        else
        {
            easyok = "全部答对经节:" + ok;
            easyerr = "答错经节:" + err;
            easyover = "答錯四個字經節" + over;
            easytotal = "尚未完的經節：" + total;
        }


        txterr.setTextColor(Color.BLUE);
       txtok.setTextColor(Color.BLUE);
       txtTile.setTextColor(Color.RED);
       txtover.setTextColor(Color.BLUE);
       txtotal.setTextColor(Color.BLUE);

       txtok.setText(easyok);
       txtTile.setText(tile);
       txterr.setText(easyerr);
       txtover.setText(easyover);
       txtotal.setText(easytotal);
       ok = 0;
       err= 0;
       over = 0;
       total = 0;
     //  Log.d("cfh", "esay = " +  easytotal.toString());


    }
}
