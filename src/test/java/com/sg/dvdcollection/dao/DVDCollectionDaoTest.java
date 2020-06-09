/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdcollection.dao;

import com.sg.dvdcollection.dto.Movies;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author travi
 */
public class DVDCollectionDaoTest {
    
    DVDCollectionDao dao = new DVDCollectionDaoFileImpl();
    
    
    public DVDCollectionDaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws Exception {
        List<Movies>movieList = dao.listAllDVD();
        for (Movies movie : movieList){
            dao.deleteDVD(movie.getTitle());
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addDVD method, of class DVDCollectionDao.
     */
    @Test
    public void testAddGetDVD() throws Exception {
        Movies movie = new Movies("Space Jam");
        //movie.setReleaseDate("March 1996");
        movie.setmPAARating("100");
        movie.setDirectorName("Jordan");
        movie.setStudio("Hollywood");
        movie.setUserRating("10");
        // -- Add Movie --
        dao.addDVD(movie.getTitle(), movie);
        // -- Get Movie --
        Movies fromDao = dao.findDVD(movie.getTitle());
        // -- Results Expected "Match" --
        assertEquals(movie, fromDao);
        
    }

    /**
     * Test of deleteDVD method, of class DVDCollectionDao.
     */
    @Test
    public void testDeleteDVD() throws Exception {
        Movies movie1 = new Movies("Space Jam");
        //movie1.setReleaseDate("March 1996");
        movie1.setmPAARating("100");
        movie1.setDirectorName("Jordan");
        movie1.setStudio("Hollywood");
        movie1.setUserRating("10");
        // -- Add Movie --
        dao.addDVD(movie1.getTitle(), movie1);
        
        Movies movie2 = new Movies("Dog Bam");
        //movie2.setReleaseDate("April 2000");
        movie2.setmPAARating("79");
        movie2.setDirectorName("Jack");
        movie2.setStudio("Hollywood");
        movie2.setUserRating("5");
        // -- Add Movie --
        dao.addDVD(movie2.getTitle(), movie2);
        
        // -- Remove Movie. Assert removed. Assert null --
        dao.deleteDVD(movie1.getTitle());
        assertEquals(1, dao.listAllDVD().size());
        assertNull(dao.findDVD(movie1.getTitle()));
        // -- Remove Movie. Assert removed. Assert null --
        dao.deleteDVD(movie2.getTitle());
        assertEquals(0, dao.listAllDVD().size());
        assertNull(dao.findDVD(movie2.getTitle()));
    }

    /**
     * Test of listAllDVD method, of class DVDCollectionDao.
     */
    @Test
    public void testListAllDVD() throws Exception {
        Movies movie1 = new Movies("Space Jam");
        //movie1.setReleaseDate("March 1996");
        movie1.setmPAARating("100");
        movie1.setDirectorName("Jordan");
        movie1.setStudio("Hollywood");
        movie1.setUserRating("10");
        // -- Add Movie --
        dao.addDVD(movie1.getTitle(), movie1);
        
        Movies movie2 = new Movies("Dog Bam");
        //movie2.setReleaseDate("April 2000");
        movie2.setmPAARating("79");
        movie2.setDirectorName("Jack");
        movie2.setStudio("Hollywood");
        movie2.setUserRating("5");
        // -- Add Movie --
        dao.addDVD(movie2.getTitle(), movie2);
        
        // -- Assert 2 movies --
        assertEquals(2, dao.listAllDVD().size());
    }

    }
