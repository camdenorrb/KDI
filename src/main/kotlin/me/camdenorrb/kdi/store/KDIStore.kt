package me.camdenorrb.kdi.store

import me.camdenorrb.kcommons.store.struct.MappedStore
import me.camdenorrb.kdi.KDI

object KDIStore : MappedStore<ClassLoader, KDI>() {

    override val name = "KDI Store"


    fun getOrCreate(classLoader: ClassLoader): KDI {
        return get(classLoader) ?: KDI().also { register(classLoader, it) }
    }

}