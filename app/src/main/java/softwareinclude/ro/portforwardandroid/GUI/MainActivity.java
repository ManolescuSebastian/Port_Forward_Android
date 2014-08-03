package softwareinclude.ro.portforwardandroid.GUI;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import softwareinclude.ro.portforwardandroid.R;
import softwareinclude.ro.portforwardandroid.util.GlobalData;
import softwareinclude.ro.portforwardandroid.util.NetworkUtil;

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

    private TextView networkStateInfo;
    private TextView externalIPInfo;
    private TextView internetGatewayDeviceInfo;


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
        networkStateInfo.setText("Network State: "+ status);

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

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.addPort:{
                Toast.makeText(getApplicationContext(),"Add Port Text",Toast.LENGTH_SHORT).show();
                networkStateInfo.setText("Network State: "+GlobalData.Data.getNetworkStatus());
                break;
            }

            default:{
                break;
            }
        }

    }
}
