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

import org.jsoup.Connection;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;


public class MainActivity extends AppCompatActivity {
    private EditText userNumber;
    private Button getFact;
    private Button getRandomNumberFact;
//    private TextView userStore;
    private String urlFactUserNumber;

    private TextView facts;
    private Button factsButton;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNumber = findViewById(R.id.userNumber);
        getFact = findViewById(R.id.getFact);
        getRandomNumberFact = findViewById(R.id.getRandomNumberFact);
//        userStore = findViewById(R.id.userStore);
        facts = findViewById(R.id.fact);
        factsButton = findViewById(R.id.factButton);


        getFact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = MainActivity.this;
                Class destActivity = ResponseToUserActivity.class;
                    String number = userNumber.getText().toString();
                    Intent intent = new Intent(context,destActivity);
                    intent.putExtra(Intent.EXTRA_TEXT,number);
                    startActivity(intent);

            }
        });

        getRandomNumberFact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(".ResponseToUserActivity");
                startActivity(intent);

            }
        });
    }
}