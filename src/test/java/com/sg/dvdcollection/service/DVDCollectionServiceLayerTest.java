/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdcollection.service;

import com.sg.dvdcollection.dao.DVDCollectionAuditDao;
import com.sg.dvdcollection.dao.DVDCollectionAuditDaoStubImpl;
import com.sg.dvdcollection.dao.DVDCollectionDao;
import com.sg.dvdcollection.dao.DVDCollectionDaoStubImpl;
import com.sg.dvdcollection.dto.Movies;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author travi
 */
public class DVDCollectionServiceLayerTest {
    
    DVDCollectionServiceLayer service;
    
    // -- Default Constructor --
    public DVDCollectionServiceLayerTest() {
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", DVDCollectionServiceLayer.class);
        
        //DVDCollectionDao dao = new DVDCollectionDaoStubImpl();
        //DVDCollectionAuditDao auditDao = new DVDCollectionAuditDaoStubImpl();
        //service = new DVDCollectionServiceLayerImpl(dao, auditDao);
    }
    // -- "END" Default Constructor --
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws Exception{
        Movies onlyMovie = new Movies("Created");
        String rDate = "01-01-2001";
        LocalDate releaseDate; 
       
       // -- Date Conversion --
        releaseDate = LocalDate.parse(rDate, DateTimeFormatter.ofPattern("MM-dd-yyyy"));        
        onlyMovie.setReleaseDate(releaseDate);       
        onlyMovie.setmPAARating("PG");
        onlyMovie.setDirectorName("John");
        onlyMovie.setStudio("Florida");
        onlyMovie.setUserRating("10");
        // -- Add Movie --
        service.createDVD(onlyMovie);
        
        // -- 2nd Created DVD
        onlyMovie = new Movies("DeletedCreated");
        rDate = "01-01-2001";
        //LocalDate releaseDate; 
       
       // -- Date Conversion --
        releaseDate = LocalDate.parse(rDate, DateTimeFormatter.ofPattern("MM-dd-yyyy"));        
        onlyMovie.setReleaseDate(releaseDate);       
        onlyMovie.setmPAARating("PG");
        onlyMovie.setDirectorName("John");
        onlyMovie.setStudio("Florida");
        onlyMovie.setUserRating("10");
        // -- Add Movie --
        service.createDVD(onlyMovie);
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of createDVD method, of class DVDCollectionServiceLayer.
     */
    
    
    @Test
    public void testCreateDVDDuplicateTitle() throws Exception {
        Movies onlyMovie = new Movies("Default");
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
        try {
        service.createDVD(onlyMovie);
        fail("Expected DVDCollectionDuplicateIdException was not thrown.");
        } catch (DVDCollectionDuplicateIdException e) {
            return;
        }
    }

    /**
     * Test of editDVD method, of class DVDCollectionServiceLayer.
     */
    @Test
    public void testCreateDVDInvalidData() throws Exception {
        Movies onlyMovie = new Movies("Home");
        String rDate = "05-05-2020";
        LocalDate releaseDate; 
       
       // -- Date Conversion --
        releaseDate = LocalDate.parse(rDate, DateTimeFormatter.ofPattern("MM-dd-yyyy"));        
        onlyMovie.setReleaseDate(releaseDate);       
        onlyMovie.setmPAARating("R");
        onlyMovie.setDirectorName("");
        onlyMovie.setStudio("Hollywood");
        onlyMovie.setUserRating("10");
        // -- Add Movie --
        try {
        service.createDVD(onlyMovie);
        fail("Expected DVDCollectionDataValidationException was not thrown.");
        } catch (DVDCollectionDataValidationException e) {
            return;
        }
    }

    /**
     * Test of listAllDVD method, of class DVDCollectionServiceLayer.
     */
    @Test
    public void testListAllDVD() throws Exception {
        assertEquals(1, service.listAllDVD().size());
    }

    /**
     * Test of findDVD method, of class DVDCollectionServiceLayer.
     */
    @Test
    public void testFindDVD() throws Exception {
        Movies dvd = service.findDVD("Default");
        assertNotNull(dvd);
    }

    /**
     * Test of deleteDVD method, of class DVDCollectionServiceLayer.
     */
    @Test
    public void testDeleteDVD() throws Exception {
        Movies dvd = service.deleteDVD("DeletedCreated");
        assertNull(dvd);
    }

    /**
     * Test of getMoviesOlderThanGroupByTitle method, of class DVDCollectionServiceLayer.
     */
    @Test
    public void testGetMoviesOlderThanGroupByTitle() throws Exception {
    }

    /**
     * Test of getMoviesOlderThan method, of class DVDCollectionServiceLayer.
     */
    @Test
    public void testGetMoviesOlderThan() throws Exception {
    }

    /**
     * Test of getMoviesNewerThan method, of class DVDCollectionServiceLayer.
     */
    @Test
    public void testGetMoviesNewerThan() throws Exception {
    }

    /**
     * Test of getAllMoviesGroupByTitle method, of class DVDCollectionServiceLayer.
     */
    @Test
    public void testGetAllMoviesGroupByTitle() throws Exception {
    }

    /**
     * Test of getMoviesByTitle method, of class DVDCollectionServiceLayer.
     */
    @Test
    public void testGetMoviesByTitle() throws Exception {
    }

    /**
     * Test of getMoviesByMPAARating method, of class DVDCollectionServiceLayer.
     */
    @Test
    public void testGetMoviesByMPAARating() throws Exception {
    }

    /**
     * Test of getMoviesByStudio method, of class DVDCollectionServiceLayer.
     */
    @Test
    public void testGetMoviesByStudio() throws Exception {
    }

    /**
     * Test of getMoviesByDirectorName method, of class DVDCollectionServiceLayer.
     */
    @Test
    public void testGetMoviesByDirectorName() throws Exception {
    }

    /**
     * Test of getAverageMovieAge method, of class DVDCollectionServiceLayer.
     */
    @Test
    public void testGetAverageMovieAge() throws Exception {
    }

    
}
