package com.app.mydrai.ui.loginmodule.passwordloginmodel

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.app.mydrai.R
import com.app.mydrai.core.presentation.base.BaseActivity
import com.app.mydrai.databinding.DummyPasswordLayoutBinding
import es.dmoral.toasty.Toasty
import logi.retail.utils.DialogUtils
import logi.retail.utils.SessionManger
import logi.retail.utils.SessionManger.Companion.PREF_FILE_NAME
import org.koin.androidx.viewmodel.ext.android.viewModel

class PasswordLoginActivity : BaseActivity<DummyPasswordLayoutBinding, PasswordViewModel>(),
    PasswordNavigator {

    private var SWITCH: Boolean = false
    private lateinit var MobileNO: String
    private lateinit var sessionManger: SessionManger
    lateinit var dummyPasswordLayoutBinding: DummyPasswordLayoutBinding
    val passwordViewModel: PasswordViewModel by viewModel()
    override fun getLayoutId(): Int {
        return R.layout.dummy_password_layout
    }

    override fun getViewModel(): PasswordViewModel {
        return passwordViewModel
    }

    override fun setUp(savedInstanceState: Bundle?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dummyPasswordLayoutBinding = getViewDataBinding()
        dummyPasswordLayoutBinding.passwordCallabck = this
        sessionManger = SessionManger(this@PasswordLoginActivity, PREF_FILE_NAME)
        initView()
    }

    private fun initView() {
        if (intent != null) {
            MobileNO = intent?.extras?.getString("MOBILE").toString()
            SWITCH = intent?.extras?.getBoolean("SWITCH", false)!!
        }

        /* dummyPasswordLayoutBinding.imgBack.setOnClickListener(object : View.OnClickListener{
             override fun onClick(v: View?) {
                 Toasty.success(this@PasswordLoginActivity,"click on backpress",Toasty.LENGTH_LONG).show()
             }
         })*/
    }

    override fun clickOnLogin() {
        var stringpass = dummyPasswordLayoutBinding!!.edtPassword.text.toString().trim()
        if (stringpass.isNullOrEmpty() && MobileNO.isNullOrEmpty()) {

        } else {
            callApi(stringpass)
        }

    }

    override fun clickOnBack() {
        onBackPressed()
    }


    private fun callApi(stringpass: String) {
        DialogUtils.startProgressDialog(this@PasswordLoginActivity);
        passwordViewModel.loginUser(
            MobileNO,
            false,
            stringpass
        ).observe(this, androidx.lifecycle.Observer {
            if (it != null) {
              /*  if (it.flag.equals("1")) {
                    DialogUtils.stopProgressDialog()
                    Toasty.success(
                            this@PasswordLoginActivity,
                            "Login Successfully",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                    if (MobileNO.equals("8483093248")) {
                        sessionManger!!.setTablet(true)
                    } else {
                        sessionManger!!.setTablet(false)
                    }

                    var registerModel: LoginDetailsModel? = it.data
                    sessionManger?.setCurrentIndustry_code("")
                    sessionManger?.setCurrentClientCode(registerModel?.clientCode.toString())
                    sessionManger?.setLocationId(registerModel?.location_id!!)
                    registerModel?.userId?.toInt()?.let { sessionManger?.setUserID(it) }
                    registerModel?.companyId?.toInt()?.let { sessionManger?.setCompanyId(it) }

                    callDocumentNumberingApi(registerModel?.location_id, registerModel?.userId)

                } else {
                    DialogUtils.stopProgressDialog()
                    Toasty.error(
                        this@PasswordLoginActivity,
                        "" + it.data?.error_message,
                        Toast.LENGTH_SHORT
                    ).show()
                }*/
            }
        })
    }

    private fun callDocumentNumberingApi(locationId: Int?, userId: Int?) {
        /*if (locationId != null) {
            passwordViewModel.getDocumentNumbering(locationId, userId)
                .observe(this, androidx.lifecycle.Observer {
                    if (it != null) {
                        var documentNumberingDetailModel = it.data
                        sessionManger?.setDocCurrentNo(documentNumberingDetailModel?.currentNo.toString())
                        sessionManger?.setDocNoOfSeesion(documentNumberingDetailModel?.noOfSessions!!)
                        sessionManger?.setDocNoPrefix(documentNumberingDetailModel?.docNumberingPrefix.toString())


                        Toasty.success(
                                this@PasswordLoginActivity,
                                "Login Successfully",
                                Toast.LENGTH_SHORT
                            )
                            .show()


                        if (SWITCH) {
                            sessionManger!!.setCurrentUserLoggedInMode(true)
                        } else {
                            sessionManger!!.setCurrentUserLoggedInMode(false)
                        }

                        var count =
                            DatabaseHelper.getDatabase(this@PasswordLoginActivity).interfaceDao()
                                .getAllItemHeaderListCount()

                        DialogUtils.stopProgressDialog()
                        if (count!! > 0) {
                            if (sessionManger!!.isTablet()) {
                                var intent =
                                    Intent(
                                        this@PasswordLoginActivity,
                                        TabletViewActivity::class.java
                                    )
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                startActivity(intent)
                            } else {
                                var intent =
                                    Intent(this@PasswordLoginActivity, MainActivity::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                startActivity(intent)


                            }
                        } else{
                            var intent = Intent(
                                this@PasswordLoginActivity,
                                DownloadingDataActivity::class.java
                            )
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(intent)
                        }
                    }
                })
        }*/
    }
}