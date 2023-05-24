package com.Game.ccmusa.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class YandNGVAdapter extends ArrayAdapter<YandNModel>
{

    ImageView yandn;
    ImageView number;
    ImageView lock;
    ImageView error;
    public YandNGVAdapter(@NonNull Context context, ArrayList<YandNModel> yandnModelArrayList) {
        super(context, 0, yandnModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View listitemView = convertView;
        if (listitemView == null)
        {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).
                    inflate(R.layout.yandn_item, parent, false);
        }

        YandNModel YandnModel = getItem(position);
        //  TextView courseTV = listitemView.findViewById(R.id.idTVCourse);
        yandn = listitemView.findViewById(R.id.chooseid);
        number = listitemView.findViewById(R.id.numid);
        lock = listitemView.findViewById(R.id.lockid);
        error = listitemView.findViewById(R.id.errid);
        switch (position)
        {
            case 0:
                number.setImageResource(R.drawable.num01);
                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error);
                break;
            case 1:
                number.setImageResource(R.drawable.num02);
                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error);

                 break;
            case 2:
                number.setImageResource(R.drawable.num03);
                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error);
                break;
            case 3:
                number.setImageResource(R.drawable.num04);
                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error); // Crash here
                break;
            case 4:
                number.setImageResource(R.drawable.num05);
                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error);
                break;
            case 5:
                number.setImageResource(R.drawable.num06);
                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error);
                break;
            case 6:
                number.setImageResource(R.drawable.num07);
                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error);
                break;
            case 7:
                number.setImageResource(R.drawable.num08);
                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error);
                break;
            case 8:
                number.setImageResource(R.drawable.num09);
                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error);
                break;
            case 9:
                number.setImageResource(R.drawable.num10);
                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error);
                break;
            case 10:
                number.setImageResource(R.drawable.num11);
                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error);
                break;
            case 11:
                number.setImageResource(R.drawable.num12);
                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error);
                break;
            case 12:
                number.setImageResource(R.drawable.num13);
                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error);
                break;
            case 13:
                number.setImageResource(R.drawable.num14);
                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error);
                break;

            case 14:
                number.setImageResource(R.drawable.num15);
                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error);
                break;
            case 15:
                number.setImageResource(R.drawable.num16);
                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error);
                break;
            case 16:
                number.setImageResource(R.drawable.num17);                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error);
                break;
            case 17:
                number.setImageResource(R.drawable.num18);
                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error);
                break;
            case 18:
                number.setImageResource(R.drawable.num19);
                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error);
                break;
            case 19:
                number.setImageResource(R.drawable.num20);
                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error);
                break;
            case 20:
                number.setImageResource(R.drawable.num21);
                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error);
                break;
            case 21:
                number.setImageResource(R.drawable.num22);
                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error);
                break;
            case 22:
                number.setImageResource(R.drawable.num23);
                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error);
                break;
            case 23:
                number.setImageResource(R.drawable.num24);
                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error);
                break;
            case 24:
                number.setImageResource(R.drawable.num25);
                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error);
                break;
            case 25:
                number.setImageResource(R.drawable.num26);
                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error);
                break;
            case 26:
                number.setImageResource(R.drawable.num27);
                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error);
                break;
            case 27:
                number.setImageResource(R.drawable.num28);
                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error);
                break;
            case 28:
                number.setImageResource(R.drawable.num29);
                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error);
                break;
            case 29:
                number.setImageResource(R.drawable.num30);
                number.setVisibility(View.VISIBLE);
                SetTableInfo(position, yandn, number, lock, error);
                break;
            default:
                break;
        }

        return listitemView;
    }
    public void SetTableInfo(int position, ImageView yandn, ImageView number, ImageView lock, ImageView error)
    {
        if (Main.myArrayYandn.get(position) == "1")
        {
            Glide.with(getContext())
                    .load(R.drawable.yandn)
                    .into(yandn);
            lock.setVisibility(View.GONE);


            error.setVisibility(View.GONE);
            if ( Main.myArrayYandNInf.get(position) == "1")
            {
                Glide.with(getContext())
                        .load(R.mipmap.check)
                        .into(error);
                error.setVisibility(View.VISIBLE);

            }
            else
            {
                if ( Main.myArrayYandNInf.get(position) == "2") {
                    Glide.with(getContext())
                            .load(R.mipmap.err)
                            .into(error);
                    error.setVisibility(View.VISIBLE);
                }
                else
                {
                    Glide.with(getContext())
                            .load(R.mipmap.allerr)
                            .into(error);
                    error.setVisibility(View.VISIBLE);
                }
            }
        }
        else
        {
            Glide.with(getContext())
                    .load(R.drawable.yandn)
                    .into(yandn);
            lock.setVisibility(View.VISIBLE);
            number.setVisibility(View.GONE);
            error.setVisibility(View.GONE);
        }
    }
}
