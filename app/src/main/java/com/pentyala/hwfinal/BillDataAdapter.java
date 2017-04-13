package com.pentyala.hwfinal;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pentyala.hwfinal.model.BillDataItem;
import com.pentyala.hwfinal.model.ComDataItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Aditya Kiran on 11/26/2016.
 */

public class BillDataAdapter extends ArrayAdapter<BillDataItem> {
    List<BillDataItem> mDataItems;
    LayoutInflater mInflater;
    public BillDataAdapter(Context context, List<BillDataItem> data) {
        super(context,R.layout.billitem, data);
        mDataItems=data;
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
        BillDataItem item = mDataItems.get(position);

        tvName.setText(item.getBill_id().toUpperCase());
        chamber.setText(item.getChamber());
        title.setText(item.getShort_title());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sss=new SimpleDateFormat("MMM dd,yyyy");
        String da="";
        try {
            da=sss.format(sdf.parse(item.getIntroduced_on()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        chamber.setText(da);
        final View finalConvertView = convertView;
        final BillDataItem finalItem=item;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(finalConvertView.getContext(),BillDetails.class);
                intent.putExtra("id",finalItem.getBill_id());
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
