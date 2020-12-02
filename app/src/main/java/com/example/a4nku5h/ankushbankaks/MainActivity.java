package com.example.a4nku5h.ankushbankaks;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a4nku5h.ankushbankaks.dialog.ProgressDialogUndetermined;
import com.example.a4nku5h.ankushbankaks.model.RootModel;
import com.example.a4nku5h.ankushbankaks.viewModel.ViewModel;

import java.util.concurrent.ExecutionException;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ConstraintLayout rootView;
    public static ViewModel viewModel;
    TextView spinner;
    public RootModel rootModel;
    TextView tv_status,tv_msg,tv_result;
    private ProgressDialogUndetermined progressDialogUndetermined;
    Button btn_openData;
    boolean isValidResult=false;
    final int ACTIVITY_CODE=5555;
    final String TYPE_KEY="TYPEKEY";
    String current_TYPE="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Ankush Demo App");
        initViews();
        }

    private void initViews(){
        //to init Views
        rootView=findViewById(R.id.main_rootView);
        tv_status=findViewById(R.id.main_status);
        tv_msg=findViewById(R.id.main_message);
        tv_result=findViewById(R.id.main_result);
        btn_openData =findViewById(R.id.main_ibtn_showData);
        btn_openData.setOnClickListener(this);
        spinner = findViewById(R.id.main_tv_spinner);
        spinner.setOnClickListener(this);
        viewModel=ViewModelProviders.of(this).get(ViewModel.class);
        viewModel.getLiveDataInstance().observe(this, new Observer<RootModel>() {
            @Override
            public void onChanged(@Nullable RootModel example){
                rootModel=example;
                renderPartialData();
            }
        });
         progressDialogUndetermined=getDialogProgressLoading();
    }
    public void getData(String type) throws ExecutionException, InterruptedException {
        //geeting data from API
        if (!isNetworkConnected()){
            Toast.makeText(this, "Something went wrong , check your internet connection", Toast.LENGTH_SHORT).show();
            progressDialogUndetermined.dismiss();
            return;
        }
        //Using Live data
        progressDialogUndetermined.show();
        viewModel.makeApiCall(type);
    }
    private void renderPartialData(){
        // to show show status whether given type is valid or not
        // just for demo application
        if(rootModel.getStatus().equals("true")){
            isValidResult=true;
            btn_openData.setVisibility(View.VISIBLE);
        }
        else{
            btn_openData.setVisibility(View.GONE);
            isValidResult=false;
            displayMessage("Data not found For selected TYPE");
        }

        tv_status.setText(rootModel.getStatus().toString());
        tv_msg.setText(rootModel.getMessage());
        if(rootModel.getResult()==null){
        tv_result.setText("null");
        }else tv_result.setText(rootModel.getResult().getScreenTitle());
        progressDialogUndetermined.dismiss();
    }
    private void displayMessage(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();

    }
    private void showDetailActivity(){
        // to show form with the help of data specified in api
        Intent detailActivityIntent=new Intent(this,DetailActivity.class);
        startActivity(detailActivityIntent);


    }

    private boolean isNetworkConnected() {
        //to check is  internet working
        ConnectivityManager cm = (ConnectivityManager)  getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.main_ibtn_showData){
            showDetailActivity();
        }else
        if (v.getId()==R.id.main_tv_spinner){
            Intent intent=new Intent(this,ServiceSelectionSpinner.class);
            startActivityForResult(intent, ACTIVITY_CODE);
            overridePendingTransition( R.anim.slide_in_top, R.anim.stay);

        }
    }
    private ProgressDialogUndetermined getDialogProgressLoading(){
        //progress dialog until API results
        ProgressDialogUndetermined progressDialogUndetermined =new ProgressDialogUndetermined(this);
        progressDialogUndetermined.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialogUndetermined.setCanceledOnTouchOutside(false);
        return progressDialogUndetermined;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //getting data from select service activity
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_CODE) {
            if(resultCode == RESULT_OK) {
                current_TYPE= data.getStringExtra(TYPE_KEY);
                spinner.setText(current_TYPE);
                try {
                    getData(current_TYPE);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onResume() {
        //to show network error
        // internet is working when user open FORM (DetailActivity) but not working when user come back from FORM to main
        super.onResume();
        try {
            if (!isNetworkConnected())
                getData(current_TYPE);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
