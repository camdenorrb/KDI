package me.camdenorrb.kdi.inject

import me.camdenorrb.kdi.store.KDIStore
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class InjectedDel<R : Any>(val returnClazz: KClass<R>, val name: String = "") : ReadOnlyProperty<Any, R> {

    private var value: R? = null


    override fun getValue(thisRef: Any, property: KProperty<*>): R {
        return value ?: checkNotNull(KDIStore[this::class.java.classLoader]?.get(returnClazz, name).also { value = it })
    }

}