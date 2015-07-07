package com.archosResearch.jCHEKS.encrypter.mock;

import com.archosResearch.jCHEKS.concept.chaoticSystem.AbstractChaoticSystem;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Thomas Lepage thomas.lepage@hotmail.ca
 */
public class MockChaoticSystem extends AbstractChaoticSystem{

    public MockChaoticSystem(int keyLength) throws Exception {
        super(keyLength);
        
        byte[] c = new byte[128];    
        Arrays.fill( c, (byte)0 );
        this.lastGeneratedKey = c;
    }
    
    @Override
    public void evolveSystem(int factor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public byte[] getKey(int requiredLength) {
        byte[] c = new byte[requiredLength];    
        Arrays.fill( c, (byte)0 );
        return c;
    }

    @Override
    public void resetSystem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractChaoticSystem cloneSystem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void deserialize(String serialization) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generateSystem(int keyLength) throws Exception {}

    @Override
    public String serialize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
