package softwareinclude.ro.portforwardandroid.asyncTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import net.sbbi.upnp.messages.UPNPResponseException;

import java.io.IOException;

import softwareinclude.ro.portforwardandroid.dialog.CustomDialogAddPort;
import softwareinclude.ro.portforwardandroid.network.UPnPPortMapper;
import softwareinclude.ro.portforwardandroid.util.ApplicationConstants;

/**
 * Created by manolescusebastian on 8/9/14.
 */
public class AddPortAsync extends AsyncTask<Void, Void, Void> {

    private final ProgressDialog progressDialog;
    private Context context;
    private UPnPPortMapper uPnPPortMapper;

    private String externalIP;
    private String internalIP;
    private int externalPort;
    private int internalPort;

    public AddPortAsync(Context context, String externalIP, String internalIP,
                        int externalPort, int internalPort) {

        progressDialog = CustomDialogAddPort.ctor(context);
        this.context = context;
        this.externalIP = externalIP;
        this.internalIP = internalIP;
        this.externalPort = externalPort;
        this.internalPort = internalPort;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        uPnPPortMapper = new UPnPPortMapper();
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... params) {

            if(uPnPPortMapper != null){
                try {
                    uPnPPortMapper.openRouterPort(externalIP, externalPort,internalIP,internalPort, ApplicationConstants.ADD_PORT_DESCRIPTION);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UPNPResponseException e) {
                    e.printStackTrace();
                }
            }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        //Send broadcast for update in the main activity
        //Intent i = new Intent(ApplicationConstants.APPLICATION_ENCODING_TEXT);
        //context.sendBroadcast(i);

        progressDialog.dismiss();
    }
}
