package io.pivotal.dmfrey.distributed.dao.repository;

import io.pivotal.dmfrey.distributed.dao.model.SampleXml;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dmfrey on 8/26/16.
 */
public interface SampleXmlRepository extends CrudRepository<SampleXml, Long> {
    
}
