package com.example.studentrating;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TeacherLogin extends AppCompatActivity {

    public static final String TEACHER_PASSWORD = "753159";

    private Button btn_teach_log;
    private EditText et_teach_pass;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        btn_teach_log = (Button) findViewById(R.id.btn_teach_log);

        btn_teach_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag){
                    et_teach_pass = (EditText) findViewById(R.id.et_pass);

                    if(et_teach_pass.getText().toString().equals(TEACHER_PASSWORD)){
                        et_teach_pass.setText("");
                        Intent i = new Intent(TeacherLogin.this, StudentScoreForTeacher.class);
                        flag = false;
                        startActivity(i);
                    }else {
                        et_teach_pass.setText("");
                        Toast.makeText(TeacherLogin.this, "Wrong password", Toast.LENGTH_SHORT).show();
                    }

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
