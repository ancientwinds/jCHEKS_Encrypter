package com.archosResearch.jCHEKS.messageChecker;

import com.archosResearch.jCHEKS.messageChecker.exception.MessageCheckerException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Thomas Lepage thomas.lepage@hotmail.ca
 */
public class MessageChecker {
    
    private static final int keyLength = 16;
    
    public static String encodeMessage(byte[] key, String message) throws MessageCheckerException {
        try {
            Mac hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(key, "HmacSHA256");
            hmac.init(secretKey);
            
            return Base64.getEncoder().encodeToString(hmac.doFinal(message.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException | InvalidKeyException | UnsupportedEncodingException ex) {
            throw new MessageCheckerException("Error while encoding the message check.", ex);
        }
    }
    
    public static boolean validateMessage(byte[] key, String message, String encodedMessage) throws MessageCheckerException {
        try {
            String messageToValidate = MessageChecker.encodeMessage(key, message);            
            
            return messageToValidate.equals(encodedMessage);
        } catch (MessageCheckerException ex) {
            throw new MessageCheckerException("Error while validating the message");
        }
    }
    
    public static int getKeyLength() {
        return MessageChecker.keyLength * 8;
    }
}
