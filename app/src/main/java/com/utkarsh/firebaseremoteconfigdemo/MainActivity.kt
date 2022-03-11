package com.utkarsh.firebaseremoteconfigdemo

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.utkarsh.firebaseremoteconfigdemo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var languageCode = ""
    private var firebaseAnalytics: FirebaseAnalytics? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
    }

    private fun init() {

        Glide.with(this).load(FirebaseRemoteConfig.getInstance().getString(BANNER_URL)).into(binding.ivBanner)

        binding.btnHindi.isVisible = FirebaseRemoteConfig.getInstance().getBoolean(SHOW_HINDI)

        binding.btnEnglish.setOnClickListener {
            setAppLanguage(UserProperties.ServerLanguagesIDS.ENGLISH, object : FirebaseSuccessListener {
                override fun onFirebaseFetchSuccess() {
                    startActivity(Intent(this@MainActivity, Language::class.java))
                }
            })
        }

        binding.btnHindi.setOnClickListener {
            setAppLanguage(UserProperties.ServerLanguagesIDS.HINDI, object : FirebaseSuccessListener {
                override fun onFirebaseFetchSuccess() {
                    startActivity(Intent(this@MainActivity, Language::class.java))
                }
            })
        }

        binding.btnGujarati.setOnClickListener {
            setAppLanguage(UserProperties.ServerLanguagesIDS.GUJARATI, object : FirebaseSuccessListener {
                override fun onFirebaseFetchSuccess() {
                    startActivity(Intent(this@MainActivity, Language::class.java))
                }
            })
        }

        binding.btnTables.setOnClickListener {
            setAppLanguage(UserProperties.ServerLanguagesIDS.GUJARATI, object : FirebaseSuccessListener {
                override fun onFirebaseFetchSuccess() {
                    startActivity(Intent(this@MainActivity, PrintTable::class.java))
                }
            })
        }
    }

    private fun setAppLanguage(languageID: String?, firebaseFetchListener: FirebaseSuccessListener) {
        FirebaseApp.initializeApp(this)
        FirebaseRemoteConfig.getInstance().apply {

            val configSettings = FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(0)
                .build()

            setConfigSettingsAsync(configSettings)
            setDefaultsAsync(R.xml.remote_config_defaults)

            fetchAndActivate().addOnCompleteListener {
                firebaseFetchListener.onFirebaseFetchSuccess()
            }
        }

        FirebaseAnalytics.getInstance(this).apply {
            //setUserProperty(UserProperties.OS, UserProperties.PLATFORM)
            if (TextUtils.isEmpty(languageID)) {
                languageCode = UserProperties.Languages.ENGLISH
                firebaseAnalytics?.setUserProperty(UserProperties.LANGUAGE, UserProperties.Languages.ENGLISH)
            } else {
                when (languageID) {
                    UserProperties.ServerLanguagesIDS.ENGLISH -> {
                        languageCode = UserProperties.Languages.ENGLISH
                        setUserProperty(UserProperties.LANGUAGE, UserProperties.Languages.ENGLISH)
                    }
                    UserProperties.ServerLanguagesIDS.HINDI -> {
                        languageCode = UserProperties.Languages.HINDI
                        setUserProperty(UserProperties.LANGUAGE, UserProperties.Languages.HINDI)
                    }
                    UserProperties.ServerLanguagesIDS.GUJARATI -> {
                        languageCode = UserProperties.Languages.GUJARATI
                        setUserProperty(UserProperties.LANGUAGE, UserProperties.Languages.GUJARATI)
                    }
                    else -> {
                        languageCode = UserProperties.Languages.ENGLISH
                        setUserProperty(UserProperties.LANGUAGE, UserProperties.Languages.ENGLISH)
                    }
                }
            }
        }
    }
}