package com.Game.ccmusa.android;

        import android.app.ActionBar;
        import android.app.Activity;
        import android.app.AlertDialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.res.Configuration;
        import android.graphics.Color;
        import android.graphics.drawable.ColorDrawable;
        import android.os.Bundle;
        import android.util.DisplayMetrics;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.ImageView;

        import com.bumptech.glide.Glide;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.BufferedReader;
        import java.io.File;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Locale;

public class Main extends Activity implements OnClickListener {

    private static final String TAG = "Main";
   // public static View continueButton, newButton, resButton;
    public static View  newButton, resButton, helpButton, aboutButton,  retButton;
    public static String fcbtn = "/btn";
    public static String Ctn = "/ctn";
    public static boolean setcn = false;  //
    public static boolean L3Text = false; // Level 3 Text
    public static boolean L2Text = false; // Level 3 Text
    public static boolean GameBtn = false;    // Game enable
    public static DisplayMetrics dm;
    public static int dens;
    public static float screensz = 0; // height of one tile
    public static int gamecnt = 0;
    public static int filenumber = 0;  //File name number
    public static boolean backbtn = false;
    public static boolean gohome = false;
    // public static boolean seltext= false;
    public static String tlang[] = new String[10];
    public static String slang[] = new String[10];
    public static List<String> list;
    public static List<String> myArrayCh;
    public static List<String> myArrayChInf;
    public static List<String> myArrayYandn;
    public static List<String> myArrayYandNInf;
    public static List<Integer> Num = new ArrayList<Integer>();
    public static List<String> Titles = new ArrayList<String>();
    public static List<String> Answer = new ArrayList<String>();
    public static List<String> Verse = new ArrayList<String>();
    public static List<String> Select = new ArrayList<String>();
    public static List<String> VerseNo = new ArrayList<String>();
    public static List<Boolean> isTrue = new ArrayList<Boolean>();
    public static List<String> myArray;
    public static List<String> myArrayInfo;
    public static int level = 3;
    public static int saveindx = 0;
    public static int saveerr = 0;
    public static int ok = 0;
    public static int error = 0;
    public  static int cnum = 0;
    public  static int ynum = 0;
    public static int choosereadjson = 0;
    public static int choosebackPressed = 0;
    public static int yandnbackPressed = 0;
    public static int yandnreadjson = 0;
    public static int currentQuestion = 0;
    public static int numberOfQuestions = 9;
    public static boolean GameOver = false;
    public static boolean isLastQuestion = false;
    public static final String OPT_MUSIC = "music";
    public static boolean OPT_MUSIC_DEF = true;
    public static final String OPT_HINTS = "hints";
    public static boolean OPT_HINTS_DEF = true;
    protected String resourceType;
    public ImageView biblegif;
    public  ImageView HomePage;
    public  enum WindowSizeClass { COMPACT, MEDIUM, EXPANDED}


  //  public static ArrayList<Bible> Bibles = new ArrayList<Bible>();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context c = getApplicationContext();
        Main.myArrayCh = new ArrayList<String>();
        Main.myArrayChInf = new ArrayList<String>();
        Main.myArrayYandn = new ArrayList<String>();
        Main.myArrayYandNInf = new ArrayList<String>();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        dm = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int w = dm.widthPixels;
        int h = dm.heightPixels;
        dens = dm.densityDpi;
        screensz = w / 10;

    //  final Animation animTranslate = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
        Log.d("CFH", "screen = " + screensz + " dm = " + dens + " w = " + w/10);

        if (setcn == false)
        {
            String Load = "";
            Locale locale = new Locale(Load);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            setContentView(R.layout.main);
            biblegif = (ImageView) findViewById(R.id.imageView);
            Glide.with(this).load(R.drawable.ezgif11123).into(biblegif);
            getData(1);
        }
        else
        {
            String Load = "cn";
            Locale locale = new Locale(Load);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            setContentView(R.layout.main);
            biblegif = (ImageView) findViewById(R.id.imageView);
            Glide.with(this).load(R.drawable.ez050923).into(biblegif);
            getData(2);
        }

        String PATH = c.getFilesDir().getPath();
        String All = PATH + "/";

        newButton = findViewById(R.id.new_button);

        newButton.setOnClickListener(this);


