package com.example.freindscafe;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener{
    private GestureDetector gestureDetector;
    private OnItemClickListener onItemClickListener;
    private View view;
    private RecyclerView recyclerView;

    public RecyclerItemClickListener(Context context, OnItemClickListener onItemClickListener) {
        this.gestureDetector = new GestureDetector((Context) context,new GestureDetector.SimpleOnGestureListener() {

            public boolean OnSingleTap(MotionEvent e)
            {

                if(onItemClickListener!=null)
                {

                   int position=recyclerView.getChildAdapterPosition(view);
                   onItemClickListener.onItemClick(view,position);
                }
                return onItemClickListener != null;
            }



        });
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public void onItemClick(View v, int position) {
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
