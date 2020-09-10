package com.app.mydrai.ui.mainModule


import android.os.Bundle
import android.webkit.WebView
import com.app.mydrai.R
import com.app.mydrai.core.presentation.base.BaseActivity
import com.app.mydrai.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    var activityMainBinding: ActivityMainBinding? = null
    val mainViewModel: MainViewModel by viewModel()
    var webView: WebView? = null
    var url = "https://app.reflexion.ai/alpha"
    private val PERMISSION_REQUEST_CODE = 3
    var loadingFinished = true
    var redirect = false

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): MainViewModel {
        return mainViewModel
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = getViewDataBinding()


    }

    override fun setUp(savedInstanceState: Bundle?) {
        //   activityMainBinding!!.webView.saveState(savedInstanceState)
    }


}









