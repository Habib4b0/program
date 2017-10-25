/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.util;

import com.stpl.ifs.ui.util.NumericConstants;

/**
 *
 * @author karthikeyans
 */
public class CalendarUtils {

    private CalendarUtils() {
    }

    public static int getMonth_index(String month) throws IllegalArgumentException {
        int index = 0;
        switch (month) {
            case "January":
                index = 1;
                break;
            case "February":
                index = NumericConstants.TWO;
                break;
            case "March":
                index = NumericConstants.THREE;
                break;
            case "April":
                index = NumericConstants.FOUR;
                break;
            case "May":
                index = NumericConstants.FIVE;
                break;
            case "June":
                index = NumericConstants.SIX;
                break;
            case "July":
                index = NumericConstants.SEVEN;
                break;
            case "August":
                index = NumericConstants.EIGHT;
                break;
            case "September":
                index = NumericConstants.NINE;
                break;
            case "October":
                index = NumericConstants.TEN;
                break;
            case "November":
                index = NumericConstants.ELEVEN;
                break;
            case "December":
                index = NumericConstants.TWELVE;
                break;
            default:
                throw new IllegalArgumentException("Input is not valid Month in Calendar :" + month);
        }
        return index;
    }

}
