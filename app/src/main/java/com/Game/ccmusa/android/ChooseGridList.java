package com.Game.ccmusa.android;

import static com.Game.ccmusa.android.SheetDialog.mContext;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class ChooseGridList extends Activity
{

    static Context ctx;
    String fname;
    String s;
    static TextView t1, t2, t3;
    Button button1, button2, button3;
    public static String et1, et2, et3;
    public int counter;
    private long timeLeftInMillis;
    private static final long COUNTDOWN_IN_MILLIS = 30000;
    private CountDownTimer countDownTimer;
    private boolean answered;

    //
    // Call from GameChooseList.java Class
    //

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ctx = getApplicationContext();
        Main.myArrayCh = new ArrayList<String>();
        Main.myArrayChInf = new ArrayList<String>();

            if (Main.setcn == false) {
                fname = "ceasy" + Main.cnum;
            } else {
                fname = "sceasy" + Main.cnum;
            }
            answered = false;
            // StartGame();
            Log.d("cfh", "read json file " + fname);
            try {
                JSONObject obj = new JSONObject(loadJSONFromAsset(fname));

                JSONArray jsonArray = obj.optJSONArray("Bible");
                Boolean flag;
                String str;
                int num = 0;
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    num = jsonObject.getInt("Num");
                    Main.Num.add(i, num);
                    str = jsonObject.getString("Title");
                    Main.Titles.add(i, str);
                    str = jsonObject.getString("Verse");
                    Main.Verse.add(i, str);
                    str = jsonObject.getString("Select");
                    //   Log.d("cfh", "Select " + str);
                    Main.Select.add(i, str);
                    str = jsonObject.getString("Answer");
                    Main.Answer.add(i, str);
                    str = jsonObject.getString("VerseNo");
                    Main.VerseNo.add(i, str);
                }
            } catch (Exception e) {
                Log.d("cfh", "error 123");
                e.printStackTrace();
            }
            Main.filenumber = Main.cnum;
            LoadGameinfo();

    }
    public  void LoadGameinfo()
    {
        setContentView(R.layout.choosequstion);
        t1 = (TextView) findViewById(R.id.t1);
        t2 = (TextView) findViewById(R.id.t2);
        t3 = (TextView) findViewById(R.id.t3);
        ImageView image = findViewById(R.id.image);

        button1 = (Button) findViewById(R.id.Button1);
        button2 = (Button) findViewById(R.id.Button2);
        button3 = (Button) findViewById(R.id.Button3);

        if (image == null) {
            Log.d("cfh", " image is null " + Main.currentQuestion +
                    " number " + Main.numberOfQuestions);
        } else {
            Glide.with(ctx)
                    .load(R.drawable.mchoose)
                    .into(image);
        }



        String str = Main.Titles.get(Main.currentQuestion);
        t3.setText(str);

        if (Main.setcn == false) {
            s = "問答題共計:" + Main.currentQuestion + "/" + Main.numberOfQuestions;
        } else {
            s = "问答题共计:" + Main.currentQuestion + "/" + Main.numberOfQuestions;
        }
        t1.setTextColor(Color.BLUE);
        t1.setText(s);
        t1.setGravity(Gravity.CENTER);
        String Sel = Main.Select.get(Main.currentQuestion);
        String[] ans = Sel.split(" ");
        et1 = ans[0];
        et2 = ans[1];
        et3 = ans[2];
        button1.setText(et1);
        button1.setVisibility(View.VISIBLE);
        button2.setText(et2);
        button2.setVisibility(View.VISIBLE);
        button3.setText(et3);
        button3.setVisibility(View.VISIBLE);
        //   button1.setTextSize(18);
    //    Log.d("cfh", " current " + Main.currentQuestion +
    //            " number " + Main.numberOfQuestions + "answered " + answered);
        if (!answered)
        {
            Log.d("cfh", "into !answered");
            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
        }
        else
        {
          //  updateCountDownText();
            showSolution();
        }


        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String str = (String) button1.getText();
                Integer num = (Integer) Main.Num.get(Main.currentQuestion);
                String Answer = Main.Answer.get(Main.currentQuestion);
                if (!answered) {
                    if (str.equals(Answer)) {
                        Main.ok += 1;
                        if (Main.setcn == false) {
                            showDialog("您好棒啊！答對了");
                        } else {
                            showDialog("您好棒啊！答对了");
                        }
                    } else {
                        //  Log.d("cfh", "num " + num + " currrent "+ Main.currentQuestion + " err btn1 " + str + " json " + Answer);
                        Main.error += 1;
                        if (Main.setcn == false) {
                            showDialog("對不起、你答錯了");
                        } else {
                            showDialog("對不起、你答错了");
                        }
                    }
                }
             //   else {
              //      showNextQuestion();
              //  }
            }
        });

        //   button2.setTextSize(18);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = (String) button2.getText();
                Integer num = (Integer) Main.Num.get(Main.currentQuestion);
                String Answer = Main.Answer.get(Main.currentQuestion);
                if (!answered) {
                    if (str.equals(Answer)) {
                        Main.ok += 1;
                        if (Main.setcn == false) {
                            showDialog("您好棒啊！答對了");
                        } else {
                            showDialog("您好棒啊！答对了");
                        }
                    } else {
                        Main.error += 1;
                        if (Main.setcn == false) {
                            showDialog("對不起、你答錯了");
                        } else {
                            showDialog("對不起、你答错了");
                        }
                    }
                }
              //  else {
              //      showNextQuestion();
              //  }
            }
        });

        //   button3.setTextSize(18);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = (String) button3.getText();
                Integer num = (Integer) Main.Num.get(Main.currentQuestion);
                String Answer = Main.Answer.get(Main.currentQuestion);
                if (!answered) {
                    if (str.equals(Answer)) {
                        Log.d("cfh", "num " + num + " currrent " + Main.currentQuestion + " ok btn3 " + str + " json " + Answer);
                        Main.ok += 1;
                        if (Main.setcn == false) {
                            showDialog("您好棒啊！答對了");
                        } else {
                            showDialog("您好棒啊！答对了");
                        }
                    } else {
                        Log.d("cfh", "num " + num + " currrent " + Main.currentQuestion + " err btn3 " + str + " json " + Answer);
                        Main.error += 1;
                        if (Main.setcn == false) {
                            showDialog("對不起、你答錯了");
                        } else {
                            showDialog("對不起、你答错了");
                        }
                    }
                }
            //    else {
              //      showNextQuestion();
              //  }
            }
        });

    }
