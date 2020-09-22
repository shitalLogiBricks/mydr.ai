package com.app.mydrai.core.network


import com.app.mydrai.data.api.QuestionModel
import com.app.mydrai.data.api.SessionModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST


public interface RetrofitApiInterface {


    @GET(WebServiceAPI.getAllSession)
    abstract fun getAllSession(
    ): Deferred<Response<SessionModel>>

    @POST(WebServiceAPI.getAllChatData)
    abstract fun getAllChat(
    ): Deferred<Response<SessionModel>>


}


