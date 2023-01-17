package com.ip.ifan;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.ip.ifan.db.DatabaseFacts;
import com.ip.ifan.db.Facts;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RoomWithRxJavaViewModel extends AndroidViewModel {
    private DatabaseFacts appDatabase;

    public RoomWithRxJavaViewModel(@NonNull Application application){
        super(application);
        appDatabase = DatabaseFacts.getDatabase(application);
    }

    public Flowable<List<Facts>> getList() {
        return appDatabase.factsDao().getFacts();
    }

    public Completable addData(String fact){
        return Completable.fromAction(() ->
        {
            Facts facts = new Facts();
            facts.setFact(fact);
            appDatabase.factsDao().saveFact(facts);
        });
//        appDatabase = DatabaseFacts.getDatabase(this.getApplication().getApplicationContext());
//        Observable<Boolean> addFact = Observable.fromCallable(() -> {
//            Facts facts = new Facts();
//            facts.setFact(fact);
//            appDatabase.factsDao().saveFact(facts);
//            return true;
//        }).subscribeOn(Schedulers.io());

    }
}
