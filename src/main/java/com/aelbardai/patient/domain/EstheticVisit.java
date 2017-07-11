package com.aelbardai.patient.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Represents a visit for an esthetic purpose
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class EstheticVisit extends Visit{

    private String allergiqueHistory;
    private String dermatologiqueHistory;
    private String estheticTreatmentHistory;

    private String classification;
    private String dermatologiqueExamination;
    private String proposedProtocol;
    private String estheticCondition;
    private String observations;

    @OneToMany(mappedBy="estheticVisit",
            fetch=FetchType.EAGER ,cascade = {CascadeType.ALL})
    @OrderBy("date ASC")
    private List<EstheticSession> sessions;


    @ManyToOne(cascade = {CascadeType.ALL},fetch= FetchType.EAGER)
    @JoinColumn(name="patient_id")
    private Patient patient;
}
