package org.solarex.customviewdemos.impl;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.solarex.customviewdemos.R;

import java.util.ArrayList;

/**
 * Created by houruhou on 09/04/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.VH> {
    private ArrayList<Model> mModels;
    private VH.OnClickListener mListener;

    public Adapter(ArrayList<Model> models, VH.OnClickListener listener){
        mModels = models;
        mListener = listener;
    }
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.setUpListener(mListener);
        holder.setUpView(mModels.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mModels.size();
    }

    public static class VH extends RecyclerView.ViewHolder{
        public SparseArray<View> mViews;
        public View mItemView;

        public interface OnClickListener {
            void onClick(Intent intent);
        }
        private OnClickListener mListener;
        public VH(View itemView) {
            super(itemView);
            mViews = new SparseArray<>();
            mItemView = itemView;
        }

        public View getView(int resId){
            View view = mViews.get(resId);
            if (view == null){
                view = mItemView.findViewById(resId);
                mViews.put(resId, view);
            }
            return view;
        }
        public void setUpListener(OnClickListener listener){
            mListener = listener;
        }
        public void setUpView(final Model model, int position){
            TextView textView = (TextView)getView(R.id.item_tv);
            textView.setText(model.getTitle());
            textView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    mListener.onClick(model.getIntent());
                }
            });
        }


    }
}
