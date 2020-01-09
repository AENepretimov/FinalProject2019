 package com.example.studentrating;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


//Изменение рейтинга студента

 public class AddScore extends AppCompatActivity {

    private TextView tv_name, tv_group;
    private EditText et_add_score;
    private Button btn_add_score;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_score);
        flag = true;

        tv_name = (TextView) findViewById(R.id.t_student_name);
        tv_group = (TextView) findViewById(R.id.t_student_group);

        final Student student = new Student(Integer.parseInt(getIntent().getStringExtra("id")),
                getIntent().getStringExtra("name"),
                getIntent().getStringExtra("group"),
                Integer.parseInt(getIntent().getStringExtra("score")));

        tv_name.setText(student.getName());
        tv_group.setText(student.getGroup());

        btn_add_score = (Button) findViewById(R.id.btn_add_score);

        btn_add_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag) {
                    et_add_score = (EditText) findViewById(R.id.et_add_score);
                    Intent i = new Intent();
                    if(et_add_score.getText().toString().length() != 0) {
                        i.putExtra("addScore", String.valueOf(Integer.parseInt(et_add_score.getText().toString()) + student.getScore()));
                        i.putExtra("name", student.getName());
                        i.putExtra("group", student.getGroup());
                        i.putExtra("id", String.valueOf(student.getId()));

                    }else{
                        i.putExtra("addScore", String.valueOf(student.getScore()));
                        i.putExtra("name", student.getName());
                        i.putExtra("group", student.getGroup());
                        i.putExtra("id", String.valueOf(student.getId()));
                    }
                    setResult(RESULT_OK, i);
                    flag = false;
                    finish();
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
