package softwareinclude.ro.portforwardandroid.asyncTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import net.sbbi.upnp.messages.UPNPResponseException;

import java.io.IOException;

import softwareinclude.ro.portforwardandroid.dialog.CustomDialogAddPort;
import softwareinclude.ro.portforwardandroid.network.NetworkUtil;
import softwareinclude.ro.portforwardandroid.network.UPnPPortMapper;
import softwareinclude.ro.portforwardandroid.util.ApplicationConstants;
import softwareinclude.ro.portforwardandroid.util.GlobalData;

/**
 * Created by manolescusebastian on 8/4/14.
 */
public class ReceiveInfoAsync extends AsyncTask<Void, Void, Void> {

    private final ProgressDialog progressDialog;
    private Context context;
    private UPnPPortMapper uPnPPortMapper;

    public ReceiveInfoAsync(Context context) {
        progressDialog = CustomDialogAddPort.ctor(context);
        this.context = context;
    }

        @Override
        protected void onPreExecute() {
        super.onPreExecute();
            uPnPPortMapper = new UPnPPortMapper();
            progressDialog.show();
    }

        @Override
        protected Void doInBackground(Void... params) {
            try {
               String status = NetworkUtil.getConnectivityStatusString(context);
               String externalIP = uPnPPortMapper.findExternalIPAddress();
               String friendlyName = uPnPPortMapper.findRouterName();
                if(externalIP != null && !externalIP.isEmpty()) {
                    GlobalData.Data.setExternalIP(externalIP);
                }
                if(friendlyName != null && !friendlyName.isEmpty()){
                    GlobalData.Data.setFriendlyName(friendlyName);
                }
                if(status != null && !status.isEmpty()){
                    GlobalData.Data.setNetworkStatus(status);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UPNPResponseException e) {
                e.printStackTrace();
            }
        return null;
    }

        @Override
        protected void onPostExecute(Void result) {
        super.onPostExecute(result);

            //Send broadcast for update in the main activity
            Intent i = new Intent(ApplicationConstants.APPLICATION_ENCODING_TEXT);
            context.sendBroadcast(i);

        progressDialog.dismiss();
    }
 }