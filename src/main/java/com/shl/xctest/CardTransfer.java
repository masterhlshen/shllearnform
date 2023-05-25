package com.shl.xctest;

import java.util.LinkedList;
import java.util.List;

public class CardTransfer {

    public static void main(String[] args) {

        int i = 1066365317;
        String s = "59F62B65";
        String trans = Integer.toHexString(i);
        int index = trans.length();
        List<String> list = new LinkedList<>();
        while (index > 0) {
            list.add(trans.substring(index - 2, index));
            index -= 2;
        }
        System.out.println(String.join("", list).toUpperCase());


    }

    static void trans(String trans) {
        trans = trans.toUpperCase();
        int index = trans.length();
        List<String> list = new LinkedList<>();
        while (index > 0) {
            list.add(trans.substring(index - 2, index));
            index -= 2;
        }
        System.out.println(String.join("", list).toUpperCase());
    }
}
