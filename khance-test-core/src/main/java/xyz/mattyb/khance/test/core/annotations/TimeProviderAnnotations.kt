package xyz.mattyb.khance.test.core.annotations

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class HourProvider(val value: Boolean = false)