/*
    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(layoutResID, null);
        resourceType = (String) view.getTag();
        Log.d("cfh", "choose tag = " + resourceType);

    }  */

    private void showNextQuestion()
    {

        if (Main.currentQuestion < Main.numberOfQuestions)
        {
           // get next one question  here
            Main.currentQuestion++;
            LoadGameinfo();
            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            answered = false;
            startCountDown();
        }
        else
        {
           // if (Main.GameOver == true)
          //  {
                GameOverDiaglog();
          //  }
          //  else
          //  {
           //     finishQuiz();
           // }
        }
    }
    private void finishQuiz() {

    }


    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis,
                1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                if (!answered)
                   updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
                showNextQuestion();
            }
        }.start();
    }
    private void checkAnswer() {
        answered = true;
        countDownTimer.cancel();

    }
    private void showSolution() {

    }



    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(),
                "%02d:%02d", minutes, seconds);
        t2.setText(timeFormatted);
        t2.setGravity(Gravity.CENTER);

        if (timeLeftInMillis < 10000) {
           t2.setTextColor(Color.RED);
        } else {
           t2.setTextColor(Color.BLUE);
        }
    }

    public void GameOverDiaglog()
    {
        final Dialog dialog = new Dialog(this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.gameoversheet);
        TextView title = dialog.findViewById(R.id.title);
        TextView results = dialog.findViewById(R.id.results);
        ImageView image = dialog.findViewById(R.id.image);

        if (Main.error > 0 ) {
            Glide.with(this)
                    .load(R.drawable.oops)
                    .into(image);
        }
        else
        {
            Glide.with(this)
                    .load(R.drawable.awesome)
                    .into(image);
        }
        Button canel = (Button) dialog.findViewById(R.id.button);
        title.setGravity(Gravity.CENTER);
        canel.setGravity(Gravity.CENTER);
        canel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Main.currentQuestion = 0;
                Main.choosereadjson = 0;
                Main.GameOver = false;
                GameOverSaveData(ctx);
                Intent intent = new Intent(ctx, GameChooseList.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        if (Main.error > 0 )
        {
            String str;
            if (Main.setcn == false) {
                str = "對不起、你答錯了" + Main.error + "題" ;
            }
            else
            {
                str = "对不起、你答错了"  + Main.error + "题" ;;
            }
            results.setText(str);
            results.setGravity(Gravity.CENTER);
        }
        else
        {
            String str;
            if (Main.setcn == false) {
                str = "您好棒啊！答對了";
            }
            else
            {
                str = "您好棒啊！答对了";

            }
            results.setText(str);
            results.setGravity(Gravity.CENTER);
        }


        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

       // Mark DEL Main.currentQuestion = 0;

    }
    public void showDialog(String msg)
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheet);
        TextView title = dialog.findViewById(R.id.title);
        TextView results = dialog.findViewById(R.id.results);
        results.setText(msg);
        results.setGravity(Gravity.CENTER);
        checkAnswer();
     //   timeLeftInMillis = COUNTDOWN_IN_MILLIS;
     //   answered = false;

        TextView verse = dialog.findViewById(R.id.verse);
        TextView verseno = dialog.findViewById(R.id.verseno);
        LinearLayout canel = dialog.findViewById(R.id.canel);
        title.setGravity(Gravity.CENTER);
        canel.setGravity(Gravity.CENTER);
        verse.setText(Main.Verse.get(Main.currentQuestion));
        verseno.setText(Main.VerseNo.get(Main.currentQuestion));
        verseno.setGravity(Gravity.CENTER);

        canel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                dialog.dismiss();
                showNextQuestion();
            }
        });
        dialog.show();  // crash here
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }

    public String loadJSONFromAsset(String fname)
    {
        String json = null;

        try {
            InputStream is = getAssets().open(fname);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
    public static  void GameOverSaveData(Context mContext)
    {
        String name = null;
        String sname;

        Main.currentQuestion = 0;
        if (Main.setcn == false)
        {
            name = "ce" + Main.filenumber + ".txt";
        }
        else
        {
            name = "cse" + Main.filenumber + ".txt";
        }
        sname = name;
        Log.d("cfh", "write to file " + sname);
        try
        {
            OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter(mContext.openFileOutput(sname, Context.MODE_PRIVATE));
            if (Main.error == 0) {
                outputStreamWriter.write("1");
                Log.d("cfh", "wrie to 1 " + Main.error);
            }// OK
            else
            {
                if (Main.error > 4) {
                    outputStreamWriter.write("3");
                    Log.d("cfh", "wrie to 3 " + Main.error);
                }
                else {
                    Log.d("cfh", "wrie to 2 " + Main.error);
                    outputStreamWriter.write("2");
                }
            }
            outputStreamWriter.close();
        }
        catch (IOException e)
        {
            Log.e("cfh Exception", "File write failed: " + e.toString());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();


        if (countDownTimer != null) {
            countDownTimer.cancel();
        }


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Main.choosereadjson = 0;
        Main.choosebackPressed = 1;
        Main.currentQuestion = 0;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        //  Log.d("CFH", "GameTableList" +  " NewBtn = " + Main.GameBtn);
        Intent main = new Intent(ctx, GameChooseList.class);
        startActivity(main);
        finish();
        /*
         if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
         */
        return;
    }
    public void setUpAnsGridView()
    {
        Main.Select.clear();
        for(String i:Main.Select)
            Main.Select.add(i);
    }

}
