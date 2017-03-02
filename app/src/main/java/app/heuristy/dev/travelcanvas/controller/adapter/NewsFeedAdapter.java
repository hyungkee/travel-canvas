package app.heuristy.dev.travelcanvas.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.heuristy.dev.travelcanvas.R;
import app.heuristy.dev.travelcanvas.model.NewsFeed;

/**
 * Created by Heuristy10 on 2017-03-01.
 */
public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedAdapter.MyViewHolder> {

    private Context mContext;
    private List<NewsFeed> newsFeedList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, detail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            detail = (TextView) view.findViewById(R.id.detail);
        }
    }


    public NewsFeedAdapter(Context mContext, List<NewsFeed> newsFeedList) {
        this.mContext = mContext;
        this.newsFeedList = newsFeedList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_feed_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        NewsFeed newsFeed = newsFeedList.get(position);
        holder.title.setText(newsFeed.getTitle());
        holder.detail.setText(newsFeed.getContents());
    }

    @Override
    public int getItemCount() {
        return newsFeedList.size();
    }

    public NewsFeed getNewsFeed(int position){
        return newsFeedList.get(position);
    }

}
