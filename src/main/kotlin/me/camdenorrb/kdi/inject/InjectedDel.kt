package me.camdenorrb.kdi.inject

import me.camdenorrb.kdi.KDI
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import kotlin.reflect.KType

class InjectedDel<R : Any>(val returnType: KType, val name: String = "") : ReadOnlyProperty<Any, R> {

    private var value: R? = null


    override fun getValue(thisRef: Any, property: KProperty<*>): R {
        return value ?: checkNotNull(KDI.get<R>(returnType, name).also { value = it })
    }

}