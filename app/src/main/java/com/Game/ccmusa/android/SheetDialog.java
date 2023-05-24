package com.Game.ccmusa.android;


import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import static com.Game.ccmusa.android.Game.item2cnt;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class SheetDialog extends BottomSheetDialogFragment {

    public static Context mContext;
    public static int no;
    public static String ok, No;
    View v;
    protected String resourceType;
    public ImageView biblegif, imgpdn;

    Animation rightAnim;
    public static  SheetDialog.SheetListener mListener = null;

    public static SheetDialog newInstance( int  info, String i1, String i3)
    {
        no = info;
        ok = i1;
        No = i3;
        return new SheetDialog();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();

        if (no == 1)
            v = inflater.inflate(R.layout.result_sheet_layout, container, false);
        else
            v = inflater.inflate(R.layout.skip_sheet_layout, container, false);
        resourceType = (String)v.getTag();
        Log.d("cfh", "SheetDialog " + resourceType);

        if ( no == 2)
        {
            TextView txt = v.findViewById(R.id.bible_verse);
            TextView txtno = v.findViewById(R.id.bible_no);
            txt.setText(ok);
            txtno.setGravity(Gravity.CENTER);
            txtno.setText(No);
        }
        else
        {
           if  (Game.item2cnt == 0 && Game.Error == 0 )
           {
               v = inflater.inflate(R.layout.result_sheet_layout, container, false);
               TextView txt1 = v.findViewById(R.id.bible_verse);
               TextView txt2 = v.findViewById(R.id.bible_no);
               biblegif = (ImageView) v.findViewById(R.id.imageView);
               rightAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.leftmove);
               if (biblegif != null) {
                //   final ViewPropertyAnimator animate = biblegif.animate(rightAnim);
                   Glide.with(this).load(R.drawable.good).into(biblegif);
                   biblegif.startAnimation(rightAnim);
               }
               txt1.setText(ok);
               txt2.setText(No);
           }
           else
           {
               v = inflater.inflate(R.layout.result_sheet_error_layout, container, false);
               TextView txt1 = v.findViewById(R.id.text1);
               TextView txt2 = v.findViewById(R.id.text2);
               TextView txt = v.findViewById(R.id.bible_verse);
               TextView txtno = v.findViewById(R.id.bible_no);


               biblegif = (ImageView) v.findViewById(R.id.imageView);
               rightAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.leftmove);
               if (biblegif != null) {
                   Glide.with(this).load(R.drawable.bad).into(biblegif);
                   biblegif.startAnimation(rightAnim);
               }
               if (Game.Error  != 0)
               {
                   txt1.setText(R.string.playmore_title);
                   txt1.setGravity(Gravity.CENTER);
                   txt2.setGravity(Gravity.CENTER);
                   if (Main.setcn == false) {
                       txt2.setText("錯誤" + Game.Error + "次數");
                   }
                   else {
                       txt2.setText("错误" + Game.Error + "次数");
                   }
               }
               txtno.setGravity(Gravity.CENTER);
               txt.setText(ok);
               txtno.setText(No);
           }
        }
        Button button = v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Game.Error = 0;
                item2cnt = 0;
                Intent i = new Intent(mContext, GameTableList.class);
                startActivity(i);
                dismiss();
            }
        });

        return v;
    }


    public interface SheetListener
    {
        void onButtonClicked(String text);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try
        {
            mListener = (SheetDialog.SheetListener) mContext;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString()
                    + " must implement SheetListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }

}

