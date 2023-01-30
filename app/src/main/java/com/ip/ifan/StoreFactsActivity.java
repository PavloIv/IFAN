package com.ip.ifan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class StoreFactsActivity extends AppCompatActivity {
    private TextView numberFromFactView;
    private TextView factStoreView;
    private Button toMain;
    private String fact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_facts);

        numberFromFactView = findViewById(R.id.numberFromFactView);
        factStoreView = findViewById(R.id.factStoreView);
        toMain = findViewById(R.id.toMaim);

        Intent intentStartActivity = getIntent();
        if (intentStartActivity.hasExtra(Intent.EXTRA_TEXT)) {
            fact = intentStartActivity.getStringExtra(Intent.EXTRA_TEXT);
            numberFromFactView.setText(Arrays.stream(fact.trim().split(" ")).findFirst().get());
            factStoreView.setText(fact);
        }

        toMain.setOnClickListener(view -> {
            Context context = StoreFactsActivity.this;
            Class<MainActivity> destActivity = MainActivity.class;
            Intent intent = new Intent(context, destActivity);
            startActivity(intent);
        });

        }
}
