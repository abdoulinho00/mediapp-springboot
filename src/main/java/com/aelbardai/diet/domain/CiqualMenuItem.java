package com.aelbardai.diet.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * Entity fetched from ciqual database
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CiqualMenuItem{

    private String ORIGGPCD;
    private String ORIGGPFR;
    private String ORIGFDCD;
    private String ORIGFDNM;
    private String EnergieKJ;
    private String EnergieKcal;
    private String EnergieNKJ; //, N x facteur Jones, avec fibres  (kJ/100g)
    private String EnergieNKcak; //, N x facteur Jones, avec fibres  (kcal/100g)
    private String Eau; // (g/100g)
    private String Proteines; // (g/100g)
    private String Proteinesbrutes; //, N x 6.25 (g/100g)
    private String Glucides; // (g/100g)
    private String Lipides; // (g/100g)
    private String Sucres; // (g/100g)
    private String Amidon; // (g/100g)
    private String Fibresalimentaires;  //(g/100g)
    private String Polyolstotaux; // (g/100g)
    private String Cendres;  //(g/100g)
    private String Alcool; // (g/100g)
    private String Acidesorganiques; // (g/100g)
    private String AGsatures; // (g/100g)
    private String AGmonoinsatures; // (g/100g)
    private String AGpolyinsatures; // (g/100g)
    private String AG40; //, butyrique (g/100g)
    private String AG60; //, caproïque (g/100g)
    private String AG80; //, caprylique (g/100g)
    private String AG100; //, caprique (g/100g)
    private String AG120; //, laurique (g/100g)
    private String AG140; //, myristique (g/100g)
    private String AG160; //, palmitique (g/100g)
    private String AG180; //, stéarique (g/100g)
    private String AG181; // 9c (n-9), oléique (g/100g)
    private String AG182; // 9c,12c (n-6), linoléique (g/100g)
    private String AG183; // c9,c12,c15 (n-3), alpha-linolénique (g/100g)
    private String AG204; // 5c,8c,11c,14c (n-6), arachidonique (g/100g)
    private String AG205; // 5c,8c,11c,14c,17c (n-3) EPA (g/100g)
    private String AG226; //  4c,7c,10c,13c,16c,19c (n-3) DHA (g/100g)
    private String Cholesterol; // (mg/100g)
    private String SelchlorureSodium; // (g/100g)
    private String Calcium;  //(mg/100g)
    private String Chlorure; // (mg/100g)
    private String Cuivre; //  (mg/100g)
    private String Fer; //  (mg/100g)
    private String Iode;  // (µg/100g)
    private String Magnesium; //  (mg/100g)
    private String Manganese;// (mg/100g)
    private String Phosphore; // (mg/100g)
    private String Potassium; // (mg/100g)
    private String Selenium; // (µg/100g)
    private String Sodium; // (mg/100g)
    private String Zinc; // (mg/100g)
    private String Retinol; // (µg/100g)
    private String BetaCarotene; // (µg/100g)
    private String VitamineD;  //(µg/100g)
    private String VitamineE; //  (mg/100g)
    private String VitamineK1; // (µg/100g)
    private String VitamineK2; // (µg/100g)
    private String VitamineC; // (mg/100g)
    private String VitamineB1; //  ou Thiamine (mg/100g)
    private String VitamineB2; // ou Riboflavine (mg/100g)
    private String VitamineB3; //  ou PP ou Niacine (mg/100g)
    private String VitamineB5; // ou Acide pantothénique (mg/100g)
    private String VitamineB6; // (mg/100g)
    private String VitamineB9; //ou Folates totaux (µg/100g)
    private String VitamineB12; //(µg/100g)

    @Override
    public String toString(){
        return String.format("ORIGGPFR: %s Energie : %sKJ" ,ORIGGPFR, EnergieKcal);
    }
}
