package com.devon.firstapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class EachMatch implements Parcelable{
    public String uid;
    public String name;
    public boolean like;
    public String imageUrl;

    public EachMatch() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public EachMatch(String uid, String name, String imageUrl, boolean like) {
        this.uid = uid;
        this.name = name;
        this.imageUrl = imageUrl;
        this.like = like;
    }

    public EachMatch(Parcel in) {
        uid = in.readString();
        name = in.readString();
        imageUrl = in.readString();
        like = in.readByte() != 0;
    }

    public static final Creator<EachMatch> CREATOR = new Creator<EachMatch>() {
        @Override
        public EachMatch createFromParcel(Parcel in) {
            return new EachMatch(in);
        }

        @Override
        public EachMatch[] newArray(int size) {
            return new EachMatch[size];
        }
    };

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", name);
        result.put("url", imageUrl);
        result.put("like", like);

        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(imageUrl);
        dest.writeByte((byte) (like ? 1 : 0));
    }


}
