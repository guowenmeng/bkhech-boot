package com.bkhech.boot.configure.kafka;

import com.bkhech.boot.configure.kafka.exception.KafkaConsumerException;
import com.bkhech.boot.configure.kafka.exception.NoSuchKafkaProducerException;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

public class KafkaClient<K, V> {
    private KafkaConfiguration.Producer producerConfig;
    private KafkaConfiguration.Consumer consumerConfig;
    private List<KafkaProducer<K, V>> producers = new ArrayList();
    private List<KafkaConsumer<K, V>> consumers = new ArrayList();
    private AtomicInteger handleCount = new AtomicInteger();
    private AtomicInteger consumerCount = new AtomicInteger();

    public KafkaClient() {
    }

    public void initProducer(KafkaConfiguration.Producer producerConfig) {
        if (producerConfig != null && !StringUtils.isEmpty(producerConfig.getBootstrapServers())) {
            this.producerConfig = producerConfig;
        }

        this.initProducerPool();
    }

    public void initConsumer(KafkaConfiguration.Consumer consumerConfig) {
        if (consumerConfig != null && !StringUtils.isEmpty(consumerConfig.getBootstrapServers())) {
            this.consumerConfig = consumerConfig;
        }

        this.initConsumerPool();
    }

    private void initProducerPool() {
        Properties pConfig = new Properties();
        pConfig.put("bootstrap.servers", this.producerConfig.getBootstrapServers());
        pConfig.put("key.serializer", this.producerConfig.getKeySerializer());
        pConfig.put("value.serializer", this.producerConfig.getValueSerializer());
        pConfig.put("acks", String.valueOf(this.producerConfig.getAcks()));
        pConfig.put("linger.ms", String.valueOf(this.producerConfig.getLinger()));
        pConfig.put("request.timeout.ms", String.valueOf(this.producerConfig.getRequestTimeOut()));
        pConfig.put("max.block.ms", String.valueOf(this.producerConfig.getMaxBlockMs()));
        pConfig.put("retries", String.valueOf(this.producerConfig.getRetries()));
        pConfig.put("retry.backoff.ms", String.valueOf(this.producerConfig.getRetryBackoffMs()));
        pConfig.put("compression.type", this.producerConfig.getCompressionType());
        pConfig.put("buffer.memory", String.valueOf(this.producerConfig.getBufferMemory()));
        pConfig.put("batch.size", String.valueOf(this.producerConfig.getBatchSize()));

        for(int i = 0; i < this.producerConfig.getProducerCount(); ++i) {
            this.producers.add(new KafkaProducer(pConfig));
        }

    }

    private void initConsumerPool() {
        Properties config = new Properties();
        config.put("bootstrap.servers", this.consumerConfig.getBootstrapServers());
        config.put("enable.auto.commit", this.consumerConfig.getEnableAutoCommit());
        config.put("auto.commit.interval.ms", this.consumerConfig.getAutoCommitIntervalMs());
        config.put("session.timeout.ms", this.consumerConfig.getSessionTimeoutMs());
        config.put("key.deserializer", this.consumerConfig.getKeySerializer());
        config.put("value.deserializer", this.consumerConfig.getValueSerializer());
        config.put("auto.offset.reset", this.consumerConfig.getAutoOffsetReset());
        if (!StringUtils.isEmpty(this.consumerConfig.getGroupId())) {
            config.put("group.id", this.consumerConfig.getGroupId());
        }

        for(int i = 0; i < this.consumerConfig.getConsumerCount(); ++i) {
            this.consumers.add(new KafkaConsumer(config));
        }

    }

    public void resetProducerPool() {
        this.producers.forEach((producer) -> {
            producer.flush();
            producer.close();
        });
        this.producers.clear();
        this.initProducerPool();
    }

    public KafkaProducer<K, V> getProducer() {
        int size = this.producers.size();
        if (size > 0) {
            try {
                return (KafkaProducer)this.producers.get(this.handleCount.getAndIncrement() % size);
            } catch (Exception var3) {
                this.handleCount.set(0);
                return this.getProducer();
            }
        } else {
            throw new NoSuchKafkaProducerException("kafka client");
        }
    }

    public KafkaConsumer<K, V> getConsumer() {
        int size = this.consumers.size();
        if (size > 0) {
            try {
                return (KafkaConsumer)this.consumers.get(this.consumerCount.getAndIncrement() % size);
            } catch (Exception var3) {
                this.consumerCount.set(0);
                return this.getConsumer();
            }
        } else {
            throw new KafkaConsumerException("no such kafka consumer kafka client");
        }
    }

    public void send(K key, V value, Callback callback) {
        String topic = this.producerConfig.getTopic();
        if (StringUtils.isEmpty(topic)) {
            throw new KafkaConsumerException("topic is empty");
        } else {
            this.send(topic, key, value, callback);
        }
    }

    public void send(K key, V value) {
        this.send(key, value, null);
    }

    public void send(String topic, K key, V value, Callback callback) {
        this.getProducer().send(new ProducerRecord(topic, key, value), callback);
    }

    public void send(String topic, K key, V value) {
        this.send(topic, key, value, null);
    }

    public void poll(String topic, int size, java.util.function.Consumer<ConsumerRecord<K, V>> callback) {
        KafkaConsumerThread<K, V> thread = this.consumerThread(topic, size, callback);
        (new Thread(thread)).start();
    }

    public void poll(String topic, java.util.function.Consumer<ConsumerRecord<K, V>> callback) {
        this.poll(topic, 100, callback);
    }

    public void poll(java.util.function.Consumer<ConsumerRecord<K, V>> callback) {
        String topic = this.consumerConfig.getTopic();
        if (StringUtils.isEmpty(topic)) {
            throw new KafkaConsumerException("topic is empty");
        } else {
            this.poll(topic, callback);
        }
    }

    public void pollOnBlock(String topic, int size, java.util.function.Consumer<ConsumerRecord<K, V>> callback) {
        this.consumerThread(topic, size, callback).work();
    }

    public KafkaConsumerThread<K, V> consumerThread(String topic, int size, java.util.function.Consumer<ConsumerRecord<K, V>> callback) {
        KafkaConsumer<K, V> consumer = this.getConsumer();
        Integer commitIntervalCount = !this.consumerConfig.getEnableAutoCommit() ? this.consumerConfig.getCommitIntervalCount() : null;
        return new KafkaConsumerThread(consumer, topic, size, commitIntervalCount, callback);
    }
}
