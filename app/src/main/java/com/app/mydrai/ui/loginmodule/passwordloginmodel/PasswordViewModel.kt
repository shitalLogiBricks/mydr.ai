package com.app.mydrai.ui.loginmodule.passwordloginmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.mydrai.core.network.RetrofitCallAPI
import com.app.mydrai.core.network.WebServiceAPI
import com.app.mydrai.core.presentation.base.BaseViewModel
import com.app.mydrai.data.api.QuestionModel
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import logi.retail.utils.DialogUtils
import retrofit2.Response

class PasswordViewModel(application: Application) : BaseViewModel(application) {
    private val uiScope = CoroutineScope(Dispatchers.Main)

    fun loginUser(
        mobile_no: String,
        is_otp_verified: Boolean,
        password: String
    ): LiveData<QuestionModel> {
        val loginModel = MutableLiveData<QuestionModel>()
      /*  uiScope.launch {

            val resultDef: Deferred<Response<LoginModel>> =
                verifiedLoginDetails(mobile_no, is_otp_verified, password)
            try {
                val result: Response<LoginModel> = resultDef.await()
                if (result.isSuccessful) {
                    val response = result.body()
                    response?.let {
                        if (response != null) {
                            if (response.flag.equals("1")) {
                                loginModel.value = response
                            } else {
                                DialogUtils.stopProgressDialog()
                                Toasty.error(
                                    getApplication(),
                                    "${response.data?.error_message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

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
        }*/
        return loginModel
    }

 /*   private fun verifiedLoginDetails(
        mobile_no: String,
        otp_verified: Boolean,
        password: String
    ): Deferred<Response<LoginModel>> {
        return RetrofitCallAPI.getInstance(WebServiceAPI.SERVERBASE_URL)!!.validateUserLogin(
            mobile_no,
            otp_verified,
            password
        )
    }*/



}