package com.example.recyclowaste;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclowaste.model.Booking;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private LayoutInflater layoutInflater;
    private List<Booking> data;

    Adapter(Context context, List<Booking> data){
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.booking_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String driver = data.get(position).getDriver();
        String type = data.get(position).getType();
        String location = data.get(position).getLocation();
        String date = data.get(position).getDate();
        String time = data.get(position).getTime();

        holder.driverText.setText(driver);
        holder.type.setText(type);
        holder.location.setText(location);
        holder.date.setText(date);
        holder.time.setText(time);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView driverText, type, location, date, time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            driverText = itemView.findViewById(R.id.driverText);
            type = itemView.findViewById(R.id.type_text);
            location = itemView.findViewById(R.id.location_text);
            date = itemView.findViewById(R.id.date_text);
            time = itemView.findViewById(R.id.time_text);

        }
    }
}
