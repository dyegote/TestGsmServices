package com.example.testgsmservices

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.ui.AppBarConfiguration
import com.example.testgsmservices.databinding.ActivityMainBinding
import com.google.android.gms.dynamite.DynamiteModule
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)
        FirebaseApp.initializeApp(this)
        FirebaseApp.initializeApp(this)
        InitSmsListener(this, null).executeAPI()
        InitSmsListener(this, null).executeAPI()
        InitSmsListener(this, null).executeAPI()


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAnalytics.setOnClickListener {
            initFirebase(this)

        }

        binding.buttonMeasureModule.setOnClickListener {
            loadMeasureModule()
        }

        binding.buttonSms.setOnClickListener {
            for(i in 1..1000) {
                InitSmsListener(this, null).executeAPI()
                FirebaseCrashlytics.getInstance().log("NO FATAL")
            }
        }

        binding.buttonExControlada.setOnClickListener{
            try {
                val x = 1/0
            } catch (ex: Exception) {
                Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonExNoControlada.setOnClickListener{
            val str: String? = null
            val x = 1/0
        }



    }



    private fun initFirebase(context: Context) {

        val app = FirebaseApp.initializeApp(context)
        FirebaseCrashlytics.getInstance().setUserId("test")
        val app2 = FirebaseApp.initializeApp(context)
        FirebaseCrashlytics.getInstance().setUserId("test")
        FirebaseApp.initializeApp(context)
        FirebaseApp.initializeApp(context)
        FirebaseApp.initializeApp(context)
        FirebaseApp.initializeApp(context)
        FirebaseApp.initializeApp(context)
        FirebaseApp.initializeApp(context)
        FirebaseApp.initializeApp(context)
        FirebaseApp.initializeApp(context)
    }

    private fun loadMeasureModule() {
        try {
            val module: DynamiteModule = DynamiteModule.load(
                this,
                DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING,
                "com.google.android.gms.measurement.dynamite"
            )
            val moduleContext = module.moduleContext


            val clazz = moduleContext
                .classLoader
                .loadClass("com.google.android.gms.measurement.internal.AppMeasurementDynamiteServicexxx")

            Log.d("Dynamite", "module loaded")
        } catch (e: DynamiteModule.LoadingException) {
            Log.e("Dynamite", "module failed", e)
        }
    }
}