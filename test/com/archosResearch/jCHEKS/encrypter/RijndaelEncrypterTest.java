/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    private final String encrypted = "44PeNnJx0s8G7UT0zrOmF3gT7Ix0LcNWbVHB4P2g2LhLuJSmnSmabYhcpHxRWiY4";
    private final String decrypted = "Voici les tests pour l'encrypter";
    
    @Test
    public void constructor_should_create() throws NoSuchAlgorithmException, NoSuchPaddingException {

        RijndaelEncrypter instance = new RijndaelEncrypter();
        assertNotNull(instance);        
    }

    @Test
    public void decrypt_should_decrypt_the_message() throws NoSuchAlgorithmException, NoSuchPaddingException, EncrypterException, Exception {
        
        RijndaelEncrypter instance = new RijndaelEncrypter(); 
        AbstractChaoticSystem chaoticSystem = new MockChaoticSystem(128);
        byte[] key = chaoticSystem.getKey(128);
        byte[] iv = chaoticSystem.getKey(16);
        String result = instance.decrypt(this.encrypted, key, iv);        
        assertEquals(result, this.decrypted);
    }
    
    @Test
    public void encrypt_should_encrypt_the_message() throws NoSuchAlgorithmException, NoSuchPaddingException, EncrypterException, Exception {
        
        RijndaelEncrypter instance = new RijndaelEncrypter();
        AbstractChaoticSystem chaoticSystem = new MockChaoticSystem(128);
        byte[] key = chaoticSystem.getKey(128);
        byte[] iv = chaoticSystem.getKey(16);        
        String result = instance.encrypt(this.decrypted, key, iv);
        assertEquals(result, this.encrypted);
    }
    
}
