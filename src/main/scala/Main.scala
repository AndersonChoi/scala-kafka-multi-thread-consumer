
import java.util.Properties
import java.util.concurrent.Executors

object Main extends App {

  // Define configurations for consume
  val threadCount = 2
  val topic: String = "test-topic"
  val consumerId: String = "test-consumer"
  val properties = new Properties()
  properties.put("group.id", consumerId)
  properties.put("bootstrap.servers", "localhost:9092")
  properties.put("key.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer")
  properties.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer")

  val executor = Executors.newCachedThreadPool()
  // Submit n thread kafka consumers
  for (threadNo <- 0 until threadCount) {
    executor.submit(new ConsumerWorker(properties, topic, threadNo))
  }

  // Shutdown hook makes graceful shutdown
  sys.ShutdownHookThread {
    ConsumerStatus.isRunning = false
    println("exiting")
  }
}