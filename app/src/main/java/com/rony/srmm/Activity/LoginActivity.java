package com.rony.srmm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rony.srmm.API.RetrofitClient;
import com.rony.srmm.R;
import com.rony.srmm.Response.LoginResponse;
import com.rony.srmm.Response.SessionDataModel;
import com.rony.srmm.Sessison.SessionManagement;
import com.rony.srmm.databinding.ActivityLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    String email, password;
    SessionManagement sessionManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sessionManagement = new SessionManagement(this);


        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckLoginCredential();
            }
        });

    }

    private void CheckLoginCredential() {
        email = binding.emailEditText.getText().toString().trim();
        password = binding.passwordEditText.getText().toString().trim();

        if (email.isEmpty() && password.isEmpty()){
            Toast.makeText(this, "Enter Login Credential", Toast.LENGTH_SHORT).show();
        }
        else {
            LoginUser();
        }
    }

    private void LoginUser() {
        RetrofitClient.getRetrofitClient(this).userLogin(email, password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                    SessionDataModel sessionDataModel = new SessionDataModel(response.body().token, email, password);
                    sessionManagement.setLoginSession(sessionDataModel);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                System.out.println("Error is : " + t.getMessage());
            }
        });
    }

    @Override
    protected void onStart() {

        if (!sessionManagement.getSessionModel().getUserEmail().equals("null")){
            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
            finish();
        }

        super.onStart();
    }
}