package io.pivotal.dmfrey.distributed.jms;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Destination;

/**
 * Created by dmfrey on 8/26/16.
 */
@Configuration
@EnableJms
public class JmsConfig {

    @Bean
    public Destination myQueue() {

        return new ActiveMQQueue( "my-queue" );
    }

}
