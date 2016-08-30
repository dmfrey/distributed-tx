package io.pivotal.dmfrey.distributed.integration;

import io.pivotal.dmfrey.distributed.dao.model.Sample;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Router;

/**
 * Created by dmfrey on 8/30/16.
 */
@MessageEndpoint
public class SampleRouter {

    @Router( inputChannel = "sample.input" )
    public String routeEvent( Sample sample ) {

        switch( sample.getEvent() ) {

            case Billing:
                return "billing";

            case Alert:
                return "alert";

            default:
                return "error";

        }

    }

}
