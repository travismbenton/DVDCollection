/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdcollection.dao;

/**
 *
 * @author travi
 */
public class DVDCollectionPersistenceException extends Exception {
    
    public DVDCollectionPersistenceException(String message){
        super(message);
    }
    
    public DVDCollectionPersistenceException(String message, Throwable cause){
        super(message, cause);
    }
    
}
