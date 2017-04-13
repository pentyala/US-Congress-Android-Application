package com.pentyala.hwfinal;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pentyala.hwfinal.model.ComDataItem;
import com.pentyala.hwfinal.model.DataItem;

import java.util.List;

/**
 * Created by Aditya Kiran on 11/26/2016.
 */

public class CommDataAdapter extends ArrayAdapter<ComDataItem> {
    List<ComDataItem> mDataItems;
    LayoutInflater mInflater;

    public CommDataAdapter(Context context, List<ComDataItem> dataItems) {
        super(context,R.layout.commitem, dataItems);
        mDataItems=dataItems;
        mInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.commitem, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.textView12);
        TextView chamber = (TextView) convertView.findViewById(R.id.chamber);
        TextView title = (TextView) convertView.findViewById(R.id.textView18);
        ComDataItem item = mDataItems.get(position);

        tvName.setText(item.getCommittee_id());
        chamber.setText(item.getChamber());
        title.setText(item.getName());
        final View finalConvertView = convertView;
        final ComDataItem finalItem=item;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(finalConvertView.getContext(),CommDetails.class);
                intent.putExtra("id",finalItem.getCommittee_id());
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
