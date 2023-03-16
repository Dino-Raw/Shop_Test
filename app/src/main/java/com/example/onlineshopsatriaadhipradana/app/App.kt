package com.example.onlineshopsatriaadhipradana.app

import android.app.Application
import com.example.onlineshopsatriaadhipradana.di.AppComponent
import com.example.onlineshopsatriaadhipradana.di.AppModule
import com.example.onlineshopsatriaadhipradana.di.DaggerAppComponent

class App: Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(context = this))
            .build()
    }
}