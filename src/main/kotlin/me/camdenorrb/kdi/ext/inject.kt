package me.camdenorrb.kdi.ext

import me.camdenorrb.kdi.inject.InjectedDel
import me.camdenorrb.kdi.store.KDIStore

inline fun <reified R : Any> inject(name: String = ""): R {
    return checkNotNull(KDIStore[object {}::class.java.classLoader]?.get(name))
}

inline fun <reified R : Any> injected(name: String = ""): InjectedDel<R> {
    return InjectedDel(R::class, name)
}
