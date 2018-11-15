import me.camdenorrb.kdi.KDI
import me.camdenorrb.kdi.ext.inject

fun main(args: Array<String>) {

    val example = NamedExample()

    example.start()
    example.stop()
}


class NamedExample {

    fun start() {

        KDI.insertAll {
            producer("depend1") { ExampleDependency("1") }
            producer("depend2") { ExampleDependency("2") }
        }

        println(ExampleRequester1().dependency)
        println(ExampleRequester2().dependency)
    }

    fun stop() {
        KDI.clear()
    }


    data class ExampleDependency(val data: String)


    data class ExampleRequester1(val dependency: ExampleDependency = inject("depend1"))

    data class ExampleRequester2(val dependency: ExampleDependency = inject("depend2"))

}