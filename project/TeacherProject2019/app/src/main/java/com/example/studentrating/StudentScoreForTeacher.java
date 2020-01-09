package com.example.studentrating;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentScoreForTeacher extends AppCompatActivity {

    private ListView listStudent;
    private Integer number_of_student;
    private ArrayAdapter<String> studentAdapter;
    private RetrofitClient retrofitClient = new RetrofitClient();

    //test string
    /*private String[] myArr = {"Иванов А.Е. МР2 10", "Петров А.Е. МР1 10", "Сидоров А.Е. МР3 10",
            "Иванов А.Е. МР2 10", "Иванов А.Е. МР2 10", "Иванов А.Е. МР2 10", "Иванов А.Е. МР2 10",
            "Иванов А.Е. МР2 10", "Иванов А.Е. МР2 10", "Иванов А.Е. МР2 10", "Иванов А.Е. МР2 10",
            "Иванов А.Е. МР2 10"};*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_score_for_teacher);

        listStudent = (ListView) findViewById(R.id.lv_for_teacher);

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
                Toast.makeText(StudentScoreForTeacher.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        listStudent.setOnItemClickListener(new ListView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(StudentScoreForTeacher.this, AddScore.class);
                intent.putExtra("name",
                        ((Student)adapterView.getItemAtPosition(i)).getName());
                intent.putExtra("group",
                        ((Student)adapterView.getItemAtPosition(i)).getGroup());
                intent.putExtra("id",
                        ((Student)adapterView.getItemAtPosition(i)).getId().toString());
                intent.putExtra("score",
                        ((Student)adapterView.getItemAtPosition(i)).getScore().toString());


                number_of_student = i;
                startActivityForResult(intent, 1);
                //Toast.makeText(StudentScoreForTeacher.this, adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(StudentScoreForTeacher.this);
                builder.setTitle("Add student\nExample: Ivanov I.I. MP1");
                final EditText input = new EditText(StudentScoreForTeacher.this);
                builder.setView(input);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String str = input.getText().toString();

                        if(str.split(" ").length == 3 &&
                                !str.split(" ")[0].equals("") &&
                                !str.split(" ")[1].equals("") &&
                                !str.split(" ")[2].equals("")) {
                            Student student = new Student(null, str.split(" ")[0] + " " + str.split(" ")[1], str.split(" ")[2], 0);

                            retrofitClient.addStudent(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    Toast.makeText(StudentScoreForTeacher.this, "Okey", Toast.LENGTH_SHORT).show();

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
                                            Toast.makeText(StudentScoreForTeacher.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    Toast.makeText(StudentScoreForTeacher.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }, student);

                        }else{
                            Toast.makeText(StudentScoreForTeacher.this, "Wrong format!", Toast.LENGTH_SHORT).show();
                        }

                        /*List<String> list = new ArrayList<>(Arrays.asList(myArr));
                        list.add();
                        myArr = (String[]) list.toArray(new String[0]);

                        studentAdapter = new ArrayAdapter<>(StudentScoreForTeacher.this,
                                android.R.layout.simple_list_item_1, myArr);
                        listStudent.setAdapter(studentAdapter);*/
                    }
                });
                builder.show();
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

        StudentAdapter studentAdapter = new StudentAdapter(StudentScoreForTeacher.this, (ArrayList<Student>) students);


        listStudent.setAdapter(studentAdapter);

       /* List<String> tmpList = new ArrayList<>();

        for (Student student : students) {
            tmpList.add(student.getName() + " " + student.getGroup() + " " + student.getScore());
        }

        ArrayAdapter<String> studentAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, tmpList);

        listStudent.setAdapter(studentAdapter);
        Log.d("StudentList", tmpList.toString());*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(data != null) {
            if (requestCode == 1) {
                //Toast.makeText(StudentScoreForTeacher.this, data.getStringExtra("addScore"), Toast.LENGTH_LONG).show();

                Student student = new Student(Integer.parseInt(data.getStringExtra("id")),
                        data.getStringExtra("name"),
                        data.getStringExtra("group"),
                        Integer.parseInt(data.getStringExtra("addScore")));

                retrofitClient.addScore(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(StudentScoreForTeacher.this, "Okey", Toast.LENGTH_SHORT).show();

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
                                Toast.makeText(StudentScoreForTeacher.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(StudentScoreForTeacher.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }, student);




                /*myArr[number_of_student] = myArr[number_of_student].split(" ")[0] + " " +
                        myArr[number_of_student].split(" ")[1] + " " +
                        myArr[number_of_student].split(" ")[2] + " " +
                        String.valueOf(Integer.parseInt(myArr[number_of_student].split(" ")[3]) + Integer.parseInt(data.getStringExtra("addScore")));
                studentAdapter.notifyDataSetInvalidated();*/
            }
        }
    }
}
