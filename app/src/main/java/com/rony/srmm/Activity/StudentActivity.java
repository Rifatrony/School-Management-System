package com.rony.srmm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.rony.srmm.API.RetrofitClient;
import com.rony.srmm.Adapter.StudentAdapter;
import com.rony.srmm.R;
import com.rony.srmm.Response.StudentResponse;
import com.rony.srmm.databinding.ActivityStudentBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentActivity extends AppCompatActivity {

    ActivityStudentBinding binding;
    RecyclerView studentRecyclerView;

    StudentResponse studentListResponse;
    StudentAdapter adapter;

    ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        shimmerFrameLayout = findViewById(R.id.shimmer);
        shimmerFrameLayout.startShimmer();
        studentRecyclerView = findViewById(R.id.studentRecyclerView);
        studentRecyclerView.setHasFixedSize(true);
        studentRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        RetrofitClient.getRetrofitClient(this).getStudentList().enqueue(new Callback<StudentResponse>() {
            @Override
            public void onResponse(Call<StudentResponse> call, Response<StudentResponse> response) {
                if (response.isSuccessful()){

                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);
                    studentRecyclerView.setVisibility(View.VISIBLE);

                    studentListResponse = response.body();
                    adapter = new StudentAdapter(getApplicationContext(), studentListResponse);
                    studentRecyclerView.setAdapter(adapter);
                    System.out.println("Size is : " + studentListResponse.student.size());
                }
            }

            @Override
            public void onFailure(Call<StudentResponse> call, Throwable t) {
                /*shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                Toast.makeText(StudentActivity.this, "Try Again", Toast.LENGTH_SHORT).show();*/
            }
        });

    }
}