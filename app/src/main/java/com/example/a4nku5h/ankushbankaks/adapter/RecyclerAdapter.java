package com.example.a4nku5h.ankushbankaks.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a4nku5h.ankushbankaks.R;
import com.example.a4nku5h.ankushbankaks.model.Field;
import com.example.a4nku5h.ankushbankaks.model.ListModel;
import com.example.a4nku5h.ankushbankaks.model.Result;

import java.util.HashMap;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ProjectNamesHolder>{
    private List<ListModel> data;
    RecyclerAdapterCallBacks listener;
    Context context;

   public void setListener(RecyclerAdapterCallBacks listener){
        this.listener=listener;
   }
    public void initModel(Context context, List<ListModel> data){
        this.data=data;
        this.context=context;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ProjectNamesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_fields,parent,false);
        return new ProjectNamesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectNamesHolder holder, final int position) {
        holder.tv_service.setText(data.get(position).getValue());
        holder.tv_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key=data.get(position).getKey();
                listener.onSpinnerItemSelected(key);
            }
        });
    }

    @Override
    public int getItemCount() {

        return data.size();
    }



    class ProjectNamesHolder extends RecyclerView.ViewHolder{
        private TextView tv_service;
        public ProjectNamesHolder(View itemView){
            super(itemView);
            tv_service=itemView.findViewById(R.id.tv_service);

        }

    }

}

