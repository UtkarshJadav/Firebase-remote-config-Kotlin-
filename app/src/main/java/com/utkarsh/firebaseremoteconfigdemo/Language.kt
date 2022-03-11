package com.utkarsh.firebaseremoteconfigdemo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.DataBindingUtil
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.utkarsh.firebaseremoteconfigdemo.databinding.ActivityLanguageBinding
import com.utkarsh.firebaseremoteconfigdemo.databinding.ActivityMainBinding

class Language : AppCompatActivity() {

    private lateinit var binding: ActivityLanguageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_language)
        binding.tvDemo.setTextColor(Color.parseColor(FirebaseRemoteConfig.getInstance().getString(COLOR_APPBAR)))
        binding.tvMessage.setTextColor(Color.parseColor(FirebaseRemoteConfig.getInstance().getString(COLOR_APPBAR)))
    }
}