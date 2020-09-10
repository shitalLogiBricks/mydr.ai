package com.app.mydrai.core.network


import com.app.mydrai.data.api.usermodel.UserListModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET


public interface RetrofitApiInterface {


    @GET(WebServiceAPI.UserList)
    abstract fun userList(
    ): Deferred<Response<UserListModel>>


}


