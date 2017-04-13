package com.pentyala.hwfinal;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.pentyala.hwfinal.model.BillDataItem;
import com.pentyala.hwfinal.model.ComDataItem;
import com.pentyala.hwfinal.model.DataItem;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    List<DataItem> dataItemList = DataProvider.legdataItemList;

    public ListView lv;
    public ListView lv1;
    public ListView lv2;
    public ListView lv99;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ((RelativeLayout)findViewById(R.id.LegislatorLayout)).setVisibility(View.VISIBLE);
        ((RelativeLayout)findViewById(R.id.CommitteeLayout)).setVisibility(View.INVISIBLE);
        ((RelativeLayout)findViewById(R.id.BillLayout)).setVisibility(View.INVISIBLE);
        ((RelativeLayout)findViewById(R.id.FavoriteLayout)).setVisibility(View.INVISIBLE);

        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView t1, t2, t3;
                t1 = (TextView) findViewById(R.id.textView);
                t2 = (TextView) findViewById(R.id.textView2);
                t3 = (TextView) findViewById(R.id.textView3);
                t1.setTextColor(Color.BLACK);
                t2.setTextColor(Color.GRAY);
                t3.setTextColor(Color.GRAY);
                ListView lv=(ListView)findViewById(R.id.listview);
                lv.setVisibility(View.VISIBLE);
                lv=(ListView)findViewById(R.id.listview1);
                lv.setVisibility(View.INVISIBLE);
                lv=(ListView)findViewById(R.id.listview2);
                lv.setVisibility(View.INVISIBLE);

                ((ListView)findViewById(R.id.leg_state_side_lv)).setVisibility(View.VISIBLE);
                ((ListView)findViewById(R.id.leg_house_side_lv)).setVisibility(View.INVISIBLE);
                ((ListView)findViewById(R.id.leg_senate_side_lv)).setVisibility(View.INVISIBLE);
            }
        });
        tv.setTextColor(Color.BLACK);
        tv = (TextView) findViewById(R.id.textView2);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView t1, t2, t3;
                t1 = (TextView) findViewById(R.id.textView);
                t2 = (TextView) findViewById(R.id.textView2);
                t3 = (TextView) findViewById(R.id.textView3);
                t1.setTextColor(Color.GRAY);
                t2.setTextColor(Color.BLACK);
                t3.setTextColor(Color.GRAY);
                ListView lv=(ListView)findViewById(R.id.listview);
                lv.setVisibility(View.INVISIBLE);
                lv=(ListView)findViewById(R.id.listview1);
                lv.setVisibility(View.VISIBLE);
                lv=(ListView)findViewById(R.id.listview2);
                lv.setVisibility(View.INVISIBLE);
                ((ListView)findViewById(R.id.leg_state_side_lv)).setVisibility(View.INVISIBLE);
                ((ListView)findViewById(R.id.leg_house_side_lv)).setVisibility(View.VISIBLE);
                ((ListView)findViewById(R.id.leg_senate_side_lv)).setVisibility(View.INVISIBLE);
            }
        });
        tv = (TextView) findViewById(R.id.textView3);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView t1, t2, t3;
                t1 = (TextView) findViewById(R.id.textView);
                t2 = (TextView) findViewById(R.id.textView2);
                t3 = (TextView) findViewById(R.id.textView3);
                t1.setTextColor(Color.GRAY);
                t2.setTextColor(Color.GRAY);
                t3.setTextColor(Color.BLACK);
                ListView lv=(ListView)findViewById(R.id.listview);
                lv.setVisibility(View.INVISIBLE);
                lv=(ListView)findViewById(R.id.listview1);
                lv.setVisibility(View.INVISIBLE);
                lv=(ListView)findViewById(R.id.listview2);
                lv.setVisibility(View.VISIBLE);
                ((ListView)findViewById(R.id.leg_state_side_lv)).setVisibility(View.INVISIBLE);
                ((ListView)findViewById(R.id.leg_house_side_lv)).setVisibility(View.INVISIBLE);
                ((ListView)findViewById(R.id.leg_senate_side_lv)).setVisibility(View.VISIBLE);
            }
        });

        lv=(ListView)findViewById(R.id.listview);
        lv1=(ListView)findViewById(R.id.listview1);
        lv2=(ListView)findViewById(R.id.listview2);
        lv.setFastScrollEnabled(true);
        lv1.setFastScrollEnabled(true);
        lv2.setFastScrollEnabled(true);

        DataProvider dp=new DataProvider();
        try {
            Thread.sleep(10000);
            System.out.println("I have done my waiting");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ListView lv_side=(ListView)findViewById(R.id.leg_state_side_lv);
        LegisSideAdapter adap=new LegisSideAdapter(this,DataProvider.legStateSide);
        lv_side.setAdapter(adap);

        ListView lv_house_side=(ListView)findViewById(R.id.leg_house_side_lv);
        LegisSideAdapter1 adap1=new LegisSideAdapter1(this,DataProvider.legHouseSide);
        lv_house_side.setAdapter(adap1);
        ListView lv_senate_side=(ListView)findViewById(R.id.leg_senate_side_lv);
        LegisSideAdapter2 adap11=new LegisSideAdapter2(this,DataProvider.legSenateSide);
        lv_senate_side.setAdapter(adap11);

        dataItemList = DataProvider.legdataItemList;
        LegisDataAdapter lda=new LegisDataAdapter(this,dataItemList);
        lv.setAdapter(lda);
        List<DataItem> houseData=DataProvider.legdatahouseItemList;
        LegisDataAdapter lda1=new LegisDataAdapter(this,houseData);
        lv1.setAdapter(lda1);
        List<DataItem> senateData=DataProvider.legdatasenateItemList;
        LegisDataAdapter lda2=new LegisDataAdapter(this,senateData);
        lv2.setAdapter(lda2);
        lv1.setVisibility(View.INVISIBLE);
        lv2.setVisibility(View.INVISIBLE);


        // End Leg
        //Start COM


        tv = (TextView) findViewById(R.id.textView21);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView t1, t2, t3;
                t1 = (TextView) findViewById(R.id.textView21);
                t2 = (TextView) findViewById(R.id.textView22);
                t3 = (TextView) findViewById(R.id.textView23);
                t1.setTextColor(Color.BLACK);
                t2.setTextColor(Color.GRAY);
                t3.setTextColor(Color.GRAY);
                ListView lv=(ListView)findViewById(R.id.comlistview);
                lv.setVisibility(View.VISIBLE);
                lv=(ListView)findViewById(R.id.comlistview1);
                lv.setVisibility(View.INVISIBLE);
                lv=(ListView)findViewById(R.id.comlistview2);
                lv.setVisibility(View.INVISIBLE);
            }
        });
        tv.setTextColor(Color.BLACK);
        tv = (TextView) findViewById(R.id.textView22);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView t1, t2, t3;
                t1 = (TextView) findViewById(R.id.textView21);
                t2 = (TextView) findViewById(R.id.textView22);
                t3 = (TextView) findViewById(R.id.textView23);
                t1.setTextColor(Color.GRAY);
                t2.setTextColor(Color.BLACK);
                t3.setTextColor(Color.GRAY);
                ListView lv=(ListView)findViewById(R.id.comlistview);
                lv.setVisibility(View.INVISIBLE);
                lv=(ListView)findViewById(R.id.comlistview1);
                lv.setVisibility(View.VISIBLE);
                lv=(ListView)findViewById(R.id.comlistview2);
                lv.setVisibility(View.INVISIBLE);
            }
        });
        tv = (TextView) findViewById(R.id.textView23);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView t1, t2, t3;
                t1 = (TextView) findViewById(R.id.textView21);
                t2 = (TextView) findViewById(R.id.textView22);
                t3 = (TextView) findViewById(R.id.textView23);
                t1.setTextColor(Color.GRAY);
                t2.setTextColor(Color.GRAY);
                t3.setTextColor(Color.BLACK);
                ListView lv=(ListView)findViewById(R.id.comlistview);
                lv.setVisibility(View.INVISIBLE);
                lv=(ListView)findViewById(R.id.comlistview1);
                lv.setVisibility(View.INVISIBLE);
                lv=(ListView)findViewById(R.id.comlistview2);
                lv.setVisibility(View.VISIBLE);
            }
        });

        ListView lv21=(ListView)findViewById(R.id.comlistview);
        ListView lv22=(ListView)findViewById(R.id.comlistview1);
        ListView lv23=(ListView)findViewById(R.id.comlistview2);

        lv21.setFastScrollEnabled(true);
        lv22.setFastScrollEnabled(true);
        lv23.setFastScrollEnabled(true);
        lv21.setVisibility(View.VISIBLE);
        lv22.setVisibility(View.INVISIBLE);
        lv23.setVisibility(View.INVISIBLE);
        List<ComDataItem> comhousedata=DataProvider.comdataItemList;
        CommDataAdapter ada=new CommDataAdapter(this,comhousedata);
        lv21.setAdapter(ada);


        List<ComDataItem> comsenatedata=DataProvider.comsenatedataItemList;
        CommDataAdapter adas=new CommDataAdapter(this,comsenatedata);
        lv22.setAdapter(adas);


        List<ComDataItem> comjointdata=DataProvider.comjointdataItemList;
        CommDataAdapter adaj=new CommDataAdapter(this,comjointdata);
        lv23.setAdapter(adaj);

        ///END COMM


        ///STart BILLS


        tv = (TextView) findViewById(R.id.textView31);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView t1, t2;
                t1 = (TextView) findViewById(R.id.textView31);
                t2 = (TextView) findViewById(R.id.textView32);
                t1.setTextColor(Color.BLACK);
                t2.setTextColor(Color.GRAY);
                ListView lv=(ListView)findViewById(R.id.billlistview);
                lv.setVisibility(View.VISIBLE);
                lv=(ListView)findViewById(R.id.billlistview1);
                lv.setVisibility(View.INVISIBLE);
            }
        });
        tv.setTextColor(Color.BLACK);
        tv = (TextView) findViewById(R.id.textView32);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView t1, t2, t3;
                t1 = (TextView) findViewById(R.id.textView31);
                t2 = (TextView) findViewById(R.id.textView32);
                t1.setTextColor(Color.GRAY);
                t2.setTextColor(Color.BLACK);
                ListView lv=(ListView)findViewById(R.id.billlistview);
                lv.setVisibility(View.INVISIBLE);
                lv=(ListView)findViewById(R.id.billlistview1);
                lv.setVisibility(View.VISIBLE);
            }
        });

        ListView lv31=(ListView)findViewById(R.id.billlistview);
        ListView lv32=(ListView)findViewById(R.id.billlistview1);
        lv31.setFastScrollEnabled(true);
        lv32.setFastScrollEnabled(true);

        lv31.setVisibility(View.VISIBLE);
        lv32.setVisibility(View.INVISIBLE);

        List<BillDataItem> billactiveDataItems=DataProvider.billactivedataItemList;
        BillDataAdapter adaa=new BillDataAdapter(this,billactiveDataItems);
        lv31.setAdapter(adaa);

        List<BillDataItem> billoldDataItems=DataProvider.billolddataItemList;
        BillDataAdapter adao=new BillDataAdapter(this,billoldDataItems);
        lv32.setAdapter(adao);

