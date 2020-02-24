# scala-kafka-multi-thread-consumer
Scala multi thread consumer application example

Manual(KOR) : https://blog.voidmainvoid.net/313

## How to run
```
$ sbt run Main
```

## How to stop
This application can reliably shut down the consumer through shutdown hook.
```
$ kill -term {process number}
```

## How to configuration
1) Declare thread counts
```
val threadCount = 2
```

2) Edit consumer configuration
```
val topic: String = "test-topic"
val consumerId: String = "test-consumer"
val properties = new Properties()
properties.put("group.id", consumerId)
properties.put("bootstrap.servers", "localhost:9092")
properties.put("key.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer")
properties.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer")
```
