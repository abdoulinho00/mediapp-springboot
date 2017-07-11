package com.aelbardai.patient.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Abstract class for common attributes to a medical visit
 */
@Entity
@Data
public abstract class Visit {

    @Id
    @GeneratedValue
    private long id;
    private String reason;
    private String description;
    private Date visitTime;
}
