package softwareinclude.ro.portforwardandroid.broadcastStatus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import softwareinclude.ro.portforwardandroid.util.GlobalData;
import softwareinclude.ro.portforwardandroid.network.NetworkUtil;

/**
 * Created by manolescusebastian on 8/3/14.
 */
public class NetworkStatus extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, final Intent intent) {

            String status = NetworkUtil.getConnectivityStatusString(context);

            GlobalData.Data.setNetworkStatus(status);

            Toast.makeText(context, status, Toast.LENGTH_LONG).show();
        }
    }