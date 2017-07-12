package com.aelbardai.patient.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Represents a patient entry
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    private static final long serialVersionUID = 6120632182834458834L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String fullname;
    private String email;
    private String phoneNumber;
    private String adress;
    private String gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    @Transient
    private int age;
    private String profession;
    private String identification;
    private String allergies;
    private float maxWeight;
    private float minWeight;
    private float goodWeight;
    private float maxIMC;
    private float minIMC;
    private float goodIMC;
    private String personalHistory;
    private String familyHistory;
    private String taste;
    private String water;
    private String treatment;
    private String observations;
    private String physicalActivity;
    @OneToMany(mappedBy="patient",
            fetch=FetchType.EAGER ,cascade = {CascadeType.ALL})
    @OrderBy("visitTime DESC")
    private List<NutritionVisit> nutritionVisits;
    @OneToMany(mappedBy="patient",
            fetch=FetchType.EAGER ,cascade = {CascadeType.ALL})
    @OrderBy("visitTime DESC")
    private List<EstheticVisit> estheticVisits;

    private String picturePath;
}
