package softwareinclude.ro.portforwardandroid.GUI;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import softwareinclude.ro.portforwardandroid.R;
import softwareinclude.ro.portforwardandroid.asyncTasks.ReceiveInfoAsync;
import softwareinclude.ro.portforwardandroid.network.NetworkUtil;
import softwareinclude.ro.portforwardandroid.util.ApplicationConstants;
import softwareinclude.ro.portforwardandroid.util.GlobalData;

/**
 *
 * @author Manolescu Sebastian
 * @version 1.0.0
 *
 * Port Forward Application
 * Find more on software-include.ro
 *
 */

public class MainActivity extends Activity implements View.OnClickListener{

    private Button addPort;
    private ImageButton searchIGDInfo;

    private TextView networkStateInfo;
    private TextView externalIPInfo;
    private TextView internetGatewayDeviceInfo;

    private BroadcastReceiver broadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        initData();
    }

    /**
     * Init Data from this activity
     */
    public void initData(){

        String status = NetworkUtil.getConnectivityStatusString(this);
        if(status != null) {
            networkStateInfo.setText("Network State: " + status);
        }


        /**
         * Update info when the broadcast receive data
         */
        IntentFilter intentFilter = new IntentFilter(
                ApplicationConstants.APPLICATION_ENCODING_TEXT);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                networkStateInfo.setText("Network State: "+GlobalData.Data.getNetworkStatus());
                externalIPInfo.setText(GlobalData.Data.getExternalIP());
                internetGatewayDeviceInfo.setText(GlobalData.Data.getFriendlyName());

            }
        };

        this.registerReceiver(broadcastReceiver, intentFilter);

    }

    /**
     * Init UI components for this activity
     */
    public void initUI(){

        networkStateInfo = (TextView)findViewById(R.id.network_state_info);
        externalIPInfo = (TextView)findViewById(R.id.external_ip_info);
        internetGatewayDeviceInfo = (TextView)findViewById(R.id.igd_name_info);

        addPort = (Button)findViewById(R.id.addPort);
        addPort.setOnClickListener(this);
        searchIGDInfo = (ImageButton)findViewById(R.id.searchIGDInfo);
        searchIGDInfo.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.addPort:{

                //Start new activity, user will add input text for each field
                Intent startActivity = new Intent(MainActivity.this, AddPortActivity.class);
                startActivity(startActivity);
                this.overridePendingTransition(R.anim.anim_slide_in_left,
                        R.anim.anim_slide_out_left);

                break;
            }

            case R.id.searchIGDInfo:{
                new ReceiveInfoAsync(this).execute();
                break;
            }

            default:{
                break;
            }
        }
    }


    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        //this.unregisterReceiver(this.broadcastReceiver);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //this.unregisterReceiver(this.broadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //this.unregisterReceiver(this.broadcastReceiver);
    }
}
