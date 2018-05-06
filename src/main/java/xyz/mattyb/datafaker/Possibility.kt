package xyz.mattyb.datafaker

import xyz.mattyb.checkmate.CheckMate
import xyz.mattyb.datafaker.enums.Casing
import java.util.*
import kotlin.math.floor

class Possibility(private val seed: Long = Random().nextLong()) {

    private val random: MersenneTwisterFast = MersenneTwisterFast(seed)

    companion object {
        val ALPHA_CHARS: CharArray = "abcdefghijklmnopqrstuvwxyz".toCharArray()
    }

    @JvmOverloads
    fun bool(likelihood: Int = 50): Boolean {
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
        val randomIndex: Int = integer(0, ALPHA_CHARS.size - 1)
        return ALPHA_CHARS[randomIndex].toString().correctCasing(casing)
    }

    private fun String.correctCasing(casing: Casing): String {
        if (casing == Casing.LOWER) {
            return this.toLowerCase()
        } else if (casing == Casing.UPPER) {
            return this.toUpperCase()
        }
        return this
    }
}
