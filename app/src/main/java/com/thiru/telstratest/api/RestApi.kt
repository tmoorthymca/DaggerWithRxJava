package com.thiru.telstratest.api

import com.thiru.telstratest.model.CountryData
import io.reactivex.Observable
import retrofit2.http.GET


/**
 * Created by Thirumoorthy on 2/19/2018.
 */
interface RestApi {

    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getCountryData(): Observable<CountryData>
}