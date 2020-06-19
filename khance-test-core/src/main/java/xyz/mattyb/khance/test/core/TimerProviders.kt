@file:JvmName("TimerProviderUtils")
package xyz.mattyb.khance.test.core

import xyz.mattyb.khance.Chance
import xyz.mattyb.khance.test.core.annotations.MonthProvider

fun MonthProvider.getMonth(chance: Chance): String {
    return if (this.value) {
        chance.time.month(this.value)
    } else {
        chance.time.month()
    }
}