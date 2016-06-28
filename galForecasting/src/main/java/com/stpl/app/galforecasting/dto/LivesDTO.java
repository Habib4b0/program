/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.galforecasting.dto;

import java.util.Date;

/**
 *
 * @author Maheshj
 */
public class LivesDTO {
   private Date startDate=new Date();
   private Date endDate=new Date();
   private int startQuator=0;
   private int startYear=0;
    private int endQuator=0;
   private int endYear=0;
   private double lives=0.0;
   private String comp_Name="empty";

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getStartQuator() {
        return startQuator;
    }

    public void setStartQuator(int startQuator) {
        this.startQuator = startQuator;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndQuator() {
        return endQuator;
    }

    public void setEndQuator(int endQuator) {
        this.endQuator = endQuator;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public String getComp_Name() {
        return comp_Name;
    }

    public void setComp_Name(String comp_Name) {
        this.comp_Name = comp_Name;
    }

 
    public double getLives() {
        return lives;
    }

    public void setLives(double lives) {
        this.lives = lives;
    }
   
}
