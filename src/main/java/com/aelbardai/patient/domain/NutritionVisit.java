package com.aelbardai.patient.domain;


import lombok.*;

import javax.persistence.*;

/**
 * Represents a visit of a nutrition patient
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class NutritionVisit extends Visit{

    /**
     *
     */
    private static final long serialVersionUID = 7982951330048611643L;

    /*
     * Health status attributes
     */
    private float height;
    private float weight;

    private float waistSize;
    private float armSize;
    private float hipSize;
    private float wristSize;

    private float baseMetabolism; // in Kcal
    private short biologicalAge;
    private float water; // in %
    private float boneMass;
    private float visceralFat;
    private float leanMass;
    private float fatMass;

    /*
     *  path to image file
     */
    private String beforePath;
    private String afterPath;
}
