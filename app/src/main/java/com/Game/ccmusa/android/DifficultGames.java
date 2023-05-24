package com.Game.ccmusa.android;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DifficultGames extends Activity {
    private static final String TAG = "cfh";
    public static Context context;
    public static int Error = 0;
    public boolean info;
    public static int gamecount = 0;
    public static String mName;
    public Game game;
    public static int level;
    public String txtError;
    public String error;
    public String Name = "";


    //
    //  1.9 DifficultGame 01/04/2022 Start
    //


    public static String item;
    public static String item1;
    public static String item2;
    public static String item2key;
    public static String item3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        int rlevel = 0;
        String fileType;
        String MedName;

        int BUFFER_SIZE = 50;
        String readLine;

        context = getApplicationContext();
        super.onCreate(savedInstanceState);

        String PATH = context.getFilesDir().getPath();
        String All = PATH + "/";
        level = Main.level;

        if (Main.setcn == false)  // 繁體中文
        {
            MedName = "med";
        } else                      // 简体中文
        {
            MedName = "smed";
        }

        if (Main.backbtn == true)
        {
            Log.d("cfh", " filenumber = " + Main.filenumber);
            Name = MedName + (int) Main.filenumber;

            if (Main.setcn == false) {
                fileType = "UTF-8";
                //   Log.d("cfh", "繁體中文");
            } else {
                fileType = "Unicode";
                //    Log.d("cfh", "简体中文");
            }
            //  Save file name for  選擇較難

            mName = Name;
            try {
              //  Log.d("CFH", "打開 File  " + Name);
                InputStream input1 = getAssets().open(Name);  // Name
                BufferedReader br = new BufferedReader(new InputStreamReader(input1, fileType), BUFFER_SIZE);
                //
                int i = 0;
                try {
                    while ((readLine = br.readLine()) != null) {
                        if (i == 4) {
                            break;
                        }
                        //    if (level == 1) {
                        if (i == 0) {
                            if (Main.setcn == false) {
                                item = readLine.substring(1);  //  聖經填字経節
                                //       Log.d("cfh", "繁體中文");
                            } else {
                                item = readLine;    //  聖經填字経節
                                //      Log.d("cfh", "3简体中文");
                            }

                        } else {
                            if (i == 1)  // // 填寫的經節   Keys
                            {
                                item1 = readLine;
                            } else {
                                if (i == 2)    // 聖經全部的經節
                                {
                                    item2 = readLine;
                                    item2key = readLine;
                                } else {
                                    if (i == 3)   // 聖經章節馬太1:23
                                    {
                                        item3 = readLine;
                                    }
                                }
                            }
                        }
                        i++;
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                br.close();
                input1.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else
            {
                Name = MedName + (int) Main.filenumber;
                Game.mName = Name;
                if (Main.setcn == false) {
                    fileType = "UTF-8";
                    //   Log.d("cfh", "繁體中文");
                } else {
                    fileType = "Unicode";
                    //    Log.d("cfh", "简体中文");
                }
                //  Save file name for  選擇較難

                mName = Name;
                try {
                  //  Log.d("CFH", "打開 File  " + Name);
                    InputStream input1 = getAssets().open(Name);  // Name
                    BufferedReader br = new BufferedReader(new InputStreamReader(input1, fileType), BUFFER_SIZE);
                    //
                    int i = 0;
                    try {
                        while ((readLine = br.readLine()) != null) {
                            if (i == 4) {
                                break;
                            }
                            //    if (level == 1) {
                            if (i == 0) {
                                if (Main.setcn == false) {
                                    item = readLine.substring(1);  //  聖經填字経節
                                    //       Log.d("cfh", "繁體中文");
                                } else {
                                    item = readLine;    //  聖經填字経節
                                    //      Log.d("cfh", "3简体中文");
                                }

                            } else {
                                if (i == 1)  // // 填寫的經節   Keys
                                {
                                    item1 = readLine;
                                    gamecount = item1.length();
                                } else {
                                    if (i == 2)    // 聖經全部的經節
                                    {
                                        item2 = readLine;
                                        item2key = readLine;
                                    } else {
                                        if (i == 3)   // 聖經章節馬太1:23
                                        {
                                            item3 = readLine;
                                        }
                                    }
                                }
                            }
                            i++;
                        }
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    br.close();
                    input1.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        if ( gamecount == 15)
        {
            Intent e = new Intent(this, Game15.class);
            startActivity(e);
        }
        else
        {
            Intent e = new Intent(this, Game25.class);
            startActivity(e);
        }
    }

}
