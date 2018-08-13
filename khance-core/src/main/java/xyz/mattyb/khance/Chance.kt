package xyz.mattyb.khance

import xyz.mattyb.checkmate.CheckMate
import xyz.mattyb.khance.enums.AgeType
import xyz.mattyb.khance.enums.Casing
import java.util.*
import kotlin.math.floor

class Chance(seed: Long = Random().nextLong()) {

    private val random: MersenneTwisterFast = MersenneTwisterFast(seed)

    companion object {
        private const val alphabet: String = "abcdefghijklmnopqrstuvwxyz"
        private const val HEX_CHARS: String = "0123456789abcedf"
    }

    @JvmOverloads
    fun bool(likelihood: Int = 50): Boolean {
        CheckMate.check().intValue(likelihood).between(0).and(100).inclusive().validate()
        return random.nextBoolean(likelihood * 0.01)
    }

    @JvmOverloads
    fun integer(min: Int = Int.MIN_VALUE, max: Int = Int.MAX_VALUE): Int {
        CheckMate.check().`is`(max >= min).truthy().validate()
        val diff: Double = (max - min.toDouble())
        return floor(random.nextDouble() * (diff + 1.0) + min).toInt()
    }

    @JvmOverloads
    fun natural(min: Int = 0, max: Int = Int.MAX_VALUE): Int {
        CheckMate.check().intValue(min).between(0).and(max).inclusive().validate()
        return integer(min, max)
    }

    fun naturalNumerals(numerals: Int): Int {
        CheckMate.check().`is`(numerals > 0).truthy().validate()
        val min: Int = 10.toThe(numerals - 1)
        val max: Int = (10.toThe(numerals)) - 1
        return natural(min, max)
    }

    @JvmOverloads
    fun letter(casing: Casing = Casing.LOWER): String {
        return character(casing).toString()
    }

    fun string(): String {
        return internalString()
    }

    fun string(length: Int): String {
        return internalString(length = length)
    }

    fun string(casing: Casing): String {
        return internalString(casing = casing)
    }

    fun string(length: Int, casing: Casing): String {
        return internalString(length = length, casing = casing)
    }

    private fun internalString(length: Int = natural(5, 20), casing: Casing = Casing.LOWER, pool:String = alphabet): String {
        CheckMate.check().`is`(length >= 0).truthy().validate()
        return (1..length).map { character(casing, pool) }.joinToString("")
    }

    @JvmOverloads
    fun character(casing: Casing = Casing.LOWER, pool:String = alphabet): Char {
        val charArray: CharArray = when (casing) {
            Casing.LOWER -> pool.toLowerCase().toCharArray()
            Casing.UPPER -> pool.toUpperCase().toCharArray()
            else -> pool.toLowerCase().toCharArray() + pool.toUpperCase().toCharArray()
        }
        val randomIndex: Int = integer(0, charArray.size - 1)
        return charArray[randomIndex]
    }

    @JvmOverloads
    fun hex(casing: Casing = Casing.LOWER): Char {
        return character(casing, HEX_CHARS)
    }

    private fun Int.toThe(power: Int): Int {
        return Math.pow(this.toDouble(), power.toDouble()).toInt()
    }

    /******************** Person ********************/

    @JvmOverloads
    fun age(ageType: AgeType? = null): Int {
        return when (ageType) {
            AgeType.BABY -> natural(0, 2)
            AgeType.CHILD -> natural(0, 12)
            AgeType.TEEN -> natural(13, 19)
            AgeType.ADULT -> natural(18, 65)
            AgeType.SENIOR -> natural(65, 100)
            else -> natural(0, 100)
        }
    }
}
