package xyz.mattyb.khance.test.core

import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.TYPE, AnnotationTarget.CLASS)
annotation class ChancePopulate(val value: KClass<*>)