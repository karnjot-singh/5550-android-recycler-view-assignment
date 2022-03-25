package com.example.classwork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HotelListAdapter extends RecyclerView.Adapter<HotelListAdapter.ViewHolder> {
    private List<HotelListData> hotelListData;
    private LayoutInflater layoutInflater;
    private ItemClickListener clickListener;

    HotelListAdapter(Context context, List<HotelListData> hotelListData) {
        this.layoutInflater = LayoutInflater.from(context);
        this.hotelListData = hotelListData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.hotel_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String hotelName = hotelListData.get(position).getName();
        String hotelPrice = hotelListData.get(position).getPrice();
        String hotelAvailability = hotelListData.get(position).getAvailability();

        holder.hotelName.setText(hotelName);
        holder.hotelPrice.setText(hotelPrice);
        holder.hotelAvailability.setText(hotelAvailability);
    }

    @Override
    public int getItemCount() {
        if (hotelListData != null){
            return hotelListData.size();
        }
        else {
            return 0;
        }
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView hotelName, hotelPrice, hotelAvailability;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hotelName = itemView.findViewById(R.id.hotel_name_text_view);
            hotelPrice = itemView.findViewById(R.id.hotel_price_text_view);
            hotelAvailability = itemView.findViewById(R.id.hotel_availability_text_view);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(clickListener != null) {
                clickListener.onClick(view, getAbsoluteAdapterPosition());
            }
        }
    }
}
