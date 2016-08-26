package io.pivotal.dmfrey.distributed.dao.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by dmfrey on 8/26/16.
 */
@Entity
@Data
public class Sample {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String message;

    @Column
    private Timestamp created;

}
