package com.rony.srmm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.rony.srmm.API.RetrofitClient;
import com.rony.srmm.Adapter.NoticeAdapter;
import com.rony.srmm.R;
import com.rony.srmm.Response.NoticeResponse;
import com.rony.srmm.databinding.ActivityNoticeBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeActivity extends AppCompatActivity {

    ActivityNoticeBinding binding;

    RecyclerView noticeRecyclerView;
    NoticeResponse noticeResponse;
    NoticeAdapter noticeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoticeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        noticeRecyclerView = findViewById(R.id.noticeRecyclerView);
        noticeRecyclerView.setHasFixedSize(true);
        noticeRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        RetrofitClient.getRetrofitClient(this).getNotice().enqueue(new Callback<NoticeResponse>() {
            @Override
            public void onResponse(Call<NoticeResponse> call, Response<NoticeResponse> response) {
                if (response.isSuccessful()){
                    noticeResponse =response.body();
                    noticeAdapter = new NoticeAdapter(getApplicationContext(), noticeResponse);
                    noticeRecyclerView.setAdapter(noticeAdapter);
                }
            }

            @Override
            public void onFailure(Call<NoticeResponse> call, Throwable t) {

            }
        });

    }
}