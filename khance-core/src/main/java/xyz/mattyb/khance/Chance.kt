package xyz.mattyb.khance

import xyz.mattyb.checkmate.CheckMate
import xyz.mattyb.khance.enums.*
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

    @JvmField
    val person = Person(this)

    class Person(private val chance: Chance) {

        private val genders = listOf("Male", "Female")

        private val firstNameMap = mapOf(
                Nationality.USA to mapOf(
                        Gender.MALE to ENG_MALE_FIRST_NAMES,
                        Gender.FEMALE to ENG_FEMALE_FIRST_NAMES
                ),
                Nationality.UK to mapOf(
                        Gender.MALE to ENG_MALE_FIRST_NAMES,
                        Gender.FEMALE to ENG_FEMALE_FIRST_NAMES
                ),
                Nationality.ITALY to mapOf(
                        Gender.MALE to ITALY_MALE_FIRST_NAMES,
                        Gender.FEMALE to ITALY_FEMALE_FIRST_NAMES
                ),
                Nationality.NETHERLANDS to mapOf(
                        Gender.MALE to NETHERLANDS_MALE_FIRST_NAMES,
                        Gender.FEMALE to NETHERLANDS_FEMALE_FIRST_NAMES
                ),
                Nationality.FRANCE to mapOf(
                        Gender.MALE to FRANCE_MALE_FIRST_NAMES,
                        Gender.FEMALE to FRANCE_FEMALE_FIRST_NAMES
                )
        )

        private val lastNameMap = mapOf(
                Nationality.USA to USA_LAST_NAMES,
                Nationality.ITALY to ITALY_LAST_NAMES,
                Nationality.NETHERLANDS to NETHERLANDS_LAST_NAMES,
                Nationality.UK to UK_LAST_NAMES,
                Nationality.GERMANY to GERMANY_LAST_NAMES,
                Nationality.JAPAN to JAPAN_LAST_NAMES,
                Nationality.SPAIN to SPAIN_LAST_NAMES,
                Nationality.FRANCE to FRANCE_LAST_NAMES
        )

        @JvmOverloads
        fun age(ageType: AgeType? = null): Int {
            return when (ageType) {
                AgeType.BABY -> chance.natural(0, 2)
                AgeType.CHILD -> chance.natural(0, 12)
                AgeType.TEEN -> chance.natural(13, 19)
                AgeType.ADULT -> chance.natural(18, 65)
                AgeType.SENIOR -> chance.natural(65, 100)
                else -> chance.natural(0, 100)
            }
        }

        fun gender(vararg extraGenders: String): String {
            return listOf(*extraGenders)
                    .plus(genders)
                    .random()
        }

        @JvmOverloads
        fun name(
                middle: Boolean = false, middleInitial: Boolean = false, gender: Gender? = null,
                vararg nationality: Nationality
        ): String {
            val first = first(gender, *nationality)
            val middleName = when {
                middle -> " " + first(gender, *nationality)
                middleInitial -> " " + chance.letter(Casing.UPPER)
                else -> ""
            }
            val last = last(*nationality)
            return "$first$middleName $last"
        }

        @JvmOverloads
        fun first(gender: Gender? = null, vararg nationality: Nationality): String {
            val genderToUse = gender ?: Gender.values().random()
            return if (nationality.isEmpty()) {
                firstNameMap[firstNameMap.keys.random()]?.get(genderToUse)?.random() ?: ""
            } else {
                firstNameMap[nationality.random()]?.get(genderToUse)?.random() ?: ""
            }
        }

        fun last(vararg nationality: Nationality): String {
            return if (nationality.isEmpty()) {
                lastNameMap[lastNameMap.keys.random()]?.random() ?: ""
            } else {
                lastNameMap[nationality.random()]?.random() ?: ""
            }
        }

        @JvmOverloads
        fun ssn(lastFourOnly: Boolean = false, dashes: Boolean = true): String {
            val sb = StringBuilder()
            val dash = if (dashes) "-" else ""
            if (!lastFourOnly) {
                (1..3).forEach { _ -> sb.append(chance.natural(0, 9)) }
                sb.append(dash)
                (1..2).forEach { _ -> sb.append(chance.natural(0, 9)) }
                sb.append(dash)
            }
            (1..4).forEach { _ -> sb.append(chance.natural(0, 9)) }
            return sb.toString()
        }
    }

    @JvmField
    val employment = Employment(this)

    class Employment(private val chance: Chance) {

        fun company(): String {
            return companies.random()
        }

        @JvmOverloads
        fun profession(includeRanking: Boolean = false): String {
            val rankings = listOf("Apprentice", "Junior", "Senior", "Lead")
            val profession = professions.random()
            val rank = if (includeRanking) {
                rankings.random() + " "
            } else {
                ""
            }
            return "$rank$profession"
        }
    }

    @JvmField
    val web = Web(this)

    class Web(private val chance: Chance) {

        @JvmOverloads
        fun tld(vararg tldTypes: TldType = arrayOf(TldType.ORIGINAL)): String {
            var types = sequenceOf<String>()
            tldTypes.forEach {
                when (it) {
                    TldType.ORIGINAL -> types = types.plus(originalTlds)
                    TldType.COUNTRY -> types = types.plus(countryTlds)
                    TldType.GENERIC -> types = types.plus(genericTlds)
                    TldType.GENERIC_CHINESE -> types = types.plus(genericChineseTlds)
                    TldType.GENERIC_FRENCH -> types = types.plus(genericFrenchTlds)
                    TldType.GENERIC_GERMAN -> types = types.plus(genericGermanTlds)
                    TldType.GENERIC_HINDI -> types = types.plus(genericHindiTlds)
                    TldType.GENERIC_ITALIAN -> types = types.plus(genericItalianTlds)
                    TldType.GENERIC_PORTUGUESE -> types = types.plus(genericPortugueseTlds)
                    TldType.GENERIC_SPANISH -> types = types.plus(genericSpanishTlds)
                    else -> {}
                }
            }
            return types.toSet().random()
        }

        fun ip(): String {
            val first = chance.natural(1, 254)
            val second = chance.natural(0, 255)
            val third = chance.natural(0, 255)
            val fourth = chance.natural(1, 254)
            return "$first.$second.$third.$fourth"
        }
    }
}
