package app.heuristy.dev.travelcanvas.controller.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

import app.heuristy.dev.travelcanvas.view.NonSwipeableViewPager;
import app.heuristy.dev.travelcanvas.R;
import app.heuristy.dev.travelcanvas.view.VSChildDrgView;
import app.heuristy.dev.travelcanvas.view.VSChildView;
import app.heuristy.dev.travelcanvas.view.VSView;


public class AddTravelFrag3 extends Fragment{

    private NonSwipeableViewPager viewPager;
    private VSView vsView;

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

        vsView = (VSView)view.findViewById(R.id.visual_schedule_view);
        SeekBar seekBar = (SeekBar) view.findViewById(R.id.scale_bar);
        vsView.setSeekBar(seekBar);

        setupVisualScheduleView(view);

        return view;
    }

    void setupVisualScheduleView(View parent){
        vsView.setWorldWidthMax(6000);
        vsView.setWorldHeightMax(6000);

        // set Background
        VSChildView vsBack = (VSChildView)parent.findViewById(R.id.vsc_background);
        vsBack.regVsView(vsView);
        vsBack.setVscWidth(vsView.getWorldWidthMax());
        vsBack.setVscHeight(vsView.getWorldHeightMax());
        vsBack.setTag("back");

        // set Views
        VSChildDrgView vsc1 = (VSChildDrgView)parent.findViewById(R.id.vsc_1);
        VSChildDrgView vsc2 = (VSChildDrgView)parent.findViewById(R.id.vsc_2);
        vsc1.regVsView(vsView);
        vsc2.regVsView(vsView);
        vsc1.setVscX(700);
        vsc1.setVscY(700);

        // TODO : View를 VSView에 붙이는 코드가 필요.

    }

    boolean checkValidation(){
        return true;
    }

}