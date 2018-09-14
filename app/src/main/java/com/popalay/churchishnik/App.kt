package com.popalay.churchishnik

import android.support.multidex.MultiDexApplication
import com.popalay.churchishnik.util.Api

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        Api.init(this)
    }
}