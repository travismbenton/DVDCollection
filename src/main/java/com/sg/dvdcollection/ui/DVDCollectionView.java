/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdcollection.ui;

import com.sg.dvdcollection.dao.DVDCollectionPersistenceException;
import com.sg.dvdcollection.dto.Movies;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;



/**
 *
 * @author travi
 */
public class DVDCollectionView {    
    
    private UserIO io;    
    
    // -- Constructor --
    public DVDCollectionView(UserIO io){
        this.io = io;
    }
    // -- "END" Constructor --
    
    public int printMenuAndGetSelections() throws DVDCollectionPersistenceException{
        
        io.print("Main Menu");
        io.print("1. Add a DVD");
        io.print("2. Remove a DVD");
        io.print("3. Edit DVD Information");
        io.print("4. List-All DVD's");
        io.print("5. Seach for DVD"); 
        io.print("6. Seach by Title");
        io.print("7. Add, Edit and Delete DVD's");        
        io.print("8. Average Age of DVD's");
        io.print("9. Newest DVD");
        io.print("10. Oldest DVD");
        io.print("11. DVD's Released \"Select Number of Years\"");        
        io.print("12. Average Number of Notes ");
        io.print("13. Search by MPAA Rating"); 
        io.print("14. Search by Studio");
        io.print("15. Search by Director");        
        io.print("16. Exit");         
        
        return io.readInt("Please select from the above choices. ", 1, 16);                
    }
    
    // ---------------------------------------------|
    
    // -- MISC MENU 1 Section -- 
    public void displayMenuExitBanner(){
        io.print("Good Bye!!!");
    }

    public void displayMenuUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    // -- "END" MISC MENU 1 Section -- 
    
    // ---------------------------------------------|  
    
    public int printMiniMenuAndGetSelection() throws DVDCollectionPersistenceException{
        
        io.print("Add, Edit and Delete Menu");
        io.print("1. Add a DVD");
        io.print("2. Edit DVD Information");
        io.print("3. Delete a DVD");                    
        io.print("4. Exit"); 
            
        return io.readInt("Please select from the above choices. ", 1, 4);
    }
        
    // ---------------------------------------------|    
   
    // -- Add DVD Section --
    public Movies getNewDVDInfo() { // New "Info" from the User
        String title = io.readString("Please enter DVD Title:");        
        String rDate = io.readString("Please enter Release Date:");        
        String mPAARating = io.readString("Please enter MPAA Rating:");
        String directorName = io.readString("Please enter Director's Name:"); 
        String studio = io.readString("Please enter Studio:");
        String userRating = io.readString("Please enter User Rating and Comments:");        
        
    // -- Create a new "Movies" object --
        Movies currentDVD = new Movies(title); // -- Title of the "New DVD"
    // -- Create a new "LocalDate" objects --            
        LocalDate releaseDate; 
       
       // -- Date Conversion --
        releaseDate = LocalDate.parse(rDate, DateTimeFormatter.ofPattern("MM-dd-yyyy"));        
        currentDVD.setReleaseDate(releaseDate);        
        // -- "END" Date Conversion --        
        currentDVD.setmPAARating(mPAARating);
        currentDVD.setDirectorName(directorName);
        currentDVD.setStudio(studio); 
        currentDVD.setUserRating(userRating);        
        
        return currentDVD;    
    }            
    public void displayAddDVDBanner() {
	io.print("=== Add New DVD ===");
    }	
    public void displayAddDVDSuccessfulBanner() {
	io.readString("DVD successfully created.  Please hit enter to continue");
    }    
    // -- "END" Add DVD Section --    
    
    // ---------------------------------------------|
    
    // -- List-All DVD's Section --
    public void displayDVDList(List<Movies> dvdList){
        // -- List title and value for each created object --
        for (Movies currentDVD : dvdList){ 
            io.print("Title: "+currentDVD.getTitle()); 
            io.print("Release Date: "+ currentDVD.getReleaseDate());
            io.print("MPAA Rating: "+ currentDVD.getmPAARating());            
            io.print("Director Name: "+ currentDVD.getDirectorName()); 
            io.print("Studio: "+ currentDVD.getStudio());
            io.print("Notes & Ratings: "+ currentDVD.getUserRating()); 
            System.out.println(" ");
	}
	io.readString("Please hit enter to continue.");        
    }    
    public void displayListAllDVDBanner() {
	    io.print("=== Display All DVD's ===");            
    }    
    // -- "END" List-All DVD's Section --
    
    // ---------------------------------------------|
    
