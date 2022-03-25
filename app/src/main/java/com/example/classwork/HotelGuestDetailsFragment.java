package com.example.classwork;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HotelGuestDetailsFragment extends Fragment {
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hotel_guest_details_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView guestDetailsTextView = view.findViewById(R.id.guest_details_text_view);

        String hotelName = getArguments().getString("hotelName");
        String hotelPrice = getArguments().getString("hotelPrice");
        String hotelAvailability = getArguments().getString("hotelAvailability");

        guestDetailsTextView.setText("For selected hotel " + hotelName + ", the price is "+hotelPrice + "[Availability is "+hotelAvailability+"]");
    }
}
