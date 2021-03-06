package me.camdenorrb.kdi.ext

import me.camdenorrb.kdi.KDI
import me.camdenorrb.kdi.inject.InjectedDel
import kotlin.reflect.typeOf


inline fun <reified R : Any> inject(name: String = ""): R {
    return KDI.get(name)
}


inline fun <reified R : Any> injected(name: String = ""): InjectedDel<R> {
    return InjectedDel(typeOf<R>(), name)
}
