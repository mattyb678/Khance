package xyz.mattyb.khance.test.core

import kotlin.reflect.KClass


//@Retention(RetentionPolicy.RUNTIME)
//@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.TYPE_PARAMETER, AnnotationTarget.TYPE)
annotation class Hydrate(val clazz: KClass<*>)