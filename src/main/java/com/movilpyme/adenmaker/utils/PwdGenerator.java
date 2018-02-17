package com.movilpyme.adenmaker.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Created by jdominguez on 1/27/17.
 */
public class PwdGenerator {

    public static String passwordMD5(String pwd) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pwd.getBytes("UTF-8"));
        return convertByteToHex(md.digest());
    }

    public static String passwordSHA512(String pwd) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(pwd.getBytes("UTF-8"));
        return convertByteToHex(md.digest());
    }

    public static String convertByteToHex(byte data[]) {
        StringBuilder hexData = new StringBuilder();
        for (int byteIndex = 0; byteIndex < data.length; byteIndex++) {
            hexData.append(Integer.toString((data[byteIndex] & 0xff) + 0x100, 16).substring(1));
        }
        return hexData.toString();
    }

    public static String generatePassword(int size) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890/@*-";
        StringBuilder salt = new StringBuilder();
        Random random = new Random();
        while (salt.length() < size) {
            int index = (int) (random.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltString = salt.toString();
        return saltString;
    }

}
