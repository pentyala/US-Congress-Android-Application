package com.pentyala.hwfinal.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Aditya Kiran on 11/24/2016.
 */

public class BillDataItem implements Parcelable {

    private String bill_id;
    private String short_title;
    private String introduced_on;
    private String bill_type;
    private String sponsor_first_name;
    private String sponsor_last_name;
    private String sponsor_title;
    private String status;
    private String congress_url;
    private String version_status;
    private String bill_url;
    private String chamber;



    public BillDataItem(Parcel in) {
        bill_id = in.readString();
        short_title = in.readString();
        introduced_on = in.readString();
        bill_type = in.readString();
        sponsor_first_name = in.readString();
        sponsor_last_name = in.readString();
        sponsor_title = in.readString();
        status = in.readString();
        congress_url = in.readString();
        version_status = in.readString();
        bill_url = in.readString();
        chamber = in.readString();
    }

    public static final Creator<BillDataItem> CREATOR = new Creator<BillDataItem>() {
        @Override
        public BillDataItem createFromParcel(Parcel in) {
            return new BillDataItem(in);
        }

        @Override
        public BillDataItem[] newArray(int size) {
            return new BillDataItem[size];
        }
    };

    public BillDataItem(String bill_id, String short_title, String introduced_on, String bill_type, String sponsor_first_name, String sponsor_last_name, String sponsor_title, String status, String congress_url, String version_status, String bill_url, String chamber) {

        this.bill_id = bill_id;
        this.short_title = short_title;
        this.introduced_on = introduced_on;
        this.bill_type = bill_type;
        this.sponsor_first_name = sponsor_first_name;
        this.sponsor_last_name = sponsor_last_name;
        this.sponsor_title = sponsor_title;
        this.status = status;
        this.congress_url = congress_url;
        this.version_status = version_status;
        this.bill_url = bill_url;
        this.chamber = chamber;
    }

    public String getBill_id() {
        return bill_id;
    }
    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }

    public String getShort_title() {
        return short_title;
    }

    public void setShort_title(String short_title) {
        this.short_title = short_title;
    }

    public String getIntroduced_on() {
        return introduced_on;
    }

    public void setIntroduced_on(String introduced_on) {
        this.introduced_on = introduced_on;
    }

    public String getBill_type() {
        return bill_type;
    }

    public void setBill_type(String bill_type) {
        this.bill_type = bill_type;
    }

    public String getSponsor_first_name() {
        return sponsor_first_name;
    }

    public void setSponsor_first_name(String sponsor_first_name) {
        this.sponsor_first_name = sponsor_first_name;
    }

    public String getSponsor_last_name() {
        return sponsor_last_name;
    }

    public void setSponsor_last_name(String sponsor_last_name) {
        this.sponsor_last_name = sponsor_last_name;
    }

    public String getSponsor_title() {
        return sponsor_title;
    }

    public void setSponsor_title(String sponsor_title) {
        this.sponsor_title = sponsor_title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCongress_url() {
        return congress_url;
    }

    public void setCongress_url(String congress_url) {
        this.congress_url = congress_url;
    }

    public String getVersion_status() {
        return version_status;
    }

    public void setVersion_status(String version_status) {
        this.version_status = version_status;
    }

    public String getBill_url() {
        return bill_url;
    }

    public void setBill_url(String bill_url) {
        this.bill_url = bill_url;
    }

    public String getChamber() {
        return chamber;
    }

    public void setChamber(String chamber) {
        this.chamber = chamber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bill_id);
        dest.writeString(short_title);
        dest.writeString(introduced_on);
        dest.writeString(bill_type);
        dest.writeString(sponsor_first_name);
        dest.writeString(sponsor_last_name);
        dest.writeString(sponsor_title);
        dest.writeString(status);
        dest.writeString(congress_url);
        dest.writeString(version_status);
        dest.writeString(bill_url);
        dest.writeString(chamber);
    }
}
