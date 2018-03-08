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
    
    private static final boolean trueFlag = true;
    
    private static final boolean falseFlag = false;

    /**
     * utilize this method to get true
     * @return true(boolean)
     */
    public boolean getTrueFlag() {
        return trueFlag;
    }

    /**
     * * utilize this method to get false
     * @return false(boolean)
     */
    public boolean getFalseFlag() {
        return falseFlag;
    }
    
}
