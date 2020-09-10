package com.app.mydrai.ui.loginmodule.optloginmodel

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.app.mydrai.R
import com.app.mydrai.core.presentation.base.BaseActivity
import com.app.mydrai.databinding.DummyOtpLayoutBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import es.dmoral.toasty.Toasty
import logi.retail.utils.DialogUtils
import logi.retail.utils.SessionManger
import logi.retail.utils.SessionManger.Companion.PREF_FILE_NAME
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class OTPLoginActivity : BaseActivity<DummyOtpLayoutBinding, OTPLoginViewModel>(),
    OTPLoginNavigatior {
    private var SWITCH: Boolean = false
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var storedVerificationId: String
    private var verificationInProgress: Boolean = false
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var MobileNO: String
    private lateinit var sessionManger: SessionManger
    private lateinit var auth: FirebaseAuth
    val otpLoginViewModel: OTPLoginViewModel by viewModel()
    lateinit var dummyOtpLayoutBinding: DummyOtpLayoutBinding

    override fun getLayoutId(): Int {
        return R.layout.dummy_otp_layout
    }

    override fun getViewModel(): OTPLoginViewModel {
        return otpLoginViewModel
    }

    override fun setUp(savedInstanceState: Bundle?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dummyOtpLayoutBinding = getViewDataBinding()
        dummyOtpLayoutBinding.loginCallabck = this
        auth = FirebaseAuth.getInstance()
        sessionManger = SessionManger(this@OTPLoginActivity, PREF_FILE_NAME)
        initView()
    }

    private fun initView() {
        DialogUtils.startProgressDialog(this)

        if (intent != null) {
            MobileNO = intent?.extras?.getString("MOBILE").toString()
            SWITCH = intent?.extras?.getBoolean("SWITCH", false)!!
        }

        Handler().postAtTime({
            val currentUser = auth.currentUser
            updateUI(currentUser)
            if (MobileNO.length == 10) {
                startPhoneNumberVerification(MobileNO)
            }
        }, 5000)

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d(TAG, "onVerificationCompleted:$credential")
                verificationInProgress = false
                updateUI(STATE_VERIFY_SUCCESS, credential)
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.w(TAG, "onVerificationFailed", e)
                verificationInProgress = false
                if (e is FirebaseAuthInvalidCredentialsException) {
                } else if (e is FirebaseTooManyRequestsException) {
                    Snackbar.make(
                        findViewById(android.R.id.content), "Quota exceeded.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                DialogUtils.stopProgressDialog()
                updateUI(STATE_VERIFY_FAILED)
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                Log.d(TAG, "onCodeSent:$verificationId")
                storedVerificationId = verificationId
                resendToken = token
                updateUI(STATE_CODE_SENT)
            }
        }
    }

    private fun startPhoneNumberVerification(phoneNumber: String) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            "+91$phoneNumber",
            60,
            TimeUnit.SECONDS,
            this,
            callbacks
        )
        verificationInProgress = true
    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun resendVerificationCode(
        phoneNumber: String,
        token: PhoneAuthProvider.ForceResendingToken?
    ) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            "+91$phoneNumber",
            60,
            TimeUnit.SECONDS,
            this,
            callbacks,
            token
        )
    }

    override fun clickOnLogin() {
        var stringOtp = dummyOtpLayoutBinding.edtPassword.text.toString().trim()
        if (!stringOtp.isNullOrEmpty() && storedVerificationId != null) {
            verifyPhoneNumberWithCode(storedVerificationId, stringOtp)
        }

    }

    override fun clickOnResend() {
        if (resendToken != null && !MobileNO.isNullOrEmpty()) {
            resendVerificationCode(MobileNO, resendToken)
        }
    }

    override fun clickOnBack() {
        onBackPressed()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_VERIFY_IN_PROGRESS, verificationInProgress)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        verificationInProgress = savedInstanceState.getBoolean(KEY_VERIFY_IN_PROGRESS)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.w(TAG, "signInWithCredential:success")
                    val user = task.result?.user
                    updateUI(STATE_SIGNIN_SUCCESS, user)
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    }
                    updateUI(STATE_SIGNIN_FAILED)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            updateUI(STATE_SIGNIN_SUCCESS, user)
        } else {
            updateUI(STATE_INITIALIZED)
        }
    }

    private fun updateUI(uiState: Int, cred: PhoneAuthCredential) {
        updateUI(uiState, null, cred)
    }

    private fun updateUI(
        uiState: Int,
        user: FirebaseUser? = auth.currentUser,
        cred: PhoneAuthCredential? = null
    ) {
        when (uiState) {
            STATE_INITIALIZED -> {
                Log.w(TAG, "STATE_INITIALIZED")
            }
            STATE_CODE_SENT -> {
                DialogUtils.stopProgressDialog()
                Toast.makeText(this, "OTP Send", Toast.LENGTH_SHORT).show()
                Log.w(TAG, "STATE_CODE_SENT")
            }
            STATE_VERIFY_FAILED -> {
                Log.w(TAG, "STATE_VERIFY_FAILED")
                DialogUtils.stopProgressDialog()
            }
            STATE_VERIFY_SUCCESS -> {
                if (cred != null) {
                    if (cred.smsCode != null) {
                        Log.w(TAG, "STATE_VERIFY_SUCCESS " + cred.smsCode)
                    } else {
                        Log.w(TAG, "STATE_VERIFY_SUCCESS")
                    }
                }
            }
            STATE_SIGNIN_FAILED -> {
                Log.w(TAG, "STATE_SIGNIN_FAILED")
                Toasty.error(this, "Please Enter Valid OTP").show()
            }
            STATE_SIGNIN_SUCCESS -> {
                signOut()
                Log.w(TAG, "STATE_SIGNIN_SUCCESS")
                Toasty.success(this, "Otp Verified Sucessfully").show()
                callApi()
            }
        }

        if (user == null) {
            // Signed out

        } else {
            // Signed in
        }
    }

    private fun callApi() {
        DialogUtils.startProgressDialog(this@OTPLoginActivity);
        otpLoginViewModel.loginUser(
                MobileNO,
                true,
                ""
            )
            .observe(this, Observer {
                if (it != null) {
                 /*   if (it.flag.equals("1")) {
                        DialogUtils.stopProgressDialog()
                        Toasty.success(
                                this@OTPLoginActivity,
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
                                this@OTPLoginActivity,
                                "" + it.data?.error_message,
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }*/
                }
            })
    }

  /*  private fun callDocumentNumberingApi(locationId: Int?, userId: Int?) {
        if (locationId != null) {
            otpLoginViewModel.getDocumentNumbering(locationId, userId).observe(this, Observer {
                if (it != null) {
                    var documentNumberingDetailModel = it.data
                    sessionManger?.setDocCurrentNo(documentNumberingDetailModel?.currentNo.toString())
                    sessionManger?.setDocNoOfSeesion(documentNumberingDetailModel?.noOfSessions!!)
                    sessionManger?.setDocNoPrefix(documentNumberingDetailModel?.docNumberingPrefix.toString())


                    Toasty.success(this@OTPLoginActivity, "Login Successfully", Toast.LENGTH_SHORT)
                        .show()


                    if (SWITCH) {
                        sessionManger!!.setCurrentUserLoggedInMode(true)
                    } else {
                        sessionManger!!.setCurrentUserLoggedInMode(false)
                    }

                    var count = DatabaseHelper.getDatabase(this@OTPLoginActivity).interfaceDao()
                        .getAllItemHeaderListCount()

                    DialogUtils.stopProgressDialog()

                    if (count!! > 0) {
                        if (sessionManger!!.isTablet()) {
                            var intent =
                                Intent(this@OTPLoginActivity, TabletViewActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(intent)
                        } else {
                            var intent = Intent(this@OTPLoginActivity, MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(intent)
                        }
                    } else {
                        var intent =
                            Intent(this@OTPLoginActivity, DownloadingDataActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                    }
                }
            })
        }
    }*/

    private fun signOut() {
        auth.signOut()
        updateUI(STATE_INITIALIZED)
    }

    companion object {
        private const val TAG = "PhoneAuthActivity"
        private const val KEY_VERIFY_IN_PROGRESS = "key_verify_in_progress"
        private const val STATE_INITIALIZED = 1
        private const val STATE_VERIFY_FAILED = 3
        private const val STATE_VERIFY_SUCCESS = 4
        private const val STATE_CODE_SENT = 2
        private const val STATE_SIGNIN_FAILED = 5
        private const val STATE_SIGNIN_SUCCESS = 6
    }
}