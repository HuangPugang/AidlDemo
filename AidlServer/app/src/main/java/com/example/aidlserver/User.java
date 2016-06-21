package com.example.aidlserver;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by paul on 16/6/21.
 */
public class User implements Parcelable {
    private int id;
    private String msgText;
    private String fromName;
    private String date;

    public User(int id, String msgText, String fromName, String date) {
        this.id = id;
        this.msgText = msgText;
        this.fromName = fromName;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    protected User(Parcel in) {
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.i("main", "客户端Message被序列化");
        dest.writeInt(id);
        dest.writeString(msgText);
        dest.writeString(fromName);
        dest.writeString(date);
    }


    @Override
    public String toString() {
        return "信息内容=" + msgText + ", 发件人="
                + fromName + ", 时间=" + date ;
    }
}
