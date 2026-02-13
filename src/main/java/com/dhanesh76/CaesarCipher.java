package com.dhanesh76;

public class CaesarCipher {
    static String encrypt(String plainText, int key){
        var builder = new StringBuilder();
        for(char c : plainText.toCharArray()){
            char ec = (char)('a'+ (c-'a'+key) % 26) ;
            builder.append(ec);
        }
        return builder.toString();
    }

    static String decrypt(String encryptedText, int key){
        var builder = new StringBuilder();
        for(char c : encryptedText.toCharArray()){
            char dc = (char)('a' + (c-'a'-key + 26) % 26) ;
            builder.append(dc);
        }
        return builder.toString();
    }

    static void main(String[] args) {
        var plainText = args[1];
        var key = Integer.parseInt(args[3]);

        var encrypted = encrypt(plainText, key);
        System.out.println("encrypted: " + encrypted);
        System.out.println("decrypted: " + decrypt(encrypted, key));
    }
}
