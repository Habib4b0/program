/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.utils.converters;

/**
 *
 * @author Vijayalakshmi.Kishore
 */
public class DataTypeConverter {
    
    /**
     * Converts an object to integer
     * @param objInput
     * @return integer
     */
    public static int convertObjectToInt (Object objInput){
        return Integer.parseInt(String.valueOf(objInput));
    }
    /**
     * Converts an object to double
     * @param objInput
     * @return double
     */
    public static double convertObjectToDouble (Object objInput){
        return Double.parseDouble(String.valueOf(objInput));
    }
    
    /**
     * Converts integer to string
     * @param strInput
     * @return string
     */
    public static int convertStringToInteger(String strInput) {
        return Integer.parseInt(strInput);
    }
    
    /**
     * Converts long to string 
     * @param longInput
     * @return long
     */
    public static long convertLongToInteger(Long longInput) {
        return Integer.parseInt(String.valueOf(longInput));
    }
    
    /**
     * Converts String to Double
     * @param stringInput
     * @return double
     */
    public static double convertStringToDouble(String stringInput) {
        return Double.parseDouble(String.valueOf(stringInput));
    }
    
}
