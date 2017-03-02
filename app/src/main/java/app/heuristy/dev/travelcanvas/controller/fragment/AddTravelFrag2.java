package app.heuristy.dev.travelcanvas.controller.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import app.heuristy.dev.travelcanvas.model.Person;
import app.heuristy.dev.travelcanvas.view.NonSwipeableViewPager;
import app.heuristy.dev.travelcanvas.R;
import app.heuristy.dev.travelcanvas.controller.activity.SearchPersonActivity;


public class AddTravelFrag2 extends Fragment{

    private static final int ACTIVITY_SEARCH_PERSON = 100;

    private NonSwipeableViewPager viewPager;

    private Button startDateButton;
    private Button endDateButton;

    private int startYear, startMonth, startDay;
    private int endYear, endMonth, endDay;

    private List<Person> companionList;
    private LinearLayout companionListLayout;


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
                startActivityForResult(intent, ACTIVITY_SEARCH_PERSON);
            }
        });
        companionList = new ArrayList();
        companionListLayout = (LinearLayout)view.findViewById(R.id.linear_companion_list);

        LinearLayout companionLayout = (LinearLayout)view.findViewById(R.id.linear_companion);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case ACTIVITY_SEARCH_PERSON:
                if(resultCode == Activity.RESULT_OK){
                    Bundle bundle = data.getExtras();
                    final Person p = (Person)bundle.get("person");
                    if(p != null){
                        final View v = LayoutInflater.from(getContext()).inflate(R.layout.list_companion, null);
                        TextView textView = (TextView)v.findViewById(R.id.text_companion);
                        textView.setText(p.toString());
                        ImageView removeImage = (ImageView)v.findViewById(R.id.image_remove);
                        removeImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                companionListLayout.removeView(v);
                                companionList.remove(p);
                            }
                        });
                        companionListLayout.addView(v,companionList.size() + 1);

                        companionList.add(p);
                    }
                }
                break;
        }
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