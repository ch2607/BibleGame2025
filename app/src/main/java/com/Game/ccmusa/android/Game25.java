package com.Game.ccmusa.android;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

import static com.Game.ccmusa.android.GridViewAdapter.DropAll;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Game25 extends Activity
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
    public Button[] b;
    String name;
    BufferedReader br = null;
    int k = 0;
    InputStream input1;
    String mLine;
    public  String MSG1;
    public  String MSG2;
    public  String MSG;
    String fileType;
    Context ctx;
    public  int x01, y01, x02, y02,  x03, y03, x04, y04, x05, y05;
    public  int x11, y11, x12, y12,  x13, y13, x14, y14, x15, y15;
    public  int x21, y21, x22, y22,  x23, y23, x24, y24, x25, y25;
    public  int x31, y31, x32, y32,  x33, y33, x34, y34, x35, y35;
    public  int x41, y41, x42, y42,  x43, y43, x44, y44, x45, y45;
    public int x, y;
    private static final Integer[] btnnumber = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15 ,0};
    private ArrayList<Integer> btn = new ArrayList<Integer>();
    public TextView mBibleNo;
    public Button SkipBtn;
    public Context mContext;
    Animation rightAnim;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game25);
        mContext = getApplicationContext();
        if (Main.setcn == false)
        {
            fileType = "UTF-8";
        }
        else
        {
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
        try
        {

         //  Log.d(TAG, "open file Name:  " + name);
            input1 = getAssets().open(name);
            br = new BufferedReader(new InputStreamReader(input1,fileType), 50);;
            while ((mLine = br.readLine()) != null)
            {
                if (Main.setcn == false)
                {
                    if (k == 0)
                        easy[k] = mLine.substring(1);
                    else
                    {
                        easy[k] = mLine;
                    }
                }
                else  // simplified chinese
                    easy[k] = mLine;
               // Log.d(TAG, "easy k = :  " + k);
                ++k;
            }
        }
        catch (IOException e)
        {
            //log the exception
            Log.d(TAG, "error cfh " + e);
            e.printStackTrace();
        } finally {
            if (br != null)
            {
                try
                {
                    br.close();
                    //     input1.close();
                }
                catch (IOException e)
                {
                    //log the exception
                }
            }
        }
        easyBibleOk = easy[1];
        length = easy[1].length();
        puzzleOK = fromPuzzleStringBible(easyBibleOk);  // Crash Here
        buttons=findButtons();       // insert Bible char

    //  Debug only  -->

       for(int i=0;i < length+1 ;i++)
       {
             this.btn.add(i);
       }


     //  <---- Debug only
       // Debug only ----->
/*
        this.btn.add(1);
        this.btn.add(2);
        this.btn.add(3);
        this.btn.add(4);
        this.btn.add(5);
        //  2

        this.btn.add(6);
        this.btn.add(7);
        this.btn.add(8);
        this.btn.add(9);
        this.btn.add(10);
        // 3
        this.btn.add(11);
        this.btn.add(12);
        this.btn.add(13);
        this.btn.add(14);
        this.btn.add(15);
        // 4
        this.btn.add(16);
        this.btn.add(17);
        this.btn.add(18);
        this.btn.add(19);
        this.btn.add(20);
        // 5
        this.btn.add(21);
        this.btn.add(22);
        this.btn.add(23);
        this.btn.add(0);
        this.btn.add(24);
        */


        // Debug only ---->
       Collections.shuffle(this.btn); //random btn array
        //  <----  Debug only
    setButton();

    Counter = (TextView) findViewById(R.id.Counter);
    BibleText = (TextView) findViewById(R.id.BibleText);
    BibleNo = (TextView) findViewById(R.id.BibleNo);
    Focus = (Chronometer) findViewById(R.id.Chronometer1);
    SkipBtn = (Button)  findViewById(R.id.skipbtn);

    Focus.setFormat("%s");
    for (int i = 1; i < length+ 1; i++)
    {
         buttons[i].setOnClickListener(new View.OnClickListener()
         {
             public void onClick(View v)
              {
                    CheckButtonMove((Button) v);
              }
        });
    }
    Focus.start();
    Counter.setText("0");
    puzOK = puzzleOK;
    if (Main.dens == 640)
    {
            BibleText.setTextSize(14);
            BibleNo.setTextSize(14);
    }
    else {
        if (Main.dens == 480) {
                BibleText.setTextSize(16);
                BibleNo.setTextSize(20);
            } else if (Main.dens == 420) {
                BibleText.setTextSize(18);
                BibleNo.setTextSize(20);
            } else {
                if (Main.dens == 400) {
                    BibleText.setTextSize(18);
                    BibleNo.setTextSize(18);
                } else {
                    if (Main.dens == 360) {
                        BibleText.setTextSize(12);
                        BibleNo.setTextSize(18);
                    } else {
                        if (Main.dens == 320) {
                            BibleText.setTextSize(18);
                            BibleNo.setTextSize(18);
                        } else {   // 240 and 214 7in Tablet
                            BibleText.setTextSize(15);
                            BibleNo.setTextSize(20);
                        }
                    }
                }
            }
        }

  //  BibleText.setText(easy[0]);
    BibleNo.setText(easy[2]);
    SkipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                No.setText(easy[0]);  //  藝術字體
                bible.setText(easy[2]);   // Bible No
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
        b = new Button[27];

        b[0] = ((Button) findViewById(R.id.Button00));
        b[1] = (Button) findViewById(R.id.Button01);
        b[2] = (Button) findViewById(R.id.Button02);
        b[3] = (Button) findViewById(R.id.Button03);
        b[4] = (Button) findViewById(R.id.Button04);
        b[5] = (Button) findViewById(R.id.Button05);
        b[6] = (Button) findViewById(R.id.Button06);
        b[7] = (Button) findViewById(R.id.Button07);
        b[8] = (Button) findViewById(R.id.Button08);
        b[9] = (Button) findViewById(R.id.Button09);
        b[10] = (Button) findViewById(R.id.Button10);
        b[11] = (Button) findViewById(R.id.Button11);
        b[12] = (Button) findViewById(R.id.Button12);
        b[13] = (Button) findViewById(R.id.Button13);
        b[14] = (Button) findViewById(R.id.Button14);
        b[15] = (Button) findViewById(R.id.Button15);
        b[16] = (Button) findViewById(R.id.Button16);
        b[17] = (Button) findViewById(R.id.Button17);
        b[18] = (Button) findViewById(R.id.Button18);
        b[19] = (Button) findViewById(R.id.Button19);
        b[20] = (Button) findViewById(R.id.Button20);
        b[21] = (Button) findViewById(R.id.Button26);
        b[22] = (Button) findViewById(R.id.Button22);
        b[23] = (Button) findViewById(R.id.Button23);
        b[24] = (Button) findViewById(R.id.Button24);

        puzOK = puzzleOK;

        int g = 0;

        for (int j = 1; j < length + 1; ++j) {
            b[j].setTextColor(WHITE);
            if (g != 25) {
                if (Main.dens == 640) {
                    b[j].setTextSize(30);
                }
                else {
                    if (Main.dens == 480)
                        b[j].setTextSize(30);
                    else {
                        if (Main.dens == 420)
                            b[j].setTextSize(30);
                        else if (Main.dens == 360)
                            b[j].setTextSize(20);
                        else if (Main.dens == 320)
                            if (Main.screensz == 170.0)
                                b[j].setTextSize(40);
                            else
                                b[j].setTextSize(20);
                        else if (Main.dens == 120)
                            if (Main.screensz == 170.0)
                                b[j].setTextSize(40);
                            else
                                b[j].setTextSize(30);
                        else if (Main.dens == 240)
                            if (Main.screensz == 170.0)
                                b[j].setTextSize(40);
                            else
                                b[j].setTextSize(30);
                    }
                }
                b[j].setText(puzOK[g]);

            }
            g++;

        }
        return b;
    }
    public void CheckButtonMove(final Button bb) {
        bad_move=true;
        int btn_text = 0,bno = 0, btnNum;
        ctx = getApplicationContext();
        btn_text = CheckButtonId(bb);
        bno=findbtn(btn_text);
        btnNum=findbtn(0);

        switch(btnNum)
        {
            case(0):
                if(bno==5||bno==1)
                    bad_move=false;
                break;
            case(1):
                if(bno==0||bno==6||bno==2)
                    bad_move=false;
                break;
            case(2):

                if(bno==1||bno==7 ||bno == 3)
                    bad_move=false;
                break;
            case(3):

                if(bno==2||bno==8||bno==4)
                    bad_move=false;
                break;
            case(4):

                if(bno==3||bno==9)
                    bad_move=false;
                break;
            case(5):

                if(bno==10||bno==0||bno==6)
                    bad_move=false;
                break;
            case(6):

                if(bno==5||bno==11 || bno == 7 || bno == 1)
                    bad_move=false;
                break;
            case(7):
                if(bno==6||bno==12||bno==8 || bno == 2)
                    bad_move=false;
                break;
            case(8):
                if(bno==7||bno==13 || bno == 9|| bno == 3)
                    bad_move=false;
                break;
            case(9):
                if(bno==8||bno==14 || bno == 4)
                    bad_move=false;
                break;
            case(10):
                if(bno==5||bno== 11  || bno == 15)
                    bad_move=false;
                break;
            case(11):
                if(bno==10||bno==16 || bno == 12|| bno == 6)
                    bad_move=false;
                break;
            case(12):
                if(bno==11||bno==17 || bno == 13|| bno == 7)
                    bad_move=false;
                break;
            case(13):
                if(bno==12||bno==18 || bno == 14 || bno == 8)
                    bad_move=false;
                break;

            case(14):
                if(bno==13||bno==19 || bno == 9)
                    bad_move=false;
                break;
            case(15):
                if(bno==10|| bno == 20 ||bno==16 )
                    bad_move=false;
                break;
            case(16):
                if(bno==15 ||bno==21 || bno == 17 || bno == 11)
                    bad_move=false;
                break;
            case(17):
                if(bno==16||bno == 22 || bno==18 || bno == 12)
                    bad_move=false;
                break;
            case(18):
                if(bno==17|| bno == 23 || bno==19 || bno == 13)
                    bad_move=false;
                break;
            case(19):
                if(bno==18 || bno == 24 || bno==14)
                    bad_move=false;
                break;
            case(20):
                if(bno==15||bno== 21)
                    bad_move=false;
                break;

            case(21):
                if(bno==20 ||bno==16 || bno == 22)
                    bad_move=false;
                break;
            case(22):
                if(bno == 21 || bno == 17 ||bno==23 )
                    bad_move=false;
                break;
            case(23):
                if(bno==22||bno==18 || bno == 24 )
                    bad_move=false;
                break;
            case(24):
                if(bno==23||bno == 19 )
                    bad_move = false;
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

        // for( int i= 0; i < length + 1; i++)  cmp charter here
        for( int i= 0; i < 24; i++)  // 24
        {
            s = puzOK[k];
            char c = s.charAt(0);
            char n;
            int a = btn.get(i);
            n = getCellText(btn.get(i));

            // Log.d("CFH", "c = " + c + "n = " + n);
            if (c != n) {
                if (n == '0') {
                    // Log.d(TAG, "CFH end 0");
                }
                return;
            } else {
                if (i != 0) {
                    // Log.d(TAG, "CFH i != 0");
                } else {
                    if (k == 0) {
                        // Log.d(TAG, "CFH k == 0");
                    }
                }
                k++;

            }
        }
        Focus.stop();
        DropAll(mContext);
        AnimationDrawable frameAnimation;
        Button cntButton, rtnButton;
        final Dialog dialog = new Dialog(this);
        if (Main.setcn == true)
            dialog.setTitle(Main.slang[4]);
        else
            dialog.setTitle(Main.tlang[4]);

        setContentView(R.layout.displaydialog);
        if (Main.setcn == false)
        {
            MSG1 = Main.tlang[6] + ":" + Focus.getText() + "\n";
            MSG2 = Main.tlang[5] + ":" + Counter.getText();
            MSG = MSG1 + MSG2;
        }
        else
        {
            MSG1 =  Main.slang[6] + ":" + Focus.getText() + "\n";
            MSG2 = Main.slang[5] + ":" + Counter.getText();
            MSG = MSG1 + MSG2;
        }
        mBibleNo = (TextView) findViewById(R.id.BibleNo);
        ImageView biblegif = (ImageView) findViewById(R.id.imageView);
        TextView txtBible = (TextView)findViewById(R.id.bible_verse);
        TextView No = (TextView)findViewById(R.id.game_value);
        TextView bible = (TextView)findViewById(R.id.game_bible);
        Glide.with(this).load(R.drawable.awesome).into(biblegif);
        rightAnim = AnimationUtils.loadAnimation(mContext, R.anim.rightmove);
        biblegif.startAnimation(rightAnim);
        txtBible.setText(MSG);  // Ans
        No.setText(easy[0]);  //  藝術字體
        bible.setText(easy[2]);   // Bible No
        rtnButton = (Button) findViewById(R.id.MainMeue);
        ctx = getApplicationContext();
        rtnButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent m = new Intent(ctx,  DiffTableList.class);
                startActivity(m);
                dialog.dismiss();
                finish();
            }
        });
    }  // Move End

    public void setButton() {

        if (Main.dens == 640)    // HTC
        {
            x   = 260;
            y   = 260;
            //   1
            x01 = 100;
            y01 = 480;
            x02 = 365;
            y02 = 480;
            x03 = 630;
            y03 = 480;
            x04 = 895;
            y04 = 480;
            x05 = 1160;
            y05 = 480;
            //    2
            x11 = 100;
            y11 = 745;
            x12 = 365;
            y12 = 745;
            x13 = 630;
            y13 = 745;
            x14 = 895;
            y14 = 745;
            x15 = 1160;
            y15 = 745;
            //    3
            x21 = 100;
            y21 = 1019;
            x22 = 365;
            y22 = 1019;
            x23 = 630;
            y23 = 1019;
            x24 = 895;
            y24 = 1019;
            x25 = 1160;
            y25 = 1019;
            //   4
            x31 = 100;
            y31 = 1275;
            x32 = 365;
            y32 = 1275;
            x33 = 630;
            y33 = 1275;
            x34 = 895;
            y34 = 1275;
            x35 = 1160;
            y35 = 1275;
            //   5
            x41 = 100;
            y41 = 1540;
            x42 = 365;
            y42 = 1540;
            x43 = 630;
            y43 = 1540;
            x44 = 895;
            y44 = 1540;
            x45 = 1160;
            y45 = 1540;
            // 6
            x41 = 100;
            y41 = 1540;
            x42 = 365;
            y42 = 1540;
            x43 = 630;
            y43 = 1540;
            x44 = 895;
            y44 = 1540;
            x45 = 1160;
            y45 = 1540;
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
                x05 = 770;
                y05 = 275;
                //    2
                x11 = 110;
                y11 = 440;
                x12 = 275;
                y12 = 440;
                x13 = 440;
                y13 = 440;
                x14 = 605;
                y14 = 440;
                x15 = 770;
                y15 = 440;
                //    3
                x21 = 110;
                y21 = 605;
                x22 = 275;
                y22 = 605;
                x23 = 440;
                y23 = 605;
                x24 = 605;
                y24 = 605;
                x25 = 770;
                y25 = 605;
                //   4
                x31 = 110;
                y31 = 770;
                x32 = 275;
                y32 = 770;
                x33 = 440;
                y33 = 770;
                x34 = 605;
                y34 = 770;
                x35 = 770;
                y35 = 770;
                //   5
                x41 = 110;
                y41 = 935;
                x42 = 275;
                y42 = 935;
                x43 = 440;
                y43 = 935;
                x44 = 605;
                y44 = 935;
                x45 = 770;
                y45 = 935;
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
                    x05 = 930;
                    y05 = 315;
                    //    2
                    x11 = 110;
                    y11 = 520;
                    x12 = 315;
                    y12 = 520;
                    x13 = 520;
                    y13 = 520;
                    x14 = 725;
                    y14 = 520;
                    x15 = 930;
                    y15 = 520;
                    //    3
                    x21 = 110;
                    y21 = 725;
                    x22 = 315;
                    y22 = 725;
                    x23 = 520;
                    y23 = 725;
                    x24 = 725;
                    y24 = 725;
                    x25 = 930;
                    y25 = 725;
                    //   4
                    x31 = 110;
                    y31 = 930;
                    x32 = 315;
                    y32 = 930;
                    x33 = 520;
                    y33 = 930;
                    x34 = 725;
                    y34 = 930;
                    x35 = 930;
                    y35 = 930;

                    //   5
                    x41 = 110;
                    y41 = 1135;
                    x42 = 315;
                    y42 = 1135;
                    x43 = 520;
                    y43 = 1135;
                    x44 = 725;
                    y44 = 1135;
                    x45 = 930;
                    y45 = 1135;

                }
                else
                {
                    if (Main.dens == 420) {
                        x   = 150;
                        y   = 150;

                        //  1
                        x01 = 110;
                        y01 = 264;
                        x02 = 264;
                        y02 = 264;
                        x03 = 418;
                        y03 = 264;
                        x04 = 572;
                        y04 = 264;
                        x05 = 726;
                        y05 = 264;
                        //    2
                        x11 = 110;
                        y11 = 418;
                        x12 = 264;
                        y12 = 418;
                        x13 = 418;
                        y13 = 418;
                        x14 = 572;
                        y14 = 418;
                        x15 = 726;
                        y15 = 418;
                        //    3
                        x21 = 110;
                        y21 = 572;
                        x22 = 264;
                        y22 = 572;
                        x23 = 418;
                        y23 = 572;
                        x24 = 572;
                        y24 = 572;
                        x25 = 726;
                        y25 = 572;
                        //   4
                        x31 = 110;
                        y31 = 726;
                        x32 = 264;
                        y32 = 726;
                        x33 = 418;
                        y33 = 726;
                        x34 = 572;
                        y34 = 726;
                        x35 = 726;
                        y35 = 726;

                        //   5
                        x41 = 110;
                        y41 = 880;
                        x42 = 264;
                        y42 = 880;
                        x43 = 418;
                        y43 = 880;
                        x44 = 572;
                        y44 = 880;
                        x45 = 726;
                        y45 = 880;

                    } else {
                        if (Main.dens == 120) {
                            if (Main.screensz == 170.0) {
                            //    Log.d(TAG, "120 170");
                                x = 170;
                                y = 170;
                                //  1
                                x01 = 50;
                                y01 = 224;
                                x02 = 224;
                                y02 = 224;
                                x03 = 398;
                                y03 = 224;
                                x04 = 572;
                                y04 = 224;
                                x05 = 746;
                                y05 = 224;
                                //   2
                                x11 = 50;
                                y11 = 398;
                                x12 = 224;
                                y12 = 398;
                                x13 = 398;
                                y13 = 398;
                                x14 = 572;
                                y14 = 398;
                                x15 = 746;
                                y15 = 398;
                                //   3
                                x21 = 50;
                                y21 = 572;
                                x22 = 224;
                                y22 = 572;
                                x23 = 398;
                                y23 = 572;
                                x24 = 572;
                                y24 = 572;
                                x25 = 746;
                                y25 = 572;
                                //  4
                                x31 = 50;
                                y31 = 746;
                                x32 = 224;
                                y32 = 746;
                                x33 = 398;
                                y33 = 746;
                                x34 = 572;
                                y34 = 746;
                                x35 = 746;
                                y35 = 746;
                                //   5
                                x41 = 50;
                                y41 = 920;
                                x42 = 224;
                                y42 = 920;
                                x43 = 398;
                                y43 = 920;
                                x44 = 572;
                                y44 = 920;
                                x45 = 746;
                                y45 = 920;

                            } else {
                                x = 120;
                                y = 120;
                                //  1
                                x01 = 50;
                                y01 = 235;
                                x02 = 175;
                                y02 = 235;
                                x03 = 300;
                                y03 = 235;
                                x04 = 425;
                                y04 = 235;
                                x05 = 550;
                                y05 = 235;
                                //   2
                                x11 = 50;
                                y11 = 360;
                                x12 = 175;
                                y12 = 360;
                                x13 = 300;
                                y13 = 360;
                                x14 = 425;
                                y14 = 360;
                                x15 = 550;
                                y15 = 360;
                                //   3
                                x21 = 50;
                                y21 = 485;
                                x22 = 175;
                                y22 = 485;
                                x23 = 300;
                                y23 = 485;
                                x24 = 425;
                                y24 = 485;
                                x25 = 550;
                                y25 = 485;
                                //  4
                                x31 = 50;
                                y31 = 610;
                                x32 = 175;
                                y32 = 610;
                                x33 = 300;
                                y33 = 610;
                                x34 = 425;
                                y34 = 610;
                                x35 = 550;
                                y35 = 610;
                                //   5
                                x41 = 50;
                                y41 = 735;
                                x42 = 175;
                                y42 = 735;
                                x43 = 300;
                                y43 = 735;
                                x44 = 425;
                                y44 = 735;
                                x45 = 550;
                                y45 = 735;
                            }
                        } else {
                            if (Main.dens == 320) {
                                if (Main.screensz == 170.0) {
                                    x = 170;
                                    y = 170;
                                    //  1
                                    x01 = 50;
                                    y01 = 224;
                                    x02 = 224;
                                    y02 = 224;
                                    x03 = 398;
                                    y03 = 224;
                                    x04 = 572;
                                    y04 = 224;
                                    x05 = 746;
                                    y05 = 224;
                                    //   2
                                    x11 = 50;
                                    y11 = 398;
                                    x12 = 224;
                                    y12 = 398;
                                    x13 = 398;
                                    y13 = 398;
                                    x14 = 572;
                                    y14 = 398;
                                    x15 = 746;
                                    y15 = 398;
                                    //   3
                                    x21 = 50;
                                    y21 = 572;
                                    x22 = 224;
                                    y22 = 572;
                                    x23 = 398;
                                    y23 = 572;
                                    x24 = 572;
                                    y24 = 572;
                                    x25 = 746;
                                    y25 = 572;
                                    //  4
                                    x31 = 50;
                                    y31 = 746;
                                    x32 = 224;
                                    y32 = 746;
                                    x33 = 398;
                                    y33 = 746;
                                    x34 = 572;
                                    y34 = 746;
                                    x35 = 746;
                                    y35 = 746;
                                    //   5
                                    x41 = 50;
                                    y41 = 920;
                                    x42 = 224;
                                    y42 = 920;
                                    x43 = 398;
                                    y43 = 920;
                                    x44 = 572;
                                    y44 = 920;
                                    x45 = 746;
                                    y45 = 920;

                                } else {
                                    x = 120;
                                    y = 120;
                                    //  1
                                    x01 = 50;
                                    y01 = 235;
                                    x02 = 175;
                                    y02 = 235;
                                    x03 = 300;
                                    y03 = 235;
                                    x04 = 425;
                                    y04 = 235;
                                    x05 = 550;
                                    y05 = 235;
                                    //   2
                                    x11 = 50;
                                    y11 = 360;
                                    x12 = 175;
                                    y12 = 360;
                                    x13 = 300;
                                    y13 = 360;
                                    x14 = 425;
                                    y14 = 360;
                                    x15 = 550;
                                    y15 = 360;
                                    //   3
                                    x21 = 50;
                                    y21 = 485;
                                    x22 = 175;
                                    y22 = 485;
                                    x23 = 300;
                                    y23 = 485;
                                    x24 = 425;
                                    y24 = 485;
                                    x25 = 550;
                                    y25 = 485;
                                    //  4
                                    x31 = 50;
                                    y31 = 610;
                                    x32 = 175;
                                    y32 = 610;
                                    x33 = 300;
                                    y33 = 610;
                                    x34 = 425;
                                    y34 = 610;
                                    x35 = 550;
                                    y35 = 610;
                                    //   5
                                    x41 = 50;
                                    y41 = 735;
                                    x42 = 175;
                                    y42 = 735;
                                    x43 = 300;
                                    y43 = 735;
                                    x44 = 425;
                                    y44 = 735;
                                    x45 = 550;
                                    y45 = 735;
                                }
                            } else {
                                if (Main.dens == 240) {
                                    x = 120;
                                    y = 120;
                                    //  1
                                    x01 = 50;
                                    y01 = 235;
                                    x02 = 175;
                                    y02 = 235;
                                    x03 = 300;
                                    y03 = 235;
                                    x04 = 425;
                                    y04 = 235;
                                    x05 = 550;
                                    y05 = 235;
                                    //   2
                                    x11 = 50;
                                    y11 = 360;
                                    x12 = 175;
                                    y12 = 360;
                                    x13 = 300;
                                    y13 = 360;
                                    x14 = 425;
                                    y14 = 360;
                                    x15 = 550;
                                    y15 = 360;
                                    //   3
                                    x21 = 50;
                                    y21 = 485;
                                    x22 = 175;
                                    y22 = 485;
                                    x23 = 300;
                                    y23 = 485;
                                    x24 = 425;
                                    y24 = 485;
                                    x25 = 550;
                                    y25 = 485;
                                    //  4
                                    x31 = 50;
                                    y31 = 610;
                                    x32 = 175;
                                    y32 = 610;
                                    x33 = 300;
                                    y33 = 610;
                                    x34 = 425;
                                    y34 = 610;
                                    x35 = 550;
                                    y35 = 610;
                                    //   5
                                    x41 = 50;
                                    y41 = 735;
                                    x42 = 175;
                                    y42 = 735;
                                    x43 = 300;
                                    y43 = 735;
                                    x44 = 425;
                                    y44 = 735;
                                    x45 = 550;
                                    y45 = 735;
                                } else {
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
                                        x05 = 710;
                                        y05 = 260;
                                        //    2
                                        x11 = 110;
                                        y11 = 410;
                                        x12 = 260;
                                        y12 = 410;
                                        x13 = 410;
                                        y13 = 410;
                                        x14 = 560;
                                        y14 = 410;
                                        x15 = 710;
                                        y15 = 410;
                                        //    3
                                        x21 = 110;
                                        y21 = 560;
                                        x22 = 260;
                                        y22 = 560;
                                        x23 = 410;
                                        y23 = 560;
                                        x24 = 560;
                                        y24 = 560;
                                        x25 = 710;
                                        y25 = 560;
                                        //   4
                                        x31 = 110;
                                        y31 = 710;
                                        x32 = 260;
                                        y32 = 710;
                                        x33 = 410;
                                        y33 = 710;
                                        x34 = 560;
                                        y34 = 710;
                                        x35 = 710;
                                        y35 = 710;

                                        //   5
                                        x41 = 110;
                                        y41 = 860;
                                        x42 = 260;
                                        y42 = 860;
                                        x43 = 410;
                                        y43 = 860;
                                        x44 = 560;
                                        y44 = 860;
                                        x45 = 710;
                                        y45 = 860;

                                    } else {
                                        x = 120;
                                        y = 120;
                                        //  1
                                        x01 = 50;
                                        y01 = 235;
                                        x02 = 175;
                                        y02 = 235;
                                        x03 = 300;
                                        y03 = 235;
                                        x04 = 425;
                                        y04 = 235;
                                        x05 = 550;
                                        y05 = 235;
                                        //   2
                                        x11 = 50;
                                        y11 = 360;
                                        x12 = 175;
                                        y12 = 360;
                                        x13 = 300;
                                        y13 = 360;
                                        x14 = 425;
                                        y14 = 360;
                                        x15 = 550;
                                        y15 = 360;
                                        //   3
                                        x21 = 50;
                                        y21 = 485;
                                        x22 = 175;
                                        y22 = 485;
                                        x23 = 300;
                                        y23 = 485;
                                        x24 = 425;
                                        y24 = 485;
                                        x25 = 550;
                                        y25 = 485;
                                        //  4
                                        x31 = 50;
                                        y31 = 610;
                                        x32 = 175;
                                        y32 = 610;
                                        x33 = 300;
                                        y33 = 610;
                                        x34 = 425;
                                        y34 = 610;
                                        x35 = 550;
                                        y35 = 610;
                                        //   5
                                        x41 = 50;
                                        y41 = 735;
                                        x42 = 175;
                                        y42 = 735;
                                        x43 = 300;
                                        y43 = 735;
                                        x44 = 425;
                                        y44 = 735;
                                        x45 = 550;
                                        y45 = 735;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 25 ; i++) {

            int text = btn.get(i);
            AbsoluteLayout.LayoutParams absParams =
                    (AbsoluteLayout.LayoutParams) buttons[text].getLayoutParams();
            switch (i) {
                //
                //  1
                //
                case (0):
                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x01;
                    absParams.y = y01;
                    buttons[text].setLayoutParams(absParams);
                    break;
                case (1):
                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x02;
                    absParams.y = y02;
                    buttons[text].setLayoutParams(absParams);
                    break;
                case (2):
                    absParams.height = x;
                    absParams.width =  y;
                    absParams.x = x03;
                    absParams.y = y03;
                    buttons[text].setLayoutParams(absParams);
                    break;
                case (3):

                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x04;
                    absParams.y = y04;
                    buttons[text].setLayoutParams(absParams);
                    break;
                case (4):
                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x05;
                    absParams.y = y05;
                    buttons[text].setLayoutParams(absParams);
                    break;
                //
                // 2 col
                //

                case (5):
                    absParams.height =  x;
                    absParams.width =  y;
                    absParams.x = x11;
                    absParams.y = y11;
                    buttons[text].setLayoutParams(absParams);
                    break;
                case (6):
                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x12;
                    absParams.y = y12;
                    buttons[text].setLayoutParams(absParams);
                    break;
                case (7):
                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x13;
                    absParams.y = y13;
                    buttons[text].setLayoutParams(absParams);
                    break;
                case (8):
                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x14;
                    absParams.y = y14;
                    buttons[text].setLayoutParams(absParams);
                    break;

                case (9):

                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x15;
                    absParams.y = y15;

                    buttons[text].setLayoutParams(absParams);
                    break;

                //
                // 3 col
                //

                case (10):
                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x21;
                    absParams.y = y21;
                    buttons[text].setLayoutParams(absParams);
                    break;
                case (11):
                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x22;
                    absParams.y = y22;
                    buttons[text].setLayoutParams(absParams);
                    break;
                case (12):
                    absParams.height = x;
                    absParams.width = y;
                    absParams.x =  x23;
                    absParams.y = y23;
                    buttons[text].setLayoutParams(absParams);
                    break;
                case (13):
                    absParams.height =  x;
                    absParams.width =  y;
                    absParams.x = x24;
                    absParams.y = y24;
                    buttons[text].setLayoutParams(absParams);
                    break;
                case (14):
                    absParams.height = x;
                    absParams.width = y;
                    absParams.x =  x25;
                    absParams.y =  y25;
                    buttons[text].setLayoutParams(absParams);
                    break;
                //
                // 4 col
                //
                case (15):

                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x31;
                    absParams.y = y31;
                    buttons[text].setLayoutParams(absParams);
                    break;
                case (16):

                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x32;
                    absParams.y = y32;

                    buttons[text].setLayoutParams(absParams);
                    break;

                case (17):

                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x33;
                    absParams.y = y33;

                    buttons[text].setLayoutParams(absParams);
                    break;

                case (18):

                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x34;
                    absParams.y = y34;

                    buttons[text].setLayoutParams(absParams);
                    break;

                case (19):

                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x35;
                    absParams.y = y35;

                    buttons[text].setLayoutParams(absParams);
                    break;
                //
                // 5 col
                //
                case (20):

                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x41;
                    absParams.y = y41;
                    buttons[text].setLayoutParams(absParams);
                    break;

                case (21):
                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x42;
                    absParams.y = y42;
                    buttons[text].setLayoutParams(absParams);
                    break;
                case (22):
                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x43;
                    absParams.y = y43;
                    buttons[text].setLayoutParams(absParams);
                    break;
                case (23):
                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x44;
                    absParams.y = y44;
                    buttons[text].setLayoutParams(absParams);
                    break;
                case (24):
                    absParams.height = x;
                    absParams.width = y;
                    absParams.x = x45;
                    absParams.y =y45;
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
     //   Log.d("cfh", "length = " + bible.length());
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
            case R.id.Button16:
                return 16;
            case R.id.Button17:
                return 17;
            case R.id.Button18:
                return 18;
            case R.id.Button19:
                return 19;
            case R.id.Button20:
                return 20;
            case R.id.Button26:
                return 21;
            case R.id.Button22:
                return 22;
            case R.id.Button23:
                return 23;
            case R.id.Button24:
                return 24;
        }
        return 0;
    }

    public char getCellText( int i) {

        return(b[i].getText().charAt(0));

    }
    public char getCellBtn( int i) {

        return(b[i].getText().charAt(0));

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
            dialog.setTitle( "XXXX");
        else
            dialog.setTitle("ZZZZ");
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
        });
*/

    }

    
    }


