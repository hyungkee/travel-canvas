package app.heuristy.dev.travelcanvas.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.heuristy.dev.travelcanvas.R;
import app.heuristy.dev.travelcanvas.model.Travel;

/**
 * Created by Heuristy10 on 2017-03-01.
 */
public class TravelsAdapter extends RecyclerView.Adapter<TravelsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Travel> travelList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, detail;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            detail = (TextView) view.findViewById(R.id.detail);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }


    public TravelsAdapter(Context mContext, List<Travel> travelList) {
        this.mContext = mContext;
        this.travelList = travelList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_travel, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Travel travel = travelList.get(position);
        holder.title.setText(travel.getName());
        holder.detail.setText(travel.getDetail());

        Glide.with(mContext).load(travel.getThumbnail()).into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return travelList.size();
    }

    public Travel getTravel(int position){
        return travelList.get(position);
    }

}
