package com.swivel.sample_feature.ui.post

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.databinding.library.baseAdapters.BR
import timber.log.Timber

class PostFormData : BaseObservable(){

    private val propertyChangeRegistry = PropertyChangeRegistry()

    var firstName : String? = ""
        @Bindable get
        set(value) {
            Timber.i("YD -> Post Form Data firstName value received $value")
            field = value
            propertyChangeRegistry.notifyChange(this, BR.firstName)
        }

    var lastName : String? = ""
    @Bindable get
    set(value) {
        Timber.i("YD -> Post Form Data lastName value received $value")
        field = value
        propertyChangeRegistry.notifyChange(this,BR.lastName)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        propertyChangeRegistry.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        propertyChangeRegistry.remove(callback)
    }
}