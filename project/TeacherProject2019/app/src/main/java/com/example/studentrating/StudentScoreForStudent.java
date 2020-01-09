package com.example.studentrating;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//
public class StudentScoreForStudent extends AppCompatActivity {

    private ListView listStudent;

    //test string
    /*private String[] myArr = {"Иванов А.Е. МР2 10", "Петров А.Е. МР1 10", "Сидоров А.Е. МР3 10",
            "Иванов А.Е. МР2 10", "Иванов А.Е. МР2 10", "Иванов А.Е. МР2 10", "Иванов А.Е. МР2 10",
            "Иванов А.Е. МР2 10", "Иванов А.Е. МР2 10", "Иванов А.Е. МР2 10", "Иванов А.Е. МР2 10",
            "Иванов А.Е. МР2 10"};*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_score_for_student);

        listStudent = (ListView) findViewById(R.id.lv_for_student);

        RetrofitClient retrofitClient = new RetrofitClient();

        retrofitClient.getStudents(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, final Response<List<Student>> response) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initList(response.body());
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Toast.makeText(StudentScoreForStudent.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void initList(List<Student> students){

        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student student, Student t1) {
                if(student.getScore() > t1.getScore())
                    return -1;
                else if(student.getScore() < t1.getScore())
                    return 1;
                else
                    return 0;
            }
        });

        StudentAdapter studentAdapter = new StudentAdapter(StudentScoreForStudent.this, (ArrayList<Student>) students);

        listStudent.setAdapter(studentAdapter);

    }

}
