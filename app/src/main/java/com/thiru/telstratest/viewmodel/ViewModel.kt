package com.thiru.telstratest.viewmodel

import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Thirumoorthy on 24/11/2018.
 */

//Base viewmodel class.
open class ViewModel {
    val compositeDisposable = CompositeDisposable()

    fun onDestroy() {
        if(!compositeDisposable.isDisposed){
            compositeDisposable.dispose()
        }
    }
}