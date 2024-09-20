package com.example.skpnewsmvvm.ui.category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CategoryViewModel extends ViewModel {

    private MutableLiveData<String> category = new MutableLiveData<>();

    public void setCategory(String category) {
        this.category.setValue(category);
    }

    public MutableLiveData<String> getCategory() {
        return category;
    }

}