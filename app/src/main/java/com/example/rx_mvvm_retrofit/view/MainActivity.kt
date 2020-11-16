package com.example.rx_mvvm_retrofit.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.rx_mvvm_retrofit.R
import com.example.rx_mvvm_retrofit.databinding.ActivityMainBinding
import com.example.rx_mvvm_retrofit.viewmodal.MainViewModal

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModal = MainViewModal(this)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModal = viewModal
        this.lifecycle.addObserver(viewModal)
    }
}