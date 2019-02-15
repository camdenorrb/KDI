import me.camdenorrb.kdi.KDI
import me.camdenorrb.kdi.ext.inject


fun main() {

    val example = ProducerExample()

    example.start()
    example.stop()
}


class ProducerExample(val kdi: KDI) {

    val producer = Producer()


    fun start() {
        kdi.insert(producer::createDependency)
        println(ExampleRequester().dependency)
    }

    fun stop() {
        kdi.clear()
    }


    class Producer {

        fun createDependency(): ExampleDependency {
            return ExampleDependency("Example")
        }

    }


    data class ExampleDependency(val data: String)

    data class ExampleRequester(val dependency: ExampleDependency = inject())

}

