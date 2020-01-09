package com.example.studentrating;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ClientAPI {

    @GET("/students")
    Call<List<Student>> getStudents();

    @POST("/add_student")
    Call<Void> addStudent(@Body Student student);

    @POST("/add_score")
    Call<Void> addScore(@Body Student student);

}
