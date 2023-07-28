package com.example.rickandmorty

import android.app.Application
import com.example.rickandmorty.dependency.AppComponent
import com.example.rickandmorty.dependency.AppModule
import com.example.rickandmorty.dependency.DaggerAppComponent

class RickAndMortyApplication : Application() {
   lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule()).build()
    }
}




