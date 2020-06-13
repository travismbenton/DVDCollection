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
public class DVDCollectionDataValidationException extends Exception {
    
    public DVDCollectionDataValidationException(String message) {
        super(message);
    }

    public DVDCollectionDataValidationException(String message,
            Throwable cause) {
        super(message, cause);
    }
}
