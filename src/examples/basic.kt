
import me.camdenorrb.kdi.KDI
import me.camdenorrb.kdi.ext.inject


fun main(args: Array<String>) {

    val example = BasicExample()

    example.start()
    example.stop()
}


class BasicExample {

    fun start() {

        // Inserts an instance of the dependency for global use
        KDI.insert { ExampleDependency("Example") }

        println(ExampleRequester1().dependency)
        println(ExampleRequester2().dependency)
    }

    fun stop() {
        KDI.clear()
    }


    data class ExampleDependency(val data: String)


    // Allows for 2 forms of dependency injection
    class ExampleRequester1(val dependency: ExampleDependency = inject())

    // Forces a specific instance of the dependency
    class ExampleRequester2 {

        val dependency: ExampleDependency = inject()

    }

}