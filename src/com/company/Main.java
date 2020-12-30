package com.company;

import java.util.Scanner;
import java.lang.Math;

public class Main {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        System.out.println("Enter string to be encrypted using lowercase characters a-z");
        String s = userInput.nextLine();

        boolean valid = regexCheck(s);

        while (!valid) {
            s = userInput.nextLine();
            valid = regexCheck(s);
        }

        encrypt(s);
        userInput.close();
    }

    // ensure input is valid
    private static boolean regexCheck(String s) {
        if (s.matches("[a-z\\s]+")) {
            return true;
        }
        else {
            System.out.println("Input only lowercase characters a-z");
            return false;
        }
    }

    private static void encrypt(String s) {
        // removes all whitespace and non-visible chars
        String noSpace = s.replaceAll("\\s","");

        // square root of length of string, noSpace, will be rounded down
        double row = Math.sqrt(noSpace.length());

        // integer conversion for array
        // casting double row to int truncates decimal --> same effect as Math.floor() function
        int intRow = (int) row;

        // square root of length of string, noSpace, rounded up
        double column = (Math.sqrt(noSpace.length()));
        // integer conversion for array
        int intCol = (int) Math.ceil(column);

        // final encrypted message
        String output = new String();

        // ensures grid is big enough
        if ((intRow * intCol) < noSpace.length()) {
            intRow = intRow + 1;
        }

        char[][] format = new char[intRow][intCol];

        // populate format array
        int k = 0;
        for (int i = 0; i < intRow; i++) {
            for (int j = 0; j < intCol; j++) {
                try {
                    // add char at element #k from noSpace to array
                    format[i][j] = noSpace.charAt(k);
                    k++;
                } catch(StringIndexOutOfBoundsException e) {
                    break;
                }
            }
        }

        // form output string
        int m = 0;
        for (int i = 0; i < intCol; i++) {
            for (int j = 0; j < intRow; j++) {
                // add a whitespace after finishing a column
                if ((m > 0) && (m % intRow == 0)) {
                    output = output + " " + format[j][i];
                }
                else {
                    output = output + format[j][i];
                }
                m++;
            }
        }
        System.out.println(output);
    }
}