package com.swivel.registration.ui.user_registration

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.databinding.library.baseAdapters.BR

/**
 * @author Yohan Dushmantha
 * @class UserRegistrationFormData
 *
 * contains form data of user registration page
 */
class UserRegistrationFormData : BaseObservable() {

    private val propertyChangeRegistry = PropertyChangeRegistry()

    var firstName : String = ""
        @Bindable get
        set(value) {
            field = value
            propertyChangeRegistry.notifyChange(this, BR.firstName)
        }

    var lastName : String = ""
        @Bindable get
        set(value) {
            field = value
            propertyChangeRegistry.notifyChange(this, BR.lastName)
        }

    var email : String = ""
        @Bindable get
        set(value) {
            field = value
            propertyChangeRegistry.notifyChange(this, BR.email)
        }

    var password : String = ""
        @Bindable get
        set(value) {
            field = value
            propertyChangeRegistry.notifyChange(this, BR.password)
        }

    var retypePassword : String = ""
        @Bindable get
        set(value) {
            field = value
            propertyChangeRegistry.notifyChange(this, BR.retypePassword)
        }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        propertyChangeRegistry.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        propertyChangeRegistry.remove(callback)
    }
}