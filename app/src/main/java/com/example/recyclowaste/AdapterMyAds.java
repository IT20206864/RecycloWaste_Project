package com.example.recyclowaste;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclowaste.model.Advertisment;

import java.util.List;


public class AdapterMyAds extends RecyclerView.Adapter<AdapterMyAds.ItemViewHolder> {

    private Context context;
    private List<Advertisment> ads;

    public AdapterMyAds(Context context , List<Advertisment> ads){
        this.context = context;
        this.ads = ads;
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.ads_item_card,parent,false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Advertisment adCurrent = ads.get(position);
        holder.description.setText(adCurrent.getDescription());
        holder.image.setImageURI(Uri.parse(adCurrent.getImage()));
    }

    @Override
    public int getItemCount() {
        return ads.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        public EditText description;
        public EditText price;
        public ImageView image;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            description = itemView.findViewById(R.id.et_productDesc);
            image = itemView.findViewById(R.id.img_product);
            price = itemView.findViewById(R.id.et_itemPrice);
        }
    }
}
