package com.thiru.telstratest.api

import com.thiru.telstratest.model.CountryData
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Thirumoorthy on 2/19/2018.
 */
class Service @Inject constructor(private val restApi: RestApi) {
    fun getCountryData(): Observable<CountryData>{
        return restApi.getCountryData()
    }
}