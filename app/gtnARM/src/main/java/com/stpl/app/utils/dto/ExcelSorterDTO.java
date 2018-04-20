/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.utils.dto;

/**
 *
 * @author Sathya.Seelan
 */
public class ExcelSorterDTO {

    private int key;
    private String value;

    public ExcelSorterDTO() {
        /*
        THE DEFAULT CONSTRUCTOR
         */
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
