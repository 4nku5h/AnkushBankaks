package com.example.a4nku5h.ankushbankaks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.example.a4nku5h.ankushbankaks.adapter.RecyclerAdapter;
import com.example.a4nku5h.ankushbankaks.adapter.RecyclerAdapterCallBacks;

public class ServiceSelectionSpinner extends AppCompatActivity implements RecyclerAdapterCallBacks, View.OnClickListener {
    RecyclerView recyclerView;
    ImageButton btn_cancel;
    final String TYPE_KEY="TYPEKEY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_selection_spinner);
        getSupportActionBar().hide();
        initViews();
        setAdapter();
    }
    private void initViews(){
        recyclerView=findViewById(R.id.spinner_selectserviceSpinner);
        btn_cancel=findViewById(R.id.spinner_btn_cancel);
        btn_cancel.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
    private void setAdapter(){
        RecyclerAdapter detailAdapter=new RecyclerAdapter();
        detailAdapter.setListener(this);
        detailAdapter.initModel(this,MainActivity.viewModel.getDummyData());
        recyclerView.setAdapter(detailAdapter);
    }

    @Override
    public void onSpinnerItemSelected(String type) {
        Intent intent = new Intent();
        intent.putExtra(TYPE_KEY, type);
        setResult(RESULT_OK, intent);
        finish();
        //Toast.makeText(getApplicationContext(),type,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.spinner_btn_cancel){
            finish();
        }
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.stay,R.anim.slide_from_top);
    }
}