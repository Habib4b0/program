/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.ui.util.converters;

import com.vaadin.v7.data.util.converter.Converter;
import java.util.Locale;

/**
 *
 * @author soundarrajan.l
 *
 * Text field converter to trim
 */
public class ConvertToZero implements Converter<String, String> {

    @Override
    public String convertToModel(String value, Class<? extends String> targetType, Locale locale) throws Converter.ConversionException {
        return "0";
    }

    @Override
    public String convertToPresentation(String value, Class<? extends String> targetType, Locale locale) throws Converter.ConversionException {
       return "0";
    }

    @Override
    public Class<String> getModelType() {
        return String.class;
    }

    @Override
    public Class<String> getPresentationType() {
        return String.class;
    }
}
