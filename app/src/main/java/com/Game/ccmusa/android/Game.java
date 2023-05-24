package com.Game.ccmusa.android;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

// import static com.Game.ccmusa.android.Main.continueButton;
import static com.Game.ccmusa.android.Main.newButton;

import androidx.appcompat.app.AppCompatActivity;
public class Game extends AppCompatActivity implements
                                            SheetDialog.SheetListener
{
    private static final String TAG = "cfh";
    public static Context context;
    public static final String KEY_DIFFICULTY="" ;
    public static final int EASY = 1;
    public static final int MEDIUM = 2;
    public static final int HARD = 3;
    protected static final int CONTINUE = -1;
    public static int Error =  0;
    public  String easyBible;
    public  String mediumBible;
    public  String mediumBibleOk;
    public  String easyBibleOk;
    public  String easyfinal;
    public  String mediumfinal;
    public  String hardfinal;
    public String hardBibleOk;
    public  String hardBible;
    public  String easykeys;
    public  String mediumkeys;
    public  String hardkeys;
    public static String  puzzlebible[];
    public static String  puzzleKeys[];
    public static String  puzzleOK[];
    public String  puzzleNo[];
    public static  int gamecount = 0;
    public static  int GameOver = 0;
    public static String mName;
    protected String resourceType;
    public Game game;
    public static int level;
    public int randomNumber = 0;
    public  String error;
    public long start_time;
    public long end_time;
    public String fcbtn ="btn";
    public String Ctn = "ctn";
    public String numEnable = "enable";
    public String Name = "";
    public static Button  SkipBottomSheet, ResultBottomSheet;

    //
    //  1.1 Game 02/29/2016 Start
    //

    public String fe = "e1";
    public String fm = "m1";
    public  String fh = "h1";

    public  int flage = 1;
    public int flagm = 1;
    public  int flagh = 1;
    public  int num = 0;
    public  String puzOK;
    public  String puzNo;
    public  static String item;
    public  static String item1;
    public  static String item2;
    public  static String item2key;
    public  static String item3;
    public static int item2cnt = 0;

    //
    //  1.3 Game 07/10/2019
    //

    public  static List<String> QnsSouces = new ArrayList<>();
    public  static List<String> AnsSouces = new ArrayList<>();
    public  static List<String> errSouces = new ArrayList<>();
    public static GridView gridQns;
    public static GridView gridAns;
    public static GridViewAdapter qnsAdapter;
    public static GridViewAdapter ansAdapter;
    public static GridViewAdapter errAdapter;
    private String[] Err = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(layoutResID, null);
        resourceType = (String) view.getTag();
        Log.d("cfh", "Game tag = " + resourceType);
        super.setContentView(view);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        int rlevel = 0;
        String EasyName;
        String MedName;
        String HardName;
        int BUFFER_SIZE = 50;
        String readLine;

        context = getApplicationContext();
        super.onCreate(savedInstanceState);

        Main.level = 2;
        level = Main.level;

        if (Main.setcn == false)  // 繁體中文
        {
            EasyName = "easy";
            MedName = "hard";
            HardName = "med";
        }
        else                      // 简体中文
        {
            EasyName = "seasy";
            MedName = "shard";
            HardName = "smed";
        }
        if (level == 1)
        {
            Name = EasyName + (int) Main.filenumber;
        }
        else {
            if (level == 2)   //  選擇較難
            {
                Name = MedName + (int) Main.filenumber;
            } else {
                if (level == 3)  // 選擇更難
                {
                    Name = HardName + (int) Main.filenumber;
                }
            }
        }
        String fileType;
        if (Main.setcn == false) {
            fileType = "UTF-8";
            //   Log.d("cfh", "繁體中文");
        }
        else {
            fileType = "Unicode";
            //    Log.d("cfh", "简体中文");
        }
        //  Save file name for  選擇較難
        if (level  != 0) {
            mName = Name;
            try {
                //     Log.d("CFH", "打開 File  " + Name + " level = " + level);
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
                        //    }

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
        if (level == 1)   // 容易
        {
            //  Log.d("cfh", "read item = " + item +" item1 = " + item1 + " item2 = " +  item2);
            easyBibleOk = item;
            easyBible = item1;
            easykeys = item2;
            easyfinal = item3;
            List<String> wordList = Arrays.asList(easyBible);
            //    len = wordList.size();
            //  Log.d("cfh", "easy[1] = " + item + "keys = " + item2  + "Bible no = " + item3);
        }
        else
        {
            if (level == 2)    // 較難
            {
                int i;
                mediumBibleOk = item;
                mediumBible = item1;
                mediumkeys = item2;
                mediumfinal = item3;
            }
            else
            {
                if (level == 3)   // 更難
                {
                    hardBibleOk = item;
                    hardBible = item1;
                    gamecount = item1.length();
                    hardkeys = item2;
                    hardfinal = item3;
                }
            }
        }
        if (level == 0)
            level = 1;
        //    Log.d("cfh", "Start here 新遊戲開始把聖經經文轉換 String []");
        if ( level == 1 || level == 2) {
            puzzlebible = getPuzzleBible(level);  // 聖經填字経節Crash Here
            puzzleKeys = getPuzzleBibleKeys(level); // 填寫的經節
            puzzleOK = getPuzzleBibleOK(level);  // 聖經全部的經節
            puzzleNo = getPuzzleBibleNo(level); //  聖經章節馬太1:23
        }

        setContentView(R.layout.grid_main);

        gridQns = (GridView) findViewById(R.id.qnsView);
        gridAns = (GridView) findViewById(R.id.ansView);

        SkipBottomSheet = (Button) findViewById(R.id.skipbtn);
        ResultBottomSheet = (Button) findViewById(R.id.ResBtn);
        ResultBottomSheet.setEnabled(false);
        ResultBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SheetDialog rbottomSheet = new SheetDialog();
                rbottomSheet.newInstance(1, item, item3);
                rbottomSheet.show(getSupportFragmentManager(), "BottomSheet");
            }
        });
        SkipBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SheetDialog sbottomSheet = new SheetDialog();
                sbottomSheet.newInstance(2, item, item3);
                sbottomSheet.show(getSupportFragmentManager(), "BottomSheet");
            }
        });
        //  }
        setUpQnsGridView();
        qnsAdapter = new GridViewAdapter(QnsSouces, this, puzzleOK,
                puzzlebible, puzzleKeys, 1);
        gridQns.setAdapter(qnsAdapter);
        gridQns.invalidateViews();

        setUpAnsGridView();
        ansAdapter = new GridViewAdapter(AnsSouces, this, puzzleOK,
                puzzlebible, puzzleKeys, 2);
        gridAns.setAdapter(ansAdapter);
        gridAns.invalidateViews();

    }


    public void setUpQnsGridView()
    {
        QnsSouces.clear();
        for(String i:puzzlebible)
        {
            QnsSouces.add(i);
        }
    }


    public void setUpAnsGridView()
    {
        AnsSouces.clear();
        for(String i:puzzleKeys)
            AnsSouces.add(i);
    }


    private  String[] getPuzzleBibleKeys(int diff) {
        String puz;
        switch (diff) {

            case HARD:
                puz = hardkeys;
                break;
            case MEDIUM:
                puz = mediumkeys;
                break;
            case EASY:
            default:
                puz = easykeys;
                break;
        }
        //   Log.d(TAG, "Carl getPuzzleBibleKeys 576  = " + puz);
        return fromPuzzleStringBible(puz);
    }
    private  String[] saveBibleKeys(int diff) {
        String puz;
        switch (diff) {

            case HARD:
                puz = hardkeys;
                break;
            case MEDIUM:
                puz = item2key;
                break;
            case EASY:
            default:
                puz = item2key;
                break;
        }
        //     Log.d(TAG, "Carl getPuzzleBibleKeys = " + puz);
        return StringBible(puz);
    }
    private  String[] getPuzzleBible(int diff) {
        String puz = null;
        //  Log.d("CFH", "getPuzzleBible level = " +diff);
        switch (diff) {

            case HARD:
                //    Log.d("CFH", "HARD = " +diff);
                puz = hardBible;
                break;
            case MEDIUM:
                //    Log.d("CFH", " = MEDIUM" +diff);
                puz = mediumBible;
                break;
            case EASY:
                //   default:
                puz = easyBible;
                //     Log.d("cfh", "easyBible = " + easyBible);
                break;
        }
        // Log.d(TAG, "Carl getPuzzleBible 624 = " + puz);

        return fromPuzzleStringBible(puz);   //  Carsh Here
        //   return puz;
    }
    private  String[] getPuzzleBibleOK(int diff) {
        switch (diff) {
            case CONTINUE:

            case HARD:
                puzOK = hardBibleOk;
                break;
            case MEDIUM:
                puzOK = mediumBibleOk;
                break;
            case EASY:
            default:
                puzOK = easyBibleOk;
                break;
        }
        // Log.d(TAG, "Carl getPuzzleBibleOK 647 = " + puzOK);
        return fromPuzzleStringBible(puzOK);
    }
    private  String[] getPuzzleBibleNo(int diff) {
        switch (diff) {
            case HARD:
                puzNo = hardfinal;
                break;
            case MEDIUM:
                puzNo = mediumfinal;
                break;
            case EASY:
            default:
                puzNo = easyfinal;
                break;
        }
        //  Log.d(TAG, "Carl getPuzzleBibleNo 663 = " + puzNo);
        return fromPuzzleStringBible(puzNo);
    }



    // @android.support.annotation.RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    static protected  String[] fromPuzzleStringBible(String bible)
    {
        //
        //  Covert to String array (easy, medium and hard
        //

        String[] tempStrings = bible.split("");   // Crash  Here
        String[] sbv = new String[bible.length()];
        //  Log.d("cfh", " bible.length = " + bible.length());
        for (int i = 0; i < bible.length(); i++) {
            if (tempStrings[0].isEmpty()) {
                sbv[i] = tempStrings[i + 1];   // i + 1
                //  Log.d("cfh", "Found   " + sbv[i]);
            } else {
                sbv[i] = tempStrings[i];
                //   Log.d("cfh", "No Found   " + sbv[i]);
            }
        }
        return sbv;  // sbv

    }
    static protected String[] StringBible( String bible) {
        //
        //  Covert to String array (easy, medium and hard
        //
        String[] tempStrings = bible.split("");
        String[] sbv = new String[bible.length()];

        for (int i = 0; i < bible.length(); i++) {
            if (tempStrings[0].isEmpty()) {

                sbv[i] = tempStrings[i+1 ];

            }
            else {
                sbv[i] = tempStrings[i];

            }
            //   Log.d("cfh", "carl ch = " + sbv[i]);
        }
        return sbv;
    }
    static protected String[] fromStringBible(String bible) {
        //
        //  Covert to String array (easy, medium and hard
        //
        String[] tempStrings = bible.split(",");
        String[] sbv = new String[bible.length()];
        for (int i = 0; i < bible.length(); i++) {
            if (tempStrings[0].isEmpty())
                sbv[i] = tempStrings[i + 1];
            else
                sbv[i] = tempStrings[i ];
        }
        return sbv;
    }


    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        return;
    }

    @Override
    public void onButtonClicked(String text) {

    }
}
