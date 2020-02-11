package com.swivel.news.base.di.builder.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Provider

/**
 * @author Yohan Dushmantha
 * @class ViewModelFactory
 *
 * MainViewModel Factory which is responsible for providing view models to injecting through dagger
 */
@Suppress("UNCHECKED CAST")
class ViewModelFactory @Inject constructor(private val viewModelsMap : Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) :
ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val creator = viewModelsMap[modelClass] ?:
                viewModelsMap.asIterable().firstOrNull{
                    modelClass.isAssignableFrom(it.key)
                }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        return try {
            creator.get() as T
        } catch (e : Exception){
            throw RuntimeException(e)
        }
    }
}