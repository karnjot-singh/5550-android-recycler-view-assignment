package com.example.classwork;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HotelSearchFragment extends Fragment {

    View view;
    ConstraintLayout constraintLayout;

    TextView titleTextView, checkInDateTextView, checkOutDateTextView, guestNumberTextView, guestNameTextView;
    DatePicker checkInDatePicker, checkOutDatePicker;
    EditText guestNumberEditText, guestNameEditText;

    Button searchButton, confirmSearchButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.hotel_search_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titleTextView = view.findViewById(R.id.title_text_view);
        checkInDateTextView = view.findViewById(R.id.check_in_date_text_view);
        checkOutDateTextView = view.findViewById(R.id.check_out_date_text_view);
        guestNumberTextView = view.findViewById(R.id.guest_number_text_view);
        guestNameTextView = view.findViewById(R.id.guest_name_text_view);

        checkInDatePicker = view.findViewById(R.id.check_in_date_picker);
        checkOutDatePicker = view.findViewById(R.id.check_out_date_picker);

        guestNumberEditText = view.findViewById(R.id.guest_number_edit_text);
        guestNameEditText = view.findViewById(R.id.guest_name_edit_text);

        searchButton = view.findViewById(R.id.search_button);
        confirmSearchButton = view.findViewById(R.id.confirm_search_button);

        titleTextView.setText(R.string.welcome_text);

        confirmSearchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String checkInDate = getDateFromCalendar(checkInDatePicker);
                String checkOutDate = getDateFromCalendar(checkOutDatePicker);
                String guestNumber = guestNumberEditText.getText().toString();
                String guestName = guestNameEditText.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("checkInDate", checkInDate);
                bundle.putString("checkOutDate", checkOutDate);
                bundle.putString("guestNumber", guestNumber);
                bundle.putString("guestName", guestName);

                HotelListFragment hotelListFragment = new HotelListFragment();
                hotelListFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_layout, hotelListFragment);
                fragmentTransaction.remove(HotelSearchFragment.this);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    private String getDateFromCalendar(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = simpleDateFormat.format(calendar.getTime());

        return formattedDate;
    }
}
