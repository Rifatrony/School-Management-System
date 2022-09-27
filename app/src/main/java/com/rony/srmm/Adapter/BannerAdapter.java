package com.rony.srmm.Adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rony.srmm.R;
import com.rony.srmm.Response.NoticeResponse;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder> {

    Context context;
    NoticeResponse noticeResponse;

    public BannerAdapter(Context context, NoticeResponse noticeResponse) {
        this.context = context;
        this.noticeResponse = noticeResponse;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.banner_sample_layout, parent, false);
        return new BannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
        if (noticeResponse.notice.size()>0){
            holder.noticeTitleTextView.setText(noticeResponse.notice.get(position).noticeTitle);
            holder.noticeDateTextView.setText(noticeResponse.notice.get(position).noticeDate);
            holder.noticeDescriptionTextView.setText(noticeResponse.notice.get(position).noticeDescription);
        }
    }

    @Override
    public int getItemCount() {
        return noticeResponse.notice.size();
    }


    public class BannerViewHolder extends RecyclerView.ViewHolder {

        TextView noticeTitleTextView, noticeDateTextView, noticeDescriptionTextView;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            noticeTitleTextView = itemView.findViewById(R.id.noticeTitleTextView);
            noticeDateTextView = itemView.findViewById(R.id.noticeDateTextView);
            noticeDescriptionTextView = itemView.findViewById(R.id.noticeDescriptionTextView);
        }
    }

}
