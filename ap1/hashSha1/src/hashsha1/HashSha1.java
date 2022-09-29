package hashsha1;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashSha1 {
    public static void main(String[] args) throws NoSuchAlgorithmException{
        try {
            //tem como objetivo identificar o tipo de criptografia
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(args[0].getBytes());
            
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
 
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            
            System.out.println("Hash Sha1: " + " : " + hashtext);
        }catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}