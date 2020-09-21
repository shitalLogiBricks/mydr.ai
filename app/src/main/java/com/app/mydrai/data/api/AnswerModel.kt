package com.app.mydrai.data.api

import android.os.Parcel
import android.os.Parcelable

class AnswerModel() : Parcelable {
    var answer: String? = ""

    constructor(parcel: Parcel) : this() {
        answer = parcel.readString()
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(answer)
    }

    override fun describeContents(): Int {
        return 0
    }


    companion object CREATOR : Parcelable.Creator<AnswerModel> {
        override fun createFromParcel(parcel: Parcel): AnswerModel {
            return AnswerModel(parcel)
        }

        override fun newArray(size: Int): Array<AnswerModel?> {
            return arrayOfNulls(size)
        }
    }
}