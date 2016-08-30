package io.pivotal.dmfrey.distributed.marshalling;

import io.pivotal.dmfrey.distributed.dao.model.Sample;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;

/**
 * Created by dmfrey on 8/30/16.
 */
@Configuration
public class MarshallingConfig {

    @Bean
    public Marshaller marshaller() {

        XStreamMarshaller xstreamMarshaller = new XStreamMarshaller();
        xstreamMarshaller.setSupportedClasses( Sample.class );
        xstreamMarshaller.getXStream().alias( "sample", Sample.class );

        return xstreamMarshaller;
    }

    @Bean
    public Unmarshaller unmarshaller( Marshaller marshaller ) {

        return (Unmarshaller) marshaller;
    }

}
