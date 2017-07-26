package com.aelbardai.diet.domain;

import org.springframework.util.StringUtils;

/**
 * Created by dev on 7/26/17.
 */
public class MenuItemMapper {

    public static MenuItem convert(CiqualMenuItem ciqualMenuItem){
        return MenuItem.builder()
                .name(ciqualMenuItem.getORIGFDNM())
                .calories(parseToDouble(ciqualMenuItem.getEnergieKcal()))
                .proteins(parseToDouble(ciqualMenuItem.getProteines()))
                .carbs(parseToDouble(ciqualMenuItem.getGlucides()))
                .fat(parseToDouble(ciqualMenuItem.getLipides()))
                .build();
    }

    private static Double parseToDouble(String string){
        try {
            return Double.parseDouble(StringUtils.trimAllWhitespace(string));
        }
        catch (NumberFormatException ex){
            return 0.;
        }
    }
}
