package com.example.studentrating;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_start;
    private boolean flag = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start = (Button) findViewById(R.id.btn_start);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag) {
                    Intent i = new Intent(MainActivity.this, Login.class);
                    flag = false;
                    startActivity(i);
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
