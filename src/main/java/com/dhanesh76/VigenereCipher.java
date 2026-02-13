package com.dhanesh76;

public class VigenereCipher {
    public static String crypt(String text, String key, int mode) {
        text = text.toUpperCase(); key = key.toUpperCase();

        StringBuilder result = new StringBuilder();
        for (int i = 0, k = 0; i < text.length(); i++) {
            int textVal = text.charAt(i) - 'A';
            int keyVal  = key.charAt(k) - 'A';
            int shifted = ((textVal + mode * keyVal) + 26) % 26 ;
            result.append((char) (shifted + 'A'));
            k = (k + 1) % key.length();
        }
        return result.toString();
    }
    public static void main(String[] args) {
        String text = args[0], key  = args[1];

        String encrypted = crypt(text, key, 1);
        String decrypted = crypt(encrypted, key, -1);

        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}
