package softwareinclude.ro.portforwardandroid.GUI;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import softwareinclude.ro.portforwardandroid.R;
import softwareinclude.ro.portforwardandroid.asyncTasks.AddPortAsync;
import softwareinclude.ro.portforwardandroid.util.GlobalData;

public class AddPortActivity extends Activity implements View.OnClickListener{

    private EditText inputExternalIP;
    private EditText inputExternalPort;
    private EditText inputInternalIP;
    private EditText inputInternalPort;

    private Button backTitlebar;
    private Button doneAddPort;
    private Button searchDeviceInternalIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_port);

        init();
    }


    /**
     * Initialize all components (Buttons,EdiText) and data
     */
    private void init() {

        backTitlebar = (Button) findViewById(R.id.addPortBackBtn);
        backTitlebar.setOnClickListener(this);
        doneAddPort = (Button) findViewById(R.id.doneAddPort);
        doneAddPort.setOnClickListener(this);
        searchDeviceInternalIP = (Button)findViewById(R.id.searchDeviceInternalIP);
        searchDeviceInternalIP.setOnClickListener(this);

        inputExternalIP = (EditText) findViewById(R.id.inputExternalIP);
        inputExternalPort = (EditText) findViewById(R.id.inputExternalPort);
        inputInternalIP = (EditText) findViewById(R.id.inputInternalIP);
        inputInternalPort = (EditText) findViewById(R.id.inputInternalPort);

        if(GlobalData.Data.getExternalIP() != null){
            inputExternalIP.setText(GlobalData.Data.getExternalIP());
        }

    }

    /**
     * Finish activity with animation
     */
    public void finishActivity() {
        this.finish();
        this.overridePendingTransition(R.anim.anim_slide_in_right,
                                       R.anim.anim_slide_out_right);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.addPortBackBtn: {

                    finishActivity();
                break;
            }

            case R.id.doneAddPort: {
                    //Open Port Using AsyncTask and UPnPPortMapper class
                    new AddPortAsync(this,inputExternalIP.getText().toString(),inputInternalIP.getText().toString(),
                                     Integer.parseInt(inputExternalPort.getText().toString()),Integer.parseInt(inputInternalPort.getText().toString())).execute();

                    //finishActivity();
                break;
            }

            case R.id.searchDeviceInternalIP: {
                //search device IP and using the methods below
                String foundDeviceInternalIP = getDottedDecimalIP(getLocalIPAddress());
                inputInternalIP.setText(foundDeviceInternalIP);

                break;
            }

            default: {
                break;
            }
        }

    }

    /**
     *
     * @return
     */
    private byte[] getLocalIPAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        if (inetAddress instanceof Inet4Address) { // fix for Galaxy Nexus. IPv4 is easy to use :-)
                            return inetAddress.getAddress();
                        }
                        //return inetAddress.getHostAddress().toString(); // Galaxy Nexus returns IPv6
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("AndroidNetworkAddressFactory", "getLocalIPAddress()", ex);
        } catch (NullPointerException ex) {
            Log.e("AndroidNetworkAddressFactory", "getLocalIPAddress()", ex);
        }
        return null;
    }

    /**
     *
     * @param ipAddr
     * @return
     */
    private String getDottedDecimalIP(byte[] ipAddr) {
        //convert to dotted decimal notation:
        String ipAddrStr = "";
        for (int i=0; i<ipAddr.length; i++) {
            if (i > 0) {
                ipAddrStr += ".";
            }
            ipAddrStr += ipAddr[i]&0xFF;
        }
        return ipAddrStr;
    }

}
