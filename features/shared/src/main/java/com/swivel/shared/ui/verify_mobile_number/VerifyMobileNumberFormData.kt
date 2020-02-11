package com.swivel.shared.ui.verify_mobile_number

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.databinding.library.baseAdapters.BR

/**
 * @author Yohan Dushmantha
 * @class VerifyMobileNumberFormData
 */

class VerifyMobileNumberFormData : BaseObservable(){

    private val propertyChangeRegistry = PropertyChangeRegistry()

    var mobileNumber : String = ""
    @Bindable get
    set(value) {
        field = value
        propertyChangeRegistry.notifyChange(this, BR.mobileNumber)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        propertyChangeRegistry.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        propertyChangeRegistry.remove(callback)
    }
}