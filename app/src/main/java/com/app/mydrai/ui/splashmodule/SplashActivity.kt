package com.app.mydrai.ui.splashmodule

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.app.mydrai.R
import com.app.mydrai.core.presentation.base.BaseActivity
import com.app.mydrai.databinding.ActivitySplashBinding
import com.app.mydrai.ui.loginmodule.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(), SplashNavigator {
    private var handler: Handler? = null
    val splashViewModel: SplashViewModel by viewModel()
    var activitySplashBinding: ActivitySplashBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handler = Handler()
        handler!!.postDelayed({
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)


    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun getViewModel(): SplashViewModel {
        return splashViewModel
    }

    override fun setUp(savedInstanceState: Bundle?) {
        activitySplashBinding = getViewDataBinding()
    }
}