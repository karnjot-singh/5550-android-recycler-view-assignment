package com.example.classwork;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class HotelListFragment extends Fragment implements ItemClickListener  {

    View view;
    TextView infoTextView;
    ProgressBar progressBar;
    List<HotelListData> hotelResponseData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hotel_list_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progress_bar);

        Bundle bundle = getArguments();
        String infoText = "Hello "+bundle.getString("guestName")+", results for "+ bundle.getString("guestNumber") +
                " guests from " + bundle.getString("checkInDate") + " to " + bundle.getString("checkOutDate");
        infoTextView = view.findViewById(R.id.heading_text_view);
        infoTextView.setText(infoText);

        //hotelResponseData = initHotelListData();
        //setupRecyclerView();
        getHotelListData();
    }

    public ArrayList<HotelListData> initHotelListData() {
        ArrayList<HotelListData> list = new ArrayList<>();

        list.add(new HotelListData("Halifax Regional Hotel", "2000$", "true"));
        list.add(new HotelListData("Hotel Pearl", "500$", "false"));
        list.add(new HotelListData("Hotel Amano", "800$", "true"));
        list.add(new HotelListData("San Jones", "250$", "false"));
        list.add(new HotelListData("Halifax Regional Hotel", "2000$", "true"));
        list.add(new HotelListData("Hotel Pearl", "500$", "false"));
        list.add(new HotelListData("Hotel Amano", "800$", "true"));
        list.add(new HotelListData("San Jones", "250$", "false"));

        return list;
    }

    private void getHotelListData() {
        progressBar.setVisibility(View.VISIBLE);
        Api.getClient().getHotelsList(new Callback<List<HotelListData>>() {
            @Override
            public void success(List<HotelListData> hotelListData, Response response) {
                hotelResponseData = hotelListData;
                setupRecyclerView();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setupRecyclerView() {
        progressBar.setVisibility(View.GONE);
        RecyclerView recyclerView = view.findViewById(R.id.hotel_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HotelListAdapter hotelListAdapter = new HotelListAdapter(getActivity(), hotelResponseData);
        recyclerView.setAdapter(hotelListAdapter);

        //Bind the click listener
        hotelListAdapter.setClickListener(this);
    }

    @Override
    public void onClick(View view, int position) {
        HotelListData hotelListData = hotelResponseData.get(position);

        String hotelName = hotelListData.getName();
        String price = hotelListData.getPrice();
        String availability = hotelListData.getAvailability();

        Bundle bundle = new Bundle();
        bundle.putString("hotelName", hotelName);
        bundle.putString("hotelPrice", price);
        bundle.putString("hotelAvailability", availability);

        HotelGuestDetailsFragment hotelGuestDetailsFragment = new HotelGuestDetailsFragment();
        hotelGuestDetailsFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.remove(HotelListFragment.this);
        fragmentTransaction.replace(R.id.main_layout, hotelGuestDetailsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();

    }
}
