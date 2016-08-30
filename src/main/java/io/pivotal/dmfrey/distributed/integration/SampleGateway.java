package io.pivotal.dmfrey.distributed.integration;

import io.pivotal.dmfrey.distributed.dao.model.Sample;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

/**
 * Created by dmfrey on 8/30/16.
 */
@MessagingGateway
public interface SampleGateway {

    @Gateway( requestChannel = "sample.input" )
    void process( Sample sample );

}
