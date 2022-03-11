package com.utkarsh.firebaseremoteconfigdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.utkarsh.firebaseremoteconfigdemo.databinding.ActivityPrintTableBinding

class PrintTable : AppCompatActivity() {
    private lateinit var binding: ActivityPrintTableBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_print_table)
        init(FirebaseRemoteConfig.getInstance().getDouble(PRINT_TABLES))
    }

    private fun init(number: Double) {
        val builder =  StringBuilder()

        for (i in 1..10){
            val res: Double = number * i
            builder.append("${number.toInt()} * $i = ${res.toInt()}\n");
        }
        binding.tvTable.text = builder.toString()
    }
}