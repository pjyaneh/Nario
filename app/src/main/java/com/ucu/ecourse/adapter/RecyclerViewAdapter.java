package com.ucu.ecourse.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ucu.ecourse.R;
import com.ucu.ecourse.interf.onMoveAndSwipedListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zhang on 2016.08.07.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements onMoveAndSwipedListener {

    private Context context;
    private List<String> mItems;
    private int color = 0;
    private View parentView;

    private final int TYPE_NORMAL = 1;
    private final int TYPE_FOOTER = 2;
    private final int TYPE_HEADER = 3;
    private final String FOOTER = "footer";
    private final String HEADER = "header";

    public RecyclerViewAdapter(Context context) {
        this.context = context;
        mItems = new ArrayList();
    }

    public void setItems(List<String> data) {
        this.mItems.addAll(data);
        notifyDataSetChanged();
    }

    public void addItem(int position, String insertData) {
        mItems.add(position, insertData);
        notifyItemInserted(position);
    }

    public void addItems(List<String> data) {
        mItems.add(HEADER);
        mItems.addAll(data);
        notifyItemInserted(mItems.size() - 1);
    }

    public void addHeader() {
        this.mItems.add(HEADER);
        notifyItemInserted(mItems.size() - 1);
    }

    public void addFooter() {
        mItems.add(FOOTER);
        notifyItemInserted(mItems.size() - 1);
    }

    public void removeFooter() {
        mItems.remove(mItems.size() - 1);
        notifyItemRemoved(mItems.size());
    }

    public void setColor(int color) {
        this.color = color;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        parentView = parent;


            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_header, parent, false);
            return new HeaderViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof RecyclerViewHolder) {
            final RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) holder;




            AlphaAnimation aa1 = new AlphaAnimation(1.0f, 0.1f);
            aa1.setDuration(400);


            AlphaAnimation aa = new AlphaAnimation(0.1f, 1.0f);
            aa.setDuration(400);


        }
    }

    @Override
    public int getItemViewType(int position) {
        String s = mItems.get(position);
        switch (s) {
            case HEADER:
                return TYPE_HEADER;
            case FOOTER:
                return TYPE_FOOTER;
            default:
                return TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mItems, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(final int position) {
        mItems.remove(position);
        notifyItemRemoved(position);


    }


    private class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private View mView;


        private RecyclerViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

        }
    }

    private class FooterViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar progress_bar_load_more;

        private FooterViewHolder(View itemView) {
            super(itemView);

        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView header_text;

        private HeaderViewHolder(View itemView) {
            super(itemView);
            header_text = itemView.findViewById(R.id.header_text);
        }
    }

}
