package com.archosResearch.jCHEKS.messageChecker;

import com.archosResearch.jCHEKS.concept.chaoticSystem.AbstractChaoticSystem;
import com.archosResearch.jCHEKS.encrypter.mock.MockChaoticSystem;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thomas Lepage thomas.lepage@hotmail.ca
 */
public class MessageCheckerTest {

    private static final String message = "MessageChecker test.";
    private static final String messageChecked = "DyVN0oWKkxgbuJrk50Xrq3Nh8+lDUkTBJwYffVbFEZs=";
    
    @Test
    public void testEncodeMessage() throws Exception {
        AbstractChaoticSystem chaoticSystem = new MockChaoticSystem(MessageChecker.getKeyLength());
        String result = MessageChecker.encodeMessage(chaoticSystem.getKey(), message);
        assertEquals(result, messageChecked);
    }

    @Test
    public void testValidateMessage() throws Exception {
        AbstractChaoticSystem chaoticSystem = new MockChaoticSystem(MessageChecker.getKeyLength());
        assertTrue(MessageChecker.validateMessage(chaoticSystem.getKey(), message, messageChecked));
    }
    
}
