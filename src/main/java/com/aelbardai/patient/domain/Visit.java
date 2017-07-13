package com.aelbardai.patient.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Abstract class for common attributes to a medical visit
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Visit {

    @Id
    @GeneratedValue
    private long id;
    private String reason;
    private String description;
    @Column(name ="visit_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date visittime;
    /*
     * Attached patient
     */
    @ManyToOne(targetEntity = Patient.class)
    @JoinColumn(name="patient_id")
    @JsonIgnore
    private Patient patient;
}
