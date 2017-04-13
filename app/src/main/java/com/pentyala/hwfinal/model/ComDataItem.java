package com.pentyala.hwfinal.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Aditya Kiran on 11/24/2016.
 */

public class ComDataItem implements Parcelable {
    private String chamber;
    private String committee_id;
    private String name;
    private String parent_committee_id;
    private String subcommittee;
    private String phone;
    private String office;


    public ComDataItem()
    {

    }

    protected ComDataItem(Parcel in) {
        chamber = in.readString();
        committee_id = in.readString();
        name = in.readString();
        parent_committee_id = in.readString();
        subcommittee = in.readString();
        phone = in.readString();
        office = in.readString();
    }

    public static final Creator<ComDataItem> CREATOR = new Creator<ComDataItem>() {
        @Override
        public ComDataItem createFromParcel(Parcel in) {
            return new ComDataItem(in);
        }

        @Override
        public ComDataItem[] newArray(int size) {
            return new ComDataItem[size];
        }
    };

    public ComDataItem(String chamber, String committee_id, String name, String parent_committee_id, String subcommittee, String phone, String office)
    {
        this.chamber = chamber;
        this.committee_id = committee_id;
        this.name = name;
        this.parent_committee_id = parent_committee_id;
        this.subcommittee = subcommittee;
        this.phone = phone;
        this.office = office;
    }


    public String getChamber() {
        return chamber;
    }

    public void setChamber(String chamber) {
        this.chamber = chamber;
    }

    public String getCommittee_id() {
        return committee_id;
    }

    public void setCommittee_id(String committee_id) {
        this.committee_id = committee_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent_committee_id() {
        return parent_committee_id;
    }

    public void setParent_committee_id(String parent_committee_id) {
        this.parent_committee_id = parent_committee_id;
    }

    public String getSubcommittee() {
        return subcommittee;
    }

    public void setSubcommittee(String subcommittee) {
        this.subcommittee = subcommittee;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(chamber);
        dest.writeString(committee_id);
        dest.writeString(name);
        dest.writeString(parent_committee_id);
        dest.writeString(subcommittee);
        dest.writeString(phone);
        dest.writeString(office);
    }
}
