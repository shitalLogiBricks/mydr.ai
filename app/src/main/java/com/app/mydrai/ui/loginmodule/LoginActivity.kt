package com.app.mydrai.ui.loginmodule


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.app.mydrai.R
import com.app.mydrai.core.presentation.base.BaseActivity
import com.app.mydrai.databinding.ActivityLoginBinding
import com.app.mydrai.ui.loginmodule.optloginmodel.OTPLoginActivity
import com.app.mydrai.ui.loginmodule.passwordloginmodel.PasswordLoginActivity
import logi.retail.utils.SessionManger
import logi.retail.utils.SessionManger.Companion.PREF_FILE_NAME
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), LoginNavigator {

    var activityLoginBinding: ActivityLoginBinding? = null
    val loginViewModel: LoginViewModel by viewModel()
    var sessionManger: SessionManger? = null
    var swithch: Boolean = false


    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun getViewModel(): LoginViewModel {
        return loginViewModel
    }

    override fun setUp(savedInstanceState: Bundle?) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLoginBinding = getViewDataBinding()
        activityLoginBinding?.loginCallabck = this
        sessionManger = SessionManger(this@LoginActivity, PREF_FILE_NAME)

        activityLoginBinding!!.checkSignIn.setOnCheckedChangeListener { buttonView, isChecked ->
            swithch = isChecked
        }

    }


    override fun clickOnRegisterNow() {
       /* var intent = Intent(this@LoginActivity, RegistrationsActivity::class.java)
        startActivity(intent)*/
    }

    override fun clickOnUseOtp() {
        var stringMobile: String = activityLoginBinding!!.edtUsername.text.toString().trim()
        if (stringMobile.isNullOrEmpty()) {
            Toast.makeText(this, "Please enter Mobile no.", Toast.LENGTH_SHORT).show()
        } else {
            var intent = Intent(this@LoginActivity, OTPLoginActivity::class.java)
            intent.putExtra("MOBILE", stringMobile)
            intent.putExtra("SWITCH", activityLoginBinding!!.checkSignIn.isChecked)
            startActivity(intent)
        }
    }

    override fun clickOnUsePassword() {
        var stringMobile: String = activityLoginBinding!!.edtUsername.text.toString().trim()
        if (stringMobile.isNullOrEmpty()) {
            Toast.makeText(this, "Please enter Mobile no.", Toast.LENGTH_SHORT).show()
        } else {
            var intent = Intent(this@LoginActivity, PasswordLoginActivity::class.java)
            intent.putExtra("MOBILE", stringMobile)
            intent.putExtra("SWITCH", activityLoginBinding!!.checkSignIn.isChecked)
            startActivity(intent)
        }
    }


}
