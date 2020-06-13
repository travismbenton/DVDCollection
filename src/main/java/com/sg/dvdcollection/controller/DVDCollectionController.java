/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdcollection.controller;

import com.sg.dvdcollection.dao.DVDCollectionDao;
import com.sg.dvdcollection.dao.DVDCollectionPersistenceException;
import com.sg.dvdcollection.dto.Movies;
import com.sg.dvdcollection.service.DVDCollectionDataValidationException;
import com.sg.dvdcollection.service.DVDCollectionDuplicateIdException;
import com.sg.dvdcollection.service.DVDCollectionServiceLayer;
import com.sg.dvdcollection.ui.DVDCollectionView;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author travi
 */
public class DVDCollectionController {      
    
    DVDCollectionServiceLayer service;
    DVDCollectionView view;
    
    // -- Constructor --
    public DVDCollectionController(DVDCollectionServiceLayer service,
            DVDCollectionView view){
        this.service = service;
        this.view = view;        
    }
    // -- "END" Constructor --
    
    public void run() throws DVDCollectionPersistenceException{
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
        while(keepGoing){                      
            
            menuSelection = getMenuSelection();
            
            switch (menuSelection){
                case 1:
                    addDVD();
                    break;
                case 2:
                    removeDVD();
                    break;
                case 3:
                    editDVD();
                    break;    
                case 4:
                    listAllDVD();
                    break;
                case 5:
                    searchForDVD();
                    break;
                case 6:
                    searchByTiteOnly();
                    break;    
                case 7:
                    addEditAndDelete();
                    break;
                case 8:
                    averageAgeOfDVDs(); 
                    break;
                case 9:
                    newestDVD();
                    break;
                case 10:
                    oldestDVD();
                    break;
                case 11:
                    numberOfYearsDVDsReleased(); 
                    break;    
                case 12:
                    averageNumberOfNotes(); // NOT DONE!!!
                    break;
                case 13:
                    searchByMPAARating(); 
                    break;
                case 14:
                    searchByStudio(); 
                    break;    
                case 15:
                    searchByDirector(); 
                    break;                                   
                case 16:
                    keepGoing = false;
                    break; 
                default:
                    unknownCommand();
                    
            } // -- SWITCH Statement --
            
        } // -- WHILE LOOP --
        exitMessage();
      } catch (DVDCollectionPersistenceException e){
          //view.displayErrorMessage(e.getMessage());
          run();
      }        
        
    } // -- run METHOD --
    
    // ---------------------------------------------| 
     
    // -- Combination DVD's Section --
    private void addEditAndDelete() 
            throws DVDCollectionPersistenceException{
            
      boolean moreChanges = true;
        int menuSelection = 0;
        try {
        while(moreChanges){      
                      
            menuSelection = getMiniMenuSelection();
            
            switch (menuSelection){                  
                case 1:
                    addDVD();
                    break;
                case 2:
                    editDVD();
                    break;
                case 3:
                    removeDVD();
                    break;                     
                case 4:
                    moreChanges = false;
                    break; 
                default:
                    unknownCommand();
                    
            } // -- SWITCH Statement --
            
        } // -- WHILE LOOP --
        exitMessage();
      } catch (DVDCollectionPersistenceException e){
          //view.displayErrorMessage(e.getMessage());
          addEditAndDelete();
      }  
    }    
    
    // -- "END" Combination DVD's Section -- 
    
    // ---------------------------------------------|
    
    // -- Menu Section --
    private int getMenuSelection() throws DVDCollectionPersistenceException{
        return view.printMenuAndGetSelections();        
    } 
    private int getMiniMenuSelection() throws DVDCollectionPersistenceException{
        return view.printMiniMenuAndGetSelection();
    }
    private void unknownCommand() {
        view.displayMenuUnknownCommandBanner();
    }
    private void exitMessage() {
        view.displayMenuExitBanner();        
    }
    
    private void mainMenu() throws DVDCollectionPersistenceException{
        getMenuSelection();
    }
    // -- "END" Menu Section --
    
    // ---------------------------------------------|  
     
    // -- Add DVD Section --    
    private void addDVD() throws DVDCollectionPersistenceException {
	view.displayAddDVDBanner();
        boolean hasErrors = false;
        do {
            Movies newDVD = view.getNewDVDInfo();
         try{ 
            service.createDVD(newDVD);
            view.displayAddDVDSuccessfulBanner();
            hasErrors = false;
        } catch(DVDCollectionDuplicateIdException | 
            DVDCollectionDataValidationException e){
            hasErrors = true;
            view.displayErrorMessage(e.getMessage());
        }
         
       }while (hasErrors); 
    } 
    // -- "END" Add DVD Section -- 
    
    // ---------------------------------------------| 
    
    // -- List-All DVD's Section -- 
    private void listAllDVD()throws DVDCollectionPersistenceException {
       view.displayListAllDVDBanner();
       List<Movies> dvdList = service.listAllDVD();
       view.displayDVDList(dvdList);       
    }    
    // -- "END" List-All DVD's Section -- 
    
    // ---------------------------------------------| 
    
    // -- Find DVD Section -- 
    private void searchForDVD()throws DVDCollectionPersistenceException {
        view.displayFindDVDBanner();
        String title = view.getDVDTitleChoice();
        Movies dvd = service.findDVD(title);
        view.displayFindDVD(dvd);
    }    
    // -- "END" Find DVD Section --
    
    // ---------------------------------------------| 
    
    // -- Find DVD By Title Section --     
    private void searchByTiteOnly()throws DVDCollectionPersistenceException {
        view.displayFindDVDByTitleBanner();
        String title = view.getDVDTitleChoice();
        Movies dvd = service.findDVD(title);
        view.displayFindDVDByTitle(dvd);    
    }    
    // -- "END" Find DVD By Title Section --
    
    // ---------------------------------------------| 
    
    // -- Remove DVD Section --
    private void removeDVD()throws DVDCollectionPersistenceException {
        view.displayRemoveDVDBanner();
        String title = view.getDVDTitleChoice();
        service.deleteDVD(title);
        view.displayRemoveDVDSuccessfulBanner();
    }
    // -- "END" Remove Section --  
    
    // ---------------------------------------------| 
    
    // -- Edit DVD Section --
    private void editDVD() throws DVDCollectionPersistenceException {        
        view.displayEditDVDBanner();
        boolean hasErrors = false;
        do{
            Movies currentMovie = view.getNewDVDInfo();
        try{
            service.editDVD(currentMovie);
            view.displayEditDVDSuccessfulBanner();
                hasErrors = false;
        } catch (DVDCollectionDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }               
        } while (hasErrors);        
    }
    // -- "END" Edit DVD Section --
    
    // ---------------------------------------------| 
    
    // -- Lambdas, Streams, and Aggregate Operations Section --
    
   /*
    Map<String, List<Movies>> movieCollection = dao.getAllMoviesGroupByTitle();         
            Set<String> currentMovie = movieCollection.keySet();
            
        currentMovie.stream()
                .forEach(name -> {movieCollection.get(name)
                                          .stream()
                                          .forEach(m ->System.out.println(m.getReleaseDate())); 
                //System.out.println(currentMovie);
                                }); 
   */
    
   /*
    List<Server> maxServer = dao.getAllServers();
   */    
    
    // -- Lambdas, Streams, and Aggregate Operations Section --
    
    
    
    // -- Average Age of DVD's Section --
    private void averageAgeOfDVDs()throws DVDCollectionPersistenceException {
        view.displayAverageAgeOfDVDBanner();
        //List<Movies> dvdList = dao.listAllDVD();
        double averageAge = service.getAverageMovieAge();
        System.out.println(averageAge);
    }    
    // -- "END" Average Age of DVD's Section --  
    
    // ---------------------------------------------| 
    
    // -- Newest DVD  Section --
    private void newestDVD()throws DVDCollectionPersistenceException {
        view.displayNewestDVDBanner();
        List<Movies> maxMovies = service.listAllDVD();
        
        Movies user1 = maxMovies.stream()                               
                                .max(Comparator.comparing(m -> m.getReleaseDate())).get();
        
                                System.out.println(user1.getReleaseDate());
        
    }    
    // -- "END" Newest DVD Section --  
    
    // ---------------------------------------------| 
    
    // -- Oldest DVD Section --
    private void oldestDVD()throws DVDCollectionPersistenceException {
        view.displayOldestDVDBanner();
        
        List<Movies> maxMovies = service.listAllDVD();
        
        Movies user2 = maxMovies.stream()                               
                                .min(Comparator.comparing(m -> m.getReleaseDate())).get();
        
                                System.out.println(user2.getReleaseDate());
        
    
    }    
    // -- "END" Oldese DVD Section --  
    
    // ---------------------------------------------| 
    
    // -- Numbers Of Years DVDs Released Section --
    private void numberOfYearsDVDsReleased()throws DVDCollectionPersistenceException {
        view.displayNumberOfYearsDVDsReleasedBanner();
        
        int released = view.numberOfYears();
        List<Movies> maxMovies = service.getMoviesNewerThan(released);
        System.out.println(" Number of Movies Released: "+maxMovies.size());  
        maxMovies.stream()
               .forEach(m -> System.out.println("Title: "+m.getTitle()+" Year: "+m.getReleaseDate()));
    }
    // -- "END" Number of Years DVDs Released Section --  
    
    // ---------------------------------------------| 
    
    // -- Average Number Of Notes Section --
    private void averageNumberOfNotes()throws DVDCollectionPersistenceException {
        view.displayAverageNumberOfNotesDVDBanner();
    }
    // -- "END" Average Number Of Notes Section --  
    
    // ---------------------------------------------| 
    
    // -- Search By MPAA Rating Section --
    private void searchByMPAARating()throws DVDCollectionPersistenceException {
         
        view.displaySearchByMPAARatingDVDBanner();        
        String selectRating = view.selectMPPARating();
        List<Movies> rating = service.getMoviesByMPAARating(selectRating.toUpperCase());
        rating.stream()
                .forEach(m -> System.out.println("Title: "+m.getTitle()+" Rating: "+m.getmPAARating()));
    }
    // -- "END" Search By MPAA Rating Section --  
    
    // ---------------------------------------------| 
    
    // -- Search By Studio Section --
    private void searchByStudio()throws DVDCollectionPersistenceException {
        
        view.displaySearchByStudioDVDBanner();
        String selectStudio = view.selectStudio();
        List<Movies> studioName = service.getMoviesByStudio(selectStudio);
        studioName.stream()
                .forEach(m -> System.out.println("Title: "+m.getTitle()+" Studio: "+m.getStudio()));
        
    }   
    // -- "END" Search By Studio Section --  
    
    // ---------------------------------------------| 
    
    // -- Search By Director Section --
    private void searchByDirector()throws DVDCollectionPersistenceException {
        view.displaySearchByDirectorDVDBanner();
        String selectDirector = view.selectDirectorName();
        List<Movies> director = service.getMoviesByDirectorName(selectDirector);
        director.stream()
                .forEach(m -> System.out.println("Title: "+m.getTitle()+" Director Name: "+m.getDirectorName()));
    }
    // -- "END" Search By Director Section --  
    
    // ---------------------------------------------| 
}
