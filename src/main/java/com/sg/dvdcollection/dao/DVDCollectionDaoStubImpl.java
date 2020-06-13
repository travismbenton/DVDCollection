/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdcollection.dao;

import com.sg.dvdcollection.dto.Movies;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author travi
 */
public class DVDCollectionDaoStubImpl implements DVDCollectionDao {
    
    Movies onlyMovie;
    List<Movies>moviesList = new ArrayList<>();
    
    // -- Default Constructor --
    public DVDCollectionDaoStubImpl(){
        onlyMovie = new Movies("Default");
        String rDate = "05-05-2020";
        LocalDate releaseDate; 
       
       // -- Date Conversion --
        releaseDate = LocalDate.parse(rDate, DateTimeFormatter.ofPattern("MM-dd-yyyy"));        
        onlyMovie.setReleaseDate(releaseDate);       
        onlyMovie.setmPAARating("R");
        onlyMovie.setDirectorName("Tom");
        onlyMovie.setStudio("Hollywood");
        onlyMovie.setUserRating("10");
        // -- Add Movie --
        moviesList.add(onlyMovie);
    }
    // -- "END" Default Constructor --
    

    @Override
    public Movies addDVD(String title, Movies dvd) throws DVDCollectionPersistenceException {
        if (title.equals(onlyMovie.getTitle())) {
            return onlyMovie;            
        } else {
            return null;
        }
    }
    
    @Override
    public List<Movies> listAllDVD() throws DVDCollectionPersistenceException {
        return moviesList;
    }
    
    @Override
    public Movies findDVD(String title) throws DVDCollectionPersistenceException {
        if (title.equals(onlyMovie.getTitle())) {
            return onlyMovie;            
        } else {
            return null;
        }
    }

    @Override
    public Movies deleteDVD(String title) throws DVDCollectionPersistenceException {
        if (title.equals(onlyMovie.getTitle())) {
            return onlyMovie;            
        } else {
            return null;
        }
    }

        // -- Lambdas, Streams, and Aggregate Operations Section --

    @Override
    public Map<String, List<Movies>> getMoviesOlderThanGroupByTitle(int ageInYears) throws DVDCollectionPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movies> getMoviesOlderThan(int ageInYears) throws DVDCollectionPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movies> getMoviesNewerThan(int ageInYears) throws DVDCollectionPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, List<Movies>> getAllMoviesGroupByTitle() throws DVDCollectionPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movies> getMoviesByTitle(String title) throws DVDCollectionPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movies> getMoviesByMPAARating(String mPAARating) throws DVDCollectionPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movies> getMoviesByStudio(String studio) throws DVDCollectionPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movies> getMoviesByDirectorName(String directorName) throws DVDCollectionPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getAverageMovieAge() throws DVDCollectionPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
