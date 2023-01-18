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

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    private EditText userNumber;
    private Button getFact;
    private Button getRandomNumberFact;
    private TextView userStore;
    private TextView userStore2;
    private TextView userStore3;
    private TextView userStore4;
    private TextView userStore5;
    private RoomWithRxJavaViewModel viewModel;
    private final CompositeDisposable mDisposable = new CompositeDisposable();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNumber = findViewById(R.id.userNumber);
        getFact = findViewById(R.id.getFact);
        getRandomNumberFact = findViewById(R.id.getRandomNumberFact);
        userStore = findViewById(R.id.userStore);
        userStore2 = findViewById(R.id.userStore2);
        userStore3 = findViewById(R.id.userStore3);
        userStore4 = findViewById(R.id.userStore4);
        userStore5 = findViewById(R.id.userStore5);
        viewModel = new RoomWithRxJavaViewModel(this.getApplication());
        viewModel.getList().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(facts -> {
                    userStore.setText(facts.get(facts.size() - 1).getFact());
                    userStore2.setText(facts.get(facts.size() - 2).getFact());
                    userStore3.setText(facts.get(facts.size() - 3).getFact());
                    userStore4.setText(facts.get(facts.size() - 4).getFact());
                    userStore5.setText(facts.get(facts.size() - 5).getFact());
                }, e -> System.out.println("roomWithRx" + e.getMessage()));

        getFact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = MainActivity.this;
                Class destActivity = ResponseToUserActivity.class;
                String number = userNumber.getText().toString();
                Intent intent = new Intent(context, destActivity);
                intent.putExtra(Intent.EXTRA_TEXT, number);
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
