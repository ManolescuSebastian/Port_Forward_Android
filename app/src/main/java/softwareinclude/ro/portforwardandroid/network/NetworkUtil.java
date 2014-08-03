package softwareinclude.ro.portforwardandroid.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import softwareinclude.ro.portforwardandroid.util.ApplicationConstants;

/**
 * Created by Manolescu Sebastian on 8/3/14.
 *
 * Used tutorial from http://viralpatel.net/blogs/android-internet-connection-status-network-change/
 */
public class NetworkUtil {

    /**
     * Get Iternet Connection Status
     * @param context
     * @return
     */
    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return ApplicationConstants.TYPE_WIFI;

            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return ApplicationConstants.TYPE_MOBILE;
        }
        return ApplicationConstants.TYPE_NOT_CONNECTED;
    }

    /**
     * Return message according to the status received from the above method
     * @param context
     * @return
     */
    public static String getConnectivityStatusString(Context context) {
        int conn = NetworkUtil.getConnectivityStatus(context);
        String status = null;
        if (conn == ApplicationConstants.TYPE_WIFI) {
            status = "Wifi enabled";
        } else if (conn == ApplicationConstants.TYPE_MOBILE) {
            status = "Mobile data enabled";
        } else if (conn == ApplicationConstants.TYPE_NOT_CONNECTED) {
            status = "Not connected to Internet";
        }
        return status;
    }
}
