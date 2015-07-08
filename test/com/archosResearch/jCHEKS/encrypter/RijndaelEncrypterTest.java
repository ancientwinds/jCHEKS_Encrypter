package com.archosResearch.jCHEKS.encrypter;

import com.archosResearch.jCHEKS.concept.chaoticSystem.AbstractChaoticSystem;
import com.archosResearch.jCHEKS.concept.exception.EncrypterException;
import com.archosResearch.jCHEKS.encrypter.mock.MockChaoticSystem;
import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thomas Lepage thomas.lepage@hotmail.ca
 */
public class RijndaelEncrypterTest {
    
    private final String encrypted = "XOeEwFuk1FeWoAqZqTNNAvNt9qlMpQJ0oQRGpOuB650wkLIRO6zJyXHcTmROWVby";
    private final String decrypted = "Voici les tests pour l'encrypter";
    
    @Test
    public void constructor_should_create() throws NoSuchAlgorithmException, NoSuchPaddingException {

        RijndaelEncrypter instance = new RijndaelEncrypter();
        assertNotNull(instance);        
    }

    @Test
    public void decrypt_should_decrypt_the_message() throws NoSuchAlgorithmException, NoSuchPaddingException, EncrypterException, Exception {
        
        RijndaelEncrypter instance = new RijndaelEncrypter(); 
        AbstractChaoticSystem chaoticSystem = new MockChaoticSystem(instance.getByteNeeded());
        byte[] key = chaoticSystem.getKey(instance.getByteNeeded());
        String result = instance.decrypt(this.encrypted, key);        
        assertEquals(result, this.decrypted);
    }
    
    @Test
    public void encrypt_should_encrypt_the_message() throws NoSuchAlgorithmException, NoSuchPaddingException, EncrypterException, Exception {
        
        RijndaelEncrypter instance = new RijndaelEncrypter();
        AbstractChaoticSystem chaoticSystem = new MockChaoticSystem(instance.getByteNeeded());
        byte[] key = chaoticSystem.getKey(instance.getByteNeeded());
        String result = instance.encrypt(this.decrypted, key);
        assertEquals(result, this.encrypted);
    }
    
}
