package softwareinclude.ro.portforwardandroid.GUI;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import softwareinclude.ro.portforwardandroid.R;
import softwareinclude.ro.portforwardandroid.util.GlobalData;

public class AddPortActivity extends Activity implements View.OnClickListener{

    private EditText inputExternalIP;
    private EditText inputExternalPort;
    private EditText inputInternalIP;
    private EditText inputInternalPort;

    private Button backTitlebar;
    private Button doneAddPort;


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

                    finishActivity();

                break;
            }

            default: {
                break;
            }
        }

    }
}
