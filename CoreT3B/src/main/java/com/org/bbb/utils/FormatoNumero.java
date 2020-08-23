package com.org.bbb.utils;

import java.text.DecimalFormat;

import org.springframework.stereotype.Component;

@Component("formatNumber")
public class FormatoNumero {

    public Double formato2Dec(float numero) {

        Double decimal = Double.valueOf(numero);
        DecimalFormat decFormat = new DecimalFormat("#.00");
        double formatDecimal = new Double(decFormat.format(decimal)).doubleValue();
        return formatDecimal;
    }

    public String redondeaCero(Double decimal) {
        String regreso = null;
        String tmp = "";
        String[] array = new String[2];
        tmp = String.valueOf(decimal);
        array = tmp.split("\\.");
        if (array[1].length() == 1) {
            regreso = tmp + "0";
        } else {
            regreso = tmp;
        }
        return regreso;
    }
	
}
