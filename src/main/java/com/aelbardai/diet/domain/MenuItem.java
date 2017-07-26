package com.aelbardai.diet.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    //breakfast, lunch, dinner
    private MenuType type;
    private String content;
    private String description;
    private double calories;
    private double proteins;
    private double carbs;
    private double fat;
}
