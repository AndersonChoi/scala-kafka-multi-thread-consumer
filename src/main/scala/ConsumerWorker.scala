import java.time.Duration
import java.util
import java.util.Properties

import org.apache.kafka.clients.consumer._

class ConsumerWorker(val props: Properties, val topic: String, val id:Int) extends Runnable {

  @Override
  override def run(): Unit = {
    println(id)
    val consumer = new KafkaConsumer[Array[Byte], Array[Byte]](props)
    consumer.subscribe(util.Collections.singletonList(topic))
    while (true) {
      val records = consumer.poll(Duration.ofSeconds(1))
      records.forEach(item => {
        println("Thread" + id + " " + new String(item.value()))
      })
    }
    println("Shutdown Thread"+id)
  }
}
