package com.dhanesh76;

import java.awt.Point;
import java.util.*;

public class PlayfairCipher {

    private static final int SIZE = 5;
    private static final char BOGUS = 'x';

    private final char[][] matrix = new char[SIZE][SIZE];
    private final Map<Character, Point> position = new HashMap<>();


    public String encrypt(String plainText, String key) {
        buildMatrix(key);
        plainText = preparePlaintext(plainText);
        return process(plainText, true);
    }

    public String decrypt(String cipherText, String key) {
        buildMatrix(key);
        return process(cipherText, false);
    }

    private String process(String text, boolean encrypt) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);

            Point p1 = position.get(a);
            Point p2 = position.get(b);

            // same row
            if (p1.x == p2.x) {
                result.append(matrix[p1.x][shift(p1.y, encrypt)]);
                result.append(matrix[p2.x][shift(p2.y, encrypt)]);
            }
            // same column
            else if (p1.y == p2.y) {
                result.append(matrix[shift(p1.x, encrypt)][p1.y]);
                result.append(matrix[shift(p2.x, encrypt)][p2.y]);
            }
            // rectangle
            else {
                result.append(matrix[p1.x][p2.y]);
                result.append(matrix[p2.x][p1.y]);
            }
        }
        return result.toString();
    }

    private int shift(int value, boolean encrypt) {
        var offset = encrypt ? 1 : -1;
        return (value + offset +  SIZE) % SIZE;
    }

    private void buildMatrix(String key) {
        position.clear();

        Set<Character> used = new HashSet<>();
        key = normalize(key);

        int index = 0;

        for (char ch : (key + "abcdefghijklmnopqrstuvwxyz").toCharArray()) {
            if (!used.add(ch)) continue;

            matrix[index / SIZE][index % SIZE] = ch;
            position.put(ch, new Point(index / SIZE, index % SIZE));
            index++;

            if (index == SIZE * SIZE) break;
        }
    }

    private String preparePlaintext(String text) {
        text = normalize(text);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length()-1; i++) {
            char a = text.charAt(i);
            char b = text.charAt(i+1);

            sb.append(a);
            if (a == b) sb.append(BOGUS);

            if(i == text.length()-2) sb.append(b);
        }

        if (sb.length() % 2 != 0) sb.append(BOGUS);
        return sb.toString();
    }

    private String normalize(String text) {
        return text.toLowerCase().replace('j', 'i');
    }

    public static void main(String[] args) {
        String plainText = args[1], key = args[3];
        var playfair = new PlayfairCipher();
        var encrypted = playfair.encrypt(plainText, key);
        System.out.println("encrypted: " +  encrypted);
        System.out.println("decrypted: " + playfair.decrypt(encrypted, key));
    }
}