package com.app.mydrai.ui.mainModule

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.mydrai.core.network.RetrofitCallAPI
import com.app.mydrai.core.network.WebServiceAPI
import com.app.mydrai.core.presentation.base.BaseViewModel
import com.app.mydrai.data.api.QuestionAndAnswerModel
import com.app.mydrai.data.api.SessionModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import logi.retail.utils.DialogUtils
import retrofit2.Response

class MainViewModel(application: Application) : BaseViewModel(application) {
    private val uiScope = CoroutineScope(Dispatchers.Main)

    fun sessionApiCalling(): LiveData<SessionModel> {
        val loginModel = MutableLiveData<SessionModel>()
        uiScope.launch {

            val resultDef: Deferred<Response<SessionModel>> =
                verifiedSessionDetails()
            try {
                val result: Response<SessionModel> = resultDef.await()
                if (result.isSuccessful) {
                    val response = result.body()
                    response?.let {
                        if (response != null) {
                            Log.e("response", response.toString())
                            loginModel.value = response

                        } else {
                            DialogUtils.stopProgressDialog()
                            Toast.makeText(
                                getApplication(),
                                "Something went wrong",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }


                    }
                } else {
                    DialogUtils.stopProgressDialog()
                    Toast.makeText(
                        getApplication(),
                        "Please Check Internet Connections.",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            } catch (ex: Exception) {

                DialogUtils.stopProgressDialog()
                resultDef.getCompletionExceptionOrNull()?.let {
                    println(resultDef.getCompletionExceptionOrNull()!!.message)
                }

            }
        }
        return loginModel
    }

    private fun verifiedSessionDetails(
    ): Deferred<Response<SessionModel>> {
        return RetrofitCallAPI.getInstance(WebServiceAPI.SERVERBASE_URL)!!.getAllSession()
    }


    fun chatApiCalling(sessionId: String, s: String): LiveData<QuestionAndAnswerModel> {
        val loginModel = MutableLiveData<QuestionAndAnswerModel>()
        uiScope.launch {

            val resultDef: Deferred<Response<QuestionAndAnswerModel>> =
                chatApiCall(sessionId)
            try {
                val result: Response<QuestionAndAnswerModel> = resultDef.await()
                if (result.isSuccessful) {
                    val response = result.body()
                    response?.let {
                        if (response != null) {
                            Log.e("response", response.toString())
                            loginModel.value = response

                        } else {
                            DialogUtils.stopProgressDialog()
                            Toast.makeText(
                                getApplication(),
                                "Something went wrong",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }


                    }
                } else {
                    DialogUtils.stopProgressDialog()
                    Toast.makeText(
                        getApplication(),
                        "Please Check Internet Connections.",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            } catch (ex: Exception) {

                DialogUtils.stopProgressDialog()
                resultDef.getCompletionExceptionOrNull()?.let {
                    println(resultDef.getCompletionExceptionOrNull()!!.message)
                }

            }
        }
        return loginModel
    }

    private fun chatApiCall(
        sessionId: String
    ): Deferred<Response<QuestionAndAnswerModel>> {
        return RetrofitCallAPI.getInstance(WebServiceAPI.SERVERBASE_URL)!!.getAllChat(sessionId, "")
    }
}
