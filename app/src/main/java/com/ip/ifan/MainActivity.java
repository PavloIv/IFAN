package com.ip.ifan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    private EditText userNumber;
    private Button getFact;
    private Button getRandomNumberFact;
    private String userStoreString;
    private String userStoreString2;
    private String userStoreString3;
    private String userStoreString4;
    private String userStoreString5;
    private Button userStore;
    private Button userStore2;
    private Button userStore3;
    private Button userStore4;
    private Button userStore5;
    private RoomWithRxJavaViewModel viewModel;

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
                    userStoreString = facts.get(facts.size()-1).getFact();
                    userStoreString2 = facts.get(facts.size()-2).getFact();
                    userStoreString3 = facts.get(facts.size()-3).getFact();
                    userStoreString4 = facts.get(facts.size()-4).getFact();
                    userStoreString5 = facts.get(facts.size()-5).getFact();
                    userStore.setText(userStoreString.substring(0,28) + "...");
                    userStore2.setText(userStoreString2.substring(0,28) + "...");
                    userStore3.setText(userStoreString3.substring(0,28) + "...");
                    userStore4.setText(userStoreString4.substring(0,28) + "...");
                    userStore5.setText(userStoreString5.substring(0,28) + "...");
                }, e -> System.out.println("roomWithRx" + e.getMessage()));

        getFact.setOnClickListener(view -> {
            Context context = MainActivity.this;
            Class<ResponseToUserActivity> destActivity = ResponseToUserActivity.class;
            String number = userNumber.getText().toString();
            Intent intent = new Intent(context, destActivity);
            intent.putExtra(Intent.EXTRA_TEXT, number);
            startActivity(intent);

        });

        getRandomNumberFact.setOnClickListener(view -> {
            Intent intent = new Intent(".ResponseToUserActivity");
            startActivity(intent);

        });

        userStore.setOnClickListener(view ->{
            Context context = MainActivity.this;
            Class<StoreFactsActivity> destActivity = StoreFactsActivity.class;
            Intent intent = new Intent(context, destActivity);
            intent.putExtra(Intent.EXTRA_TEXT, userStoreString);
            startActivity(intent);
        });
        userStore2.setOnClickListener(view ->{
            Context context = MainActivity.this;
            Class<StoreFactsActivity> destActivity = StoreFactsActivity.class;
            Intent intent = new Intent(context, destActivity);
            intent.putExtra(Intent.EXTRA_TEXT, userStoreString2);
            startActivity(intent);
        });
        userStore3.setOnClickListener(view ->{
            Context context = MainActivity.this;
            Class<StoreFactsActivity> destActivity = StoreFactsActivity.class;
            Intent intent = new Intent(context, destActivity);
            intent.putExtra(Intent.EXTRA_TEXT, userStoreString3);
            startActivity(intent);
        });
        userStore4.setOnClickListener(view ->{
            Context context = MainActivity.this;
            Class<StoreFactsActivity> destActivity = StoreFactsActivity.class;
            Intent intent = new Intent(context, destActivity);
            intent.putExtra(Intent.EXTRA_TEXT, userStoreString4);
            startActivity(intent);
        });
        userStore5.setOnClickListener(view ->{
            Context context = MainActivity.this;
            Class<StoreFactsActivity> destActivity = StoreFactsActivity.class;
            Intent intent = new Intent(context, destActivity);
            intent.putExtra(Intent.EXTRA_TEXT, userStoreString5);
            startActivity(intent);
        });
    }
}
