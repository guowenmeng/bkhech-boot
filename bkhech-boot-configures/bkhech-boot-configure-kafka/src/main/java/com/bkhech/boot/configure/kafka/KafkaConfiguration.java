package com.bkhech.boot.configure.kafka;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ConfigurationProperties(prefix="kafka")
public class KafkaConfiguration {
    private final Producer producer = new Producer();
    private final Consumer consumer = new Consumer();

    public Producer getProducer() {
        return this.producer;
    }

    public Consumer getConsumer() {
        return this.consumer;
    }

    public static class Producer {
        private int producerCount = 1;
        private String bootstrapServers = "localhost:9092";
        private String topic;
        private String keySerializer = "org.apache.kafka.common.serialization.StringSerializer";
        private String valueSerializer = "org.apache.kafka.common.serialization.StringSerializer";
        private long requestTimeOut = 30000L;
        private long maxBlockMs = 60000L;
        private int acks = 1;
        private int retries = 0;
        private long retryBackoffMs = 100L;
        private String compressionType = "none";
        private int batchSize = 16384;
        private long bufferMemory = 33554432L;
        private long linger = 0L;

        public int getProducerCount() {
            return this.producerCount;
        }

        public void setProducerCount(int producerCount) {
            this.producerCount = producerCount;
        }

        public String getBootstrapServers() {
            return this.bootstrapServers;
        }

        public void setBootstrapServers(String bootstrapServers) {
            this.bootstrapServers = bootstrapServers;
        }

        public String getTopic() {
            return this.topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public String getKeySerializer() {
            return this.keySerializer;
        }

        public void setKeySerializer(String keySerializer) {
            this.keySerializer = keySerializer;
        }

        public String getValueSerializer() {
            return this.valueSerializer;
        }

        public void setValueSerializer(String valueSerializer) {
            this.valueSerializer = valueSerializer;
        }

        public long getRequestTimeOut() {
            return this.requestTimeOut;
        }

        public void setRequestTimeOut(long requestTimeOut) {
            this.requestTimeOut = requestTimeOut;
        }

        public long getMaxBlockMs() {
            return this.maxBlockMs;
        }

        public void setMaxBlockMs(long maxBlockMs) {
            this.maxBlockMs = maxBlockMs;
        }

        public int getAcks() {
            return this.acks;
        }

        public void setAcks(int acks) {
            this.acks = acks;
        }

        public int getRetries() {
            return this.retries;
        }

        public void setRetries(int retries) {
            this.retries = retries;
        }

        public long getRetryBackoffMs() {
            return this.retryBackoffMs;
        }

        public void setRetryBackoffMs(long retryBackoffMs) {
            this.retryBackoffMs = retryBackoffMs;
        }

        public String getCompressionType() {
            return this.compressionType;
        }

        public void setCompressionType(String compressionType) {
            this.compressionType = compressionType;
        }

        public int getBatchSize() {
            return this.batchSize;
        }

        public void setBatchSize(int batchSize) {
            this.batchSize = batchSize;
        }

        public long getBufferMemory() {
            return this.bufferMemory;
        }

        public void setBufferMemory(long bufferMemory) {
            this.bufferMemory = bufferMemory;
        }

        public long getLinger() {
            return this.linger;
        }

        public void setLinger(long linger) {
            this.linger = linger;
        }
    }

    public static class Consumer {
        private int consumerCount = 1;
        private String bootstrapServers = "localhost:9092";
        private String topic;
        private boolean enableAutoCommit = false;
        private int autoCommitIntervalMs = 1000;
        private int commitIntervalCount = 200;
        private int sessionTimeoutMs = 15000;
        private String keySerializer = "org.apache.kafka.common.serialization.StringDeserializer";
        private String valueSerializer = "org.apache.kafka.common.serialization.StringDeserializer";
        private String groupId;
        private String autoOffsetReset = "latest";

        public int getConsumerCount() {
            return this.consumerCount;
        }

        public void setConsumerCount(int consumerCount) {
            this.consumerCount = consumerCount;
        }

        public String getBootstrapServers() {
            return this.bootstrapServers;
        }

        public void setBootstrapServers(String bootstrapServers) {
            this.bootstrapServers = bootstrapServers;
        }

        public String getTopic() {
            return this.topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public boolean getEnableAutoCommit() {
            return this.enableAutoCommit;
        }

        public void setEnableAutoCommit(boolean enableAutoCommit) {
            this.enableAutoCommit = enableAutoCommit;
        }

        public int getAutoCommitIntervalMs() {
            return this.autoCommitIntervalMs;
        }

        public void setAutoCommitIntervalMs(int autoCommitIntervalMs) {
            this.autoCommitIntervalMs = autoCommitIntervalMs;
        }

        public int getCommitIntervalCount() {
            return this.commitIntervalCount;
        }

        public void setCommitIntervalCount(int commitIntervalCount) {
            this.commitIntervalCount = commitIntervalCount;
        }

        public int getSessionTimeoutMs() {
            return this.sessionTimeoutMs;
        }

        public void setSessionTimeoutMs(int sessionTimeoutMs) {
            this.sessionTimeoutMs = sessionTimeoutMs;
        }

        public String getKeySerializer() {
            return this.keySerializer;
        }

        public void setKeySerializer(String keySerializer) {
            this.keySerializer = keySerializer;
        }

        public String getValueSerializer() {
            return this.valueSerializer;
        }

        public void setValueSerializer(String valueSerializer) {
            this.valueSerializer = valueSerializer;
        }

        public String getGroupId() {
            return this.groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getAutoOffsetReset() {
            return this.autoOffsetReset;
        }

        public void setAutoOffsetReset(String autoOffsetReset) {
            this.autoOffsetReset = autoOffsetReset;
        }
    }

    @Bean
    @Scope("prototype")
    @ConditionalOnBean({KafkaConfiguration.class})
    public KafkaClient<String, String> kafkaClient() {
        KafkaClient<String, String> kafkaClient = new KafkaClient();
        kafkaClient.initProducer(this.producer);
        kafkaClient.initConsumer(this.consumer);
        return kafkaClient;
    }
}