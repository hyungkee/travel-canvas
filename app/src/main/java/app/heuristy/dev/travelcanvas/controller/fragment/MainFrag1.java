package app.heuristy.dev.travelcanvas.controller.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.heuristy.dev.travelcanvas.controller.adapter.NewsFeedAdapter;
import app.heuristy.dev.travelcanvas.R;
import app.heuristy.dev.travelcanvas.model.NewsFeed;


public class MainFrag1 extends Fragment{

    private RecyclerView recyclerView;
    private NewsFeedAdapter adapter;
    private List newsFeedList;

    public MainFrag1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.main_frag_1, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        newsFeedList = new ArrayList<>();
        adapter = new NewsFeedAdapter(getContext(), newsFeedList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareNewsFeed();

        return view;
    }


    private void prepareNewsFeed(){
        NewsFeed a = new NewsFeed("NewsFeed 1", "Contents 1");
        newsFeedList.add(a);

        a = new NewsFeed("NewsFeed 2", "Contents 2");
        newsFeedList.add(a);

        a = new NewsFeed("NewsFeed 3", "Contents 3");
        newsFeedList.add(a);

    }

}