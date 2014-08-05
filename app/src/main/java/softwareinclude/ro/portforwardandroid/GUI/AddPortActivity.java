package softwareinclude.ro.portforwardandroid.GUI;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import softwareinclude.ro.portforwardandroid.R;

public class AddPortActivity extends Activity implements View.OnClickListener{


    private Button backTitlebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_port);

        initData();
        initUI();
    }


    private void initData () {


    }


    private void initUI() {

        backTitlebar = (Button) findViewById(R.id.addPortBackBtn);
        backTitlebar.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.addPortBackBtn:{
                this.finish();
                this.overridePendingTransition(R.anim.anim_slide_in_right,
                        R.anim.anim_slide_out_right);

                break;
            }

            default: {
                break;
            }
        }

    }
}
