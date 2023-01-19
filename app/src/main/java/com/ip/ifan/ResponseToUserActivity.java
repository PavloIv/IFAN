package com.ip.ifan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Connection;

import java.util.Arrays;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ResponseToUserActivity extends AppCompatActivity {
    private TextView responseToUser;
    private TextView userNumberView;
    private Button toMain;
    private RoomWithRxJavaViewModel viewModel;
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    private String numberFact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response_to_user);

        responseToUser = findViewById(R.id.responseToUser);
        userNumberView = findViewById(R.id.userNumberView);
        toMain = findViewById(R.id.toMaim);
        viewModel = new RoomWithRxJavaViewModel(this.getApplication());

        Intent intentStartActivity = getIntent();
        if (intentStartActivity.hasExtra(Intent.EXTRA_TEXT)) {
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
                            numberFact = response.body();
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
        } else {
            GetFacts.getFactsAboutNumberRandomNumber().subscribe(new Observer<Connection.Response>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                }

                @Override
                public void onNext(Connection.@NonNull Response response) {
                    userNumberView.setText(Arrays
                            .stream(response.body().trim().split(" "))
                            .findFirst().get());
                    responseToUser.setText(response.body());
                    numberFact = response.body();
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

        toMain.setOnClickListener(view -> {
            if (numberFact != null) {
                mDisposable.add(viewModel.addData(numberFact).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe());
            }
            Context context = ResponseToUserActivity.this;
            Class<MainActivity> destActivity = MainActivity.class;
            Intent intent = new Intent(context, destActivity);
            startActivity(intent);
        });
    }
}