/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.archosResearch.jCHEKS.encrypter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Thomas Lepage thomas.lepage@hotmail.ca
 */
public class mainTemp {
    
        
    public static void main(String args[]) throws IOException {
        try {
            RijndaelEncrypter encrypter = new RijndaelEncrypter();
            
            String test = "Test d'encryption $#!@$#@";
            MockCS cs = new MockCS();
            cs.temp();
                  
            String encrypted = encrypter.encrypt(test, cs);
            String decrypted = encrypter.decrypt(encrypted, cs);
            
            System.out.println("Encrypted: " + new String(encrypted));
            System.out.println("Decrypted: " + new String(decrypted));

        } catch (NoSuchAlgorithmException | NoSuchPaddingException ex) {
            Logger.getLogger(mainTemp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
