/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.gtnforecasting.dto;

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
   private String compName="empty";

    public Date getStartDate() {
        return startDate == null ? null : (Date) startDate.clone();
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate == null ? null : (Date) startDate.clone();
    }

    public Date getEndDate() {
        return endDate == null ? null : (Date) endDate.clone();
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate == null ? null : (Date) endDate.clone();
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

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

 
    public double getLives() {
        return lives;
    }

    public void setLives(double lives) {
        this.lives = lives;
    }
   
}
