package io.pivotal.dmfrey.distributed.integration;

import io.pivotal.dmfrey.distributed.dao.model.Sample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

/**
 * Created by dmfrey on 8/30/16.
 */
@MessageEndpoint
@Slf4j
public class BillingEndpoint {

    @ServiceActivator( inputChannel = "billing" )
    public void processBillingEvent( Message<Sample> message ) {
        log.debug( "processBillingEvent : enter" );

        log.info( "processBillingEvent : sample=" + message.getPayload().toString() );

        log.debug( "processBillingEvent : exit" );
    }

}
