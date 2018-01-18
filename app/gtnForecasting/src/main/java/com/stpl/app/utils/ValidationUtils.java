/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.utils;

/**
 *
 * @author gopinath
 */
public final class ValidationUtils {

    /**
     * The Constant SPECIAL_CHAR.
     */
    public static final String SPECIAL_CHAR = "([0-9|a-z|A-Z|\\.|\\,|\\_|\\-|\\@|\\#|\\$|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*";
    public static final String ALPHANUMERIC_VALIDATION = "([0-9|a-z|A-Z|\\*])*";
    public static final String TEXT_VALIDATION = "([a-z|A-Z|\\*])*";

    public static final String NDC_VALIDATION_MSG = "NDC# can contain only only be Alphanumeric";
    public static final String BRAND_NAME_VALIDATION_MSG = "Brand Name can contain only Text";
    public static final String NDC_DESC_VALIDATION_MSG = "Ndc Desc can contain only Text";

    
}
