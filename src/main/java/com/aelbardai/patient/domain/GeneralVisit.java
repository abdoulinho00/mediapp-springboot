package com.aelbardai.patient.domain;


import lombok.*;

import javax.persistence.Entity;

/**
 *
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class GeneralVisit extends Visit {
}
