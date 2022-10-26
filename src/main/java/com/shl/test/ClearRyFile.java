package com.shl.test;

import java.math.BigDecimal;

public class ClearRyFile {

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("22");
        BigDecimal b = new BigDecimal("10").divide(new BigDecimal("13"), 16, BigDecimal.ROUND_HALF_UP);

        a = a.multiply(b).divide(new BigDecimal("2"), 16, BigDecimal.ROUND_HALF_UP)
                .add(new BigDecimal("6").multiply(b));

        System.out.println(a.doubleValue());

        a = a.multiply(new BigDecimal("100")).multiply(new BigDecimal(4));
        System.out.println(a.doubleValue());

        a = new BigDecimal("22").divide(new BigDecimal(2))
                .add(new BigDecimal("6")).multiply(new BigDecimal("10")).multiply(new BigDecimal("100"))
                .multiply(new BigDecimal("4")).divide(new BigDecimal("13"), 4, BigDecimal.ROUND_HALF_UP);
        System.out.println(a.doubleValue());

    }



}
