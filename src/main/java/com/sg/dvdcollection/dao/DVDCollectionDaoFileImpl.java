/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdcollection.dao;

import com.sg.dvdcollection.dto.Movies;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author travi
 */
public class DVDCollectionDaoFileImpl implements DVDCollectionDao {
    
// -- Look up Movies by title. The parameter in DTO contructor is "title" --
    Map<String, Movies> movieCollection = new HashMap<>();
    
    public static final String DVDCOLLECTION_FILE = "dvdcollection.txt";
    public static final String DELIMITER = "::";
    
    
// -- Put the supplied DVD into our map using the supplied title as the key --
    @Override
    public Movies addDVD(String title, Movies dvd)
            throws DVDCollectionPersistenceException {
        Movies newDVD = movieCollection.put(title, dvd);
        writeDvdcollection();
        return newDVD;
    }            
    
    @Override
    public Movies deleteDVD(String title)
            throws DVDCollectionPersistenceException {
        Movies removeDVD = movieCollection.remove(title);
        writeDvdcollection();
        return removeDVD;
    }    
                
    @Override
    public Movies findDVD(String title)
            throws DVDCollectionPersistenceException{
        loadDvdcollection();
        return movieCollection.get(title);
    }  
    
    @Override
    public List<Movies> listAllDVD()
            throws DVDCollectionPersistenceException{
        loadDvdcollection();
        return new ArrayList<Movies>(movieCollection.values());
    }
    
    
    // -- Lambdas, Streams, and Aggregate Operations Section --
    
    
    @Override
    public Map<String, List<Movies>> getMoviesOlderThanGroupByTitle(int ageInYears) 
        throws DVDCollectionPersistenceException { // Returns all Servers
        loadDvdcollection();
        return movieCollection.values() //Collection of "Server" OBJECTS
                              .stream()              
                              .collect(Collectors.groupingBy(m -> m.getTitle()));// To a Map
         // or  .collect(Collectors.groupingBy(Server::getManufacturer)); 
    }

    @Override
    public List<Movies> getMoviesOlderThan(int ageInYears) 
            throws DVDCollectionPersistenceException {
            loadDvdcollection();
            return movieCollection.values() //Collection of "Server" OBJECTS
                                  .stream()
                                  .filter(m -> m.getMovieAge() > ageInYears)
                                  .collect(Collectors.toList());
    }

    @Override
    public List<Movies> getMoviesNewerThan(int ageInYears) 
            throws DVDCollectionPersistenceException {
            loadDvdcollection();
            return movieCollection.values() //Collection of "Server" OBJECTS
                                  .stream()
                                  .filter(m -> m.getMovieAge() < ageInYears)
                                  .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<Movies>> getAllMoviesGroupByTitle() 
            throws DVDCollectionPersistenceException {
            loadDvdcollection();
            return movieCollection.values() //Collection of "Server" OBJECTS
                                  .stream()              
                                  .collect(Collectors.groupingBy(m -> m.getTitle()));// To a Map
         // or  .collect(Collectors.groupingBy(Server::getManufacturer));
    }

    @Override
    public List<Movies> getMoviesByTitle(String title) 
            throws DVDCollectionPersistenceException {
            loadDvdcollection();
            return movieCollection.values() //Collection of "Server" OBJECTS
                                  .stream()
                                  .filter(m -> m.getTitle().equalsIgnoreCase(title))
                                  .collect(Collectors.toList());
    }
    
    @Override
    public List<Movies> getMoviesByMPAARating(String mPAARating) 
            throws DVDCollectionPersistenceException {
            loadDvdcollection();
            return movieCollection.values() //Collection of "Server" OBJECTS
                                  .stream()
                                  .filter(m -> m.getmPAARating().equalsIgnoreCase(mPAARating))
                                  .collect(Collectors.toList());
    }
    
     @Override
    public List<Movies> getMoviesByStudio(String studio) 
            throws DVDCollectionPersistenceException {
            loadDvdcollection();
            return movieCollection.values() //Collection of "Server" OBJECTS
                                  .stream()
                                  .filter(m -> m.getStudio().equalsIgnoreCase(studio))
                                  .collect(Collectors.toList());
    }

    @Override
    public List<Movies> getMoviesByDirectorName(String directorName) 
            throws DVDCollectionPersistenceException {
            loadDvdcollection();
            return movieCollection.values() //Collection of "Server" OBJECTS
                                  .stream()
                                  .filter(m -> m.getDirectorName().equalsIgnoreCase(directorName))
                                  .collect(Collectors.toList());
    }

    
    @Override
    public double getAverageMovieAge() throws DVDCollectionPersistenceException {
        loadDvdcollection();
        return movieCollection.values()
                .stream()
                .mapToLong(m -> m.getMovieAge())
                .average()
                .getAsDouble();
    }
    
    
    
    
    
    // -- LOAD - ROSTER --
    private void loadDvdcollection() throws DVDCollectionPersistenceException {
        Scanner scanner;
        
        try {            
            scanner = new Scanner(
                    new BufferedReader(
                        new FileReader(DVDCOLLECTION_FILE)));
        } catch (FileNotFoundException e){
            throw new DVDCollectionPersistenceException(
                    "-_- Could not load dvdcollection data into memory", e);
        }
        
        String currentLine;
        String[] currentTokens;
        
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);            
                
            Movies currentDVD = new Movies(currentTokens[0]);            
            
            currentDVD.setReleaseDate(LocalDate.parse(currentTokens[1]));
	    currentDVD.setmPAARating(currentTokens[2]);
	    currentDVD.setDirectorName(currentTokens[3]);
            currentDVD.setStudio(currentTokens[4]);
	    currentDVD.setUserRating(currentTokens[5]);            
            
            movieCollection.put(currentDVD.getTitle(), currentDVD);           
        }
        scanner.close(); 
        
    } // -- "END" loadRoster() --
    
    
    // -- WRITE - ROSTER --
    private void writeDvdcollection() throws DVDCollectionPersistenceException {
	    
	    PrintWriter out;
	    
	    try {                
	        out = new PrintWriter(new FileWriter(DVDCOLLECTION_FILE));
	    } catch (IOException e) {
	        throw new DVDCollectionPersistenceException(
	                "Could not save dvdcollection data.", e);
	    }	    
	    
	    List<Movies> DVDList = this.listAllDVD();
	    for (Movies currentDVD : DVDList) {
	        // write the Student object to the file
	        out.println(currentDVD.getTitle() + DELIMITER
	                + currentDVD.getReleaseDate() + DELIMITER 
	                + currentDVD.getmPAARating()+ DELIMITER
                        + currentDVD.getDirectorName() + DELIMITER 
	                + currentDVD.getStudio()+ DELIMITER
	                + currentDVD.getUserRating()); 
                        
	        // force PrintWriter to write line to the file
	        out.flush();
	    }
	    // Clean up
	    out.close();
            
    } // -- "END" writeRoster() --
    
    
    
}
