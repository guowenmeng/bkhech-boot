package com.bkhech.boot.autoconfigure.kafka;

import com.bkhech.boot.configure.kafka.KafkaConfiguration;
import org.apache.kafka.clients.KafkaClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author guowm[guowm@5fun.com]
 * @date 2019/8/13
 */
@Configuration
@ConditionalOnClass(KafkaClient.class)
@Import({KafkaConfiguration.class})
public class KafkaAutoConfiguration {
    
}
