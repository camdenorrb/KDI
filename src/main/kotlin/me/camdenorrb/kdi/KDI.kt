package me.camdenorrb.kdi

import me.camdenorrb.kdi.produce.Producer
import kotlin.reflect.KClass

object KDI {

    val registry = mutableMapOf<KClass<*>, MutableMap<String, Producer<*>>>()


    fun clear() {
        registry.clear()
    }


    inline fun <reified T : Any> get(name: String = ""): T {
        return get(T::class, name)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> get(clazz: KClass<T>, name: String = ""): T {
        return registry[clazz]?.get(name)?.get() as T
    }


    fun insertAll(block: KDI.() -> Unit) {
        block(this)
    }

    fun insertAll(vararg producers: Producer<*>) {
        producers.forEach { insert(it) }
    }


    inline fun <reified R : Any> insert(noinline block: () -> R) {
        insert("", block)
    }

    inline fun <reified R : Any> insert(name: String = "", noinline block: () -> R) {

        val clazz = R::class
        val entry = registry.getOrPut(clazz, ::mutableMapOf)

        entry[name] = Producer(name, clazz, block)
    }

    fun <R : Any> insert(producer: Producer<R>) {
        val entry = registry.getOrPut(producer.clazz, ::mutableMapOf)
        entry[producer.name] = producer
    }


    inline fun <reified R : Any> remove() {
        remove(R::class)
    }

    inline fun <reified R : Any> remove(name: String) {
        remove(R::class, name)
    }


    fun <R : Any> remove(clazz: KClass<R>) {
        registry.remove(clazz)
    }

    fun <R : Any> remove(clazz: KClass<R>, name: String) {
        registry.remove(clazz)?.remove(name)
    }


    inline fun <reified R : Any> KDI.producer(noinline block: () -> R) {
        producer("", block)
    }

    inline fun <reified R : Any> KDI.producer(name: String = "", noinline block: () -> R) {

        val producer = Producer(name, R::class, block)
        val entry = registry.getOrPut(producer.clazz) { mutableMapOf() }

        entry[producer.name] = producer
    }

}