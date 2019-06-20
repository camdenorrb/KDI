package me.camdenorrb.kdi

import me.camdenorrb.kdi.produce.Producer
import kotlin.reflect.KType
import kotlin.reflect.jvm.javaType
import kotlin.reflect.typeOf


object KDI {

    // KClass of type --> Name of stored value --> Producer
    val registry = mutableMapOf<KType, MutableMap<String, Producer<*>>>()


    fun clear() {
        registry.clear()
    }


    inline fun <reified T> get(name: String = ""): T {
        return get(typeOf<T>(), name)
    }

    fun <T> get(type: KType, name: String = ""): T {
        try {
            return registry[type]?.get(name)?.get() as T
        }
        catch (ex: TypeCastException) {
            error("Could not get the value for ${type.javaType.typeName} with name '$name'")
        }
    }


    fun insertAll(block: KDI.() -> Unit) {
        block(this)
    }
/*

    fun insertAll(vararg producers: Producer<*>) {
        producers.forEach { insert(it) }
    }*/



    inline fun <reified R> insert(noinline block: () -> R) {
        insert("", block)
    }


    inline fun <reified R> insert(name: String = "", noinline block: () -> R) {

        val type = typeOf<R>()
        val entry = registry.getOrPut(type) { mutableMapOf() }

        entry[name] = Producer(name, type, block)
    }


    inline fun <reified R> insert(producer: Producer<R>) {
        val entry = registry.getOrPut(typeOf<R>()) { mutableMapOf() }
        entry[producer.name] = producer
    }



    inline fun <reified R> remove() {
        remove(typeOf<R>())
    }


    inline fun <reified R> remove(name: String) {
        remove(typeOf<R>(), name)
    }


    fun remove(type: KType) {
        registry.remove(type)
    }

    fun remove(type: KType, name: String) {
        registry.remove(type)?.remove(name)
    }



    inline fun <reified R> KDI.producer(noinline block: () -> R) {
        producer("", block)
    }


    inline fun <reified R> KDI.producer(name: String = "", noinline block: () -> R) {

        val type = typeOf<R>()

        val producer = Producer(name, type, block)
        val entry = registry.getOrPut(type) { mutableMapOf() }

        entry[producer.name] = producer
    }

}