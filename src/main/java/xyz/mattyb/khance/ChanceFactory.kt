package xyz.mattyb.khance

object ChanceFactory {

    /**
     * Creates a [Chance] with a random seed.
     *
     * @return a [Chance] instance with a random seed.
     */
    @JvmStatic
    fun chance(): Chance {
        return Chance()
    }

    /**
     * Creates a [Chance] with the specified seed.
     *
     * @param seed the seed for the possibility.
     * @return a [Chance] instance with the specified seed.
     */
    @JvmStatic
    fun chance(seed: Long): Chance {
        return Chance(seed)
    }
}