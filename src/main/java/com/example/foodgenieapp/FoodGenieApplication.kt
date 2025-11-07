package com.example.foodgenieapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class FoodGenieApplication : Application() {

    @Inject
    lateinit var databaseInitializer: DatabaseInitializer

    override fun onCreate() {
        super.onCreate()
        // Initialize sample data
        databaseInitializer.initializeSampleData()
    }
}