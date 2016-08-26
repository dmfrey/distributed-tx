package io.pivotal.dmfrey.distributed.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by dmfrey on 8/26/16.
 */
@Entity
@Data
public class SampleXml {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Lob
    private String xml;

    @Column
    private Timestamp created;

    @OneToOne
    private Sample sample;

}
