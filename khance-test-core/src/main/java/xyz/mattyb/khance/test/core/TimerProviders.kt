@file:JvmName("TimeProviderUtils")
package xyz.mattyb.khance.test.core

import xyz.mattyb.khance.Chance
import xyz.mattyb.khance.test.core.annotations.*

fun MonthProvider.getMonth(chance: Chance): String {
    return if (this.value) {
        chance.time.month(this.value)
    } else {
        chance.time.month()
    }
}

fun MonthProvider.getMonthNumeric(chance: Chance): Int = chance.time.monthNumeric()

fun MinuteProvider.getMinute(chance: Chance): Int = chance.time.minute()

fun SecondProvider.getSecond(chance: Chance): Int = chance.time.second()

fun MillisecondProvider.getMillisecond(chance: Chance): Int = chance.time.millisecond()

fun HourProvider.getHour(chance: Chance): Int {
    return if (this.value) {
        chance.time.hour(this.value)
    } else {
        chance.time.hour()
    }
}