package me.camdenorrb.kdi.produce

import kotlin.reflect.KType

class Producer<R>(val name: String, val type: KType, val block: () -> R) {

    private var value: R? = null


    fun get(): R {
        return value ?: block().also { value = it }
    }

}