///END BILLS



        ///START FAVORITES
        ListView lv41=(ListView)findViewById(R.id.favlegview);

        ListView lv42=(ListView)findViewById(R.id.favbillview);
        ListView lv43=(ListView)findViewById(R.id.favcommview);
        lv41.setFastScrollEnabled(true);
        lv42.setFastScrollEnabled(true);
        lv43.setFastScrollEnabled(true);
        TextView tv41,tv42,tv43;
        tv41=(TextView)findViewById(R.id.textView41);
        tv42=(TextView)findViewById(R.id.textView42);
        tv43=(TextView)findViewById(R.id.textView43);

        tv41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FavoritesData fd=new FavoritesData();
                TextView t1, t2, t3;
                t1 = (TextView) findViewById(R.id.textView41);
                t2 = (TextView) findViewById(R.id.textView42);
                t3 = (TextView) findViewById(R.id.textView43);
                t2.setTextColor(Color.GRAY);
                t1.setTextColor(Color.BLACK);
                t3.setTextColor(Color.GRAY);
                lv99=(ListView)findViewById(R.id.favlegview);
                //lv99.setVisibility(View.VISIBLE);
                ((RelativeLayout)findViewById(R.id.favlegrellay)).setVisibility(View.VISIBLE);
                if(FavoritesData.legisSize!=0) {
                    LegisDataAdapter ada = new LegisDataAdapter(getApplicationContext(), fd.legis);
                    lv99.setAdapter(ada);
                    LegisSideAdapter3 lsa3=new LegisSideAdapter3(getApplicationContext(),fd.legisSide);
                    ((ListView)findViewById(R.id.favlegsideview)).setAdapter(lsa3);
                    lv99.setFastScrollEnabled(true);
                }
                lv=(ListView)findViewById(R.id.favbillview);
                lv.setVisibility(View.INVISIBLE);
                lv=(ListView)findViewById(R.id.favcommview);
                lv.setVisibility(View.INVISIBLE);
            }
        });
        tv42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FavoritesData fd=new FavoritesData();
                TextView t1, t2, t3;
                t1 = (TextView) findViewById(R.id.textView41);
                t2 = (TextView) findViewById(R.id.textView42);
                t3 = (TextView) findViewById(R.id.textView43);
                t2.setTextColor(Color.BLACK);
                t1.setTextColor(Color.GRAY);
                t3.setTextColor(Color.GRAY);
                lv99=(ListView)findViewById(R.id.favlegview);
                //lv99.setVisibility(View.INVISIBLE);
                ((RelativeLayout)findViewById(R.id.favlegrellay)).setVisibility(View.INVISIBLE);
                lv=(ListView)findViewById(R.id.favbillview);
                lv.setVisibility(View.VISIBLE);
                if(FavoritesData.billSize!=0) {
                    BillDataAdapter ada = new BillDataAdapter(getApplicationContext(), fd.bill);
                    lv.setAdapter(ada);
                }
                lv=(ListView)findViewById(R.id.favcommview);
                lv.setVisibility(View.INVISIBLE);
            }
        });
        tv43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FavoritesData fd=new FavoritesData();
                TextView t1, t2, t3;
                t1 = (TextView) findViewById(R.id.textView41);
                t2 = (TextView) findViewById(R.id.textView42);
                t3 = (TextView) findViewById(R.id.textView43);
                t2.setTextColor(Color.GRAY);
                t1.setTextColor(Color.GRAY);
                t3.setTextColor(Color.BLACK);
                lv99=(ListView)findViewById(R.id.favlegview);
                //lv99.setVisibility(View.INVISIBLE);
                ((RelativeLayout)findViewById(R.id.favlegrellay)).setVisibility(View.INVISIBLE);
                lv=(ListView)findViewById(R.id.favbillview);
                lv.setVisibility(View.INVISIBLE);
                lv=(ListView)findViewById(R.id.favcommview);
                lv.setVisibility(View.VISIBLE);
                if(FavoritesData.commSize!=0) {
                    CommDataAdapter ada = new CommDataAdapter(getApplicationContext(), fd.comm);
                    lv.setAdapter(ada);
                }
            }
        });




        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        RelativeLayout rll,rlc,rlb,rlf;
        rll=(RelativeLayout)findViewById(R.id.LegislatorLayout);
        rlc=(RelativeLayout)findViewById(R.id.CommitteeLayout);
        rlb=(RelativeLayout)findViewById(R.id.BillLayout);
        rlf=(RelativeLayout)findViewById(R.id.FavoriteLayout);

        if (id == R.id.nav_camera) {
            rlc.setVisibility(View.INVISIBLE);
            rll.setVisibility(View.VISIBLE);
            rlb.setVisibility(View.INVISIBLE);
            rlf.setVisibility(View.INVISIBLE);
            if(((ListView)findViewById(R.id.listview)).getVisibility()==View.VISIBLE)
                ((ListView)findViewById(R.id.leg_state_side_lv)).setVisibility(View.VISIBLE);

            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            rlc.setVisibility(View.INVISIBLE);
            rlb.setVisibility(View.VISIBLE);
            rll.setVisibility(View.INVISIBLE);
            rlf.setVisibility(View.INVISIBLE);
            ((ListView)findViewById(R.id.leg_state_side_lv)).setVisibility(View.INVISIBLE);
        } else if (id == R.id.nav_slideshow) {
            rlc.setVisibility(View.VISIBLE);
            rlb.setVisibility(View.INVISIBLE);
            rll.setVisibility(View.INVISIBLE);
            rlf.setVisibility(View.INVISIBLE);
            ((ListView)findViewById(R.id.leg_state_side_lv)).setVisibility(View.INVISIBLE);
        } else if (id == R.id.nav_manage) {
            rlc.setVisibility(View.INVISIBLE);
            rlb.setVisibility(View.INVISIBLE);
            rll.setVisibility(View.INVISIBLE);
            rlf.setVisibility(View.VISIBLE);
            ((ListView)findViewById(R.id.leg_state_side_lv)).setVisibility(View.INVISIBLE);
            FavoritesData fd=new FavoritesData();
            if(FavoritesData.legisSize==0)
                ;
            else {
                LegisDataAdapter ada = new LegisDataAdapter(getApplicationContext(), fd.legis);
                ((ListView) findViewById(R.id.favlegview)).setAdapter(ada);
                LegisSideAdapter3 lsa3=new LegisSideAdapter3(getApplicationContext(),fd.legisSide);
                ((ListView)findViewById(R.id.favlegsideview)).setAdapter(lsa3);

            }
        } else if (id == R.id.nav_share) {
            Intent in=new Intent(getApplicationContext(),AboutMe.class);
            this.getApplicationContext().startActivity(in);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }


    @Override
    public void onPause()
    {
        super.onPause();
        FavoritesData fd=new FavoritesData();
        if(FavoritesData.legisSize==0)
            ;
        else {
            LegisDataAdapter ada = new LegisDataAdapter(getApplicationContext(), fd.legis);
            ((ListView) findViewById(R.id.favlegview)).setAdapter(ada);
        }

        if(FavoritesData.billSize==0)
            ;
        else {
            BillDataAdapter ada = new BillDataAdapter(getApplicationContext(), fd.bill);
            ((ListView) findViewById(R.id.favbillview)).setAdapter(ada);
        }


        if(FavoritesData.commSize==0)
            ;
        else {
            CommDataAdapter ada = new CommDataAdapter(getApplicationContext(), fd.comm);
            ((ListView) findViewById(R.id.favcommview)).setAdapter(ada);
        }



    }


    @Override
    public void onResume()
    {
        super.onResume();
        FavoritesData fd=new FavoritesData();
        if(FavoritesData.legisSize==0) {
            LegisDataAdapter ada = new LegisDataAdapter(getApplicationContext(), new ArrayList<DataItem>());
            ((ListView) findViewById(R.id.favlegview)).setAdapter(ada);
            LegisSideAdapter3 lsa3=new LegisSideAdapter3(getApplicationContext(),new ArrayList<String>());
            ((ListView)findViewById(R.id.favlegsideview)).setAdapter(lsa3);

        }
        else {
            LegisDataAdapter ada = new LegisDataAdapter(getApplicationContext(), fd.legis);
            ((ListView) findViewById(R.id.favlegview)).setAdapter(ada);
            LegisSideAdapter3 lsa3=new LegisSideAdapter3(getApplicationContext(),fd.legisSide);
            ((ListView)findViewById(R.id.favlegsideview)).setAdapter(lsa3);
        }

        if(FavoritesData.billSize==0)
            ;
        else {
            BillDataAdapter ada = new BillDataAdapter(getApplicationContext(), fd.bill);
            ((ListView) findViewById(R.id.favbillview)).setAdapter(ada);
        }


        if(FavoritesData.commSize==0) {
            CommDataAdapter cd=new CommDataAdapter(this,new ArrayList<ComDataItem>());
            ((ListView) findViewById(R.id.favcommview)).setAdapter(cd);
        }
        else {
            CommDataAdapter ada = new CommDataAdapter(getApplicationContext(), fd.comm);
            ((ListView) findViewById(R.id.favcommview)).setAdapter(ada);
        }

    }



    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public void moveList(int c,int position)
    {
        if(c==1)
        {
            System.out.println(position);
            //System.out.println("i am working hard");
            lv.setSelection(position);
        }
        else if(c==2)
        {
            lv1.setSelection(position);
        }
        else if(c==3)
        {
            lv2.setSelection(position);
        }
        else if(c==99)
        {
            lv99.setSelection(position);
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    }
}