        helpButton = findViewById(R.id.help_button);
        helpButton.setOnClickListener(this);
        retButton = findViewById(R.id.results_button);
        retButton.setOnClickListener(this);
        aboutButton = findViewById(R.id.about_button);
        aboutButton.setOnClickListener(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(layoutResID, null);
        resourceType = (String) view.getTag();
        Log.d("cfh", "main tag = " + resourceType);
        super.setContentView(view);
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.new_button:
                Intent n = new Intent(this, GameLevel.class);
                n.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(n);
                Main.GameBtn = true;
                break;
           case   R.id.help_button:
                Intent h = new Intent(this, HelpGame.class);
               startActivity(h);
                break;
            case R.id.results_button:
                Intent r = new Intent(this, AllResults.class);
                startActivity(r);
                break;
            case R.id.about_button:
                Intent a = new Intent(this, About.class);
                startActivity(a);
                break;

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Context c = getApplicationContext();
        String PATH = c.getFilesDir().getPath();
        String All = PATH + "/";
        File f = new File(All, fcbtn);
        File ff = new File(All, Ctn);
        switch (item.getItemId()) {
            case R.id.simplified:

                if (setcn == false) {

                    String Load = "cn";
                    Locale locale = new Locale(Load);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                    this.setContentView(R.layout.main);

                  newButton = findViewById(R.id.new_button);
                  newButton.setOnClickListener(this);
                   helpButton = findViewById(R.id.help_button);
                    helpButton.setOnClickListener(this);
                    resButton = findViewById(R.id.results_button);
                    resButton.setOnClickListener(this);
                    aboutButton = findViewById(R.id.about_button);
                    aboutButton.setOnClickListener(this);

                    }
                    setcn = true;
                biblegif = (ImageView) findViewById(R.id.imageView);
                Glide.with(this).load(R.drawable.ez050923).into(biblegif);

                resButton = findViewById(R.id.results_button);
                resButton.setOnClickListener(this);

                return true;
            case R.id.traditional:
                if (setcn == true)
                {

                    String Load = "";
                    Locale locale = new Locale(Load);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                    this.setContentView(R.layout.main);

                    newButton = findViewById(R.id.new_button);
                    newButton.setOnClickListener(this);
                    biblegif = (ImageView) findViewById(R.id.imageView);
                    Glide.with(this).load(R.drawable.ezgif11123).into(biblegif);
                    setcn = false;
                }
                helpButton = findViewById(R.id.help_button);
                helpButton.setOnClickListener(this);
                resButton = findViewById(R.id.results_button);
                resButton.setOnClickListener(this);
                aboutButton = findViewById(R.id.about_button);
                aboutButton.setOnClickListener(this);
                break;

        }
        return false;
    }

    public void openNewGameDialog() {

        new AlertDialog.Builder(this).setTitle(R.string.new_game_title).setItems(R.array.difficulty, new DialogInterface.OnClickListener() {

            public void onClick(
                    DialogInterface dialoginterface, int i) {


                startGame(i);
            }
        }).show();
    }


    public void startGame(int i) {

        Intent intent = new Intent(this, Game.class);
        intent.putExtra(Game.KEY_DIFFICULTY, i);
        startActivity(intent);

    }

    @Override
    protected void onPause() {
        super.onPause();
        // Music.stop(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //  Log.d(TAG, "onResme");
        // Music.play(this, R.raw.main);

    }

    public void getData(int lang) {
        BufferedReader br = null;
        int k = 0;
        InputStream input1;
        String mLine;

        if (lang == 1) {
            try {
                input1 = getAssets().open("traditional");
                br = new BufferedReader(new InputStreamReader(input1, "UTF-8"), 50);
                while ((mLine = br.readLine()) != null) {
                    if (k == 0)
                        tlang[k] = mLine.substring(1);
                    else
                        tlang[k] = mLine;
                    ++k;
                }
            } catch (IOException e) {
                //log the exception
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
        }
        else
        {
            k = 0;
            try {
                input1 = getAssets().open("simplified");
                br = new BufferedReader(new InputStreamReader(input1, "Unicode"), 50);
                while ((mLine = br.readLine()) != null) {
                    //process line
                    slang[k] = mLine;
                    ++k;
                }
            } catch (IOException e) {
                //log the exception
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
        }
    }
    private void getSupportActionBar() {
    }

}