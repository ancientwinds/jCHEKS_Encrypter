package com.archosResearch.jCHEKS.encrypter;

import com.archosResearch.jCHEKS.concept.chaoticSystem.AbstractChaoticSystem;
import com.archosResearch.jCHEKS.concept.encrypter.AbstractEncrypter;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.logging.*;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author fgagnon
 */
public class RijndaelEncrypter extends AbstractEncrypter{

    private static final String TRANSFORMATION = "AES/CBC/NoPadding";
    private static final String ALGORITHM = "AES";
    private static final String DIGEST = "MD5";
    
    private final MessageDigest digest;

    private final Cipher cipher;
    
    public RijndaelEncrypter() throws NoSuchAlgorithmException, NoSuchPaddingException {
        this.digest = MessageDigest.getInstance(DIGEST);  
        this.cipher = Cipher.getInstance(TRANSFORMATION);
    }
    
    @Override
    public byte[] encrypt(byte[] text, AbstractChaoticSystem chaoticSystem) {
        
        byte[] encryptedData;
        
        try {
            IvParameterSpec IVParamSpec = new IvParameterSpec(chaoticSystem.IV());
            SecretKey password = new SecretKeySpec(this.digest.digest(chaoticSystem.Key()), ALGORITHM);

            this.cipher.init(Cipher.ENCRYPT_MODE, password, IVParamSpec);
            encryptedData = this.cipher.doFinal(text);
            return encryptedData;
            
        } catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(RijndaelEncrypter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public byte[] decrypt(byte[] text, AbstractChaoticSystem chaoticSystem) {
        try {
            IvParameterSpec IVParamSpec = new IvParameterSpec(chaoticSystem.IV());
            SecretKey password = new SecretKeySpec(this.digest.digest(chaoticSystem.Key()), ALGORITHM);

            this.cipher.init(Cipher.DECRYPT_MODE, password, IVParamSpec);
            byte[] decryptedData = this.cipher.doFinal(text);
            return decryptedData;
        } catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(RijndaelEncrypter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
