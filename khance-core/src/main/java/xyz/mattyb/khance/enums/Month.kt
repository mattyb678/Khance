package xyz.mattyb.khance.enums

enum class Month(val abbreviation: String, val numeric: Int) {
    JANUARY("Jan", 1),
    FEBRUARY("Feb", 2),
    MARCH("Mar", 3),
    APRIL("Apr", 4),
    MAY("May", 5),
    JUNE("Jun", 6),
    JULY("Jul", 7),
    AUGUST("Aug", 8),
    SEPTEMBER("Sep", 9),
    OCTOBER("Oct", 10),
    NOVEMBER("Nov", 11),
    DECEMBER("Dec", 12)
    ;

    val fullName: String = name.toLowerCase().capitalize()

    companion object {
        @JvmField
        val all = values().toList()
    }
}