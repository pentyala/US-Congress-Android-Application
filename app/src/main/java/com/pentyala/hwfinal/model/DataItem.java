package com.pentyala.hwfinal.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DataItem implements Parcelable {

    private String bioguide_id;
    private String first_name;
    private String last_name;
    private String title;
    private String chamber;
    private String party;
    private String state_name;
    private String term_start;
    private String term_end;
    private String district;
    private String progress;
    private String facebook_id;
    private String twitter_id;
    private String website;
    private String fax;
    private String oc_email;
    private String phone;
    private String office;
    //private String chamber1;
    private String birthday;

    public DataItem()
    {

    }

    protected DataItem(Parcel in) {
        bioguide_id = in.readString();
        first_name = in.readString();
        last_name = in.readString();
        title = in.readString();
        chamber = in.readString();
        party = in.readString();
        state_name = in.readString();
        term_start = in.readString();
        term_end = in.readString();
        district = in.readString();
        progress = in.readString();
        facebook_id = in.readString();
        twitter_id = in.readString();
        website = in.readString();
        fax = in.readString();
        oc_email = in.readString();
        phone = in.readString();
        office = in.readString();
        birthday = in.readString();
    }

    public static final Creator<DataItem> CREATOR = new Creator<DataItem>() {
        @Override
        public DataItem createFromParcel(Parcel in) {
            return new DataItem(in);
        }

        @Override
        public DataItem[] newArray(int size) {
            return new DataItem[size];
        }
    };

    public DataItem(String bioguide_id, String birthday, String chamber, String district, String facebook_id, String fax, String first_name, String last_name, String oc_email, String office, String party, String phone, String progress, String state_name, String website, String twitter_id, String title, String term_start, String term_end) {
        this.bioguide_id = bioguide_id;
        this.birthday = birthday;
        this.chamber = chamber;
        this.district = district;
        this.facebook_id = facebook_id;
        this.fax = fax;
        this.first_name = first_name;
        this.last_name = last_name;
        this.oc_email = oc_email;
        this.office = office;
        //this.chamber1 = chamber1;
        this.party = party;
        this.phone = phone;
        this.progress = progress;
        this.state_name = state_name;
        this.website = website;
        this.twitter_id = twitter_id;
        this.title = title;
        this.term_start = term_start;
        this.term_end = term_end;
    }


    public String getBioguide_id() {
        return bioguide_id;
    }

    public void setBioguide_id(String bioguide_id) {
        this.bioguide_id = bioguide_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChamber() {
        return chamber;
    }

    public void setChamber(String chamber) {
        this.chamber = chamber;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getTerm_start() {
        return term_start;
    }

    public void setTerm_start(String term_start) {
        this.term_start = term_start;
    }

    public String getTerm_end() {
        return term_end;
    }

    public void setTerm_end(String term_end) {
        this.term_end = term_end;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getFacebook_id() {
        return facebook_id;
    }

    public void setFacebook_id(String facebook_id) {
        this.facebook_id = facebook_id;
    }

    public String getTwitter_id() {
        return twitter_id;
    }

    public void setTwitter_id(String twitter_id) {
        this.twitter_id = twitter_id;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getOc_email() {
        return oc_email;
    }

    public void setOc_email(String oc_email) {
        this.oc_email = oc_email;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bioguide_id);
        dest.writeString(this.birthday);
        dest.writeString(this.chamber);
        dest.writeString(this.district);
        dest.writeString(this.facebook_id);
        dest.writeString(this.fax);
        dest.writeString(this.first_name);
        dest.writeString(this.last_name);
        dest.writeString(this.oc_email);
        dest.writeString(this.party);
        dest.writeString(this.phone);
        dest.writeString(this.progress);
        dest.writeString(this.state_name);
        dest.writeString(this.term_end);
        dest.writeString(this.term_start);
        dest.writeString(this.website);
        dest.writeString(this.title);
        dest.writeString(this.twitter_id);


    }

   /* @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.itemId);
        dest.writeString(this.itemName);
        dest.writeString(this.description);
        dest.writeString(this.category);
        dest.writeInt(this.sortPosition);
        dest.writeDouble(this.price);
        dest.writeString(this.image);
    }

    protected DataItem(Parcel in) {
        this.itemId = in.readString();
        this.itemName = in.readString();
        this.description = in.readString();
        this.category = in.readString();
        this.sortPosition = in.readInt();
        this.price = in.readDouble();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<DataItem> CREATOR = new Parcelable.Creator<DataItem>() {
        @Override
        public DataItem createFromParcel(Parcel source) {
            return new DataItem(source);
        }

        @Override
        public DataItem[] newArray(int size) {
            return new DataItem[size];
        }
    };
    */
}
