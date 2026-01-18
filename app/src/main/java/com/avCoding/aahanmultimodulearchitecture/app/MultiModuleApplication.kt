package com.avCoding.aahanmultimodulearchitecture.app

import android.app.Application
import com.avCoding.aahanmultimodulearchitecture.BuildConfig
import com.avcoding.core_local.session.SessionLocalDataSource
import com.avcoding.core_logging.AppLogger
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class MultiModuleApplication : Application()  {

    @Inject
    lateinit var sessionLocalDataSource: SessionLocalDataSource

    override fun onCreate() {
        super.onCreate()
        sessionLocalDataSource.initSync()

        setUpLogger()
    }

    private fun setUpLogger() {
        AppLogger.init(this) {
            isLoggingEnabled = true
            retentionDays = 7
            enableConsoleLogs = BuildConfig.DEBUG
        }
    }

}