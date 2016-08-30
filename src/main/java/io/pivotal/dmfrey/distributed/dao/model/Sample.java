package io.pivotal.dmfrey.distributed.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by dmfrey on 8/26/16.
 */
@Entity
@Data
public class Sample {

    public enum Event { Billing, Alert };

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String message;

    @Column
    private Timestamp created;

    @Enumerated( EnumType.STRING )
    private Event event;

}
