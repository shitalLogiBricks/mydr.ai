package com.app.mydrai.ui.mainModule


import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.mydrai.R
import com.app.mydrai.core.presentation.base.BaseActivity
import com.app.mydrai.data.api.QuestionAndAnswerModel
import com.app.mydrai.databinding.ActivityMainBinding
import com.app.mydrai.ui.mainModule.adapter.AnswerAdapter
import logi.retail.utils.DialogUtils
import logi.retail.utils.SessionManger
import logi.retail.utils.SessionManger.Companion.PREF_FILE_NAME
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    var activityMainBinding: ActivityMainBinding? = null
    val mainViewModel: MainViewModel by viewModel()
    var questionAdapter: AnswerAdapter? = null
    var questionList = ArrayList<QuestionAndAnswerModel>()
    var sessionManger: SessionManger? = null
    var mainNavigator:MainNavigator?=null


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): MainViewModel {
        return mainViewModel
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = getViewDataBinding()
        mainNavigator=this
        sessionManger = SessionManger(this@MainActivity, PREF_FILE_NAME)
        DialogUtils.startProgressDialog(this@MainActivity);
        mainViewModel.sessionApiCalling().observe(this, Observer {
            if (it != null) {

                sessionManger?.setSessionId(it.sessionId?.toString()!!)

                callChatApi()
            }
        })
    }

    private fun callChatApi() {
        mainViewModel.chatApiCalling(sessionManger?.getSessionId()!!, "")
            .observe(this, Observer {
                if (it.error == null) {
                    DialogUtils.stopProgressDialog()
                    activityMainBinding?.txtQuestion?.text = it.question
                    var option: List<String>? = it.options
                    questionAdapter =
                        AnswerAdapter(
                            this@MainActivity,
                            option as ArrayList<String>,mainNavigator

                        )
                    activityMainBinding!!.recyclerAndList.layoutManager =
                        LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
                    activityMainBinding!!.recyclerAndList.adapter = questionAdapter

                } else if (it?.error!=null) {

                    DialogUtils.stopProgressDialog()
                    Toast.makeText(this@MainActivity, "" + it.error, Toast.LENGTH_SHORT).show()

                }
            })


    }

    override fun setUp(savedInstanceState: Bundle?) {

    }


}









