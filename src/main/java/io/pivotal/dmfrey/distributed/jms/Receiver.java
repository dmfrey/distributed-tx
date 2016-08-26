package io.pivotal.dmfrey.distributed.jms;

import io.pivotal.dmfrey.distributed.dao.model.Sample;
import io.pivotal.dmfrey.distributed.dao.model.SampleXml;
import io.pivotal.dmfrey.distributed.dao.service.SampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * Created by dmfrey on 8/26/16.
 */
@Component
@Slf4j
public class Receiver {

    private final SampleService sampleService;

    public Receiver( final SampleService sampleService ) {

        this.sampleService = sampleService;

    }

    @JmsListener( destination = "my-queue" )
    @Transactional
    public void onMessage( String message ) {
        log.debug( "onMessage : enter" );

        log.info( "onMessage : message = " + message );

        Sample sample = parseSampleFromMessage( message );
        SampleXml sampleXml = parseSampleXmlFromMessage( message );
        sampleService.saveEvent( sample, sampleXml );

        log.debug( "onMessage : exit" );
    }

    private Sample parseSampleFromMessage( final String message ) {

        Sample sample = new Sample();
        sample.setMessage( message );

        log.debug( "parseSampleFromMessage : sample = " + sample.toString() );

        return sample;
    }

    private SampleXml parseSampleXmlFromMessage( final String message ) {

        SampleXml sampleXml = new SampleXml();
        sampleXml.setXml( message );

        log.debug( "parseSampleXmlFromMessage : sampleXml = " + sampleXml.toString() );

        return sampleXml;
    }

}
