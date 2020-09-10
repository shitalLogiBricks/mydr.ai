package com.app.mydrai.utils

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.mydrai.R
import com.app.mydrai.databinding.ActivityNetworkStatusBinding
import com.app.mydrai.ui.mainModule.MainActivity

class NetworkStatus : AppCompatActivity(), View.OnClickListener {
    var networkStatusBinding: ActivityNetworkStatusBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE
            )
        }
        networkStatusBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_network_status)
        networkStatusBinding!!.btnretry.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (Connectionnet.getConnectionnetInstance().isConnected(applicationContext)) {
            val l = Intent(this@NetworkStatus, MainActivity::class.java)
            startActivity(l)
            finish()
        } else {
            Connectionnet.getConnectionnetInstance()
                .showNetworkToastMessage(applicationContext)
        }
    }
}