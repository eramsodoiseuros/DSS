package BL;

import UI.Menu;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

public class LeitorQR {
    public static String generateQR(final String password) {
        Random rand = new Random();
        int r = rand.nextInt(1000);
        String yolo = r + "!?XD";
        try {
            byte[] hash = MessageDigest.getInstance("SHA-512").digest((password + yolo).getBytes());
            return Base64.getEncoder().encodeToString(hash).substring(0,5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(generateQR("ok"));
    }
}
