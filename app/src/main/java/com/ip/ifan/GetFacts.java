package com.ip.ifan;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GetFacts {

    private static String url;

    public static Observable<Connection.Response> getFactsAboutNumber(String number) {
        url = "http://numbersapi.com/" + number + "/trivia";
        Observable<Connection.Response> response = Observable.fromCallable(() -> Jsoup.connect(url).execute())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return response;
    }

    public static Observable<Connection.Response> getFactsAboutNumberRandomNumber() {
        url = "http://numbersapi.com/random/math";
        Observable<Connection.Response> response = Observable.fromCallable(() -> Jsoup.connect(url).execute())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return response;
    }
}
