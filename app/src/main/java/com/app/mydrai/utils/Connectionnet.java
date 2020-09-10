package com.app.mydrai.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.widget.Toast;

import com.app.mydrai.R;


public class Connectionnet extends Activity {
    private static Connectionnet connectionnet = null;

    public static Connectionnet getConnectionnetInstance() {
        if (connectionnet == null) {
            connectionnet = new Connectionnet();
        }
        return connectionnet;
    }

    public boolean isConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        if (manager == null) {
            return false;
        }
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    public void showNetworkToastMessage(Context context) {
        Toast toast = Toast.makeText(context, R.string.no_internet, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

    public void showLoingmsg(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }
}
