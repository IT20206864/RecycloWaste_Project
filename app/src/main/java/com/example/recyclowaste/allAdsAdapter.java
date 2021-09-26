package com.example.recyclowaste;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclowaste.model.Advertisment;
import com.squareup.picasso.Picasso;

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

        Log.d("Image Value", "onBindViewHolder: " + adCurrent.getImage().trim() );
            Picasso.get().load(adCurrent.getImage().trim()).into(holder.image);
            holder.price.setText(Float.toString(adCurrent.getPrice()));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,R.array.quantity_array,android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        holder.spinner.setAdapter(adapter);


    }
    public void onItemSelected(AdapterView<?> parent, View view,int pos,long id){
        parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent){

    }

    @Override
    public int getItemCount() {
        return ads.size();
    }

    public class adsViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title,description,price;
        Spinner spinner;
        public adsViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.img_all_ads);
            description = itemView.findViewById(R.id.desc_all_ads);
            title = itemView.findViewById(R.id.title_all_ads);
            price = itemView.findViewById(R.id.price_all_ads);
            spinner = (Spinner)itemView.findViewById(R.id.spinner_all_ads);

        }
    }
}
