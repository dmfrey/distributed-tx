package io.pivotal.dmfrey.distributed.dao.service;

import io.pivotal.dmfrey.distributed.dao.model.Sample;
import io.pivotal.dmfrey.distributed.dao.model.SampleXml;
import io.pivotal.dmfrey.distributed.dao.repository.SampleRepository;
import io.pivotal.dmfrey.distributed.dao.repository.SampleXmlRepository;
import io.pivotal.dmfrey.distributed.integration.IntegrationConfig;
import io.pivotal.dmfrey.distributed.integration.SampleGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * Created by dmfrey on 8/26/16.
 */
@Service
@Slf4j
public class SampleService {

    private final SampleRepository sampleRepository;
    private final SampleXmlRepository sampleXmlRepository;
    private final SampleGateway sampleGateway;

    public SampleService( final SampleRepository sampleRepository, final SampleXmlRepository sampleXmlRepository, final SampleGateway sampleGateway ) {

        this.sampleRepository = sampleRepository;
        this.sampleXmlRepository = sampleXmlRepository;
        this.sampleGateway = sampleGateway;

    }

    @Transactional( propagation = Propagation.MANDATORY )
    public void saveEvent( Sample sample, SampleXml sampleXml ) {
        log.debug( "saveEvent : enter" );

        sample.setCreated( new Timestamp( System.currentTimeMillis() ) );

        Sample sampleCreated = sampleRepository.save( sample );
        if( null != sampleCreated ) {
            log.info( "saveEvent : sampleCreated = " + sampleCreated );

            sampleGateway.process( sampleCreated );

            sampleXml.setSample( sampleCreated );
            sampleXml.setCreated( new Timestamp( System.currentTimeMillis() ) );

            SampleXml sampleXmlCreated = sampleXmlRepository.save( sampleXml );
            if( null != sampleXmlCreated ) {
                log.info( "saveEvent : sampleXmlCreated = " + sampleXmlCreated );
            }

        }

        log.debug( "saveEvent : exit" );
    }

}
