package app.heuristy.dev.travelcanvas.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.heuristy.dev.travelcanvas.R;
import app.heuristy.dev.travelcanvas.controller.listener.RecyclerItemClickListener;
import app.heuristy.dev.travelcanvas.controller.adapter.TravelsAdapter;
import app.heuristy.dev.travelcanvas.model.Travel;
import app.heuristy.dev.travelcanvas.tool.MyTools;
import app.heuristy.dev.travelcanvas.controller.activity.TravelActivity;


public class MainFrag2 extends Fragment{

    private RecyclerView recyclerView;
    private TravelsAdapter adapter;
    private List<Travel> travelList;

    public MainFrag2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_frag_2, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        travelList = new ArrayList<>();
        adapter = new TravelsAdapter(getContext(), travelList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new MyTools.GridSpacingItemDecoration(2, MyTools.dpToPx(10, getResources()), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever

                        Travel travel = adapter.getTravel(position);

                        Intent intent = new Intent(getContext(), TravelActivity.class);
                        intent.putExtra("travel_image_src", travel);
                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), (View)view.findViewById(R.id.thumbnail), "travel_image");
                        startActivity(intent, options.toBundle());

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        prepareTravels();

        SearchView searchView = (SearchView)view.findViewById(R.id.search_companion);

        return view;
    }


    private void prepareTravels(){
        int[] covers = new int[]{
                R.drawable.travel1,
                R.drawable.travel2,
                R.drawable.travel3
        };

        Travel a = new Travel("Japan", "japan detail", covers[0], 1);
        travelList.add(a);

        a = new Travel("Australia", "australia detail", covers[1], 2);
        travelList.add(a);

        a = new Travel("America", "america detail", covers[2], 3);
        travelList.add(a);
    }


}