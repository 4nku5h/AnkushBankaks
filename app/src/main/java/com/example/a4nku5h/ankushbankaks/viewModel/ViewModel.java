package com.example.a4nku5h.ankushbankaks.viewModel;

import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.example.a4nku5h.ankushbankaks.Retrofit.ApiService;
import com.example.a4nku5h.ankushbankaks.model.ListModel;
import com.example.a4nku5h.ankushbankaks.model.RootModel;
import com.example.a4nku5h.ankushbankaks.Retrofit.RetrofitInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModel extends android.arch.lifecycle.ViewModel {
    private MutableLiveData<RootModel> mutableLiveData;
    RootModel currentData;
    List<ListModel> dummy_dataset;
    public ViewModel(){
        mutableLiveData=new MutableLiveData<>();

        //////////////Dummy Data
        dummy_dataset=new ArrayList<ListModel>();
        dummy_dataset.add(new ListModel("1","(type-1) ELECTRICITY BILL "));
        dummy_dataset.add(new ListModel("2","(type-2) WATER BILL "));
        dummy_dataset.add(new ListModel("3","(type-3) WATER BILL "));
        dummy_dataset.add(new ListModel("4","(type-4) dummy"));
        dummy_dataset.add(new ListModel("5","(type-5) dummy"));
        dummy_dataset.add(new ListModel("6","(type-6) dummy"));
        dummy_dataset.add(new ListModel("7","(type-7) dummy"));
        dummy_dataset.add(new ListModel("8","(type-8) dummy"));
        dummy_dataset.add(new ListModel("9","(type-9) dummy"));
        dummy_dataset.add(new ListModel("10","(type10) dummy"));
        dummy_dataset.add(new ListModel("11","(type-11) dummy"));
        dummy_dataset.add(new ListModel("12","(type-12) dummy"));
        dummy_dataset.add(new ListModel("13","(type-13) dummy"));
        dummy_dataset.add(new ListModel("14","(type-14) dummy"));
       ////////////////


    }
    public List<ListModel> getDummyData(){
        return dummy_dataset;
    }
    public MutableLiveData<RootModel> getLiveDataInstance(){
        return mutableLiveData;
    }
    public void makeApiCall(String type) throws ExecutionException, InterruptedException {
        // Performing Api call In separate Thread (using Async Task)

        new ApicallTask().execute(type);
    }
    public RootModel getCurrentData(){
        return currentData;
    }


    public class ApicallTask extends AsyncTask<String,Void, MutableLiveData<RootModel>>{

        @Override
        protected MutableLiveData<RootModel> doInBackground(String... strings) {
            String type=strings[0];
            ApiService apiService= RetrofitInstance.getRetrofitClient().create(ApiService.class);
            Call<RootModel> data= apiService.getResultById(type);
            data.enqueue(new Callback<RootModel>() {
                @Override
                public void onResponse(Call<RootModel> call, Response<RootModel> response) {
                    //if api respond
                    currentData=response.body();
                    mutableLiveData.postValue(response.body());
                    // Example result1=response.body();
                    //Toast.makeText(, result1.getResult().getNumberOfFields().toString(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<RootModel> call, Throwable t) {
                    //in case api fails
                    currentData=null;
                    mutableLiveData.postValue(null);
                }
            });
            return mutableLiveData;
        }
    }
}
