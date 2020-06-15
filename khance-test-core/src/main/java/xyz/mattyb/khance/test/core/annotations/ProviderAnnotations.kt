package xyz.mattyb.khance.test.core.annotations

import xyz.mattyb.khance.enums.Casing
import xyz.mattyb.khance.enums.Continent
import kotlin.reflect.KClass

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class ChanceProvider

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class BoolProvider(val likelihood: Int = 50, val field: Field = Field(""))

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class IntegerProvider(val min: Int = Int.MIN_VALUE, val max: Int = Int.MAX_VALUE, val field: Field = Field(""))

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class NaturalProvider(val min: Int = 0, val max: Int = Int.MAX_VALUE, val numerals: Int = -1000, val field: Field = Field(""))

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class StringProvider(val length: Int = -1, val casing: Casing = Casing.LOWER, val field: Field = Field(""))

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class HashProvider(val length: Int = -1, val casing: Casing = Casing.LOWER)

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class DieProvider(val value: Int = 6)

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class DiceProvider(val value: Int = 6, val rolls: Int = 3)

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class IpProvider

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class CityProvider(val value: Array<Continent> = [])

@Retention(AnnotationRetention.RUNTIME)
annotation class Field(val value: String)

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class PopulatedClass(val value : KClass<*>)