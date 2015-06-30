package com.archosResearch.jCHEKS.messageChecker;

import com.archosResearch.jCHEKS.messageChecker.exception.SecureAckGeneratorException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 *
 * @author Thomas Lepage thomas.lepage@hotmail.ca
 */
public class SecureAckGenerator {
    
    private static final int keyLength = 16;
    
    public static String generateSecureAck(byte[] key, String message) throws SecureAckGeneratorException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(key);
            messageDigest.update(message.getBytes("UTF-8"));
            
            return Base64.getEncoder().encodeToString(messageDigest.digest());
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            throw new SecureAckGeneratorException("Error while generating the secure ack.", ex);
        }
    }
    
    public static boolean validateSecureAck(byte[] key, String message, String secureAck) throws SecureAckGeneratorException {
        try {
            String newSecureAck = SecureAckGenerator.generateSecureAck(key, message);
            return newSecureAck.equals(secureAck);
        } catch (SecureAckGeneratorException ex) {
            throw new SecureAckGeneratorException("Error while validating the secure ack");
        }
       
    }
    
    public static int getKeyLength() {
        return SecureAckGenerator.keyLength * 8;
    }
}