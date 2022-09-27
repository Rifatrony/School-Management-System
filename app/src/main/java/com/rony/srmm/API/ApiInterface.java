package com.rony.srmm.API;

import com.rony.srmm.Response.CostResponse;
import com.rony.srmm.Response.LoginResponse;
import com.rony.srmm.Response.NoticeResponse;
import com.rony.srmm.Response.StudentResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("user/login")
    Call<LoginResponse> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("student")
    Call<StudentResponse> getStudentList();

    @GET("cost")
    Call<List<CostResponse>> getCost();

    @GET("notice")
    Call<NoticeResponse> getNotice();
}
