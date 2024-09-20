package com.example.skpnewsmvvm.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.skpnewsmvvm.databinding.FragmentCategoryBinding;

import java.util.ArrayList;

public class CategoryFragment extends Fragment {
    private ArrayList<Categories> categoriesList;
    private FragmentCategoryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CategoryViewModel notificationsViewModel =
                new ViewModelProvider(this).get(CategoryViewModel.class);
        categoriesList = new ArrayList<>();
        binding = FragmentCategoryBinding.inflate(inflater, container, false);

        categoriesList.add(new Categories("https://images.pexels.com/photos/46798/the-ball-stadion-football-the-pitch-46798.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" , "رياضة"));
        categoriesList.add(new Categories("https://ultratunisia.ultrasawt.com/sites/default/files/styles/img828x427/public/2023-03/%D8%B1%D9%85%D8%B6%D8%A7%D9%86%20%D8%AC%D9%8A%D8%AA%D9%8A.jpg?itok=9tpQCAcH" , "رمضان"));
        categoriesList.add(new Categories("https://madar21.com/wp-content/uploads/2024/03/65e72142266cd-1024x576.png" , "الخدمة العسكرية"));

        AdapterCategories categories = new AdapterCategories(getActivity() , categoriesList);
        binding.recycle.setAdapter(categories);
        binding.recycle.setLayoutManager(new GridLayoutManager(getActivity() , 2));


        return  binding.getRoot();
    }
}