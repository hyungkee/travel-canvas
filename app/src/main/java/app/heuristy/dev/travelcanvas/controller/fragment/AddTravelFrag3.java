package app.heuristy.dev.travelcanvas.controller.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.heuristy.dev.travelcanvas.view.NonSwipeableViewPager;
import app.heuristy.dev.travelcanvas.R;
import app.heuristy.dev.travelcanvas.view.VisualScheduleView;


public class AddTravelFrag3 extends Fragment{

    private NonSwipeableViewPager viewPager;
    private VisualScheduleView visualScheduleView;

    public AddTravelFrag3() {
        // Required empty public constructor
    }


    public AddTravelFrag3 setViewPager(NonSwipeableViewPager viewPager) {
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
        View view = inflater.inflate(R.layout.frag_add_travel_3, container, false);

        Button prevBtn = (Button)view.findViewById(R.id.prev_btn);
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });

        Button nextBtn = (Button)view.findViewById(R.id.done_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkValidation())
                    getActivity().onBackPressed();
            }
        });

        visualScheduleView = (VisualScheduleView)view.findViewById(R.id.visual_schedule_view);
        SeekBar seekBar = (SeekBar)visualScheduleView.findViewById(R.id.scale_bar);
        visualScheduleView.setSeekBar(seekBar);

        setupVisualScheduleView();

        return view;
    }

    void setupVisualScheduleView(){
    }

    boolean checkValidation(){
        return true;
    }

}