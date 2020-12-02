package com.example.a4nku5h.ankushbankaks;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.a4nku5h.ankushbankaks.adapter.RecyclerAdapter;
import com.example.a4nku5h.ankushbankaks.model.Field;
import com.example.a4nku5h.ankushbankaks.model.Result;
import com.example.a4nku5h.ankushbankaks.model.RootModel;
import com.example.a4nku5h.ankushbankaks.model.Value;
import com.example.a4nku5h.ankushbankaks.viewModel.ViewModel;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewModel viewModel;
    RootModel rootModel;
    int viewCount=0;
    private MaterialEditText et_email,et_phone,et_custId,et_name, et_scNumber;
    Field f_month,f_email,f_custId,f_phone,f_name,f_scNo;
    LinearLayout layout_month,layout_email,layout_custId,layout_phone,layout_name,layout_scNum;
    NiceSpinner spinner;
    Button btn_proceed;
    boolean isValidData=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initViews();
        populateDynamicLayout();
        showData();
        //setAdapter(rootModel.getResult());
    }
    private void initViews(){
        et_email =findViewById(R.id.detail_etEmail);
        et_phone =findViewById(R.id.detail_etPhone);
        et_name =findViewById(R.id.detail_etName);
        et_custId =findViewById(R.id.detail_custId);
        et_scNumber =findViewById(R.id.detail_etScNum);
        //layouts
        //layout_month=findViewById(R.id.mo);
        layout_email=findViewById(R.id.detail_layout_email);
        layout_custId=findViewById(R.id.detail_layout_custId);
        layout_phone=findViewById(R.id.detail_layout_phone);
        layout_name=findViewById(R.id.detail_layout_name);
        layout_scNum=findViewById(R.id.detail_layout_scNum);
        layout_month=findViewById(R.id.detail_layout_month);
        btn_proceed=findViewById(R.id.details_btn_proceed);
        btn_proceed.setOnClickListener(this);
        spinner=findViewById(R.id.detail_spinner_month);
        viewModel= MainActivity.viewModel;
        rootModel=viewModel.getCurrentData();
    }

    private void showData(){
        //custId.setHint(rootModel.getResult().getFields().get(2).getHintText());

    }

    private void populateDynamicLayout(){
        //For electricity bill type=1,  sc number,customer id
        //for water bill       type=2,  month id, customer id
        //for water bill       type=3,  month id, email, phone ,name

        getSupportActionBar().setTitle(rootModel.getResult().getScreenTitle());
        for (Field field:rootModel.getResult().getFields()){
            switch (field.getName()){
                case "month_id":
                    f_month=field;
                    layout_month.setVisibility(View.VISIBLE);
                    List<String> list_months = new LinkedList<>();
                    for (Value value:f_month.getUiType().getValues()){
                        list_months.add(value.getName());
                    }
                    spinner.attachDataSource(list_months);
                    viewCount++;
                    //
                    break;
                case "customer_email":
                    f_email=field;
                    layout_email.setVisibility(View.VISIBLE);
                    et_email.setHint(f_email.getHintText());
                    viewCount++;
                    break;
                case "customer_id":
                    f_custId=field;
                    layout_custId.setVisibility(View.VISIBLE);
                    et_custId.setHint(f_custId.getHintText());
                    viewCount++;
                    break;
                case "customer_phone":
                    f_phone=field;
                    layout_phone.setVisibility(View.VISIBLE);
                    et_phone.setHint(f_phone.getHintText());
                    viewCount++;
                    break;
                case "name":
                    f_name=field;
                    layout_name.setVisibility(View.VISIBLE);
                    et_name.setHint(f_name.getHintText());
                    viewCount++;
                    break;
                case "sc_no":
                    f_scNo=field;
                    layout_scNum.setVisibility(View.VISIBLE);
                    et_scNumber.setHint(f_scNo.getHintText());
                    viewCount++;
                    break;
            }
        }

    }
    private boolean isValid(){
        //used different pattern for EMAIL and PHONE,because previous one isnt working(that mentioned in API)
        isValidData=true;
        int tempViewCount=0;

        if (f_month!=null){
            tempViewCount++;
        }
        if (f_email!=null){
            Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(et_email.getText().toString().trim());

            if (!matcher.find()){
                et_email.setError("Invalid Email");
                isValidData=false;
            }else tempViewCount++;
        }
        //custId
            if (f_custId!=null){
            Pattern pattern1=Pattern.compile(f_custId.getRegex());
            Matcher matcher1=pattern1.matcher(et_custId.getText().toString().trim());
            if (et_custId.getText().length()==0||!matcher1.find()){
                et_custId.setError("Invalid customer id");
                isValidData=false;
            }else tempViewCount++;
        }
        //phone
        if (f_phone!=null){

            Pattern pattern2=Pattern.compile("[6789][0-9]{9}");
            Matcher matcher2=pattern2.matcher(et_phone.getText().toString().trim());
            if (!matcher2.find()){
                et_phone.setError("Invalid Phone number");
                isValidData=false;
            }else tempViewCount++;
        }
        //name
        if (f_name!=null){

            Pattern pattern3=Pattern.compile(f_name.getRegex());
            Matcher matcher3=pattern3.matcher(et_name.getText().toString().trim());
            if ( et_name.getText().length()==0|| !matcher3.find() ){
                et_name.setError("Invalid Name");
                isValidData=false;
            }else tempViewCount++;
        }
        // scnum
        if (f_scNo!=null){
            Pattern pattern4=Pattern.compile(f_scNo.getRegex());
            Matcher matcher4=pattern4.matcher(et_scNumber.getText().toString().trim());
            if (et_scNumber.getText().length()==0||!matcher4.find()){
                et_scNumber.setError("Invalid SC Number");
                isValidData=false;
            }else tempViewCount++;
        }
        if (tempViewCount!=viewCount){
            Toast.makeText(this, "All fields is mendatory", Toast.LENGTH_SHORT).show();
            return false;
        }

        return isValidData;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.details_btn_proceed){
            if(isValid()){
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                },1000);
            }
        }
    }
}