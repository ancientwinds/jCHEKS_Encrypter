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

    public MockChaoticSystem(String uniqueId, int keyLength) throws Exception {
        super(uniqueId, keyLength);
        byte[] b = new byte[16];    
        Arrays.fill( b, (byte)0 );
        this.lastGeneratedIV = b;
        
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public String Serialize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Deserialize(String serialization) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generateSystem(int keyLength) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
