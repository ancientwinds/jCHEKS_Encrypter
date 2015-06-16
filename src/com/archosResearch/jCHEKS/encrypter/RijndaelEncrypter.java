package com.archosResearch.jCHEKS.encrypter;

import com.archosResearch.jCHEKS.concept.chaoticSystem.AbstractChaoticSystem;
import com.archosResearch.jCHEKS.concept.encrypter.AbstractEncrypter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.logging.*;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author fgagnon
 */
public class RijndaelEncrypter extends AbstractEncrypter{

    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String ALGORITHM = "AES";
    private static final String DIGEST = "MD5";
    
    private final MessageDigest digest;

    private final Cipher cipher;
    
    public RijndaelEncrypter() throws NoSuchAlgorithmException, NoSuchPaddingException {
        this.digest = MessageDigest.getInstance(DIGEST);  
        this.cipher = Cipher.getInstance(TRANSFORMATION);
    }
    
    @Override
    public String encrypt(String text, AbstractChaoticSystem chaoticSystem) {
        
        byte[] encryptedData;
        try {
            IvParameterSpec IVParamSpec = new IvParameterSpec(chaoticSystem.IV());
            SecretKey password = new SecretKeySpec(this.digest.digest(chaoticSystem.Key()), ALGORITHM);

            this.cipher.init(Cipher.ENCRYPT_MODE, password, IVParamSpec);
            encryptedData = this.cipher.doFinal(text.getBytes("UTF8"));
          
            return new BASE64Encoder().encode(encryptedData);
            
        } catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(RijndaelEncrypter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String decrypt(String text, AbstractChaoticSystem chaoticSystem) {        
        
        try {
            IvParameterSpec IVParamSpec = new IvParameterSpec(chaoticSystem.IV());
            SecretKey password = new SecretKeySpec(this.digest.digest(chaoticSystem.Key()), ALGORITHM);

            this.cipher.init(Cipher.DECRYPT_MODE, password, IVParamSpec);
            byte[] decodedData = new BASE64Decoder().decodeBuffer(text);
            byte[] decryptedData = this.cipher.doFinal(decodedData);
            
            return new String(decryptedData);
            
            
        } catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | IOException ex) {
            Logger.getLogger(RijndaelEncrypter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
