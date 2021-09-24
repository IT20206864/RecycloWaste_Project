package com.example.recyclowaste;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclowaste.model.Advertisment;

import java.util.List;

public class allAdsAdapter extends RecyclerView.Adapter<allAdsAdapter.adsViewHolder>{

    private Context context;
    private List<Advertisment> ads;

    public allAdsAdapter(Context context , List<Advertisment> ads){
        this.context = context;
        this.ads = ads;
    }

    @NonNull
    @Override
    public adsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.all_ads_card,parent,false);
        adsViewHolder adsView = new adsViewHolder(view);
        return adsView;
    }

    @Override
    public void onBindViewHolder(@NonNull adsViewHolder holder, int position) {
            Advertisment adCurrent = ads.get(position);
            holder.title.setText(adCurrent.getTitle());
            holder.description.setText(adCurrent.getDescription());
            holder.image.setImageURI(Uri.parse(adCurrent.getImage()));
     //       holder.price.setText((int) adCurrent.getPrice());


    }

    @Override
    public int getItemCount() {
        return ads.size();
    }

    public class adsViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title,description,price;
        public adsViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.img_all_ads);
            description = itemView.findViewById(R.id.desc_all_ads);
            title = itemView.findViewById(R.id.title_all_ads);
            price = itemView.findViewById(R.id.price_all_ads);

        }
    }
}
