package com.pentyala.hwfinal;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pentyala.hwfinal.model.DataItem;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.List;

import static com.pentyala.hwfinal.R.id.imageView;

public class LegisDetails extends AppCompatActivity {

    String id="";
    List<DataItem> dat;
    DataItem data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legis_details);
        this.setTitle("Legislator Details");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        id=getIntent().getStringExtra("id");
        dat=DataProvider.legdataItemList;
        int i=0;
        for(;i<dat.size();i++)
        {
            if(dat.get(i).getBioguide_id().equals(id))
            {
                data=dat.get(i);
                break;
            }
        }
        if(data.getParty()=="R") {
            ((TextView) findViewById(R.id.textView5)).setText("Republican");

        }
        else
        {
            ((TextView) findViewById(R.id.textView5)).setText("Democrat");
        }
            TextView name,email,chamber,contact,startterm,endterm,term,office,state,fax,birthday;
        name=(TextView)findViewById(R.id.textView6);
        email=(TextView)findViewById(R.id.textView7);
        chamber=(TextView)findViewById(R.id.textView8);
        contact=(TextView)findViewById(R.id.textView9);
        startterm=(TextView)findViewById(R.id.textView10);
        endterm=(TextView)findViewById(R.id.textView11);
        //term=(TextView)findViewById(R.id.textView12);
        office=(TextView)findViewById(R.id.textView13);
        state=(TextView)findViewById(R.id.textView14);
        fax=(TextView)findViewById(R.id.textView15);
        birthday=(TextView)findViewById(R.id.textView16);
        name.setText(data.getTitle()+". "+data.getLast_name()+", "+data.getFirst_name());
        email.setText(data.getOc_email());
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email = new Intent(android.content.Intent.ACTION_SENDTO);
                startActivity(Intent.createChooser(email,"Choose an Email client :"));
            }
        });
        chamber.setText(data.getChamber());
        contact.setText(data.getPhone());
        startterm.setText(data.getTerm_start());
        endterm.setText(data.getTerm_end());
        //term.setText((data.getProgress()));
        ((TextView)findViewById(R.id.textView999)).setText(new Double(data.getProgress()).intValue()+"%");
        ((ProgressBar)findViewById(R.id.progress)).setProgress(new Double(data.getProgress()).intValue());
        office.setText(data.getOffice());
        state.setText(data.getState_name());
        fax.setText(data.getFax());
        birthday.setText(data.getBirthday());
        ImageView imd=(ImageView)findViewById(R.id.imageView8);
        if(data.getParty()=="R") {

            imd.setImageResource(R.drawable.r);
        }
        else {
            imd.setImageResource(R.drawable.d);
        }
        ImageView iv= (ImageView) findViewById(R.id.imageView7);
        Picasso.with(getApplicationContext())
                .load("https://theunitedstates.io/images/congress/original/"+data.getBioguide_id()+".jpg")
                .into(iv);
        final ImageView fav=(ImageView)findViewById(R.id.imageView3);
        boolean f=false;
        if(FavoritesData.isFavoriteLegislator(data))
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
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!finalF) {
                    fav.setImageResource(R.drawable.starfilled);
                    FavoritesData.addFavLeg(data);
                }
                else {
                    fav.setImageResource(R.drawable.star);
                    FavoritesData.removeFav(data);
                    //update the index.
                }
            }
        });

        ImageView t=(ImageView)findViewById(R.id.imageView4);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.getFacebook_id()=="")
                {
                    Toast.makeText(getApplicationContext(),"No Facebook page available",Toast.LENGTH_SHORT).show();
                }
                else {
                    Uri uri = Uri.parse("http://www.facebook.com/" + data.getFacebook_id());
                    Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(launchBrowser);
                }
            }
        });

        t=(ImageView)findViewById(R.id.imageView5);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.getTwitter_id()=="")
                {
                    Toast.makeText(getApplicationContext(),"No Twitter link available",Toast.LENGTH_SHORT).show();
                }
                else {
                    Uri uri = Uri.parse("http://www.twitter.com/" + data.getTwitter_id());
                    Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(launchBrowser);
                }
            }
        });

        t=(ImageView)findViewById(R.id.imageView6);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.getWebsite()=="")
                {
                    Toast.makeText(getApplicationContext(),"No Website available",Toast.LENGTH_SHORT).show();
                }
                else {
                    Uri uri = Uri.parse(data.getWebsite());
                    Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(launchBrowser);
                }
            }
        });



//        ImageView iv=(ImageView)findViewById(R.id.imageView7);
  //      Drawable res = getResources().getDrawable(getResources().getIdentifier(uri, null, getPackageName()));
    //    iv.setImageDrawable(res);
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
