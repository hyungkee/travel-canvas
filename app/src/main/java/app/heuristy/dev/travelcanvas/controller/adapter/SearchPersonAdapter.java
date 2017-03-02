package app.heuristy.dev.travelcanvas.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import app.heuristy.dev.travelcanvas.R;
import app.heuristy.dev.travelcanvas.model.Person;

/**
 * Created by Heuristy10 on 2017-03-02.
 */

public class SearchPersonAdapter extends BaseAdapter implements Filterable {

    List<Person> mData;
    List<Person> mStringFilterList;
    ValueFilter valueFilter;
    private LayoutInflater inflater;

    public SearchPersonAdapter(List cancel_type) {
        mData=cancel_type;
        mStringFilterList = cancel_type;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Person getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {

        PersonHolder holder = null;
        Person p = mData.get(position);

        if (inflater == null) {
            inflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView != null){
            holder = (PersonHolder)convertView.getTag();
            if((holder.id > 0 && p.id < 0) || (holder.id < 0 && p.id > 0))
                convertView = null; // 이번 뷰는 버리고 새로 만든다.
        }

        if(convertView == null){
            if(p.id < 0 ){ // divider
                convertView = inflater.inflate(R.layout.list_search_person_divider, parent, false);
                holder = new PersonHolder();
                holder.name = (TextView)convertView.findViewById(R.id.name);
                holder.id = p.id;
                convertView.setTag(holder);
            }else{ // person data
                convertView = inflater.inflate(R.layout.list_search_person, parent, false);
                holder = new PersonHolder();
                holder.name = (TextView)convertView.findViewById(R.id.name);
                holder.number = (TextView)convertView.findViewById(R.id.number);
                holder.id = p.id;
                convertView.setTag(holder);
            }
        }

        if(p.id < 0){ // divider
            holder.name.setText(p.getName());
        }else{ // person data
            holder.name.setText(p.getName());
            holder.number.setText(p.getNumber());
        }

        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                List filterList = new ArrayList();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    Person p = mStringFilterList.get(i);
                    if(p.getId() < 0){ // person divider
                        filterList.add(mStringFilterList.get(i));
                    } else if ((p.getName().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(mStringFilterList.get(i));
                    } else if ((p.getNumber().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(mStringFilterList.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            mData = (List) results.values;
            notifyDataSetChanged();
        }

    }

    private class PersonHolder{
        int id;
        TextView name;
        TextView number;
    }

}