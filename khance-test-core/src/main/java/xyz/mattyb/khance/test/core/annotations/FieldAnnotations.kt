package xyz.mattyb.khance.test.core.annotations

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ChanceBool(val likelihood: Int = 50, val value: String)

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ChanceInteger(val min: Int = Int.MIN_VALUE, val max: Int = Int.MAX_VALUE, val value: String)