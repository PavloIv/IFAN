package com.ip.ifan;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.ip.ifan.db.DatabaseFacts;
import com.ip.ifan.db.Facts;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class RoomWithRxJavaViewModel extends AndroidViewModel {
    private final DatabaseFacts appDatabase;

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

    }
}
