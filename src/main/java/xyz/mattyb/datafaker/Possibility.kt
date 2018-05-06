package xyz.mattyb.datafaker

import xyz.mattyb.checkmate.CheckMate
import xyz.mattyb.datafaker.enums.Casing
import java.util.*
import kotlin.math.floor

class Possibility(private val seed: Long = Random().nextLong()) {

    private val random: MersenneTwisterFast = MersenneTwisterFast(seed)

    companion object {
        private val alphabet: String = "abcdefghijklmnopqrstuvwxyz"
        val LOWER_ALPHA_CHARS: CharArray = alphabet.toCharArray()
        val UPPER_ALPHA_CHARS: CharArray = alphabet.toUpperCase().toCharArray()
        val ALL_ALPHA_CHARS: CharArray = LOWER_ALPHA_CHARS + UPPER_ALPHA_CHARS;
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
        val charArray: CharArray = when (casing) {
            Casing.LOWER -> LOWER_ALPHA_CHARS
            Casing.UPPER -> UPPER_ALPHA_CHARS
            else -> ALL_ALPHA_CHARS
        }
        val randomIndex: Int = integer(0, charArray.size - 1)
        return charArray[randomIndex].toString()
    }
}
