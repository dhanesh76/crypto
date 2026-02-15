package com.dhanesh76.out.com.dhanesh76;

import java.util.Arrays;

public class RailCipher {

    String encrypt(String plain, int depth) {
        var table = new StringBuilder[depth];
        for (int i=0; i < depth; i++) table[i] = new StringBuilder();

        int r = 0, d = 1;
        for (char c : plain.toCharArray()) {
            table[r].append(c);
            if (r == 0 || r == depth - 1) d *= -1;
            r += d;
        }
        var cipher = new StringBuilder();
        for (var row : table) cipher.append(row);
        return cipher.toString();
    }

    String decrypt(String cipher, int depth){

        return "";
    }

    static void main(String[] args) {
        String text = args[0]; int depth = Integer.parseInt(args[0]);
        System.out.println( new RailCipher().encrypt(text, depth));
    }
}
