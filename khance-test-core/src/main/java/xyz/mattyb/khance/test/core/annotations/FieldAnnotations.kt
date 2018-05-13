package xyz.mattyb.khance.test.core.annotations

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class BoolProvider(val likelihood: Int = 50, val value: String = "")

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class IntegerProvider(val min: Int = Int.MIN_VALUE, val max: Int = Int.MAX_VALUE, val value: String = "")

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class ChanceProvider