package com.Game.ccmusa.android;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.WHITE;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;

import static com.Game.ccmusa.android.Game.AnsSouces;
import static com.Game.ccmusa.android.Game.QnsSouces;
import static com.Game.ccmusa.android.Game.ansAdapter;
import static com.Game.ccmusa.android.Game.errAdapter;
import static com.Game.ccmusa.android.Game.gridAns;

import static com.Game.ccmusa.android.Game.gridQns;
import static com.Game.ccmusa.android.Game.item2cnt;
import static com.Game.ccmusa.android.Game.item2key;
import static com.Game.ccmusa.android.Game.item3;
import static com.Game.ccmusa.android.Game.qnsAdapter;

import static com.Game.ccmusa.android.Main.newButton;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class GridViewAdapter extends BaseAdapter {
    List<String> listSources;
    Context mContext;
    String item[];
    String item1[];
    String item2[];
    int flag;

    // define local value

    static String sc;
    char ch;
    int len = 0;
    int flag2 = 0;
    int flag3 = 0;
    int loop = 0;
    boolean Text2 = false;
    boolean Text3 = false;
    int TextLen = 0;
    public static Button preButton;
    public static int selbtn = 0;
    float screen = 12;
    protected String resourceType;


    public GridViewAdapter(List<String> listSources, Context mContext,
                           String item[], String item1[],
                           String item2[],
                           int flag) {
        this.listSources = listSources;
        this.mContext = mContext;
        this.item = item;
        this.item1 = item1;
        this.item2 = item2;
        this.flag = flag;

    }

    @Override
    public int getCount() {
        //  Log.d("cfh", "xxx size = " + listSources.size());
        return listSources.size();
    }

    @Override
    public Object getItem(int position) {
        return listSources.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView,
                        final ViewGroup parent) {
        final Button button;
        int sz = 0;

        if (convertView == null) {
            button = new Button(mContext);
            switch (Main.dens) {
                case 630:
                    button.setLayoutParams(new GridView.LayoutParams(140, 140));
                    button.setTextSize(20);
                    break;
                case 480:  // Galaxy_Z_Fold3 Galaxy_S21 sz 108
                     sz = (int)(Main.screensz - screen);
                    button.setLayoutParams(new GridView.LayoutParams(sz , sz ));
                    button.setTextSize(22);
                    break;
                case 440:
                    button.setLayoutParams(new GridView.LayoutParams(90, 90));
                    button.setTextSize(20);
                    break;
                case 420:
                    button.setLayoutParams(new GridView.LayoutParams(90, 90));
                    button.setTextSize(20);
                    break;
                case 360: // samsung flip 2  sz 72
                    sz = (int)(Main.screensz - screen);
                    button.setLayoutParams(new GridView.LayoutParams(sz, sz));
                    button.setTextSize(20);
                    break;
                case 320:  // 1080X1920 note 3
                    sz = (int)(Main.screensz - screen);
                    if (Main.screensz == 120) {
                        button.setLayoutParams(new GridView.LayoutParams(sz,sz ));
                        button.setTextSize(30);// 12'
                    } else {
                        if (Main.screensz == 200) {  // Pixel  C
                            button.setLayoutParams(new GridView.LayoutParams(sz, sz));
                            button.setTextSize(35);// 12'
                        } else {
                            button.setLayoutParams(new GridView.LayoutParams(sz, sz));
                            button.setTextSize(35);
                        }
                    }
                    break;
                case 240:
                    if (Main.screensz == 180) {  // 12" samsung
                        button.setLayoutParams(new GridView.LayoutParams(140, 140));
                        button.setTextSize(40);
                    } else {
                        if (Main.screensz == 88) {
                            button.setLayoutParams(new GridView.LayoutParams(80, 80));
                            button.setTextSize(30);
                        } else {
                            button.setLayoutParams(new GridView.LayoutParams(60, 60));
                            button.setTextSize(25);
                        }
                    }
                    break;
                case 213:  // LG 7
                    button.setLayoutParams(new GridView.LayoutParams(80, 80));
                    button.setTextSize(35);
                    break;

            }
            button.setPadding(2, 2, 2, 2);

            if (selbtn == 0) {
                preButton = new Button(mContext);
                preButton.setLayoutParams(new GridView.LayoutParams(90, 90));
                preButton.setPadding(2, 2, 2, 2);
                preButton.setTextSize(20);
                selbtn = 1;
            }

            if (flag == 2) {
                if (Main.level == 1) {
                    button.setText(listSources.get(position));
                } else {
                    if (Main.level == 2)
                    {
                        if (flag2 == listSources.size()) {
                            Text2 = true;
                        } else {
                            flag2++;
                            button.setText(listSources.get(position));
                        }
                        if (Text2 == true) {
                            switch (loop) {
                                case 0:
                                    loop++;
                                    button.setText(listSources.get(position));
                                    break;
                                case 1:
                                    loop++;
                                    break;
                                case 2:
                                    loop++;
                                    button.setText(listSources.get(position));
                                    break;
                                case 3:
                                    loop++;
                                    break;
                                case 4:
                                    loop++;
                                    button.setText(listSources.get(position));
                                    break;
                                case 5:
                                    loop++;
                                    break;
                                case 6:
                                    loop++;
                                    button.setText(listSources.get(position));
                                    break;
                                case 7:
                                    loop++;
                                    break;
                                case 8:
                                    loop++;
                                    button.setText(listSources.get(position));
                                    break;
                                case 9:
                                    loop = 0;
                                    Text2 = false;
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    else
                    {
                        if (Main.level == 2)
                        {
                            if (flag3 == listSources.size()) {
                                Text3 = true;
                            } else {
                                flag3++;
                                button.setText(listSources.get(position));
                            }
                        }
                        if (Text3 == true) {
                            if (flag3 == listSources.size()) {
                                Text3 = false;
                            } else
                                flag3++;
                        }
                    }
                }
            } else {
                //
                // flag == 1
                //
                if (flag == 1)
                {

                    sc = listSources.get(position).toString();
                    ch = sc.charAt(0);
                    if (ch == ' ') {
                        button.setBackgroundResource(R.mipmap.slotans);
                        button.setOnDragListener(new ChoiceDragListener(position, button));
                    //    spacelen += 1;
                    } else {
                        button.setBackgroundResource(R.mipmap.btnall);
                    }
                    button.setText(listSources.get(position));
                    button.setTextColor(WHITE);

                } else {
                    if (flag == 3) {

                    }
                }
            }

            if (flag == 1)  // QNSCollection
            {
                sc = listSources.get(position).toString();
                ch = sc.charAt(0);
                if (ch == ' ') {
                    button.setBackgroundResource(R.mipmap.slotans);
                   button.setOnDragListener(new ChoiceDragListener(position, button));
                    item2cnt++;
                } else {
                    button.setBackgroundResource(R.mipmap.btnall);   // sudoku
                }
              //  Log.d("cfh", " 307");
               // button.setText(listSources.get(position));
              //  button.setTextColor(WHITE);  // WHITE     Carl MARK
            }
            else
            {
                if (flag == 2)   // ANSCollection
                {
                    button.setTextColor(BLACK);  // ANSCollection
                    button.setOnTouchListener(new ChoiceTouchListener());
                    if (Main.level == 1)
                    {
                        sc = listSources.get(position).toString();
                        ch = sc.charAt(0);
                        // Log.d("cfh", "sc = " + sc + " p = " + position);
                        if (ch == ' ') {
                            // Log.d("cfh", "empty");
                            button.setBackgroundResource(R.mipmap.slotans);
                        } else {
                            //  Log.d("cfh", "slot");
                            button.setBackgroundResource(R.mipmap.slotans);
                        }
                    }
                    else
                    {
                        if (Main.level == 2 || Main.level == 3)
                        {
                            if (Main.L2Text == true)
                            {
                                sc = listSources.get(position).toString();  //
                                ch = sc.charAt(0);
                                if (ch == ' ')
                                    button.setBackgroundResource(R.mipmap.slotans);
                                else
                                    button.setBackgroundResource(R.mipmap.btnall);
                            }
                            else
                            {
                                button.setBackgroundResource(R.mipmap.slotans);
                                TextLen++;
                                len = listSources.size();
                                if (TextLen == len)
                                {
                             //       showReady(0, 0);
                                    TextLen = 1;
                                }
                            }
                        }
                        else
                        {
                            sc = listSources.get(position).toString();
                            ch = sc.charAt(0);
                            if (ch == ' ') {
                                button.setBackgroundResource(R.mipmap.slotans);
                            } else {
                                button.setBackgroundResource(R.mipmap.btnall);
                            }
                        }
                    }
                }
                else
                {
                    if (flag == 3)
                    {

                    }
                    if (Game.Error == 0)
                    {
                        button.setBackgroundResource(R.mipmap.empty);
                    }

                }
            }

        } else {
            button = (Button) convertView;

        }
        return button;
    }

    public void OkAnimation() {
        final Animation rightAnim = AnimationUtils.loadAnimation(mContext, R.anim.fly_out);
        preButton.setBackgroundResource(R.mipmap.btnall);  //  sudoku
        preButton.startAnimation(rightAnim);
    }

    private final class ChoiceTouchListener implements View.OnTouchListener {
        Button button;

        @SuppressLint("NewApi")
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                final int positive = motionEvent.getActionButton();

                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                //start dragging the item touched
                view.startDrag(data, shadowBuilder, view, 0);
                return true;
            } else {
                return false;
            }
        }
    }

    @SuppressLint("NewApi")
    private class ChoiceDragListener implements View.OnDragListener {
        int p;
        Button btn;

        public ChoiceDragListener(int position, Button button) {
            btn = button;
            p = position;
           // item2cnt = item2key.length();
        }

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DROP:

                    //handle the dragged view being dropped over a drop view
                    View view = (View) event.getLocalState();

                    TextView dropTarget = (TextView) v;
                      //   dropTarget.setBackground(BLACK);

                    TextView dropped = (TextView) view;

                    if (dropped.getText().toString().charAt(0) == item[p].toString().charAt(0)) {

                        view.setVisibility(View.INVISIBLE);
                        Log.d("cfh", " " + dropped.getText().toString().charAt(0) + " " + item[p].toString().charAt(0));
                    //    dropTarget.setTextColor(WHITE);
                        btn.setBackgroundResource(R.mipmap.btnall);

                      //  dropTarget.setText(dropTarget.getText().toString());
                        dropTarget.setText(dropTarget.getText().toString() + dropped.getText().toString());
                             dropTarget.setTypeface(Typeface.DEFAULT_BOLD);

                        Object tag = dropTarget.getTag();
                        if (tag != null) {
                            int existingID = (Integer) tag;
                        }
                        dropTarget.setTag(dropped.getId());
                        dropTarget.setOnDragListener(null);
                        item2cnt--;
                        //
                        // is last one ?
                        //
                        if (item2cnt == 0)
                        {
                            Game.ResultBottomSheet.setBackgroundColor(Color.parseColor("#4daf51"));
                            Game.ResultBottomSheet.setEnabled(true);
                            DropAll(mContext);
                        }
                    }
                    else
                    {
                        //  add error count here ?
                        Game.Error++;
                        Toast toast = new Toast(mContext);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        TextView tv = new TextView(mContext);
                        tv.setBackgroundColor(Color.RED);
                        tv.setTextColor(Color.WHITE);
                        tv.setTextSize(25);
                        Typeface t = Typeface.create("serif", Typeface.BOLD_ITALIC);
                        tv.setTypeface(t);
                        tv.setPadding(10, 10, 10, 10);
                        tv.setText("錯誤！重新選擇...");
                        toast.setView(tv);
                        toast.show();
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //no action necessary
                    break;
                default:
                    break;
            }
            return true;

    }

    }
    public static  void DropAll(Context mContext)
    {
        String name = null;
        String sname;


        if (Main.setcn == false) {
            if (Main.level == 1) {
                name = "e" + Main.filenumber + ".txt";
                ;
            } else {
                if (Main.level == 2) {
                    name = "h" + Main.filenumber + ".txt";
                    ;
                } else {
                    if (Main.level == 3) {
                        name = "m" + Main.filenumber + ".txt";
                        ;
                    }
                }
            }
        }
        else
        {
            if (Main.level == 1) {
                name = "se" + Main.filenumber + ".txt";
                ;
            } else {
                if (Main.level == 2) {
                    name = "sh" + Main.filenumber + ".txt";
                    ;
                } else {
                    if (Main.level == 3) {
                        name = "sm" + Main.filenumber + ".txt";
                        ;
                    }
                }
            }

        }
        sname = name;
        try
        {
            OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter(mContext.openFileOutput(sname, Context.MODE_PRIVATE));
            if (Game.Error == 0)
                outputStreamWriter.write("1");  // OK
            else
                outputStreamWriter.write("2");
            outputStreamWriter.close();
        }
        catch (IOException e)
        {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
