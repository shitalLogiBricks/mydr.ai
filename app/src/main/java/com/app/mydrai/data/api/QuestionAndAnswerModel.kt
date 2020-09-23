package com.app.mydrai.data.api

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class QuestionAndAnswerModel {
    @SerializedName("question")
    @Expose
    var question: String? = null

    @SerializedName("options")
    @Expose
    var options: List<String>? = null

    @SerializedName("session_id")
    @Expose
    var sessionId: String? = null


    @SerializedName("error")
    @Expose
    var error: String? = null

}