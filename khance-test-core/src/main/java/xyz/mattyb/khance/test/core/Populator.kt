package xyz.mattyb.khance.test.core

import xyz.mattyb.khance.Chance
import xyz.mattyb.khance.test.core.annotations.IntegerProvider
import xyz.mattyb.khance.test.core.annotations.PopulatedClass
import xyz.mattyb.khance.test.core.annotations.StringProvider
import kotlin.reflect.KClass
import kotlin.reflect.KFunction

class Populator {
    companion object {
        @JvmStatic
        fun <T : ChancePopulator> populate(chance: Chance, clazz: Class<T>) : Any? {
            val popClass: PopulatedClass? = clazz.annotations.find { it.annotationClass == PopulatedClass::class } as? PopulatedClass

            val kClass: KClass<*>? = popClass?.value
            val ctor: KFunction<*>? = kClass?.constructors?.firstOrNull()
            val obj: Any = ctor?.call() ?: return null

            clazz.annotations
                    .filterNot { it.annotationClass == PopulatedClass::class }
                    .forEach { set(chance, obj, it) }

            return obj
        }

        private fun set(chance: Chance, obj: Any, annotation: Annotation) {
            if (annotation is StringProvider) {
                val toSet = if (annotation.length < 0) {
                    chance.string(annotation.casing)
                } else chance.string(annotation.length, annotation.casing)
                var field = obj.javaClass.getDeclaredField(annotation.field.value)
                field.isAccessible = true
                field.set(obj, toSet)
                field.isAccessible = false
            } else if (annotation is IntegerProvider) {
                val toSet = chance.integer(annotation.min, annotation.max)
                var field = obj.javaClass.getDeclaredField(annotation.field.value)
                field.isAccessible = true
                field.set(obj, toSet)
                field.isAccessible = false
            }
        }
    }


}


