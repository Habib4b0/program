/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.util.constants;

/**
 * Utilize this class to get boolean true/false.
 * Do not create any boolean constants locally.
 * @author Vijayalakshmi.Kishor
 */
public final class BooleanConstant {
    
    private static final boolean TRUE_FLAG = true;
    
    private static final boolean FALSE_FLAG = false;

    /**
     * utilize this method to get true
     * @return true(boolean)
     */
    public static boolean getTrueFlag() {
        return TRUE_FLAG;
    }

    /**
     * * utilize this method to get false
     * @return false(boolean)
     */
    public static boolean getFalseFlag() {
        return FALSE_FLAG;
    }
    
}