    // -- Find DVD Section -- 
    public void displayFindDVDBanner () {
	    io.print("=== Display DVD ===");
    }
    public String getDVDTitleChoice() {
	    return io.readString("Please enter the DVD Title.");
    } 
    public void displayFindDVD(Movies dvd) {
	    if (dvd != null) {
	        io.print(dvd.getTitle());
	        io.print(dvd.getReleaseDate().toString());
	        io.print(dvd.getmPAARating());
	        io.print(dvd.getDirectorName());
                io.print(dvd.getStudio());
	        io.print(dvd.getUserRating());                
	    } else {
	        io.print("No such DVD exist.");
	    }
	    io.readString("Please hit enter to continue.");
    }    
    // -- "END" Find DVD Section --
    
    // ---------------------------------------------| 
    
    // -- Find DVD By Title Section -- 
    public void displayFindDVDByTitleBanner () {
	    io.print("=== Display DVD Titles ===");
    }
    public void displayFindDVDByTitle(Movies dvd) {
	    if (dvd != null) {
	        io.print(dvd.getTitle());	        
	    } else {
	        io.print("No such Title exist.");
	    }
	    io.readString("Please hit enter to continue.");
    }
    // -- "END" Find DVD By Title Section --
    
    // ---------------------------------------------| 
    
    // -- Remove DVD Section --
    public void displayRemoveDVDBanner () {
	    io.print("=== Remove DVD ===");
    }
    public void displayRemoveDVDSuccessfulBanner () {
	    io.readString("DVD successfully removed. "
                    + "Please hit enter to continue.");
    }
    // -- "END" Remove Section --
    
    // ---------------------------------------------|
    
    // -- Edit DVD Section --
    public void displayEditDVDBanner () {
	    io.print("=== Edit DVD ===");
    }    
   // -- "END" Edit DVD Section --
    
    // ---------------------------------------------| 
    
    // -- Display Error MSG to User Section --
    public void displayErrorMessage(String errorMsg) {
	    io.print("=== ERROR ===");
	    io.print(errorMsg);
	}
    // -- "END" Display Error MSG to User Section --

    // ---------------------------------------------| 
    
    // -- Lambdas, Streams, and Aggregate Operations Section --
    
    // -- Average Age of DVD's  Section --
    public void displayAverageAgeOfDVDBanner () {
	    io.print("=== DVD's Average Age ===");
    }    
    // -- "END" Average Age of DVD's Section --  
    
    // ---------------------------------------------| 
    
    // -- Newest DVD  Section --
    public void displayNewestDVDBanner () {
	    io.print("=== Newest DVD ===");
    }       
    // -- "END" Newest DVD Section --  
    
    // ---------------------------------------------| 
    
    // -- Oldest DVD Section --
    public void displayOldestDVDBanner () {
	    io.print("=== Oldest DVD ===");
    }   
    // -- "END" Oldese DVD Section --  
    
    // ---------------------------------------------| 
    
    // -- Number of Years DVDs Released Section --
    int released;
    public void displayNumberOfYearsDVDsReleasedBanner() {
	    io.print("=== DVD's Released ===");
    }
    public int numberOfYears(){
            return io.readInt("Please enter amount of years.");            
    } 
    // -- "END" Number of Years DVDs Released Section --  
    
    // ---------------------------------------------| 
    
    // -- Average Number Of Notes Section --
    public void displayAverageNumberOfNotesDVDBanner () {
	    io.print("=== Average Number of DVD Notes ===");
    }
    // -- "END" Average Number Of Notes Section --  
    
    // ---------------------------------------------| 
    
    // -- Search By MPAA Rating Section --
    public void displaySearchByMPAARatingDVDBanner () {
	    io.print("=== MPAA DVD Ratings ===");
    }
    public String selectMPPARating(){
            return io.readString("Select Rating: \"G\", \"PG-13\", \"R\".");
    }
    // -- "END" Search By MPAA Rating Section --  
    
    // ---------------------------------------------| 
    
    // -- Search By Studio Section --
    public void displaySearchByStudioDVDBanner () {
	    io.print("=== Studio Results ===");
    }
    public String selectStudio(){
            return io.readString("Select \"Studio\" Name.");
    }
    // -- "END" Search By Studio Section --  
    
    // ---------------------------------------------| 
    
    // -- Search By Director Section --
    public void displaySearchByDirectorDVDBanner () {
	    io.print("=== Director Results ===");
    }
    public String selectDirectorName(){
            return io.readString("Select \"Director\" Name.");
    }
    // -- "END" Search By Director Section --  
    
    // ---------------------------------------------| 
}
