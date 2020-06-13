/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdcollection.service;

import com.sg.dvdcollection.dao.DVDCollectionPersistenceException;
import com.sg.dvdcollection.dto.Movies;
import java.util.List;
import java.util.Map;

/**
 *
 * @author travi
 */
public interface DVDCollectionServiceLayer {
    
    public void createDVD(Movies dvd) throws
            DVDCollectionDuplicateIdException,
            DVDCollectionDataValidationException,
            DVDCollectionPersistenceException;
    
    public void editDVD(Movies dvd) throws 
            DVDCollectionPersistenceException,
            DVDCollectionDataValidationException;
            
    
    public List<Movies> listAllDVD() throws
             DVDCollectionPersistenceException;
            
    
    public Movies findDVD(String title) throws
             DVDCollectionPersistenceException;
            
    
    public Movies deleteDVD(String title) throws
             DVDCollectionPersistenceException;
    
    
    // -- Lambdas, Streams, and Aggregate Operations Section --
    
    
    public Map<String, List<Movies>> getMoviesOlderThanGroupByTitle(int ageInYears)
            throws DVDCollectionPersistenceException;
    
    public List<Movies> getMoviesOlderThan(int ageInYears)
            throws DVDCollectionPersistenceException;
    
    public List<Movies> getMoviesNewerThan(int ageInYears)
            throws DVDCollectionPersistenceException;
    
    public Map<String, List<Movies>> getAllMoviesGroupByTitle()
            throws DVDCollectionPersistenceException;

    public List<Movies> getMoviesByTitle(String title)
            throws DVDCollectionPersistenceException;
    
    public List<Movies> getMoviesByMPAARating(String mPAARating) 
            throws DVDCollectionPersistenceException;
    
    public List<Movies> getMoviesByStudio(String studio) 
            throws DVDCollectionPersistenceException;
    
    public List<Movies> getMoviesByDirectorName(String directorName) 
            throws DVDCollectionPersistenceException;
    
    public double getAverageMovieAge() // Average Age of Movie
            throws DVDCollectionPersistenceException; 
    
}
