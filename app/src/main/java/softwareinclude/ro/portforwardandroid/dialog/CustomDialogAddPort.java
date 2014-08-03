package softwareinclude.ro.portforwardandroid.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import softwareinclude.ro.portforwardandroid.R;

/**
 * Created by manolescusebastian on 8/3/14.
 */

public class CustomDialogAddPort extends ProgressDialog {
    private AnimationDrawable animation;

    public static ProgressDialog ctor(Context context) {
        CustomDialogAddPort dialog = new CustomDialogAddPort(context);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        return dialog;
    }

    public CustomDialogAddPort(Context context) {
        super(context);
    }

    public CustomDialogAddPort(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_progress_dialog);

        ImageView la = (ImageView) findViewById(R.id.animation);
        la.setBackgroundResource(R.drawable.custom_progress_dialog_animation);
        animation = (AnimationDrawable) la.getBackground();
    }

    @Override
    public void show() {
        super.show();
        animation.start();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        animation.stop();
    }
}