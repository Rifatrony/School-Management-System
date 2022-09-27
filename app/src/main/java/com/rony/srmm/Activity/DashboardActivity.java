package com.rony.srmm.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.rony.srmm.API.RetrofitClient;
import com.rony.srmm.Adapter.BannerAdapter;
import com.rony.srmm.R;
import com.rony.srmm.Response.NoticeResponse;
import com.rony.srmm.Sessison.SessionManagement;
import com.rony.srmm.databinding.ActivityDashboardBinding;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {

    ActivityDashboardBinding binding;
    SessionManagement sessionManagement;
    RecyclerView bannerNoticeRecyclerView;
    NoticeResponse noticeResponse;
    BannerAdapter bannerAdapter;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.setTitle("Dashboard");
        sessionManagement = new SessionManagement(this);

        layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);


        getBannerNotice();

        binding.imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        binding.navigationView.setItemIconTintList(null);

        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                binding.drawerLayout.closeDrawer(GravityCompat.START);

                switch (id){
                    case R.id.profile:

                        Toast.makeText(DashboardActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.notice:
                        Toast.makeText(DashboardActivity.this, "Notice", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.result:
                        Toast.makeText(DashboardActivity.this, "Result", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.logout:
                        sessionManagement.removeLoginSession();
                        Toast.makeText(DashboardActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                        break;

                    default:
                        return true;
                }
                return true;
            }
        });

        binding.studentCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), StudentActivity.class));
            }
        });

        binding.costCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CostActivity.class));
            }
        });
        binding.noticeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NoticeActivity.class));
            }
        });



    }

    private void getBannerNotice() {

        bannerNoticeRecyclerView = findViewById(R.id.bannerNoticeRecyclerView);
        bannerNoticeRecyclerView.setHasFixedSize(true);
        bannerNoticeRecyclerView.setLayoutManager(layoutManager);

        RetrofitClient.getRetrofitClient(this).getNotice().enqueue(new Callback<NoticeResponse>() {
            @Override
            public void onResponse(Call<NoticeResponse> call, Response<NoticeResponse> response) {
                if (response.isSuccessful()){
                    noticeResponse = response.body();
                    bannerAdapter = new BannerAdapter(getApplicationContext(), noticeResponse);
                    bannerNoticeRecyclerView.setAdapter(bannerAdapter);
                    System.out.println("Size is : " + noticeResponse.notice.size());


                    LinearSnapHelper snapHelper = new LinearSnapHelper();
                    snapHelper.attachToRecyclerView(bannerNoticeRecyclerView);

                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if (layoutManager.findLastCompletelyVisibleItemPosition() < (bannerAdapter.getItemCount() - 1)){
                                layoutManager.smoothScrollToPosition(bannerNoticeRecyclerView, new RecyclerView.State(),
                                        layoutManager.findLastCompletelyVisibleItemPosition() + 1);
                            }else if (layoutManager.findLastCompletelyVisibleItemPosition() < (bannerAdapter.getItemCount() - 1)){
                                layoutManager.smoothScrollToPosition(bannerNoticeRecyclerView, new RecyclerView.State(), 0);
                            }
                        }
                    },0, 3000);

                }
            }

            @Override
            public void onFailure(Call<NoticeResponse> call, Throwable t) {

            }
        });

    }
}