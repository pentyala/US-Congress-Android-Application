package com.pentyala.hwfinal;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.pentyala.hwfinal.model.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Aditya Kiran on 11/24/2016.
 */

public class DataProvider {
    public static List<DataItem> legdataItemList=new ArrayList<>();
    public static List<DataItem> legdatahouseItemList=new ArrayList<>();
    public static List<DataItem> legdatasenateItemList=new ArrayList<>();

    public static List<String> legStateSide=new ArrayList<>();
    public static List<String> legHouseSide=new ArrayList<>();
    public static List<String> legSenateSide=new ArrayList<>();


    public static List<ComDataItem> comdataItemList=new ArrayList<>();
    public static List<ComDataItem> comsenatedataItemList=new ArrayList<>();
    public static List<ComDataItem> comjointdataItemList=new ArrayList<>();


    public static List<BillDataItem> billactivedataItemList=new ArrayList<>();
    public static List<BillDataItem> billolddataItemList=new ArrayList<>();


    static {
        new Thread(new Runnable() {
            @Override
            public void run() {
                legdataItemList = new ArrayList<>();
                legdatahouseItemList = new ArrayList<>();
                legdatasenateItemList = new ArrayList<>();

                try
                {
                    String url = "http://pentyala.us-west-2.elasticbeanstalk.com/Homework/index.php?op=legislators&category=both";
                    URL obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("User-Agent", "Mozilla/5.0");
                    int responseCode = con.getResponseCode();
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    DataItem l = new DataItem();
                    try {
                        Gson gson = new Gson();
                        JsonObject json = gson.fromJson(response.toString(), JsonObject.class);
                        JsonObject objj = new JsonParser().parse(response.toString()).getAsJsonObject();
                        JsonArray results = objj.getAsJsonArray("results");
                        for (int i = 0; i < results.size(); i++) {
                            JsonObject person = results.get(i).getAsJsonObject();
                            String fax = "", facebook_id = "", twitter_id = "", oc_email = "", website = "", phone = "", office = "", birthday = "";
                            if (person.get("fax") != JsonNull.INSTANCE)
                                fax = person.get("fax").getAsString();
                            else
                                fax = "";
                            if (person.get("facebook_id") != JsonNull.INSTANCE)
                                facebook_id = person.get("facebook_id").getAsString();
                            else
                                facebook_id = "";
                            if (person.get("twitter_id") != JsonNull.INSTANCE)
                                twitter_id = person.get("twitter_id").getAsString();
                            else
                                twitter_id = "";
                            if (person.get("oc_email") != JsonNull.INSTANCE)
                                oc_email = person.get("oc_email").getAsString();
                            else
                                oc_email = "";
                            if (person.get("website") != JsonNull.INSTANCE)
                                website = person.get("website").getAsString();
                            else
                                website = "";
                            if (person.get("phone") != JsonNull.INSTANCE)
                                phone = person.get("phone").getAsString();
                            else
                                phone = "";
                            if (person.get("office") != JsonNull.INSTANCE)
                                office = person.get("office").getAsString();
                            else
                                office = "";
                            if (person.get("birthday") != JsonNull.INSTANCE)
                                birthday = new SimpleDateFormat("dd MMM, yyyy").format(new SimpleDateFormat("MM-dd-yyy").parse(person.get("birthday").getAsString()));
                            else
                                birthday = "";

                            String district = "";
                            if (person.get("district") != JsonNull.INSTANCE)
                                district = person.get("district").getAsString();
                            else
                                district = "";

                            String bioguide_id = "";
                            if (person.get("bioguide_id") != JsonNull.INSTANCE)
                                bioguide_id = person.get("bioguide_id").getAsString();
                            else
                                bioguide_id = "";
                            String chamber = "";
                            if (person.get("chamber") != JsonNull.INSTANCE)
                                chamber = person.get("chamber").getAsString();
                            else
                                chamber = "";
                            String term_start = "";
                            if (person.get("term_start") != JsonNull.INSTANCE)
                                term_start =new SimpleDateFormat("MMM dd, yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(person.get("term_start").getAsString()));
                            else
                                term_start = "";

                            String term_end = "";
                            if (person.get("term_end") != JsonNull.INSTANCE)
                                term_end = new SimpleDateFormat("MMM dd, yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(person.get("term_end").getAsString()));
                            else
                                term_end = "";

                            String title = "";
                            if (person.get("title") != JsonNull.INSTANCE)
                                title = person.get("title").getAsString();
                            else
                                title = "";

                            String progress = "";
                            if (person.get("progress") != JsonNull.INSTANCE)
                                progress = person.get("progress").getAsString();
                            else
                                progress = "";


                            String state_name = "";
                            if (person.get("state_name") != JsonNull.INSTANCE)
                                state_name = person.get("state_name").getAsString();
                            else
                                state_name = "";

                            String first_name = "";
                            if (person.get("first_name") != JsonNull.INSTANCE)
                                first_name = person.get("first_name").getAsString();
                            else
                                first_name = "";

                            String last_name = "";
                            if (person.get("last_name") != JsonNull.INSTANCE)
                                last_name = person.get("last_name").getAsString();
                            else
                                last_name = "";

                            String party = "";
                            if (person.get("party") != JsonNull.INSTANCE)
                                party = person.get("party").getAsString();
                            else
                                party = "";


                            DataItem dtt=new DataItem(bioguide_id, birthday, chamber, district, facebook_id, fax, first_name, last_name, oc_email, office, party, phone, progress, state_name, website, twitter_id, title, term_start, term_end);
                            legdataItemList.add(dtt);
                            if(!legStateSide.contains(state_name.charAt(0)+""))
                            {
                                legStateSide.add(state_name.charAt(0)+"");
                            }
                        }
                        legdataItemList=Sort.sortLeg(legdataItemList,1);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(e);
                    }
                } catch (Exception e) {
                    System.out.println(e.toString() + "ksjadfbnkjanfka");
                }
            }
        }).start();




        new Thread(new Runnable() {
            @Override
            public void run() {
                legdatahouseItemList = new ArrayList<>();

                try
                {
                    String url = "http://pentyala.us-west-2.elasticbeanstalk.com/Homework/index.php?op=legislators&category=house";
                    URL obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("User-Agent", "Mozilla/5.0");
                    int responseCode = con.getResponseCode();
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    DataItem l = new DataItem();
                    try {
                        Gson gson = new Gson();
                        JsonObject json = gson.fromJson(response.toString(), JsonObject.class);
                        JsonObject objj = new JsonParser().parse(response.toString()).getAsJsonObject();
                        JsonArray results = objj.getAsJsonArray("results");
                        for (int i = 0; i < results.size(); i++) {
                            JsonObject person = results.get(i).getAsJsonObject();
                            String fax = "", facebook_id = "", twitter_id = "", oc_email = "", website = "", phone = "", office = "", birthday = "";
                            if (person.get("fax") != JsonNull.INSTANCE)
                                fax = person.get("fax").getAsString();
                            else
                                fax = "";
                            if (person.get("facebook_id") != JsonNull.INSTANCE)
                                facebook_id = person.get("facebook_id").getAsString();
                            else
                                facebook_id = "";
                            if (person.get("twitter_id") != JsonNull.INSTANCE)
                                twitter_id = person.get("twitter_id").getAsString();
                            else
                                twitter_id = "";
                            if (person.get("oc_email") != JsonNull.INSTANCE)
                                oc_email = person.get("oc_email").getAsString();
                            else
                                oc_email = "";
                            if (person.get("website") != JsonNull.INSTANCE)
                                website = person.get("website").getAsString();
                            else
                                website = "";
                            if (person.get("phone") != JsonNull.INSTANCE)
                                phone = person.get("phone").getAsString();
                            else
                                phone = "";
                            if (person.get("office") != JsonNull.INSTANCE)
                                office = person.get("office").getAsString();
                            else
                                office = "";
                            if (person.get("birthday") != JsonNull.INSTANCE)
                                birthday = new SimpleDateFormat("dd MMM, yyyy").format(new SimpleDateFormat("MM-dd-yyy").parse(person.get("birthday").getAsString()));
                            else
                                birthday = "";

                            String district = "";
                            if (person.get("district") != JsonNull.INSTANCE)
                                district = person.get("district").getAsString();
                            else
                                district = "";

                            String bioguide_id = "";
                            if (person.get("bioguide_id") != JsonNull.INSTANCE)
                                bioguide_id = person.get("bioguide_id").getAsString();
                            else
                                bioguide_id = "";
                            String chamber = "";
                            if (person.get("chamber") != JsonNull.INSTANCE)
                                chamber = person.get("chamber").getAsString();
                            else
                                chamber = "";
                            String term_start = "";
                            if (person.get("term_start") != JsonNull.INSTANCE)
                                term_start = new SimpleDateFormat("MMM dd, yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(person.get("term_start").getAsString()));
                            else
                                term_start = "";

                            String term_end = "";
                            if (person.get("term_end") != JsonNull.INSTANCE)
                                term_end = new SimpleDateFormat("MMM dd, yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(person.get("term_end").getAsString()));
                            else
                                term_end = "";

                            String title = "";
                            if (person.get("title") != JsonNull.INSTANCE)
                                title = person.get("title").getAsString();
                            else
                                title = "";

                            String progress = "";
                            if (person.get("progress") != JsonNull.INSTANCE)
                                progress = person.get("progress").getAsString();
                            else
                                progress = "";


                            String state_name = "";
                            if (person.get("state_name") != JsonNull.INSTANCE)
                                state_name = person.get("state_name").getAsString();
                            else
                                state_name = "";

                            String first_name = "";
                            if (person.get("first_name") != JsonNull.INSTANCE)
                                first_name = person.get("first_name").getAsString();
                            else
                                first_name = "";

                            String last_name = "";
                            if (person.get("last_name") != JsonNull.INSTANCE)
                                last_name = person.get("last_name").getAsString();
                            else
                                last_name = "";

                            String party = "";
                            if (person.get("party") != JsonNull.INSTANCE)
                                party = person.get("party").getAsString();
                            else
                                party = "";


                            DataItem dtt=new DataItem(bioguide_id, birthday, chamber, district, facebook_id, fax, first_name, last_name, oc_email, office, party, phone, progress, state_name, website, twitter_id, title, term_start, term_end);
                            legdatahouseItemList.add(dtt);
                            if(!legHouseSide.contains(last_name.charAt(0)+""))
                            {
                                legHouseSide.add(last_name.charAt(0)+"");
                            }

                        }
                        Collections.sort(legHouseSide);
                        legdatahouseItemList=Sort.sortLeg(legdatahouseItemList,2);

                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(e);
                    }
                } catch (Exception e) {
                    System.out.println(e.toString() + "ksjadfbnkjanfka");
                }
            }
        }).start();





        new Thread(new Runnable() {
            @Override
            public void run() {
                legdatasenateItemList = new ArrayList<>();
                try
                {
                    String url = "http://pentyala.us-west-2.elasticbeanstalk.com/Homework/index.php?op=legislators&category=senate";
                    URL obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("User-Agent", "Mozilla/5.0");
                    int responseCode = con.getResponseCode();
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    DataItem l = new DataItem();
                    try {
                        Gson gson = new Gson();
                        JsonObject json = gson.fromJson(response.toString(), JsonObject.class);
                        JsonObject objj = new JsonParser().parse(response.toString()).getAsJsonObject();
                        JsonArray results = objj.getAsJsonArray("results");
                        for (int i = 0; i < results.size(); i++) {
                            JsonObject person = results.get(i).getAsJsonObject();
                            String fax = "", facebook_id = "", twitter_id = "", oc_email = "", website = "", phone = "", office = "", birthday = "";
                            if (person.get("fax") != JsonNull.INSTANCE)
                                fax = person.get("fax").getAsString();
                            else
                                fax = "";
                            if (person.get("facebook_id") != JsonNull.INSTANCE)
                                facebook_id = person.get("facebook_id").getAsString();
                            else
                                facebook_id = "";
                            if (person.get("twitter_id") != JsonNull.INSTANCE)
                                twitter_id = person.get("twitter_id").getAsString();
                            else
                                twitter_id = "";
                            if (person.get("oc_email") != JsonNull.INSTANCE)
                                oc_email = person.get("oc_email").getAsString();
                            else
                                oc_email = "";
                            if (person.get("website") != JsonNull.INSTANCE)
                                website = person.get("website").getAsString();
                            else
                                website = "";
                            if (person.get("phone") != JsonNull.INSTANCE)
                                phone = person.get("phone").getAsString();
                            else
                                phone = "";
                            if (person.get("office") != JsonNull.INSTANCE)
                                office = person.get("office").getAsString();
                            else
                                office = "";
                            if (person.get("birthday") != JsonNull.INSTANCE) {
                                birthday = new SimpleDateFormat("dd MMM, yyyy").format(new SimpleDateFormat("MM-dd-yyy").parse(person.get("birthday").getAsString()));
                                System.out.println(birthday);
                            }
                            else
                                birthday = "";
                            String district = "";
                            if (person.get("district") != JsonNull.INSTANCE)
                                district = person.get("district").getAsString();
                            else
                                district = "";

                            String bioguide_id = "";
                            if (person.get("bioguide_id") != JsonNull.INSTANCE)
                                bioguide_id = person.get("bioguide_id").getAsString();
                            else
                                bioguide_id = "";
                            String chamber = "";
                            if (person.get("chamber") != JsonNull.INSTANCE)
                                chamber = person.get("chamber").getAsString();
                            else
                                chamber = "";
                            String term_start = "";
                            if (person.get("term_start") != JsonNull.INSTANCE)
                                term_start = new SimpleDateFormat("MMM dd, yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(person.get("term_start").getAsString()));
                            else
                                term_start = "";

                            String term_end = "";
                            if (person.get("term_end") != JsonNull.INSTANCE)
                                term_end = new SimpleDateFormat("MMM dd, yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(person.get("term_end").getAsString()));
                            else
                                term_end = "";

                            String title = "";
                            if (person.get("title") != JsonNull.INSTANCE)
                                title = person.get("title").getAsString();
                            else
                                title = "";

                            String progress = "";
                            if (person.get("progress") != JsonNull.INSTANCE)
                                progress = person.get("progress").getAsString();
                            else
                                progress = "";


                            String state_name = "";
                            if (person.get("state_name") != JsonNull.INSTANCE)
                                state_name = person.get("state_name").getAsString();
                            else
                                state_name = "";

                            String first_name = "";
                            if (person.get("first_name") != JsonNull.INSTANCE)
                                first_name = person.get("first_name").getAsString();
                            else
                                first_name = "";

                            String last_name = "";
                            if (person.get("last_name") != JsonNull.INSTANCE)
                                last_name = person.get("last_name").getAsString();
                            else
                                last_name = "";

                            String party = "";
                            if (person.get("party") != JsonNull.INSTANCE)
                                party = person.get("party").getAsString();
                            else
                                party = "";


                            DataItem dtt=new DataItem(bioguide_id, birthday, chamber, district, facebook_id, fax, first_name, last_name, oc_email, office, party, phone, progress, state_name, website, twitter_id, title, term_start, term_end);
                            legdatasenateItemList.add(dtt);
                            if(!legSenateSide.contains(last_name.charAt(0)+""))
                            {
                                legSenateSide.add(last_name.charAt(0)+"");
                            }

                        }
                        Collections.sort(legSenateSide);
                        legdatasenateItemList=Sort.sortLeg(legdatasenateItemList,3);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(e);
                    }
                } catch (Exception e) {
                    System.out.println(e.toString() + "ksjadfbnkjanfka");
                }
            }
        }).start();










        new Thread(new Runnable() {
            @Override
            public void run() {
                comdataItemList = new ArrayList<>();
                try {
                    String url = "http://pentyala.us-west-2.elasticbeanstalk.com/Homework/index.php?op=committees&category=house";
                    URL obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("User-Agent", "Mozilla/5.0");
                    int responseCode = con.getResponseCode();
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    ComDataItem item = new ComDataItem();
                    try {
                        Gson gson = new Gson();
                        JsonObject json = gson.fromJson(response.toString(), JsonObject.class);
                        JsonObject objj = new JsonParser().parse(response.toString()).getAsJsonObject();
                        JsonArray results = objj.getAsJsonArray("results");
                        for (int i = 0; i < results.size(); i++) {
                            JsonObject person = results.get(i).getAsJsonObject();
                            String chamber="",committee_id="",name="",parent_committee_id="",subcommittee="",phone="",office="";
                            if (person.get("chamber") != JsonNull.INSTANCE)
                                chamber = person.get("chamber").getAsString();
                            else
                                chamber = "";
                            if (person.get("committee_id") != JsonNull.INSTANCE)
                                committee_id = person.get("committee_id").getAsString();
                            else
                                committee_id = "";
                            if (person.get("name") != JsonNull.INSTANCE)
                                name = person.get("name").getAsString();
                            else
                                name = "";
                            if (person.get("parent_committee_id") != JsonNull.INSTANCE)
                                parent_committee_id = person.get("parent_committee_id").getAsString();
                            else
                                parent_committee_id = "";
                            if (person.get("subcommittee") != JsonNull.INSTANCE)
                                subcommittee = person.get("subcommittee").getAsString();
                            else
                                subcommittee = "";
                            if (person.get("phone") != JsonNull.INSTANCE)
                                phone = person.get("phone").getAsString();
                            else
                                phone = "";

                            comdataItemList.add(new ComDataItem(chamber,committee_id,name,parent_committee_id,subcommittee,phone,""));
                        }
                        comdataItemList = Sort.sortComm(comdataItemList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //Senate

        new Thread(new Runnable() {
            @Override
            public void run() {
                comdataItemList = new ArrayList<>();
                try {
                    String url = "http://pentyala.us-west-2.elasticbeanstalk.com/Homework/index.php?op=committees&category=senate";
                    URL obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("User-Agent", "Mozilla/5.0");
                    int responseCode = con.getResponseCode();
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    ComDataItem item = new ComDataItem();
                    try {
                        Gson gson = new Gson();
                        JsonObject json = gson.fromJson(response.toString(), JsonObject.class);
                        JsonObject objj = new JsonParser().parse(response.toString()).getAsJsonObject();
                        JsonArray results = objj.getAsJsonArray("results");
                        for (int i = 0; i < results.size(); i++) {
                            JsonObject person = results.get(i).getAsJsonObject();
                            String chamber="",committee_id="",name="",parent_committee_id="",subcommittee="",phone="",office="";
                            if (person.get("chamber") != JsonNull.INSTANCE)
                                chamber = person.get("chamber").getAsString();
                            else
                                chamber = "";
                            if (person.get("committee_id") != JsonNull.INSTANCE)
                                committee_id = person.get("committee_id").getAsString();
                            else
                                committee_id = "";
                            if (person.get("name") != JsonNull.INSTANCE)
                                name = person.get("name").getAsString();
                            else
                                name = "";
                            if (person.get("parent_committee_id") != JsonNull.INSTANCE)
                                parent_committee_id = person.get("parent_committee_id").getAsString();
                            else
                                parent_committee_id = "";
                            if (person.get("subcommittee") != JsonNull.INSTANCE)
                                subcommittee = person.get("subcommittee").getAsString();
                            else
                                subcommittee = "";
                            comsenatedataItemList.add(new ComDataItem(chamber,committee_id,name,parent_committee_id,subcommittee,"",""));
                        }
                        comsenatedataItemList = Sort.sortComm(comsenatedataItemList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


        //JOint

        new Thread(new Runnable() {
            @Override
            public void run() {
                comjointdataItemList = new ArrayList<>();
                try {
                    String url = "http://pentyala.us-west-2.elasticbeanstalk.com/Homework/index.php?op=committees&category=joint";
                    URL obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("User-Agent", "Mozilla/5.0");
                    int responseCode = con.getResponseCode();
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    ComDataItem item = new ComDataItem();
                    try {
                        Gson gson = new Gson();
                        JsonObject json = gson.fromJson(response.toString(), JsonObject.class);
                        JsonObject objj = new JsonParser().parse(response.toString()).getAsJsonObject();
                        JsonArray results = objj.getAsJsonArray("results");
                        for (int i = 0; i < results.size(); i++) {
                            JsonObject person = results.get(i).getAsJsonObject();
                            String chamber="",committee_id="",name="",parent_committee_id="",subcommittee="",phone="",office="";
                            if (person.get("chamber") != JsonNull.INSTANCE)
                                chamber = person.get("chamber").getAsString();
                            else
                                chamber = "";
                            if (person.get("committee_id") != JsonNull.INSTANCE)
                                committee_id = person.get("committee_id").getAsString();
                            else
                                committee_id = "";
                            if (person.get("name") != JsonNull.INSTANCE)
                                name = person.get("name").getAsString();
                            else
                                name = "";
                            if (person.get("parent_committee_id") != JsonNull.INSTANCE)
                                parent_committee_id = person.get("parent_committee_id").getAsString();
                            else
                                parent_committee_id = "";
                            if (person.get("subcommittee") != JsonNull.INSTANCE)
                                subcommittee = person.get("subcommittee").getAsString();
                            else
                                subcommittee = "";
                            comjointdataItemList.add(new ComDataItem(chamber,committee_id,name,parent_committee_id,subcommittee,"",""));
                        }
                        comjointdataItemList = Sort.sortComm(comjointdataItemList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();





        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Working on your data");
                billactivedataItemList = new ArrayList<>();
                try {
                    String url = "http://pentyala.us-west-2.elasticbeanstalk.com/Homework/index.php?op=bills&category=active";
                    URL obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("User-Agent", "Mozilla/5.0");
                    int responseCode = con.getResponseCode();
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    ComDataItem item = new ComDataItem();
                    try {
                        Gson gson = new Gson();
                        JsonObject json = gson.fromJson(response.toString(), JsonObject.class);
                        JsonObject objj = new JsonParser().parse(response.toString()).getAsJsonObject();
                        JsonArray results = objj.getAsJsonArray("results");
                        for (int i = 0; i < results.size(); i++) {
                            JsonObject person = results.get(i).getAsJsonObject();
                            String bill_id,short_title,introduced_on,bill_type,sponsor_first_name,sponsor_last_name,sponsor_title,status,congress_url,version_status,bill_url,chamber;
                            if (person.get("chamber") != JsonNull.INSTANCE)
                                chamber = person.get("chamber").getAsString();
                            else
                                chamber = "";

                            if (person.get("bill_id") != JsonNull.INSTANCE)
                                bill_id = person.get("bill_id").getAsString();
                            else
                                bill_id = "";

                            if (person.get("short_title") != JsonNull.INSTANCE)
                                short_title = person.get("short_title").getAsString();
                            else
                                short_title = person.get("official_title").getAsString();;
                            if (person.get("introduced_on") != JsonNull.INSTANCE)
                                introduced_on = person.get("introduced_on").getAsString();
                            else
                                introduced_on = "";

                            if (person.get("bill_type") != JsonNull.INSTANCE)
                                bill_type = person.get("bill_type").getAsString();
                            else
                                bill_type = "";

                            if (person.get("sponsor").getAsJsonObject().get("first_name")!= JsonNull.INSTANCE)
                                sponsor_first_name = person.get("sponsor").getAsJsonObject().get("first_name").getAsString();
                            else
                                sponsor_first_name = "";
                            if (person.get("sponsor").getAsJsonObject().get("last_name") != JsonNull.INSTANCE)
                                sponsor_last_name = person.get("sponsor").getAsJsonObject().get("last_name").getAsString();
                            else
                                sponsor_last_name = "";

                            if (person.get("sponsor").getAsJsonObject().get("title") != JsonNull.INSTANCE)
                                sponsor_title = person.get("sponsor").getAsJsonObject().get("title").getAsString();
                            else
                                sponsor_title = "";
                            if (person.get("history").getAsJsonObject().get("active") != JsonNull.INSTANCE)
                                status = person.get("history").getAsJsonObject().get("active").getAsString();
                            else
                                status = "";
                            if (person.get("urls").getAsJsonObject().get("congress") != JsonNull.INSTANCE)
                                congress_url = person.get("urls").getAsJsonObject().get("congress").getAsString();
                            else
                                congress_url = "";
                            if (person.get("last_version").getAsJsonObject().get("version_name") != JsonNull.INSTANCE)
                                version_status = person.get("chamber").getAsString();
                            else
                                version_status = "";
                            if (person.get("last_version").getAsJsonObject().get("urls").getAsJsonObject().get("pdf") != JsonNull.INSTANCE)
                                bill_url = person.get("last_version").getAsJsonObject().get("urls").getAsJsonObject().get("pdf").getAsString();
                            else
                                bill_url = "";
                            billactivedataItemList.add(new BillDataItem(bill_id,short_title,introduced_on,bill_type,sponsor_first_name,sponsor_last_name,sponsor_title,status,congress_url,version_status,bill_url,chamber));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    ///end active
    ///start old
        new Thread(new Runnable() {
            @Override
            public void run() {
                billolddataItemList = new ArrayList<>();
                try {
                    String url = "http://pentyala.us-west-2.elasticbeanstalk.com/Homework/index.php?op=bills&category=old";
                    URL obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("User-Agent", "Mozilla/5.0");
                    int responseCode = con.getResponseCode();
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    ComDataItem item = new ComDataItem();
                    try {
                        Gson gson = new Gson();
                        JsonObject json = gson.fromJson(response.toString(), JsonObject.class);
                        JsonObject objj = new JsonParser().parse(response.toString()).getAsJsonObject();
                        JsonArray results = objj.getAsJsonArray("results");
                        for (int i = 0; i < results.size(); i++) {
                            JsonObject person = results.get(i).getAsJsonObject();
                            String bill_id,short_title,introduced_on,bill_type,sponsor_first_name,sponsor_last_name,sponsor_title,status,congress_url,version_status,bill_url,chamber;
                            if (person.get("chamber") != JsonNull.INSTANCE)
                                chamber = person.get("chamber").getAsString();
                            else
                                chamber = "";

                            if (person.get("bill_id") != JsonNull.INSTANCE)
                                bill_id = person.get("bill_id").getAsString();
                            else
                                bill_id = "";

                            if (person.get("short_title") != JsonNull.INSTANCE)
                                short_title = person.get("short_title").getAsString();
                            else
                                short_title = person.get("official_title").getAsString();;
                            if (person.get("introduced_on") != JsonNull.INSTANCE)
                                introduced_on = person.get("introduced_on").getAsString();
                            else
                                introduced_on = "";

                            if (person.get("bill_type") != JsonNull.INSTANCE)
                                bill_type = person.get("bill_type").getAsString();
                            else
                                bill_type = "";

                            if (person.get("sponsor").getAsJsonObject().get("first_name")!= JsonNull.INSTANCE)
                                sponsor_first_name = person.get("sponsor").getAsJsonObject().get("first_name").getAsString();
                            else
                                sponsor_first_name = "";
                            if (person.get("sponsor").getAsJsonObject().get("last_name") != JsonNull.INSTANCE)
                                sponsor_last_name = person.get("sponsor").getAsJsonObject().get("last_name").getAsString();
                            else
                                sponsor_last_name = "";

                            if (person.get("sponsor").getAsJsonObject().get("title") != JsonNull.INSTANCE)
                                sponsor_title = person.get("sponsor").getAsJsonObject().get("title").getAsString();
                            else
                                sponsor_title = "";
                            if (person.get("history").getAsJsonObject().get("active") != JsonNull.INSTANCE)
                                status = person.get("history").getAsJsonObject().get("active").getAsString();
                            else
                                status = "";
                            if (person.get("urls").getAsJsonObject().get("congress") != JsonNull.INSTANCE)
                                congress_url = person.get("urls").getAsJsonObject().get("congress").getAsString();
                            else
                                congress_url = "";
                            if (person.get("last_version").getAsJsonObject().get("version_name") != JsonNull.INSTANCE)
                                version_status = person.get("chamber").getAsString();
                            else
                                version_status = "";
                            if (person.get("last_version").getAsJsonObject().get("urls").getAsJsonObject().get("pdf") != JsonNull.INSTANCE)
                                bill_url = person.get("last_version").getAsJsonObject().get("urls").getAsJsonObject().get("pdf").getAsString();
                            else
                                bill_url = "";
                            billolddataItemList.add(new BillDataItem(bill_id,short_title,introduced_on,bill_type,sponsor_first_name,sponsor_last_name,sponsor_title,status,congress_url,version_status,bill_url,chamber));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}


class Sort
{
    public static List<DataItem> sortLeg(List<DataItem> data,int ch)
    {
        if(ch==1) {
            Collections.sort(data, new Comparator<DataItem>() {
                @Override
                public int compare(final DataItem object1, final DataItem object2) {
                    return object1.getState_name().compareTo(object2.getState_name());
                }
            });
            return data;
        }
        else if (ch==2){
            Collections.sort(data, new Comparator<DataItem>() {
                @Override
                public int compare(final DataItem object1, final DataItem object2) {
                    return object1.getLast_name().compareTo(object2.getLast_name());
                }
            });
            return data;
        }
        else{
            Collections.sort(data, new Comparator<DataItem>() {
                @Override
                public int compare(final DataItem object1, final DataItem object2) {
                    return object1.getLast_name().compareTo(object2.getLast_name());
                }
            });
            return data;
        }
    }

    public static List<BillDataItem> sortBill(List<BillDataItem> data)
    {
        Collections.sort(data, new Comparator<BillDataItem>() {
            @Override
            public int compare(final BillDataItem object1, final BillDataItem object2) {
                return object2.getIntroduced_on().compareTo(object1.getIntroduced_on());
            }
        });
        return data;
    }

    public static List<ComDataItem> sortComm(List<ComDataItem> data)
    {
        Collections.sort(data, new Comparator<ComDataItem>() {
            @Override
            public int compare(final ComDataItem object1, final ComDataItem object2) {
                return object1.getName().compareTo(object2.getName());
            }
        });
        return data;
    }



}
