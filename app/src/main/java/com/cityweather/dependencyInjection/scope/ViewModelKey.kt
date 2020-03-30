package com.cityweather.dependencyInjection.scope

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Annotation class for view model key
 *
 * Out keyword is used to get the generic view model in output.
 *
 * @param value The [KClass] of type [ViewModel]
 *
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)