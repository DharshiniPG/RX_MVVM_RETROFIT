package com.example.rx_mvvm_retrofit.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleObserver;

import android.app.Activity;
import android.os.Bundle;

import com.example.rx_mvvm_retrofit.R;
import com.example.rx_mvvm_retrofit.databinding.ActivityMainBinding;
import com.example.rx_mvvm_retrofit.viewmodal.MainViewModal;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainViewModal viewModal = new MainViewModal(this);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModal(viewModal);

        this.getLifecycle().addObserver(viewModal);
    }
}