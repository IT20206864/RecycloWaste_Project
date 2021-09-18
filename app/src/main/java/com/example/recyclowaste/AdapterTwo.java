package com.example.recyclowaste;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterTwo extends RecyclerView.Adapter<AdapterTwo.ViewHolder> {

    private List<ModelClassReview> userList;
    private LayoutInflater LayoutInflater;

    AdapterTwo (List<ModelClassReview>userList){
        this.userList=userList;
    }

    @NonNull
    @Override
    public AdapterTwo.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.inflate(R.layout.reviewitem_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int resource=userList.get(position).getImageview1();
        String name=userList.get(position).getTextview1();
        String review=userList.get(position).getTextview2();
        String time=userList.get(position).getTextview3();

        holder.setDataReview(resource,name,review,time);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView1;
        private TextView textView2;
        private TextView textView3;

        public ViewHolder(@NonNull android.view.View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView1);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
        }

        public void setDataReview(int resource, String name, String review, String time) {

            imageView.setImageResource(resource);
            textView1.setText(name);
            textView2.setText(review);
            textView3.setText(time);
        }
    }

}

