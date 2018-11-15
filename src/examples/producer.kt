import me.camdenorrb.kdi.KDI
import me.camdenorrb.kdi.ext.inject


fun main(args: Array<String>) {

    val example = ProducerExample()

    example.start()
    example.stop()
}


class ProducerExample {

    val producer = Producer()


    fun start() {
        KDI.insert(producer::createDependency)
        println(ExampleRequester().dependency)
    }

    fun stop() {
        KDI.clear()
    }


    class Producer {

        fun createDependency(): ExampleDependency {
            return ExampleDependency("Example")
        }

    }


    data class ExampleDependency(val data: String)

    data class ExampleRequester(val dependency: ExampleDependency = inject())

}

