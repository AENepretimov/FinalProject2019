package com.example.studentrating;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    private Button btn_student, btn_teacher;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_student = (Button) findViewById(R.id.btn_student);
        btn_teacher = (Button) findViewById(R.id.btn_teach);

        btn_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag){
                    Intent i = new Intent(Login.this, StudentScoreForStudent.class);
                    startActivity(i);
                    flag = false;
                }
            }
        });

        btn_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag){
                    Intent i = new Intent(Login.this, TeacherLogin.class);
                    startActivity(i);
                    flag = false;
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        flag = true;

    }
}
