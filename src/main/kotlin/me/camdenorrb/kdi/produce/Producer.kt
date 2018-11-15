package me.camdenorrb.kdi.produce

import kotlin.reflect.KClass

class Producer<R : Any>(val name: String, val clazz: KClass<R>, val block: () -> R) {

    private var value: R? = null


    fun get(): R {
        return value ?: block().also { value = it }
    }

}