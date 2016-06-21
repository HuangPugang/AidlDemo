package com.example.aidlserver;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Message2 implements Parcelable {

	private int id;
	private String msgText;
	private String fromName;
	private String date;

	public Message2() {
		super();

	}

	public Message2(int id, String msgText, String fromName, String date) {
		super();
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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public String toString() {
		return "msgText" + msgText + "fromName"
				+ fromName + ", date=" + date ;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		Log.i("main", "客户端Message被序列化");
		dest.writeInt(id);
		dest.writeString(msgText);
		dest.writeString(fromName);
		dest.writeString(date);
	}

	public static final Creator<Message2> CREATOR = new Creator<Message2>() {

		@Override
		public Message2[] newArray(int size) {
			return new Message2[size];
		}

		@Override
		public Message2 createFromParcel(Parcel source) {
			Log.i("main", "createFromParcel");
			return new Message2(source.readInt(), source.readString(),
					source.readString(), source.readString());
		}
	};
}
