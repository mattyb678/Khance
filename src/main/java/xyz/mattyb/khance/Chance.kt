package xyz.mattyb.khance

import xyz.mattyb.checkmate.CheckMate
import xyz.mattyb.khance.enums.Casing
import java.util.*
import kotlin.math.floor

class Chance(private val seed: Long = Random().nextLong()) {

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
    fun letter(casing: Casing = Casing.LOWER): String {
        return character(casing).toString()
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
}
