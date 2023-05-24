package com.Game.ccmusa.android;

import static android.graphics.Color.BLACK;

import static com.Game.ccmusa.android.GridViewAdapter.DropAll;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Game15 extends Activity
         {
    private TextView Counter;
    private TextView BibleText;
    private TextView BibleNo;
    private Button[] buttons;
    private Boolean bad_move=false;
    private static final String TAG = "CFH";
    public String easy[] = new String[10];
    public String easyBibleOk;
    public String  puzzleOK[];
    public String[] puzOK;
    public int length = 0;
    public boolean en = true;
    private Chronometer Focus;
    public Button[] bB;
    String name;
    BufferedReader br = null;
    int k = 0;
    InputStream input1;
    String mLine;
    public  String MSG1;
    public  String MSG2;
    public  String MSG;
    ImageView Image;
    String fileType;
    Context ctx;
    public  int x01, y01, x02, y02,  x03, y03, x04, y04;
    public  int x11, y11, x12, y12,  x13, y13, x14, y14;
    public  int x21, y21, x22, y22,  x23, y23, x24, y24;
    public  int x31, y31, x32, y32,  x33, y33, x34, y34;
    public int x, y;
    private static final Integer[] btnnumber = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15 ,0};
    private ArrayList<Integer> btn = new ArrayList<Integer>();
    public Button SkipBtn;
    public TextView mBibleNo;
    public  static String item;
    public  static String item1;
    public  static String item2;
    public  static String item2key;
    public  static String item3;
    public Context mContext;
    Animation rightAnim;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game15);
        mContext = getApplicationContext();
        if (Main.setcn == false) {
            fileType = "UTF-8";
        }
        else {
            fileType = "Unicode";
        }

        if (Main.setcn == false)
        {
            name = Game.mName;
        }
        else
        {
            name = Game.mName;
        }
        k = 0;
        try {

          //  Log.d(TAG, "open file Name:  " + name);
            input1 = getAssets().open(name);
            br = new BufferedReader(new InputStreamReader(input1,fileType), 50);;
            while ((mLine = br.readLine()) != null) {
                if (k == 4) {
                    break;
                }
                //    if (level == 1) {
                if (k == 0) {
                    if (Main.setcn == false) {
                        item = mLine.substring(1);  //  聖經填字経節
                        //       Log.d("cfh", "繁體中文");
                    } else {
                        item = mLine;    //  聖經填字経節
                        //      Log.d("cfh", "3简体中文");
                    }

                } else {
                    if (k == 1)  // // 填寫的經節   Keys
                    {
                        item1 = mLine;
                    } else {
                        if (k == 2)    // 聖經全部的經節
                        {
                            item2 = mLine;
                            item2key = mLine;
                        } else {
                            if (k == 3)   // 聖經章節馬太1:23
                            {
                                item3 = mLine;
                            }
                        }
                    }

                }
                //    }

                k++;
            }
        } catch (IOException e) {
            //log the exception
            Log.d(TAG, "error cfh " + e);
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                    //     input1.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        easyBibleOk = item1;
        length = item1.length();
    //    Log.d("cfh", " " + easyBibleOk);
        puzzleOK = fromPuzzleStringBible(easyBibleOk);

        buttons=findButtons();       // insert Bible char
        //  Debug only  disable here
       //  /*
       for(int i=0;i < length+1 ;i++)  // 16
       {
          this.btn.add(i);
       }
       // */

       //Debug only  enable Here
        /*
        this.btn.add(1);
        this.btn.add(2);
        this.btn.add(3);
        this.btn.add(4);
        //  2
        this.btn.add(5);
        this.btn.add(6);
        this.btn.add(7);
        this.btn.add(8);
        // 3
        this.btn.add(9);
        this.btn.add(10);
        this.btn.add(11);
        this.btn.add(12);
        // 4
        this.btn.add(13);         //   11
        this.btn.add(14);          //   15
        this.btn.add(0);        //  12
        this.btn.add(15);         //  15
        */
      Collections.shuffle(this.btn); //random btn array

        setButton();

        Counter = (TextView) findViewById(R.id.Counter);
        //   MsgText = (TextView) findViewById(R.id.MsgText);
        BibleText = (TextView) findViewById(R.id.BibleText);
        BibleNo = (TextView) findViewById(R.id.BibleNo);
        TextView TV = (TextView) findViewById(R.id.BibleText);
        Focus = (Chronometer) findViewById(R.id.Chronometer1);
        Image  = (ImageView) findViewById(R.id.imageView);
         SkipBtn = (Button)  findViewById(R.id.skipbtn);
       // Log.d("cfh", "Skip = " + SkipBtn);

        Focus.setFormat("%s");
        //  Focus.getFormat();
        for (int i = 1; i < 16; i++) {
            buttons[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Move((Button) v);
                }
            });
        }
        Focus.start();

        Counter.setText("0");
        //  MsgText.setText(easy[2]);
        puzOK = puzzleOK;
        if (Main.dens == 640)
        {
            BibleText.setTextSize(14);
            BibleNo.setTextSize(14);
        }
        else {
            if (Main.dens == 480) {
                BibleText.setTextSize(18);
                BibleNo.setTextSize(20);
            } else if (Main.dens == 420) {
                BibleText.setTextSize(18);
                BibleNo.setTextSize(18);
            } else {
                if (Main.dens == 400) {
                    BibleText.setTextSize(18);
                    BibleNo.setTextSize(18);
                } else {
                    if (Main.dens == 360) {
                        BibleText.setTextSize(12);
                        BibleNo.setTextSize(20);
                    } else {
                        if (Main.dens == 320) {
                            BibleText.setTextSize(18);
                            BibleNo.setTextSize(18);
                        } else {   // 240 and 214 7in Tablet
                            BibleText.setTextSize(22);
                            BibleNo.setTextSize(20);
                        }
                    }
                }
            }
        }

     //   BibleText.setText(item);   // display bible words
        BibleNo.setText(item2);   // display bible words

        SkipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AnimationDrawable frameAnimation;
                Button  rtnButton;
                String MSG1;
                String MSG2;
                String MSG;
                Animation rightAnim;

                final Dialog dialog = new Dialog(mContext);
                if (Main.setcn == false)
                    dialog.setTitle(Main.tlang[4]);
                else
                    dialog.setTitle(Main.slang[4]);

                setContentView(R.layout.displaydialog);
                if (Main.setcn == false)
                {
                    MSG1 = Main.tlang[6]  + ":" + Focus.getText() + "\n";
                    MSG2 = Main.tlang[5] + ":" + Counter.getText();
                    MSG = MSG1 + MSG2;
                }
                else
                {
                    MSG1 = Main.slang[6] + ":" + Focus.getText() + "\n";
                    MSG2 = Main.slang[5] + ":" + Counter.getText();
                    MSG = MSG1 + MSG2;
                }

                TextView mBibleNo = (TextView) findViewById(R.id.BibleNo);
                ImageView biblegif = (ImageView) findViewById(R.id.imageView);
                TextView txtBible = (TextView) findViewById(R.id.bible_verse);
                TextView No = (TextView) findViewById(R.id.game_value);
                TextView bible = (TextView) findViewById(R.id.game_bible);
                Glide.with(mContext).load(R.drawable.bad).into(biblegif);
                rightAnim = AnimationUtils.loadAnimation(mContext, R.anim.leftmove);
                biblegif.startAnimation(rightAnim);
                txtBible.setText(MSG);  // Ans
                No.setText(item);  //  經節
                bible.setText(item2);   // Bible No
                bible.setTextColor(BLACK);
                No.setTextColor(BLACK);
                txtBible.setTextColor(BLACK);
                rtnButton = (Button) findViewById(R.id.MainMeue);

                rtnButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {

                        Intent m = new Intent(mContext,  DiffTableList.class);
                        startActivity(m);
                        dialog.dismiss();
                        finish();
                    }
                });
            }
        });
    }
    public Button[] findButtons() {
        bB = new Button[17];

        bB[0] = ((Button) findViewById(R.id.Button00));
        bB[1] = (Button) findViewById(R.id.Button01);
        bB[2] = (Button) findViewById(R.id.Button02);
        bB[3] = (Button) findViewById(R.id.Button03);
        bB[4] = (Button) findViewById(R.id.Button04);
        bB[5] = (Button) findViewById(R.id.Button05);
        bB[6] = (Button) findViewById(R.id.Button06);
        bB[7] = (Button) findViewById(R.id.Button07);
        bB[8] = (Button) findViewById(R.id.Button08);
        bB[9] = (Button) findViewById(R.id.Button09);
        bB[10] = (Button) findViewById(R.id.Button10);
        bB[11] = (Button) findViewById(R.id.Button11);
        bB[12] = (Button) findViewById(R.id.Button12);
        bB[13] = (Button) findViewById(R.id.Button13);
        bB[14] = (Button) findViewById(R.id.Button14);
        bB[15] = (Button) findViewById(R.id.Button15);

        puzOK = puzzleOK;
        int g = 0;
        for (int j = 1; j < length + 1; ++j) {
            if (g != 16) {
                if (Main.dens == 640)
                    bB[j].setTextSize(30);
                else {
                    if (Main.dens == 480)
                        bB[j].setTextSize(30);
                    else if (Main.dens == 420)
                        bB[j].setTextSize(30);
                    else if (Main.dens == 360)
                        bB[j].setTextSize(20);
                    else if (Main.dens == 320)
                        bB[j].setTextSize(30);
                    else
                        bB[j].setTextSize(40);
                }
           //     Log.d("cfh", "G = " + g + "   " + puzOK[g] + " j = " + j);
                bB[j].setText(puzOK[g]);
                if ( bB[j].isEnabled() == false)
                    bB[j].setEnabled(en);
            }
            g++;
        }
        return bB;
    }


    public void Move(final Button bb) {
        bad_move=true;
        int btn_text = 0,bno = 0, btnNum;
        ctx = getApplicationContext();

        btn_text = CheckButtonId(bb);
        bno=findbtn(btn_text);
        btnNum=findbtn(0);

        switch(btnNum)
        {
            case(0):
                if(bno==4||bno==1)
                    bad_move=false;
                break;
            case(1):
                if(bno==0||bno==5||bno==2)
                    bad_move=false;
                break;
            case(2):

                if(bno==1||bno==6 ||bno == 3)
                    bad_move=false;
                break;
            case(3):

                if(bno==2||bno==7)
                    bad_move=false;
                break;
            case(4):

                if(bno==0||bno==8||bno==5)
                    bad_move=false;
                break;
            case(5):

                if(bno==4||bno==1||bno==6|| bno == 9)
                    bad_move=false;
                break;
            case(6):

                if(bno==5||bno==2|| bno == 7|| bno == 10)
                    bad_move=false;
                break;
            case(7):
                if(bno==6||bno==3||bno==11)
                    bad_move=false;
                break;
            case(8):
                if(bno==4 ||bno==9 || bno == 12)
                    bad_move=false;
                break;
            case(9):
                if(bno==8||bno==5 || bno == 10|| bno == 13)
                    bad_move=false;
                break;
            case(10):
                if(bno==9||bno==6 || bno == 11|| bno == 14)
                    bad_move=false;
                break;
            case(11):
                if(bno==10||bno== 7 ||  bno == 15)
                    bad_move=false;
                break;
            case(12):
                if(bno==8||  bno == 13)
                    bad_move=false;
                break;
            case(13):
                if(bno==12||bno==9 || bno == 14)
                    bad_move=false;
                break;

            case(14):
                if(bno==13||bno==10 || bno == 15)
                    bad_move=false;
                break;
            case(15):
                if(bno==14||  bno == 11)
                    bad_move=false;
                break;

        }

        if(bad_move==true)
        {
            return;
        }
        btn.remove(bno);
        btn.add(bno, 0);
        btn.remove(btnNum);
        btn.add(btnNum,btn_text);

        setButton();
        Counter.setText(Integer.toString(Integer.parseInt((String) Counter.getText())+1));
        String s;
        int k = 0;

        k= 0;
        int bId = 0;
        //  bId = CheckButtonId(bb);
        for( int i= 0; i < 15; i++)   // 15
        {
            s = puzOK[k];
            char c = s.charAt(0);
            char n;
            int a = btn.get(i);
            n = getCellText(btn.get(i));
            if (c != n) {
                if (n == '0') {
                }
                return;
            } else {
                if (i != 0) {

                } else {
                    if (k == 0) {
                        ;
                    }
                }
                k++;
            }
        }
        Focus.stop();
        DropAll(mContext);
        AnimationDrawable frameAnimation;
        Button  rtnButton;
        final Dialog dialog = new Dialog(this);
        if (Main.setcn == false)
            dialog.setTitle(Main.tlang[4]);
        else
            dialog.setTitle(Main.slang[4]);

        setContentView(R.layout.displaydialog);
        if (Main.setcn == false)
        {
            MSG1 = Main.tlang[6]  + ":" + Focus.getText() + "\n";
            MSG2 = Main.tlang[5] + ":" + Counter.getText();
            MSG = MSG1 + MSG2;
        }
        else
        {
            MSG1 = Main.slang[6] + ":" + Focus.getText() + "\n";
            MSG2 = Main.slang[5] + ":" + Counter.getText();
            MSG = MSG1 + MSG2;
        }

        mBibleNo = (TextView) findViewById(R.id.BibleNo);
        ImageView biblegif = (ImageView) findViewById(R.id.imageView);
        TextView txtBible = (TextView)findViewById(R.id.bible_verse);
        TextView No = (TextView)findViewById(R.id.game_value);
        TextView bible = (TextView)findViewById(R.id.game_bible);
        Glide.with(this).load(R.drawable.awesome).into(biblegif);
        rightAnim = AnimationUtils.loadAnimation(mContext, R.anim.leftmove);
        biblegif.startAnimation(rightAnim);
        txtBible.setText(MSG);  // Ans
        No.setText(item);  //  經節
        bible.setText(item2);   // Bible No
        bible.setTextColor(BLACK);
        No.setTextColor(BLACK);
        txtBible.setTextColor(BLACK);
        rtnButton = (Button) findViewById(R.id.MainMeue);
        ctx = getApplicationContext();
        rtnButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String time;
                String count;
                //     mRef = new Firebase("https://ccmusa-1b07a.firebaseio.com/Tracks15");
                time = Focus.getText().toString();
                count = Counter.getText().toString();

                Intent m = new Intent(ctx,  DiffTableList.class);
                startActivity(m);
                dialog.dismiss();
                finish();
            }
        });

    }  // Move End

    public void setButton()
    {
        if (Main.dens == 640)    // HTC
        {
            x   = 260;
            y   = 260;
            //   1
            x01 = 180;
            y01 = 480;
            x02 = 445;
            y02 = 480;
            x03 = 710;
            y03 = 480;
            x04 = 975;
            y04 = 480;
            //    2
            x11 = 180;
            y11 = 745;
            x12 = 445;
            y12 = 745;
            x13 = 710;
            y13 = 745;
            x14 = 975;
            y14 = 745;
            //    3
            x21 = 180;
            y21 = 1010;
            x22 = 445;
            y22 = 1010;
            x23 = 710;
            y23 = 1010;
            x24 = 975;
            y24 = 1010;
            //   4
            x31 = 180;
            y31 = 1275;
            x32 = 445;
            y32 = 1275;
            x33 = 710;
            y33 = 1275;
            x34 = 975;
            y34 = 1275;

        }
        else
        {
            if (Main.dens == 400 )    // Samsung Note 3
            {

                x   = 160;
                y   = 160;
                //  1
                x01 = 110;
                y01 = 275;
                x02 = 275;
                y02 = 275;
                x03 = 440;
                y03 = 275;
                x04 = 605;
                y04 = 275;
                //    2
                x11 = 110;
                y11 = 440;
                x12 = 275;
                y12 = 440;
                x13 = 440;
                y13 = 440;
                x14 = 605;
                y14 = 440;
                //    3
                x21 = 110;
                y21 = 605;
                x22 = 275;
                y22 = 605;
                x23 = 440;
                y23 = 605;
                x24 = 605;
                y24 = 605;
                //   4
                x31 = 110;
                y31 = 770;
                x32 = 275;
                y32 = 770;
                x33 = 440;
                y33 = 770;
                x34 = 605;
                y34 = 770;

            }
            else
            {
                if (Main.dens  ==  480)
                {
                    x   = 200;
                    y   = 200;
                    //  1
                    x01 = 110;
                    y01 = 315;
                    x02 = 315;
                    y02 = 315;
                    x03 = 520;
                    y03 = 315;
                    x04 = 725;
                    y04 = 315;
                    //    2
                    x11 = 110;
                    y11 = 520;
                    x12 = 315;
                    y12 = 520;
                    x13 = 520;
                    y13 = 520;
                    x14 = 725;
                    y14 = 520;
                    //    3
                    x21 = 110;
                    y21 = 725;
                    x22 = 315;
                    y22 = 725;
                    x23 = 520;
                    y23 = 725;
                    x24 = 725;
                    y24 = 725;
                    //   4
                    x31 = 110;
                    y31 = 930;
                    x32 = 315;
                    y32 = 930;
                    x33 = 520;
                    y33 = 930;
                    x34 = 725;
                    y34 = 930;

                }
                else {
                    if ( Main.dens == 420)
                    {
                        x   = 160;
                        y   = 160;
                        //  1
                        x01 = 110;
                        y01 = 275;
                        x02 = 275;
                        y02 = 275;
                        x03 = 440;
                        y03 = 275;
                        x04 = 605;
                        y04 = 275;
                        //    2
                        x11 = 110;
                        y11 = 440;
                        x12 = 275;
                        y12 = 440;
                        x13 = 440;
                        y13 = 440;
                        x14 = 605;
                        y14 = 440;
                        //    3
                        x21 = 110;
                        y21 = 605;
                        x22 = 275;
                        y22 = 605;
                        x23 = 440;
                        y23 = 605;
                        x24 = 605;
                        y24 = 605;
                        //   4
                        x31 = 110;
                        y31 = 770;
                        x32 = 275;
                        y32 = 770;
                        x33 = 440;
                        y33 = 770;
                        x34 = 605;
                        y34 = 770;

                    }
                    else {
                        if (Main.dens == 360) {
                            x   = 150;
                            y   = 150;
                            //  1
                            x01 = 110;
                            y01 = 260;
                            x02 = 260;
                            y02 = 260;
                            x03 = 410;
                            y03 = 260;
                            x04 = 560;
                            y04 = 260;
                            //    2
                            x11 = 110;
                            y11 = 410;
                            x12 = 260;
                            y12 = 410;
                            x13 = 410;
                            y13 = 410;
                            x14 = 560;
                            y14 = 410;
                            //    3
                            x21 = 110;
                            y21 = 560;
                            x22 = 260;
                            y22 = 560;
                            x23 = 410;
                            y23 = 560;
                            x24 = 560;
                            y24 = 560;
                            //   4
                            x31 = 110;
                            y31 = 710;
                            x32 = 260;
                            y32 = 710;
                            x33 = 410;
                            y33 = 710;
                            x34 = 560;
                            y34 = 710;
                        } else {

                            x = 120;
                            y = 120;
                            //  1
                            x01 = 110;
                            y01 = 235;
                            x02 = 235;
                            y02 = 235;
                            x03 = 360;
                            y03 = 235;
                            x04 = 485;
                            y04 = 235;
                            //   2
                            x11 = 110;
                            y11 = 360;
                            x12 = 235;
                            y12 = 360;
                            x13 = 360;
                            y13 = 360;
                            x14 = 485;
                            y14 = 360;
                            //   3
                            x21 = 110;
                            y21 = 485;
                            x22 = 235;
                            y22 = 485;
                            x23 = 360;
                            y23 = 485;
                            x24 = 485;
                            y24 = 485;
                            //  4
                            x31 = 110;
                            y31 = 610;
                            x32 = 235;
                            y32 = 610;
                            x33 = 360;
                            y33 = 610;
                            x34 = 485;
                            y34 = 610;
                        }
                    }
                }

            }
        }
        for(int i=0; i < 16 ; i++)  // 16
        {
            int text=btn.get(i);
            //  Log.d(TAG, "CFH text= " + text);
            AbsoluteLayout.LayoutParams absParams =
                    (AbsoluteLayout.LayoutParams)buttons[text].getLayoutParams();
            switch(i) {
                // 1
                case(0):
                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x01;
                    absParams.y = y01;
                    buttons[text].setLayoutParams(absParams);
                    //  Log.d(TAG, "0 CFH TXT= " +buttons[0].getText());
                    break;
                case(1):
                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x02;
                    absParams.y = y02;
                    buttons[text].setLayoutParams(absParams);
                    // Log.d(TAG, "1 CFH TXT= " +buttons[1].getText());
                    break;
                case(2):

                    absParams.height = x;
                    absParams.width = y;
                    absParams.x =x03;
                    absParams.y = y03;
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(3):
                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x04;
                    absParams.y = y04;
                    buttons[text].setLayoutParams(absParams);
                    break;
                //  2
                case(4):
                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x11;
                    absParams.y = y11;
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(5):
                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x12;
                    absParams.y = y12;
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(6):
                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x13;
                    absParams.y = y13;
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(7):
                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x14;
                    absParams.y = y14;
                    buttons[text].setLayoutParams(absParams);
                    break;
                // 3
                case(8):
                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x21;
                    absParams.y = y21;
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(9):
                    absParams.height = x;
                    absParams.width =  y;
                    absParams.x = x22;
                    absParams.y = y22;
                    break;
                case(10):
                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x23;
                    absParams.y = y23;
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(11):
                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x24;
                    absParams.y = y24;
                    buttons[text].setLayoutParams(absParams);
                    break;
                // 4
                case(12):
                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x31;
                    absParams.y = y31;
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(13):
                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x32;
                    absParams.y = y32;
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(14):
                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x33;
                    absParams.y = y33;
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(15):
                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x34;
                    absParams.y = y34;
                    buttons[text].setLayoutParams(absParams);
                    break;
            }
        }
    }

    public int findbtn(int element)
    {
        int i=0;

        for(i=0; i< length ;i++)
        {
            if(btn.get(i)==element)
            {
                break;
            }
        }
        return i;
    }
    static protected String[] fromPuzzleStringBible( String bible) {

        String[] tempStrings = bible.split("");
        String[] sbv = new String[bible.length()];
       // Log.d("cfh", "length = " + bible.length());
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
    public int findidx(Button  b)
    {
        char n;
        char b_text;

        b_text= b.getText().charAt(0);

        for (int i = 0; i < 16 ; i++)
        {
            String s = puzOK[i];
            char c = s.charAt(0);
            if (c == b_text)
            {
                return i;
            }
        }

        return -1;
    }
    public  int CheckButtonId(Button b) {
        switch (b.getId()) {
            case R.id.Button00:
                return 0;
            case R.id.Button01:
                return 1;
            case R.id.Button02:
                return 2;
            case R.id.Button03:
                return 3;
            case R.id.Button04:
                return 4;
            case R.id.Button05:
                return 5;
            case R.id.Button06:
                return 6;
            case R.id.Button07:
                return 7;
            case R.id.Button08:
                return 8;
            case R.id.Button09:
                return 9;
            case R.id.Button10:
                return 10;
            case R.id.Button11:
                return 11;
            case R.id.Button12:
                return 12;
            case R.id.Button13:
                return 13;
            case R.id.Button14:
                return 14;
            case R.id.Button15:
                return 15;
        }
        return 0;
    }
    public char getCellText( int i) {

        return( bB[i].getText().charAt(0));  // Why 0

    }
    public char getCellBtn( int i) {

        return( bB[i].getText().charAt(0));

    }

    @Override
    public void onBackPressed() {

        Button YesButton;
        Button NoButton;
/*
        TextView text;
        ImageView image;
        final Dialog dialog = new Dialog(this);
        if (Main.setcn == true)
            dialog.setTitle(Main.slang[7]);
        else
            dialog.setTitle(Main.tlang[7]);
        dialog.setContentView(R.layout.ansyesno);

        YesButton = (Button) dialog.findViewById(R.id.YesButton);
        NoButton = (Button) dialog.findViewById(R.id.NoButton);


        if (Main.setcn == true) {
            YesButton.setText(Main.slang[3]);
        }
        else
        {
            YesButton.setText(Main.tlang[3]);
        }
        dialog.setCancelable(false);

        dialog.show();
        YesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                dialog.dismiss();
                finish();
            }
        });
        NoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                dialog.dismiss();

            }
        });   */

    }


}


