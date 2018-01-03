package com.example.admin.isspasstimes.view.main;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.isspasstimes.R;
import com.example.admin.isspasstimes.model.Response;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "AdapterTag";
    private List<Response> mValues;
    private final OnListInteractionListener mListener;

    public MyItemRecyclerViewAdapter(List<Response> items, OnListInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        //TimeUnit timeUnit;
        //String time = timeUnit.convert(mValues.get(position).getRisetime(), TimeUnit.HOURS);
        Date date = new Date(mValues.get(position).getRisetime());
        DateFormat formatter = new SimpleDateFormat("MM.dd HH:mm:ss z");
        String dateFormatted = formatter.format(date);

        int duration = mValues.get(position).getDuration();
        int mins = duration/60;
        int secs = duration%60;
        Log.d(TAG, "onBindViewHolder: " + duration);
        holder.tvRiseTime.setText(dateFormatted);
        holder.tvDuration.setText(mins + " mins " + secs + " seconds");

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mValues == null){
            return 0;
        }
        return mValues.size();
    }

    public void updateList(List<Response> list){
        if (mValues != null) {
            mValues.clear();
        }
        mValues = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvRiseTime;
        public final TextView tvDuration;
        public Response mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvRiseTime = (TextView) view.findViewById(R.id.tvRiseTime);
            tvDuration = (TextView) view.findViewById(R.id.tvDuration);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvDuration.getText() + "'";
        }
    }

    interface OnListInteractionListener{
        void onListFragmentInteraction(Response item);
    }
}
