package com.example.studentrating;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private ClientAPI clientAPI;

    public RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://188.225.58.136:8080")
                .addConverterFactory(GsonConverterFactory.create()).build();

        clientAPI = retrofit.create(ClientAPI.class);

    }

    public void getStudents(Callback<List<Student>> callback){
        clientAPI.getStudents().enqueue(callback);
    }

    public void addStudent(Callback<Void> callback, Student student){
        clientAPI.addStudent(student).enqueue(callback);
    }

    public void addScore(Callback<Void> callback, Student student){
        clientAPI.addScore(student).enqueue(callback);
    }

}
