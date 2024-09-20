package com.example.skpnewsmvvm.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.skpnewsmvvm.AdapterNews.News;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {
    public Repo repo = new Repo();

    public LiveData<ArrayList<News>> fetchData() {
        MutableLiveData<ArrayList<News>> mutableLiveData = new MutableLiveData<>();
        repo.getNews().observeForever(news -> {
            mutableLiveData.setValue(news);
        });

        return mutableLiveData;
    }
}