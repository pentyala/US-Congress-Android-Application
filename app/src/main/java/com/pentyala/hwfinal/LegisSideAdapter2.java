package com.pentyala.hwfinal;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pentyala.hwfinal.model.DataItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Aditya Kiran on 11/27/2016.
 */

public class LegisSideAdapter2 extends ArrayAdapter {
    List<String> data;
    LayoutInflater minflater;
    Context context;
    public LegisSideAdapter2(Context context,List<String> dat) {
        super(context,R.layout.legissidelayout ,dat);
        data=dat;
        this.context=context;
        minflater=LayoutInflater.from(context);
    }
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = minflater.inflate(R.layout.legissidelayout, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.textView20);
        tvName.setText(data.get(position));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(context);
                if(context instanceof MainActivity)
                {
                    int i=0,pos;
                    List<DataItem> dataitems= DataProvider.legdatasenateItemList;
                    for(i=0;i<dataitems.size();i++)
                    {
                        if((dataitems.get(i).getLast_name().charAt(0)+"").equals(data.get(position)))
                        {
                            pos=i;
                            System.out.println("I found one at "+dataitems.get(i).getState_name());
                            break;
                        }
                    }
                    ((MainActivity)context).moveList(3,i);
                }
            }
        });

        return convertView;
    }

}
