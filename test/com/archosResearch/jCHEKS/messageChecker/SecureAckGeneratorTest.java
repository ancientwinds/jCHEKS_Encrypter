package com.archosResearch.jCHEKS.messageChecker;

import com.archosResearch.jCHEKS.concept.chaoticSystem.AbstractChaoticSystem;
import com.archosResearch.jCHEKS.encrypter.mock.MockChaoticSystem;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thomas Lepage thomas.lepage@hotmail.ca
 */
public class SecureAckGeneratorTest {
    
    private static final String message = "MessageChecker test.";
    private static final String messageChecked = "mUKC11+jxcBGZGEIf3IJ3g==";
    
    @Test
    public void testGenerateSecureAck() throws Exception {
        AbstractChaoticSystem chaoticSystem = new MockChaoticSystem(SecureAckGenerator.getKeyLength());
        String result = SecureAckGenerator.generateSecureAck(chaoticSystem.getKey(), message);
        assertEquals(result, messageChecked);
    }

    @Test
    public void testValidateSecureAck() throws Exception {
        AbstractChaoticSystem chaoticSystem = new MockChaoticSystem(SecureAckGenerator.getKeyLength());
        assertTrue(SecureAckGenerator.validateSecureAck(chaoticSystem.getKey(), message, messageChecked));
    }
    
}
