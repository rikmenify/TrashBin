package com.example.rikirikmen.trashbin;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Riki Rikmen on 6/4/2016.
 */

public class Trash implements Parcelable {
    private String trashName;
    private int trashQty;
    private int trashImg;

    public Trash(String trashName, int trashQty, int trashImg) {
        this.trashName = trashName;
        this.trashQty = trashQty;
        this.trashImg = trashImg;
    }

    protected Trash(Parcel in) {
        trashName = in.readString();
        trashQty = in.readInt();
        trashImg = in.readInt();
    }

    public static final Creator<Trash> CREATOR = new Creator<Trash>() {
        @Override
        public Trash createFromParcel(Parcel in) {
            return new Trash(in);
        }

        @Override
        public Trash[] newArray(int size) {
            return new Trash[size];
        }
    };

    public String getTrashName() {

        return trashName;
    }

    public void setTrashName(String trashName) {
        this.trashName = trashName;
    }

    public int getTrashQty() {
        return trashQty;
    }

    public void setTrashQty(int trashQty) {
        this.trashQty = trashQty;
    }

    public int getTrashImg() {
        return trashImg;
    }

    public void setTrashImg(int trashImg) {
        this.trashImg = trashImg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(trashName);
        dest.writeInt(trashQty);
        dest.writeInt(trashImg);
    }
}
