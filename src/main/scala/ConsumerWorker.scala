import java.time.Duration
import java.util
import java.util.Properties

import org.apache.kafka.clients.consumer._

class ConsumerWorker(val props: Properties, val topic: String, val id: Int) extends Runnable {

  @Override
  override def run(): Unit = {
    val threadName = "Thread" + id
    ConsumerStatus.consumerInfo += threadName
    val consumer = new KafkaConsumer[Array[Byte], Array[Byte]](props)
    consumer.subscribe(util.Collections.singletonList(topic))
    while (ConsumerStatus.isRunning) {
      val records = consumer.poll(Duration.ofSeconds(1))
      records.forEach(item => {
        println(threadName + " " + new String(item.value()))
      })
    }
    consumer.close()
    println("Shutdown Thread" + id)
    ConsumerStatus.consumerInfo -= threadName
  }
}
