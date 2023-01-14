package com.ip.ifan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Connection;

import java.util.Arrays;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class ResponseToUserActivity extends AppCompatActivity {
    private TextView responseToUser;
    private TextView userNumberView;
    private Button toMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response_to_user);

        responseToUser = findViewById(R.id.responseToUser);
        userNumberView = findViewById(R.id.userNumberViev);

        toMain = findViewById(R.id.toMaim);

        Intent intentStartActivity = getIntent();
        if (intentStartActivity.hasExtra(Intent.EXTRA_TEXT)){
            String userNumber = intentStartActivity.getStringExtra(Intent.EXTRA_TEXT);
            GetFacts.getFactsAboutNumber(userNumber)
                    .subscribe(new Observer<Connection.Response>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(Connection.@NonNull Response response) {
                            userNumberView.setText(userNumber);
                            responseToUser.setText(response.body());
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            responseToUser.setText("We have problem." +
                                    "Maybe your don't write your number? " +
                                    "Try again");
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }else {
            GetFacts.getFactsAboutNumberRandomNumber().subscribe(new Observer<Connection.Response>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                public void onNext(Connection.@NonNull Response response) {
                    userNumberView.setText(Arrays
                            .stream(response.body().trim().split(" "))
                            .findFirst().get()
                            .toString());
                    responseToUser.setText(response.body());
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    responseToUser.setText("We have problem.Try again");
                }

                @Override
                public void onComplete() {

                }
            });
        }

        toMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = ResponseToUserActivity.this;
                Class destActivity = MainActivity.class;
                Intent intent = new Intent(context,destActivity);
                startActivity(intent);
            }
        });

    }
}