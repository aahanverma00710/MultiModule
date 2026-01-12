package com.avCoding.aahanmultimodulearchitecture.app

import android.app.Application
import com.avcoding.core_local.session.SessionLocalDataSource
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
    }

}