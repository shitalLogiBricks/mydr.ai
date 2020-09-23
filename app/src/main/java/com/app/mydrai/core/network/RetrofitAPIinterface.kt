package com.app.mydrai.core.network


import com.app.mydrai.data.api.QuestionAndAnswerModel
import com.app.mydrai.data.api.SessionModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header


public interface RetrofitApiInterface {


    @GET(WebServiceAPI.getAllSession)
    abstract fun getAllSession(
    ): Deferred<Response<SessionModel>>

    @GET(WebServiceAPI.getAllChatData)
    abstract fun getAllChat(
        @Header("session_id") session_id: String, @Header("reply") reply: String
    ): Deferred<Response<QuestionAndAnswerModel>>


}


