package com.thiru.telstratest.viewmodel

import android.content.Context
import android.text.TextUtils
import com.thiru.telstratest.App
import com.thiru.telstratest.api.Service
import com.thiru.telstratest.model.Row
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import android.net.NetworkInfo
import android.net.ConnectivityManager



/**
 * Created by Thirumoorthy on 24/11/2018.
 */
class MainViewModel(val context: Context) : ViewModel() {
    @Inject
    lateinit var service: Service

    init {
        App.getApp(context).getDataComponent().inject(this)
    }

    fun getCountryData() = service.getCountryData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun filterListItem(mutableList: MutableList<Row>):MutableList<Row>{
        if(mutableList.isNotEmpty())
            return mutableList.filter { isAllDataInRow(it) }.toMutableList()
        else return mutableList
    }

    fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

   private fun isAllDataInRow(row: Row) = !TextUtils.isEmpty(row.title) || !TextUtils.isEmpty(row.description) || !TextUtils.isEmpty(row.imageHref)
}