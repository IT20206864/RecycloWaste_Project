package com.example.recyclowaste;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        holder.booking = data.get(position);
        String driver = holder.booking.getDriver();
        String type = holder.booking.getType();
        String location = holder.booking.getLocation();
        String date = holder.booking.getDate();
        String time = holder.booking.getTime();

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
        Booking booking;
        Button btnDetails;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            driverText = itemView.findViewById(R.id.driverText);
            type = itemView.findViewById(R.id.type_text);
            location = itemView.findViewById(R.id.location_text);
            date = itemView.findViewById(R.id.date_text);
            time = itemView.findViewById(R.id.time_text);
            btnDetails = itemView.findViewById(R.id.btnDetails);

        }
    }
}
