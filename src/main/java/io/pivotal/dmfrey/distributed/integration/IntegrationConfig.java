package io.pivotal.dmfrey.distributed.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;

/**
 * Created by dmfrey on 8/30/16.
 */
@Configuration
@EnableIntegration
@IntegrationComponentScan
public class IntegrationConfig {

    @Bean
    public MessageChannel billing() {

        return new DirectChannel();
    }

    @Bean
    public MessageChannel alert() {

        return new DirectChannel();
    }

    @Bean
    public MessageChannel error() {

        return new DirectChannel();
    }

}
