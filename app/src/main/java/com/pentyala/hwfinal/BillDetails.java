package com.pentyala.hwfinal;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pentyala.hwfinal.model.BillDataItem;

import java.util.List;

import static android.R.attr.data;

public class BillDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_details);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        String s=getIntent().getStringExtra("id");
        List<BillDataItem> bdi=DataProvider.billactivedataItemList;
        BillDataItem bd= null;
        System.out.println(s);
        boolean flag=true;
        for(int i=0;i<bdi.size();i++)
        {
            System.out.println(s.equals(bdi.get(i).getBill_id()));
            if(bdi.get(i).getBill_id().equals(s))
            {
                flag=false;
                bd=bdi.get(i);
                break;
            }
        }
        if(flag)
        {
            bdi=DataProvider.billolddataItemList;
            for(int i=0;i<bdi.size();i++)
            {
                if(bdi.get(i).getBill_id().equals(s))
                {
                    flag=false;
                    bd=bdi.get(i);
                    break;
                }
            }
        }
        System.out.println(flag);
        TextView bill_id,title,bill_type,sponsor,chamber,status,introduced_on,congress_url,version_status,bill_url;
        bill_id=(TextView)findViewById(R.id.textView21);
        title=(TextView)findViewById(R.id.textView22);
        bill_type=(TextView)findViewById(R.id.textView23);
        sponsor=(TextView)findViewById(R.id.textView24);
        chamber=(TextView)findViewById(R.id.textView25);
        status=(TextView)findViewById(R.id.textView26);
        introduced_on=(TextView)findViewById(R.id.textView27);
        congress_url=(TextView)findViewById(R.id.textView28);
        version_status=(TextView)findViewById(R.id.textView29);
        bill_url=(TextView)findViewById(R.id.textView30);

        bill_id.setText(capitalize(bd.getBill_id()));
        title.setText(bd.getShort_title());
        bill_type.setText(bd.getBill_type());
        sponsor.setText(bd.getSponsor_title()+". "+bd.getSponsor_last_name()+", "+bd.getSponsor_first_name());
        chamber.setText(bd.getChamber());
        status.setText(bd.getStatus());
        introduced_on.setText(bd.getIntroduced_on());
        congress_url.setText(bd.getCongress_url());
        version_status.setText(bd.getVersion_status());
        bill_url.setText(bd.getBill_url());

        final ImageView fav=(ImageView)findViewById(R.id.imageView9);
        boolean f=false;
        if(FavoritesData.isFavoriteBill(bd))
        {
            fav.setImageResource(R.drawable.starfilled);
            f=true;
        }
        else
        {
            fav.setImageResource(R.drawable.star);
            f=false;
        }

        final boolean finalF = f;
        final BillDataItem finalBd = bd;
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!finalF) {
                    fav.setImageResource(R.drawable.starfilled);
                    FavoritesData.addFavBill(finalBd);
                }
                else {
                    fav.setImageResource(R.drawable.star);
                    FavoritesData.removeFav(finalBd);
                }
            }
        });

    }
    public String capitalize(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        return new StringBuffer(strLen)
                .append(Character.toTitleCase(str.charAt(0)))
                .append(str.substring(1))
                .toString();
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        NavUtils.navigateUpFromSameTask(this);
        return true;
    }

}
