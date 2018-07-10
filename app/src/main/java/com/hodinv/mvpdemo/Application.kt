package com.hodinv.mvpdemo

import android.app.Application
import com.hodinv.mvpdemo.model.Repository

class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        Repository.initialize(this)
    }
}
