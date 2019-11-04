package com.example.hw3

import java.io.Serializable
import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable

class Human(val name: String, val hairColor: String):
 Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(hairColor)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Human> {
        override fun createFromParcel(parcel: Parcel): Human {
            return Human(parcel)
        }

        override fun newArray(size: Int): Array<Human?> {
            return arrayOfNulls(size)
        }
    }
}