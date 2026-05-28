package com.example.testgsmservices

import android.app.Application
import android.os.DeadObjectException
import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics

class App: Application() {

    private val defaultHandler = Thread.getDefaultUncaughtExceptionHandler()

    override fun onCreate() {
        super.onCreate()

        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            if (isGmsCrash(throwable)) {
                Log.w("GMS_GUARD", "Crash de GMS interceptado en '${thread.name}', ignorando", throwable)
                try {
                    FirebaseCrashlytics.getInstance().recordException(throwable)
                } catch (_: Exception) {}
                return@setDefaultUncaughtExceptionHandler
            }
            defaultHandler?.uncaughtException(thread, throwable)
        }
    }

    private fun isGmsCrash(t: Throwable): Boolean {
        if (t is DeadObjectException) {
            return t.stackTrace.any { it.className.startsWith("com.google.android.gms") }
        }

        return false
    }
}