package com.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    
        //@Value("${kafka.bootstrap-servers}")
        private String bootstrapServers = "b-2-public.mskcluster.plvcmc.c8.kafka.eu-central-1.amazonaws.com:9198,b-1-public.mskcluster.plvcmc.c8.kafka.eu-central-1.amazonaws.com:9198,b-3-public.mskcluster.plvcmc.c8.kafka.eu-central-1.amazonaws.com:9198";

        //@Value("${security.protocol}")
        private String securityProtocol = "SASL_SSL";

       // @Value("${sasl.mechanism}")
        private String saslMechanism = "AWS_MSK_IAM";
    
        //@Value("${sasl.jaas.config}")
        private String saslJaasConfig = "software.amazon.msk.auth.iam.IAMLoginModule required;";

       // @Value("${sasl.client.callback.handler.class}")
        private String saslClientCallbackHandler = "software.amazon.msk.auth.iam.IAMClientCallbackHandler";
    
        public ConsumerFactory<String, String> consumerFactory() {    
            Map<String, Object> configProps = new HashMap<>();
            configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
            configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer_gp1");
            configProps.put("security.protocol", securityProtocol);
            configProps.put(SaslConfigs.SASL_MECHANISM, saslMechanism);
            configProps.put(SaslConfigs.SASL_JAAS_CONFIG, saslJaasConfig);
            configProps.put(SaslConfigs.SASL_CLIENT_CALLBACK_HANDLER_CLASS, saslClientCallbackHandler);
            configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            return new DefaultKafkaConsumerFactory<>(configProps);
        }
    
        @Bean
        public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> rawKafkaListenerContainerFactory() {
            ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
            factory.setConsumerFactory(consumerFactory());
            return factory;
        }


    

}
