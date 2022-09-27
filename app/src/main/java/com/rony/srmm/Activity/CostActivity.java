package com.rony.srmm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rony.srmm.API.RetrofitClient;
import com.rony.srmm.Adapter.CostAdapter;
import com.rony.srmm.R;
import com.rony.srmm.Response.CostResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CostActivity extends AppCompatActivity {

    List<CostResponse> costResponseList;
    RecyclerView recyclerView;
    CostAdapter costAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        costResponseList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RetrofitClient.getRetrofitClient(this).getCost().enqueue(new Callback<List<CostResponse>>() {
            @Override
            public void onResponse(Call<List<CostResponse>> call, Response<List<CostResponse>> response) {
                /*shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);*/
                recyclerView.setVisibility(View.VISIBLE);

                costResponseList = response.body();
                costAdapter = new CostAdapter(getApplicationContext(), costResponseList);
                recyclerView.setAdapter(costAdapter);
                System.out.println(response.body());
                Toast.makeText(getApplicationContext(), "List is is " + costResponseList.size() , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<CostResponse>> call, Throwable t) {

            }
        });
    }
}