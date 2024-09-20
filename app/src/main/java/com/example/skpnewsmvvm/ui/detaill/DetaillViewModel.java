package com.example.skpnewsmvvm.ui.detaill;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DetaillViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DetaillViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}