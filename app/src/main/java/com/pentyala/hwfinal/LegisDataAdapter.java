package com.pentyala.hwfinal;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
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
 * Created by Aditya Kiran on 11/25/2016.
 */

public class LegisDataAdapter extends ArrayAdapter<DataItem> {

    List<DataItem> mDataItems;
    LayoutInflater mInflater;

    public LegisDataAdapter(Context context, List<DataItem> dataItems) {
        super(context,R.layout.legisitem, dataItems);
        mDataItems=dataItems;
        mInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.legisitem, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.itemNameText);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        TextView state = (TextView) convertView.findViewById(R.id.State);
        TextView district = (TextView) convertView.findViewById(R.id.District);
        ImageView iv=(ImageView)convertView.findViewById(R.id.imageView);
        DataItem item = mDataItems.get(position);
        Picasso.with(convertView.getContext())
                .load("https://theunitedstates.io/images/congress/original/"+item.getBioguide_id()+".jpg")
                .into(iv);
        tvName.setText(item.getLast_name()+", "+item.getFirst_name());
        state.setText("("+item.getParty()+") "+item.getState_name()+"   -");
        if(item.getDistrict()=="")
            district.setText("District 0");
        else
            district.setText("District "+item.getDistrict());
        final View finalConvertView = convertView;
        final DataItem finalItem=item;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(finalConvertView.getContext(),LegisDetails.class);
                intent.putExtra("id",finalItem.getBioguide_id());
                finalConvertView.getContext().startActivity(intent);

            }
        });

        /*InputStream inputStream = null;
        try {
            String imageFile = item.getImage();
            inputStream = getContext().getAssets().open(imageFile);
            Drawable d = Drawable.createFromStream(inputStream, null);
            imageView.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        return convertView;
    }

}
