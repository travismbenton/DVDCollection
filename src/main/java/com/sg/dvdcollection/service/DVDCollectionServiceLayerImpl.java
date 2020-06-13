/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdcollection.service;

import com.sg.dvdcollection.dao.DVDCollectionAuditDao;
import com.sg.dvdcollection.dao.DVDCollectionDao;
import com.sg.dvdcollection.dao.DVDCollectionPersistenceException;
import com.sg.dvdcollection.dto.Movies;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author travi
 */
public class DVDCollectionServiceLayerImpl implements DVDCollectionServiceLayer{
    
    DVDCollectionDao dao;
    DVDCollectionAuditDao auditDao;
    
    // -- Constructor --
    public DVDCollectionServiceLayerImpl(DVDCollectionDao dao, 
            DVDCollectionAuditDao auditDao) {
    this.dao = dao;
    this.auditDao = auditDao;
    }
    // -- "END" Constructor --

    @Override
    public void createDVD(Movies dvd) throws 
            DVDCollectionDuplicateIdException, 
            DVDCollectionDataValidationException, 
            DVDCollectionPersistenceException {
        
        if (dao.findDVD(dvd.getTitle()) != null) {
        throw new DVDCollectionDuplicateIdException(
                "ERROR: Could not create Movie.  Title "
                + dvd.getTitle()
                + " already exists");
    }
        validateMoviedData(dvd);
        dao.addDVD(dvd.getTitle(), dvd);
        
        // -- AUDIT LOG --
        //auditDao.writeAuditEntry( 
            //"Movie " + dvd.getTitle() + " CREATED.");            
    }
    
    @Override
    public void editDVD(Movies dvd) throws 
            DVDCollectionPersistenceException, 
            DVDCollectionDataValidationException {        
        validateMoviedData(dvd);
        dao.addDVD(dvd.getTitle(), dvd);        
        // -- AUDIT LOG --
        //auditDao.writeAuditEntry( 
           //"Movie " + dvd.getTitle() + " EDITED.");        
    }

    @Override
    public List<Movies> listAllDVD() throws 
            DVDCollectionPersistenceException {
        return dao.listAllDVD();        
    }

    @Override
    public Movies findDVD(String title) throws 
            DVDCollectionPersistenceException {
        return dao.findDVD(title);
        
    }

    @Override
    public Movies deleteDVD(String title) throws 
            DVDCollectionPersistenceException {
        Movies removedDVD = dao.deleteDVD(title);
        
        // -- AUDIT LOG --
        //auditDao.writeAuditEntry("Movie " + title + " REMOVED.");
        return removedDVD; 
        
    }
    
    
    private void validateMoviedData(Movies dvd) throws
        DVDCollectionDataValidationException {
        
        if (dvd.getReleaseDate()== null
            || dvd.getmPAARating() == null
            || dvd.getmPAARating().trim().length() == 0
            || dvd.getDirectorName() == null
            || dvd.getDirectorName().trim().length() == 0 
            || dvd.getStudio() == null
            || dvd.getStudio().trim().length() == 0
            || dvd.getUserRating()== null
            || dvd.getUserRating().trim().length() == 0) {
             
        throw new DVDCollectionDataValidationException(                
                "ERROR: All fields [Release Date, MPAA Rating, Director Name,"
                        + "Studio, and User Rating] are required.");
        }
        
    }
    
    
    // -- Lambdas, Streams, and Aggregate Operations Section --
    

    @Override
    public Map<String, List<Movies>> getMoviesOlderThanGroupByTitle(int ageInYears) throws DVDCollectionPersistenceException {
        return dao.getMoviesOlderThanGroupByTitle(ageInYears);
    }

    @Override
    public List<Movies> getMoviesOlderThan(int ageInYears) throws DVDCollectionPersistenceException {
        return dao.getMoviesOlderThan(ageInYears);
    }

    @Override
    public List<Movies> getMoviesNewerThan(int ageInYears) throws DVDCollectionPersistenceException {
        return dao.getMoviesNewerThan(ageInYears);
    }

    @Override
    public Map<String, List<Movies>> getAllMoviesGroupByTitle() throws DVDCollectionPersistenceException {
        return dao.getAllMoviesGroupByTitle();
    }

    @Override
    public List<Movies> getMoviesByTitle(String title) throws DVDCollectionPersistenceException {
        return dao.getMoviesByTitle(title);
    }

    @Override
    public List<Movies> getMoviesByMPAARating(String mPAARating) throws DVDCollectionPersistenceException {
        return dao.getMoviesByMPAARating(mPAARating);
    }

    @Override
    public List<Movies> getMoviesByStudio(String studio) throws DVDCollectionPersistenceException {
        return dao.getMoviesByStudio(studio);
    }

    @Override
    public List<Movies> getMoviesByDirectorName(String directorName) throws DVDCollectionPersistenceException {
        return dao.getMoviesByDirectorName(directorName);
    }

    @Override
    public double getAverageMovieAge() throws DVDCollectionPersistenceException {
        return dao.getAverageMovieAge();
    }

    
    
}
