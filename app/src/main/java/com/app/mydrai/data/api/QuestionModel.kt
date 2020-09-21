package com.app.mydrai.data.api

import android.os.Parcel
import android.os.Parcelable

class QuestionModel() : Parcelable {
    var mainQuestion: String = ""
    var subQuestion: String = ""
    var answerModel: ArrayList<AnswerModel>? = null

    constructor(parcel: Parcel) : this() {
        mainQuestion = parcel?.readString().toString()
        subQuestion = parcel?.readString().toString()

    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(mainQuestion)
        dest?.writeString(subQuestion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<QuestionModel> {
        override fun createFromParcel(parcel: Parcel): QuestionModel {
            return QuestionModel(parcel)
        }

        override fun newArray(size: Int): Array<QuestionModel?> {
            return arrayOfNulls(size)
        }
    }

}