package ru.geekbrains.android1;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.LinkedList;

public class UserData implements Parcelable {

    private String textBox;

    public String getTextBox() {
        return textBox;
    }

    public void setTextBox(String textBox) {
        this.textBox = textBox;
    }

    protected UserData(Parcel in) {
        textBox = in.readString();
    }

    public static final Creator<UserData> CREATOR = new Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };

    public UserData(String textBox) {
        this.textBox = textBox;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(textBox);
    }
}
