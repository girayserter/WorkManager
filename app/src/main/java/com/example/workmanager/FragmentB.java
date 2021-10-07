package com.example.workmanager;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.workmanager.databinding.FragmentBBinding;

public class FragmentB extends Fragment {

    public FragmentB() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle=getArguments();
        FragmentBArgs args=FragmentBArgs.fromBundle(bundle);
        FragmentBBinding binding= DataBindingUtil.inflate(inflater,
                R.layout.fragment_b,container,false);
        binding.txtData.setText(args.getTexxxt());
        return binding.getRoot();
    }
}