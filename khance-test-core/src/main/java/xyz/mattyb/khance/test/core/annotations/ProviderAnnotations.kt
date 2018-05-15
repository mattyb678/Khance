package xyz.mattyb.khance.test.core.annotations

import xyz.mattyb.khance.enums.Casing

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class ChanceProvider

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class BoolProvider(val likelihood: Int = 50, val value: String = "")

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class IntegerProvider(val min: Int = Int.MIN_VALUE, val max: Int = Int.MAX_VALUE, val value: String = "")

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class NaturalProvider(val min: Int = 0, val max: Int = Int.MAX_VALUE, val numerals:  Int = -1000, val value: String = "")

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class StringProvider(val length: Int = -1, val casing: Casing = Casing.LOWER)