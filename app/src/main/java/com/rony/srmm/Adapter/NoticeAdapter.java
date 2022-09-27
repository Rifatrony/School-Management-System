package com.rony.srmm.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rony.srmm.R;
import com.rony.srmm.Response.NoticeResponse;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {

    Context context;
    NoticeResponse noticeResponse;
    String date, day, month, year;

    public NoticeAdapter(Context context, NoticeResponse noticeResponse) {
        this.context = context;
        this.noticeResponse = noticeResponse;
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notice_sample_layout, parent, false);
        return new NoticeViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {

        holder.noticeTitleTextView.setText(noticeResponse.notice.get(position).noticeTitle);
        holder.noticeDateTextView.setText(noticeResponse.notice.get(position).noticeDate);
        holder.noticeDescriptionTextView.setText(noticeResponse.notice.get(position).noticeDescription);

        date = noticeResponse.notice.get(position).noticeDate;
        if (!date.isEmpty()){
            day = date.substring(0,2);
            month = date.substring(3,5);
            year = date.substring(6,10);
            System.out.println(day + " " + month+ " " + year);
            holder.dateTextView.setText(day + "/" + month);
            holder.yearTextView.setText(year);

        }

    }

    @Override
    public int getItemCount() {
        return noticeResponse.notice.size();
    }


    public class NoticeViewHolder extends RecyclerView.ViewHolder {

        TextView noticeTitleTextView, noticeDateTextView, noticeDescriptionTextView, dateTextView, yearTextView;

        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            noticeTitleTextView = itemView.findViewById(R.id.noticeTitleTextView);
            noticeDateTextView = itemView.findViewById(R.id.noticeDateTextView);
            noticeDescriptionTextView = itemView.findViewById(R.id.noticeDescriptionTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            yearTextView = itemView.findViewById(R.id.yearTextView);
        }
    }
}
