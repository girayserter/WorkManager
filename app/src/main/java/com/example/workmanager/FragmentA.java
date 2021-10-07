package com.example.workmanager;

import android.database.DatabaseUtils;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.workmanager.databinding.FragmentABinding;

import java.util.concurrent.TimeUnit;


public class FragmentA extends Fragment {

    Prefs prefs;
    public FragmentA() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        prefs=new Prefs(getActivity().getApplicationContext());
        com.example.workmanager.databinding.FragmentABinding binding=
                DataBindingUtil.inflate(inflater,R.layout.fragment_a,container,false);
        binding.button.setOnClickListener(v -> {
            NavDirections action = FragmentADirections.actionFragmentAToFragmentB2(
                    binding.textView.getText().toString());
            Navigation.findNavController(container).navigate(action);
        });

        WorkRequest request= new PeriodicWorkRequest.Builder(
                Worker.class,15, TimeUnit.MINUTES).build();
        WorkManager.getInstance(getContext()).enqueue(request);
        WorkManager.getInstance(getContext()).getWorkInfoByIdLiveData(
                request.getId()).observe(getActivity(), new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                if(workInfo!=null&&workInfo.getState()== WorkInfo.State.SUCCEEDED){
                    Toast.makeText(getContext(),"başarılı",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Handler timeHandler = new Handler();
        timeHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.txtTime.setText(prefs.getTime());
                timeHandler.postDelayed(this,1000);
            }
        }, 0);//0 secs delay
        return binding.getRoot();
    }
}