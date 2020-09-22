package com.app.mydrai.data.api

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class SessionModel {

    @SerializedName("session_id")
    @Expose
    var sessionId: String? = null

    @SerializedName("timestamp")
    @Expose
    var timestamp: String? = null

}