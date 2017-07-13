package com.aelbardai.patient.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstheticSession {

    @Id
    @GeneratedValue
    private long id;
    private Date date;


    @ManyToOne(cascade = {CascadeType.ALL},fetch= FetchType.EAGER)
    @JoinColumn(name="esthetic_visit_id")
    private EstheticVisit estheticVisit;
}
