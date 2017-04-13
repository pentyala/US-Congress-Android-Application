package com.pentyala.hwfinal;

import android.media.Image;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pentyala.hwfinal.model.ComDataItem;

import org.w3c.dom.Text;

import java.util.List;

public class CommDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comm_details);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        this.setTitle("Committee Details");
        String s=getIntent().getStringExtra("id");
        ComDataItem cdi=new ComDataItem();
        List<ComDataItem> lcd=DataProvider.comdataItemList;
        boolean flag=false;
        for(int i=0;i<lcd.size();i++)
        {
            if(lcd.get(i).getCommittee_id().equals(s))
            {
                flag=true;
                cdi=lcd.get(i);
                break;
            }
        }
        if(!flag)
        {
            lcd=DataProvider.comsenatedataItemList;
            for(int i=0;i<lcd.size();i++)
            {
                if(lcd.get(i).getCommittee_id().equals(s))
                {
                    flag=true;
                    cdi=lcd.get(i);
                    break;
                }
            }

        }
        if(!flag)
        {
            lcd=DataProvider.comjointdataItemList;
            for(int i=0;i<lcd.size();i++)
            {
                if(lcd.get(i).getCommittee_id().equals(s))
                {
                    flag=true;
                    cdi=lcd.get(i);
                    break;
                }
            }
        }
        TextView comid,name,chamber,parent_committee,contact,office;
        comid=(TextView)findViewById(R.id.textView21);
        name=(TextView)findViewById(R.id.textView22);
        chamber=(TextView)findViewById(R.id.textView23);
        parent_committee=(TextView)findViewById(R.id.textView24);
        contact=(TextView)findViewById(R.id.textView25);
        office=(TextView)findViewById(R.id.textView26);
        comid.setText(cdi.getCommittee_id());
        name.setText(cdi.getName());
        chamber.setText(cdi.getChamber());
        if(cdi.getParent_committee_id() == "")
            parent_committee.setText("N.A");
        else
            parent_committee.setText(cdi.getParent_committee_id());
        if(cdi.getPhone()=="")
            contact.setText("N.A");
        else
            contact.setText(cdi.getPhone());
        if(cdi.getOffice()=="")
            office.setText("N.A");
        else
            office.setText(cdi.getOffice());

        final ImageView fav = (ImageView) findViewById(R.id.imageView9);
        boolean f=false;
        if(FavoritesData.isFavoriteCommittee(cdi))
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
        final ComDataItem finalCdi = cdi;
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!FavoritesData.isFavoriteCommittee(finalCdi)) {
                    fav.setImageResource(R.drawable.starfilled);
                    FavoritesData.addFavCom(finalCdi);
                }
                else {
                    fav.setImageResource(R.drawable.star);
                    FavoritesData.removeFav(finalCdi);
                }
            }
        });

    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        NavUtils.navigateUpFromSameTask(this);
        return true;
    }
}
