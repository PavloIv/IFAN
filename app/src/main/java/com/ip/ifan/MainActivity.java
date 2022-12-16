package com.ip.ifan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private EditText userNumber;
    private Button getFact;
    private Button getRandomNumberFact;
    private TextView userStore;
    private String urlFactUserNumber;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNumber = findViewById(R.id.userNumber);
        getFact = findViewById(R.id.getFact);
        getRandomNumberFact = findViewById(R.id.getRandomNumberFact);
        userStore = findViewById(R.id.userStore);


        getFact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = MainActivity.this;
                Class destActivity = ResponseToUserActivity.class;
                    String number = userNumber.getText().toString();
                    urlFactUserNumber = ("http://numbersapi.com/" + number + "/trivia");
                    Intent intent = new Intent(context,destActivity);
                    intent.putExtra(Intent.EXTRA_TEXT,urlFactUserNumber);
                    startActivity(intent);

            }
        });

        getRandomNumberFact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlFactRandomNumber = "http://numbersapi.com/random/math";
                Intent intent = new Intent(".ResponseToUserActivity");
                intent.putExtra(Intent.EXTRA_TEXT,urlFactRandomNumber);
                startActivity(intent);

            }
        });
    }

}