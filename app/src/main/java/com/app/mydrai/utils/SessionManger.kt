package logi.retail.utils

import android.content.Context
import android.content.SharedPreferences


public class SessionManger(context: Context, prefFileName: String) {

    private val mPrefs: SharedPreferences
    private var editor: SharedPreferences.Editor? = null

    init {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)
        editor = mPrefs.edit()
    }


    fun getCurrentUserLoggedInMode(): Boolean {
        return mPrefs.getBoolean(PREF_KEY_USER_LOGGED_IN_MODE, false)
    }

    fun setCurrentUserLoggedInMode(mode: Boolean) {
        mPrefs.edit().putBoolean(PREF_KEY_USER_LOGGED_IN_MODE, mode).apply()
    }

    fun setSaleInvoiceCount(saleInvoiceCountt: String) {
        mPrefs.edit().putString(SALES_INVOICE_COUNT, saleInvoiceCountt).apply()

    }

    fun getSaleInvoiceCount(): String {
        return mPrefs.getString(SALES_INVOICE_COUNT, "")!!
    }

    fun setUserID(user_id: Int) {
        mPrefs.edit().putInt(USER_ID, user_id).apply()

    }

    fun setBluetooth_Print_Name(bluePrintName: String?) {
        mPrefs.edit().putString(BLUE_TOOTH_PRINT_NAME, bluePrintName).apply()

    }

    fun getBluetooth_Print_Name(): String? {
        return mPrefs.getString(BLUE_TOOTH_PRINT_NAME, "")
    }


    fun getBluetooth_Print_Type(): String? {
        return mPrefs.getString(BLUE_TOOTH_PRINT_TYPE, "")
    }


    fun setBluetooth_Print_Type(bluePrintType: String?) {
        mPrefs.edit().putString(BLUE_TOOTH_PRINT_TYPE, bluePrintType).apply()
    }

    fun setBluetooth_Weight_Name(blueWeightName: String?) {
        mPrefs.edit().putString(BLUE_TOOTH_WEIGHT_NAME, blueWeightName).apply()
    }

    fun getBluetooth_Weight_Name(): String? {
        return mPrefs.getString(BLUE_TOOTH_WEIGHT_NAME, "")
    }


    fun getBluetooth_Weight_Type(): String? {
        return mPrefs.getString(BLUE_TOOTH_WEIGHT_TYPE, "")
    }


    fun setBluetooth_Weight_Type(blueWeightType: String?) {
        mPrefs.edit().putString(BLUE_TOOTH_WEIGHT_TYPE, blueWeightType).apply()

    }


    fun getUserID(): Int {
        return mPrefs.getInt(USER_ID, 0)
    }


    fun setUserName(user_name: String) {
        mPrefs.edit().putString(USER_NAME, user_name).apply()

    }

    fun getUserName(): String {
        return mPrefs.getString(USER_NAME, "")!!
    }


    fun setSessionId(session_id: String) {
        mPrefs.edit().putString(SESSION_ID, session_id).apply()

    }

    fun getSessionId(): String {
        return mPrefs.getString(SESSION_ID, "")!!
    }


    fun setCounterId(counter_id: String) {
        mPrefs.edit().putString(COUNTER_ID, counter_id).apply()

    }

    fun getCounterId(): String {
        return mPrefs.getString(COUNTER_ID, "")!!
    }


    fun setSaleInvoiceNumber(invoice_number: String) {
        mPrefs.edit().putString(SALE_INVOICE_NUMBER, invoice_number).apply()
    }

    fun getSaleInvoiceNumber(): String {
        return mPrefs.getString(SALE_INVOICE_NUMBER, "")!!
    }


    fun setCompanyId(comapny_id: Int) {
        mPrefs.edit().putInt(SELECTED_COMPANY_ID, comapny_id).apply()
    }

    fun getCompanyId(): Int {
        return mPrefs.getInt(SELECTED_COMPANY_ID, 0)
    }

    fun setCustomerLoyaltyPoint(customer_loyalty_point: String) {
        mPrefs.edit().putString(CUSTOMER_LOYALTY_POINT, customer_loyalty_point).apply()
    }

    fun getCustomerLoyaltyPoint(): String {
        return mPrefs.getString(CUSTOMER_LOYALTY_POINT, "")!!
    }


    fun setCustomerRedeemPoint(customer_redeem_point: String) {
        mPrefs.edit().putString(CUSTOMER_REDEEM_POINT, customer_redeem_point).apply()
    }

    fun getCustomerRedeemPoint(): String {
        return mPrefs.getString(CUSTOMER_REDEEM_POINT, "")!!
    }


    fun setTotalValue(total_amount: String) {
        mPrefs.edit().putString(TOTAL_CART_VALUE, total_amount).apply()
    }

    fun getTotalValue(): String {
        return mPrefs.getString(TOTAL_CART_VALUE, "")!!
    }


    fun setLoyaltyPointConversion(loyalty_point_conversion: String) {
        mPrefs.edit().putString(CUSTOMER_LOYALTY_POINT_CONVERSION, loyalty_point_conversion).apply()
    }

    fun getLoyaltyPointConversion(): String {
        return mPrefs.getString(CUSTOMER_LOYALTY_POINT_CONVERSION, "")!!
    }


    fun clearSessionManger() {
        editor!!.clear()
        editor!!.commit()

    }


    fun getPrintData(): String? {
        return mPrefs.getString(PRINT_DATA, "")
    }

    fun setPrintData(print_data: String) {
        mPrefs.edit().putString(PRINT_DATA, print_data).apply()
    }


    fun getCompanyDisplayName(): String? {
        return mPrefs.getString(COMAPNY_DISPLAY_NAME, "")
    }

    fun setCompanyDisplayName(comapy_display_name: String) {
        mPrefs.edit().putString(COMAPNY_DISPLAY_NAME, comapy_display_name).apply()
    }


    fun getStateName(): String? {
        return mPrefs.getString(STATE_NAME, "")
    }

    fun setStateName(state_name: String) {
        mPrefs.edit().putString(STATE_NAME, state_name).apply()

    }


    fun setIsWeight(isWeight1: String?) {
        mPrefs.edit().putString(IS_WEIGHT, isWeight1).apply()
    }

    fun getIsWeight(): String? {
        return mPrefs.getString(IS_WEIGHT, "")
    }

    fun getStateUTCode(): String? {
        return mPrefs.getString(STATE_UT_CODE, "")
    }

    fun setStateUTCode(state_ut_code: String) {
        mPrefs.edit().putString(STATE_UT_CODE, state_ut_code).apply()

    }


    fun getRegisterTelephony(): String? {
        return mPrefs.getString(REGISTER_TELEPHONE, "")
    }

    fun setRegisterTelephony(register_telephony: String) {
        mPrefs.edit().putString(REGISTER_TELEPHONE, register_telephony).apply()

    }


    fun getPlantGstin(): String? {
        return mPrefs.getString(PLANT_GSTIN, "")
    }

    fun setPlantGstin(plant_gstin: String) {
        mPrefs.edit().putString(PLANT_GSTIN, plant_gstin).apply()

    }

    fun getPlantAddress(): String? {
        return mPrefs.getString(PLANT_ADDRESS, "")
    }

    fun setPlantAddress(plant_address: String) {
        mPrefs.edit().putString(PLANT_ADDRESS, plant_address).apply()

    }


    fun isFlash(): Boolean {
        return mPrefs.getBoolean(
            PREF_FLASH, false
        )
    }

    fun setFlash(flag: Boolean) {
        mPrefs.edit().putBoolean(PREF_FLASH, flag)
            .apply()
    }

    fun isFront(): Boolean {
        return mPrefs.getBoolean(
            PREF_FRONT,
            false
        )
    }


    fun getCurrentClientCode(): String? {
        return mPrefs.getString(SELECTED_CLIENT_CODE, "")
    }

    fun setCurrentClientCode(client_code: String) {
        mPrefs.edit().putString(SELECTED_CLIENT_CODE, client_code).apply()

    }

    fun getLocationId(): Int? {
        return mPrefs.getInt(LOCATION_ID, 0)
    }

    fun setLocationId(location_id: Int) {
        mPrefs.edit().putInt(LOCATION_ID, location_id).apply()

    }


    fun getDocNoOfSeesion(): Int? {
        return mPrefs.getInt(DOC_NO_SESSION, 0)
    }

    fun setDocNoOfSeesion(doc_no_session: Int) {
        mPrefs.edit().putInt(DOC_NO_SESSION, doc_no_session).apply()

    }


    fun getDocCurrentNo(): String? {
        return mPrefs.getString(DOC_NO_CURRENT_NO, "")
    }

    fun setDocCurrentNo(doc_current_no: String) {
        mPrefs.edit().putString(DOC_NO_CURRENT_NO, doc_current_no).apply()

    }


    fun getDocNoPrefix(): String? {
        return mPrefs.getString(DOC_NO_PREFIX, "")
    }

    fun setDocNoPrefix(doc_no_session: String) {
        mPrefs.edit().putString(DOC_NO_PREFIX, doc_no_session).apply()

    }


    fun getCurrentIndustry_code(): String? {
        return mPrefs.getString(SELECTED_INDUSTRY_CODE, "")
    }

    fun setCurrentIndustry_code(industry_code: String) {
        mPrefs.edit().putString(SELECTED_INDUSTRY_CODE, industry_code).apply()

    }

    fun setIpAddress(string: String) {
        mPrefs.edit().putString(PREF_IP, string).apply()
    }

    fun getIpAddress(): String? {
        return mPrefs.getString(PREF_IP, "")
    }

    fun setTablet(flag: Boolean) {
        mPrefs.edit().putBoolean(TABLET, flag).apply()
    }

    fun isTablet(): Boolean {
        return mPrefs.getBoolean(TABLET, false)
    }


    fun getPrint_data(): String? {
        return mPrefs.getString(PRINT_DATA, "")
    }

    fun setPrint_data(saleInvoiceCountt: String?) {
        mPrefs.edit().putString(PRINT_DATA, saleInvoiceCountt).apply()
    }

    fun setFront(flag: Boolean) {
        mPrefs.edit().putBoolean(PREF_FRONT, flag)
            .apply()
    }


    fun setUIOpening(uiOpening: String?) {
        mPrefs.edit().putString(UI_OPENING, uiOpening).apply()
    }

    fun getUIOpening(): String? {
        return mPrefs.getString(UI_OPENING, "")
    }


    fun clearAll() {
        mPrefs.edit().clear().commit()
    }


    companion object {
        const val PREF_FILE_NAME = "SmartMart.pref"
        private val PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE"
        private val SALES_INVOICE_COUNT = "sales_invoice_count"
        private val SESSION_ID = "session_id"
        private val COUNTER_ID = "counter_id"
        private val USER_ID = "user_id"
        private val USER_NAME = "user_name"
        private val COUNTER_NAME = "counter_name"
        private val SALE_INVOICE_NUMBER = "sale_invoice_number"
        private val CUSTOMER_LOYALTY_POINT = "customer_loyalty_points"
        private val CUSTOMER_REDEEM_POINT = "redeem_points"
        private val CUSTOMER_LOYALTY_POINT_CONVERSION = "loyalty_point_conversion"
        private val TOTAL_CART_VALUE = "total_cart_value"
        private val PRINT_DATA = "print_data"
        private val COMAPNY_DISPLAY_NAME = "company_display_name"
        private val STATE_NAME = "state_name"
        private val STATE_UT_CODE = "state_ut_code"
        private val REGISTER_TELEPHONE = "register_telephone"
        private val PLANT_GSTIN = "plant_gstin"
        private val PLANT_ADDRESS = "plant_address"
        private const val PREF_FLASH = "flash"
        private const val PREF_FRONT = "front"
        private const val BLUE_TOOTH_WEIGHT_NAME = "Bluetooth_Weight_Name"
        private const val BLUE_TOOTH_WEIGHT_TYPE = "Bluetooth_Weight_Type"
        private const val BLUE_TOOTH_PRINT_NAME = "Bluetooth_Print_Name"
        private const val BLUE_TOOTH_PRINT_TYPE = "Bluetooth_Print_Type"
        private const val IS_WEIGHT = "is_weight"
        private const val SELECTED_INDUSTRY_CODE = "industry_code"
        private const val CURRENT_SELECTED_VIEW = "current_selected_view"
        private const val PREF_IP = "ip_address"
        private const val SELECTED_CLIENT_CODE = "client_code"
        private const val SELECTED_COMPANY_ID = "comapny_id"
        private const val LOCATION_ID = "location_id"
        private const val DOC_NO_SESSION = "no_of_session"
        private const val DOC_NO_PREFIX = "doc_numbering_prefix"
        private const val DOC_NO_CURRENT_NO = "current_no"
        private const val TABLET = "is_tablet"
        private const val UI_OPENING = "ui_opening"


    }
}


