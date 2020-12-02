package com.example.a4nku5h.ankushbankaks.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;

import com.example.a4nku5h.ankushbankaks.R;
import com.github.ybq.android.spinkit.SpinKitView;

public class ProgressDialogUndetermined extends Dialog {
    public SpinKitView spinKitView;
    public ProgressDialogUndetermined( Activity context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_progress);
        spinKitView=findViewById(R.id.spin_kit);

    }
}
