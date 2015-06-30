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
    public static String encodeMessage(byte[] key, String message) throws MessageCheckerException {
        try {
            Mac sha256_hmac = Mac.getInstance("Hmacsha256");
            SecretKeySpec secretKey = new SecretKeySpec(key, "HmacSHA256");
            sha256_hmac.init(secretKey);
            
            return Base64.getEncoder().encodeToString(sha256_hmac.doFinal(message.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException | InvalidKeyException | UnsupportedEncodingException ex) {
            throw new MessageCheckerException("Error while encoding the message check.", ex);
        }
    }
}
