package com.thiru.telstratest.component

import com.thiru.telstratest.App
import com.thiru.telstratest.api.Service
import com.thiru.telstratest.module.NetworkModule
import com.thiru.telstratest.viewmodel.MainViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Thirumoorthy on 24/11/2018.
 */
@Singleton
@Component(modules = arrayOf(NetworkModule::class))
interface NetworkComponent {
    fun inject(app: App)
    fun inject(service: Service)
    fun inject(listViewModel: MainViewModel)
}