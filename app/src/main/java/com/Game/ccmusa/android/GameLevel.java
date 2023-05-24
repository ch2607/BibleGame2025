package com.Game.ccmusa.android;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

// import static com.Game.ccmusa.android.Main.continueButton;
import static com.Game.ccmusa.android.Main.newButton;

import androidx.core.content.res.ResourcesCompat;

import com.bumptech.glide.Glide;

public class GameLevel extends Activity implements View.OnClickListener {

    private static final String TAG = "ABT";
    Button chooseButton, yandnButton, fillButton;
    protected String resourceType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources res = getResources();
        Drawable drawable = ResourcesCompat.getDrawable(res, R.drawable.homepage, null);
        setContentView(R.layout.gamelevel);
      //  this.setContentView(R.layout.gamelevel);

     //   final Animation rightAnim = AnimationUtils.loadAnimation(this, R.anim.move);
     //   final Animation leftAnim = AnimationUtils.loadAnimation(this, R.anim.move);
        ImageView biblegif = (ImageView) findViewById(R.id.imageView);
        if (biblegif != null)
            if (Main.setcn == false)
              Glide.with(this).load(R.drawable.newgames).into(biblegif);
            else
                Glide.with(this).load(R.drawable.ez051023).into(biblegif);
        chooseButton = (Button) findViewById(R.id.choose_button);

    //    easyButton.startAnimation(rightAnim);
        chooseButton.setOnClickListener(this);

        yandnButton = findViewById(R.id.yandn_button);
      //  medButton.startAnimation(leftAnim);
        yandnButton.setOnClickListener(this);

        fillButton = findViewById(R.id.fill_button);
   //     hardButton.startAnimation(rightAnim);
        fillButton.setOnClickListener(this);
        if (Main.screensz == 72)
        {
            chooseButton.setTextSize(12);
            yandnButton.setTextSize(12);
            fillButton.setTextSize(12);
        }
        else
        {
            if (Main.screensz == 108)
            {
                chooseButton.setTextSize(18);
                yandnButton.setTextSize(18);
                fillButton.setTextSize(18);
            }
            else
            {
                chooseButton.setTextSize(20);
                yandnButton.setTextSize(20);
                fillButton.setTextSize(20);
            }
        }
    }
    /*
    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(layoutResID, null);
        resourceType = (String) view.getTag();
        Log.d("cfh", "gamelevel tag = " + resourceType);
        super.setContentView(view);
    }  */
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.choose_button:    // Choose
                Main.level = 1;
                Intent e = new Intent(this,GameChooseList.class);
                startActivity(e);
                break;
            case R.id.yandn_button:    // YesandNo
                Main.level = 2;
                Intent m = new Intent(this,GameYandNList.class);
                startActivity(m);
                break;
            case R.id.fill_button:  // fill in the blank
                Main.level = 3;
                Intent h = new Intent(this,GameTableList.class);
                startActivity(h);
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
       // Log.d("CFH", "GameLevel onDestroy: ");
    }

    @Override
    public void onBackPressed() {
       // Log.d("CFH", "GameLevel" + " NewBtn = " + Main.GameBtn);
        Intent main = new Intent(this, Main.class);
        startActivity(main);
        finish();
        return;
    }
}
