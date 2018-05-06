package xyz.mattyb.datafaker

object FakerFactory {

    /**
     * Creates a [Faker] with a random seed.
     *
     * @return a [Faker] instance with a random seed.
     */
    @JvmStatic
    fun faker(): Faker {
        return Faker()
    }

    /**
     * Creates a [Faker] with the specified seed.
     *
     * @param seed the seed for the faker.
     * @return a [Faker] instance with the specified seed.
     */
    @JvmStatic
    fun faker(seed: Long): Faker {
        return Faker(seed)
    }
}