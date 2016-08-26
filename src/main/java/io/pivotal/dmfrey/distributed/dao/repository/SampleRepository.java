package io.pivotal.dmfrey.distributed.dao.repository;

import io.pivotal.dmfrey.distributed.dao.model.Sample;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dmfrey on 8/26/16.
 */
public interface SampleRepository extends CrudRepository<Sample, Long> {

}
