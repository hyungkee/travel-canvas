package app.heuristy.dev.travelcanvas.controller.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

import app.heuristy.dev.travelcanvas.view.NonSwipeableViewPager;
import app.heuristy.dev.travelcanvas.R;
import app.heuristy.dev.travelcanvas.controller.activity.SearchPersonActivity;


public class AddTravelFrag2 extends Fragment{

    private NonSwipeableViewPager viewPager;

    private Button startDateButton;
    private Button endDateButton;

    private int startYear, startMonth, startDay;
    private int endYear, endMonth, endDay;


    public AddTravelFrag2() {
        // Required empty public constructor
    }


    public AddTravelFrag2 setViewPager(NonSwipeableViewPager viewPager) {
        this.viewPager = viewPager;
        return this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_add_travel_2, container, false);

        Button prevBtn = (Button)view.findViewById(R.id.prev_btn);
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
            }
        });

        Button nextBtn = (Button)view.findViewById(R.id.next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkValidation())
                    viewPager.setCurrentItem(2);
            }
        });

        startDateButton = (Button)view.findViewById(R.id.btn_start_date);
        endDateButton = (Button)view.findViewById(R.id.btn_end_date);

        Calendar cal = new GregorianCalendar();

        updateStartDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        startDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        updateStartDate(i, i1, i2);
                    }
                }, startYear, startMonth, startDay).show();
            }
        });

        updateEndDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        endDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        updateEndDate(i, i1, i2);
                    }
                }, endYear, endMonth, endDay).show();
            }
        });

        Button addCompanionBtn = (Button)view.findViewById(R.id.btn_add_companion);
        addCompanionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SearchPersonActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }


    void updateStartDate(int year, int month, int day){
        startYear = year;
        startMonth = month;
        startDay = day;
        startDateButton.setText(startYear+"-"+startMonth+"-"+startDay);
    }

    void updateEndDate(int year, int month, int day){
        endYear =  year;
        endMonth = month;
        endDay = day;
        endDateButton.setText(endYear+"-"+endMonth+"-"+endDay);
    }

    boolean checkValidation(){
        return true;
    }

}