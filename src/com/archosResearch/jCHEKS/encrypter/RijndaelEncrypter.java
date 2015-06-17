package com.archosResearch.jCHEKS.encrypter;

import com.archosResearch.jCHEKS.concept.chaoticSystem.AbstractChaoticSystem;
import com.archosResearch.jCHEKS.concept.encrypter.AbstractEncrypter;
import com.archosResearch.jCHEKS.concept.exception.EncrypterException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.*;

/**
 *
 * @author Thomas Lepage thomas.lepage@hotmail.ca
 */
public class RijndaelEncrypter extends AbstractEncrypter {

    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String ALGORITHM = "AES";
    // TODO : Use SHA2 instead of MD5
    private static final String DIGEST = "MD5";

    private final MessageDigest digest;
    private final Cipher cipher;

    public RijndaelEncrypter() throws NoSuchAlgorithmException, NoSuchPaddingException {
        this.digest = MessageDigest.getInstance(DIGEST);
        this.cipher = Cipher.getInstance(TRANSFORMATION);
    }

    @Override
    public String encrypt(String textToEncrypt, AbstractChaoticSystem chaoticSystem) throws EncrypterException {

        try {
            SecretKey password = new SecretKeySpec(this.digest.digest(chaoticSystem.Key()), ALGORITHM);

            this.cipher.init(Cipher.ENCRYPT_MODE, password, new IvParameterSpec(chaoticSystem.IV()));
            byte[] encryptedData = this.cipher.doFinal(textToEncrypt.getBytes("UTF8"));

            return Base64.getEncoder().encodeToString(encryptedData);

        } catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException ex) {
            throw new EncrypterException("Rijndael Encryption error", ex);
        }
    }

    @Override
    public String decrypt(String textToDecrypt, AbstractChaoticSystem chaoticSystem) throws EncrypterException {

        try {
            SecretKey password = new SecretKeySpec(this.digest.digest(chaoticSystem.Key()), ALGORITHM);

            this.cipher.init(Cipher.DECRYPT_MODE, password, new IvParameterSpec(chaoticSystem.IV()));
            byte[] decodedData = Base64.getDecoder().decode(textToDecrypt);
            byte[] decryptedData = this.cipher.doFinal(decodedData);

            return new String(decryptedData);

        } catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException ex) {
            throw new EncrypterException("Rijndael Decryption error", ex);
        }
    }
}
