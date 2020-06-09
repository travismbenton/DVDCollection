/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdcollection.dto;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/**
 *
 * @author travi
 */
public class Movies {    
    
    private String title; // -- Key --
    // -- Below are the Values --    
    
    private LocalDate releaseDate;         
    private String mPAARating;
    private String directorName;
    private String studio;
    private String userRating;    
    
//User rating (allows user to enter additional information, e.g., "Good family movie")

    // -- Constructor --
    
    public Movies(String title) { 
        this.title = title;
    }

    // -- GETTERS & SETTERS --
    
    public String getTitle() { // Read only field. No setter.
        return title;
    }    

// -- Below are read & write fields. Set manually after a "Movies object" has been instantiated.    

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }    
        
    public String getmPAARating() {
        return mPAARating;
    }

    public void setmPAARating(String mPAARating) {
        this.mPAARating = mPAARating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }
    
    
    // -- Calculated per perimeters. "Manually entered" --
    public long getMovieAge(){
        Period p = releaseDate.until(LocalDate.now());
        return p.getYears();
    } 

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.title);
        hash = 37 * hash + Objects.hashCode(this.releaseDate);
        hash = 37 * hash + Objects.hashCode(this.mPAARating);
        hash = 37 * hash + Objects.hashCode(this.directorName);
        hash = 37 * hash + Objects.hashCode(this.studio);
        hash = 37 * hash + Objects.hashCode(this.userRating);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Movies other = (Movies) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.mPAARating, other.mPAARating)) {
            return false;
        }
        if (!Objects.equals(this.directorName, other.directorName)) {
            return false;
        }
        if (!Objects.equals(this.studio, other.studio)) {
            return false;
        }
        if (!Objects.equals(this.userRating, other.userRating)) {
            return false;
        }
        if (!Objects.equals(this.releaseDate, other.releaseDate)) {
            return false;
        }
        return true;
    }

    
    
    
    
}
