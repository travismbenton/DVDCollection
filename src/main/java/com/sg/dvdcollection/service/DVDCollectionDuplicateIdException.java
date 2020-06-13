/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdcollection.service;

/**
 *
 * @author travi
 */
public class DVDCollectionDuplicateIdException extends Exception {
    
    public DVDCollectionDuplicateIdException(String message) {
        super(message);
    }

    public DVDCollectionDuplicateIdException(String message,
            Throwable cause) {
        super(message, cause);
    }
    
}
