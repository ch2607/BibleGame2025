package com.Game.ccmusa.android;

import android.app.LauncherActivity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {
    private ArrayList<ListItem> listData;
    private final LayoutInflater layoutInflater;
    Context aContext;
    int ret = 0;
    public CustomListAdapter(Context aContext, ArrayList<ListItem> listData) {
        this.listData = listData;
        this.aContext = aContext;
        layoutInflater = LayoutInflater.from(aContext);
    }
    @Override
    public int getCount() {

        return listData.size();
    }
    @Override
    public Object getItem(int position)
    {
        return listData.get(position);
    }
    @Override
    public long getItemId(int position)
    {
        return position;
    }
    public View getView(int position, View v, ViewGroup vg)
    {
        ViewHolder holder;

        if (v == null)
        {
            v = layoutInflater.inflate(R.layout.listimage, null);
          //  v.setBackgroundColor(Color.LTGRAY);
            holder = new ViewHolder();
            holder.uName = (TextView) v.findViewById(R.id.textView);
            holder.uImage = (ImageView) v.findViewById(R.id.imageView);
            v.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) v.getTag();
        }
        holder.uName.setText(listData.get(position).getName());
        holder.uName.setTextColor(Color.BLUE);
        if (Main.dens ==  640) {
            holder.uName.setTextSize(20);
        }
        else
        {
            if (Main.dens == 480)
            {
                holder.uName.setTextSize(18);
            }
            else
            {
                if (Main.dens ==  420)
                {
                    holder.uName.setTextSize(18);
                }
                else
                {
                    if (Main.dens ==  320)
                    {
                        holder.uName.setTextSize(18);
                    }
                    else
                    {
                        if (Main.dens ==  240)
                        {
                            holder.uName.setTextSize(20);
                        }
                        else
                        {
                            if (Main.dens ==  213)
                            {
                                holder.uName.setTextSize(30);

                            }
                        }
                    }
                }
            }
        }
        if (Main.list.size() != 0)
        {
            if (Main.list.get(position) == "1")
            {
                holder.uImage.setImageResource(R.mipmap.check);
            }
            else
            {
                if (Main.list.get(position) == "2")
                {

                    holder.uImage.setImageResource(R.mipmap.err);
                }
                else
                {
                    if (Main.list.get(position ) == "3")
                    {
                        holder.uImage.setImageResource(R.mipmap.allerr);
                    }
                    else
                    {
                       // Log.d("cfh", "p = " + position);
                        // add cnt to 400 to 410 GameTableList  Line 180
                        if (Main.list.get(position + 1 ) == "0") // Crash here
                        {
                            holder.uImage.setImageResource(R.drawable.clear);
                        }
                    }
                }
            }
        }
        else
        {
            holder.uImage.setImageResource(R.drawable.clear);
        }
        // animation for listview
        final Animation rightAnim = AnimationUtils.loadAnimation(aContext, R.anim.leftmove);
        v.startAnimation(rightAnim);
        return v;
    }
    static class ViewHolder {
        TextView uName;
        ImageView uImage;

    }
}
