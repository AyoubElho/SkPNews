package com.example.skpnewsmvvm.ui.detaill;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.skpnewsmvvm.databinding.FragmentDetaillBinding;
import com.google.android.material.appbar.MaterialToolbar;

public class DetaillFragment extends Fragment {
    private FragmentDetaillBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (binding == null) {
            binding = FragmentDetaillBinding.inflate(inflater, container, false);
        }

        Bundle bundle = getArguments();
        if (bundle != null) {
            Glide.with(getActivity()).load(Uri.parse(bundle.getString("image"))).into(binding.imageView);
            binding.tvTitle.setText(bundle.getString("title"));
            binding.tvDate.setText(bundle.getString("date"));
            binding.tvBody.setText(bundle.getString("desc"));
        }

        return binding.getRoot();
    }
}