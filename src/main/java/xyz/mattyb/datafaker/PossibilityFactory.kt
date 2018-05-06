package xyz.mattyb.datafaker

object PossibilityFactory {

    /**
     * Creates a [Possibility] with a random seed.
     *
     * @return a [Possibility] instance with a random seed.
     */
    @JvmStatic
    fun possibility(): Possibility {
        return Possibility()
    }

    /**
     * Creates a [Possibility] with the specified seed.
     *
     * @param seed the seed for the possibility.
     * @return a [Possibility] instance with the specified seed.
     */
    @JvmStatic
    fun possibility(seed: Long): Possibility {
        return Possibility(seed)
    }
}