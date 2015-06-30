package com.archosResearch.jCHEKS.messageChecker.exception;

import com.archosResearch.jCHEKS.concept.exception.AbstractCHEKSException;

/**
 *
 * @author Thomas Lepage thomas.lepage@hotmail.ca
 */
public class SecureAckGeneratorException extends AbstractCHEKSException{

    public SecureAckGeneratorException(String message) {
        super(message);
    }
    
    public SecureAckGeneratorException(String message, Throwable throwable) {
        super(message, throwable);
    }
    
}
