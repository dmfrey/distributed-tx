package io.pivotal.dmfrey.distributed.jms;

import static io.pivotal.dmfrey.distributed.dao.model.Sample.Event;

import io.pivotal.dmfrey.distributed.dao.model.Sample;
import io.pivotal.dmfrey.distributed.dao.model.SampleXml;
import io.pivotal.dmfrey.distributed.dao.service.SampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by dmfrey on 8/26/16.
 */
@Component
@Slf4j
public class Receiver {

    private final SampleService sampleService;
    private final Unmarshaller unmarshaller;

    @Autowired
    public Receiver( final SampleService sampleService, final Unmarshaller unmarshaller ) {

        this.sampleService = sampleService;
        this.unmarshaller = unmarshaller;

    }

    @JmsListener( destination = "my-queue" )
    @Transactional
    public void onMessage( String message ) throws IOException {
        log.debug( "onMessage : enter" );

        log.info( "onMessage : message = " + message );

        Sample sample = parseSampleFromMessage( message );
        SampleXml sampleXml = parseSampleXmlFromMessage( message );
        sampleService.saveEvent( sample, sampleXml );

        log.debug( "onMessage : exit" );
    }

    private Sample parseSampleFromMessage( final String message ) throws IOException {

        Sample sample = (Sample) unmarshaller.unmarshal( new StreamSource( new StringReader( message ) ) );

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
