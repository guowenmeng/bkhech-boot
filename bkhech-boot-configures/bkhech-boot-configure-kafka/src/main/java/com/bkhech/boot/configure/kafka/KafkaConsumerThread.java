package com.bkhech.boot.configure.kafka;

import com.bkhech.boot.configure.kafka.exception.KafkaConsumerException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class KafkaConsumerThread<K, V>
        implements Runnable {
    private KafkaConsumer<K, V> consumer;
    private String topic;
    private Integer size;
    private Integer commitIntervalCount;
    private Consumer<ConsumerRecord<K, V>> callback;
    private boolean isClosed = false;

    public KafkaConsumerThread(KafkaConsumer<K, V> consumer, String topic, Integer size, Integer commitIntervalCount, Consumer<ConsumerRecord<K, V>> callback) {
        this.consumer = consumer;
        this.topic = topic;
        this.size = size;
        this.commitIntervalCount = commitIntervalCount;
        this.callback = callback;
    }

    @Override
    public void run() {
        work();
    }

    public void work() {
        try {
            boolean commitFlag = this.commitIntervalCount != null;
            AtomicInteger count = new AtomicInteger();

            this.consumer.subscribe(Arrays.asList(new String[]{this.topic}));
            while (!this.isClosed) {
                ConsumerRecords<K, V> records = this.consumer.poll(this.size.intValue());
                for (ConsumerRecord<K, V> record : records) {
                    this.callback.accept(record);
                    count.getAndIncrement();
                }
                if ((commitFlag) && (count.get() >= this.commitIntervalCount.intValue())) {
                    count.set(0);
                    this.consumer.commitAsync();
                }
            }
        } catch (Exception e) {
            throw new KafkaConsumerException("kafka poll exception: " + e.getMessage());
        } finally {
            close();
        }
    }

    public void close() {
        this.isClosed = true;
        this.consumer.close();
    }
}