package com.swivel.login.ui.verify_password

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.databinding.library.baseAdapters.BR

class VerifyPasswordFormData : BaseObservable(){

    private val propertyChangeRegistry = PropertyChangeRegistry()

    var password : String = ""
        @Bindable get
        set(value) {
            field = value
            propertyChangeRegistry.notifyChange(this, BR.password)
        }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        propertyChangeRegistry.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        propertyChangeRegistry.remove(callback)
    }
}