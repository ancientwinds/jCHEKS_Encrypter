package com.archosResearch.jCHEKS.messageChecker;

import com.archosResearch.jCHEKS.concept.chaoticSystem.AbstractChaoticSystem;
import com.archosResearch.jCHEKS.encrypter.mock.MockChaoticSystem;
import com.archosResearch.jCHEKS.messageChecker.exception.MessageCheckerException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author etudiant
 */
public class MessageCheckerTest {

    private static final String message = "MessageChecker test.";
    private static final String messageChecked = "DyVN0oWKkxgbuJrk50Xrq3Nh8+lDUkTBJwYffVbFEZs=";
    
    /**
     * Test of encodeMessage method, of class MessageChecker.
     * @throws java.lang.Exception
     */
    @Test
    public void testEncodeMessage() throws Exception {
        AbstractChaoticSystem chaoticSystem = new MockChaoticSystem(MessageChecker.getKeyLength());
        String result = MessageChecker.encodeMessage(chaoticSystem.getKey(), message);
        assertEquals(result, messageChecked);
    }

    /**
     * Test of validateMessage method, of class MessageChecker.
     */
    @Test
    public void testValidateMessage() throws Exception {
        AbstractChaoticSystem chaoticSystem = new MockChaoticSystem(MessageChecker.getKeyLength());
        assertTrue(MessageChecker.validateMessage(chaoticSystem.getKey(), message, messageChecked));
    }
    
}
