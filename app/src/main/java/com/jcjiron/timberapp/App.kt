package com.jcjiron.timberapp

import android.app.Application
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.crashlytics.ktx.setCustomKeys
import com.google.firebase.ktx.BuildConfig
import com.google.firebase.ktx.Firebase
import timber.log.Timber


class App: Application() {

    override fun onCreate() {
        super.onCreate()

        Firebase.crashlytics.setCrashlyticsCollectionEnabled(true)
        FirebaseAnalytics.getInstance(this)

        //Debug and crashlytics tree
        Timber.plant(if (BuildConfig.DEBUG) Timber.DebugTree() else CrashReportingTree())


    }

    private class CrashReportingTree : Timber.Tree() {
        protected override fun log(
            priority: Int,
            tag: String?,
            message: String,
            throwable: Throwable?
        ) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return
            }
            val t = throwable ?: Exception(message)

            // Crashlytics
            val crashlytics = Firebase.crashlytics
                crashlytics.setCustomKeys {
                key(CRASHLYTICS_KEY_PRIORITY, priority)
                key(CRASHLYTICS_KEY_TAG, tag.orEmpty())
                    key(CRASHLYTICS_KEY_MESSAGE, message)
            }

            // Firebase Crash Reporting
            crashlytics.log("$priority $tag $message")
            crashlytics.recordException(t)
        }

        companion object {
            private const val CRASHLYTICS_KEY_PRIORITY = "priority"
            private const val CRASHLYTICS_KEY_TAG = "tag"
            private const val CRASHLYTICS_KEY_MESSAGE = "message"
        }
    }
}