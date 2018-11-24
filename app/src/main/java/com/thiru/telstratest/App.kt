package com.thiru.telstratest

import android.app.Application
import android.content.Context
import com.thiru.telstratest.component.DaggerNetworkComponent
import com.thiru.telstratest.component.NetworkComponent
import com.thiru.telstratest.module.NetworkModule

/**
 * Created by Thirumoorthy on 24/11/2018.
 */
class App : Application() {

    private lateinit var networkComponent: NetworkComponent

    companion object {
        fun getApp(context: Context):App{
            return (context.applicationContext) as App
        }
    }
    override fun onCreate() {
        super.onCreate()
        initDataComponent()
        networkComponent.inject(this)
    }

    private fun initDataComponent() {
        networkComponent = DaggerNetworkComponent.builder()
                .networkModule(NetworkModule("https://dl.dropboxusercontent.com/"))
                .build()
    }

    fun getDataComponent(): NetworkComponent {
        return networkComponent
    }
}