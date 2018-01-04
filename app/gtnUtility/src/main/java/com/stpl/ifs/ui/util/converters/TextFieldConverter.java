/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.ui.util.converters;

import com.stpl.ifs.ui.util.UIUtil;
import com.vaadin.v7.data.util.converter.Converter;
import java.util.Locale;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author soundarrajan.l
 *
 * Text field converter to trim
 */
public class TextFieldConverter implements Converter<String, String> {

    @Override
    public String convertToModel(String value, Class<? extends String> targetType, Locale locale) throws Converter.ConversionException {
        if (value != null && !UIUtil.NULL.equals(value)) {
            return value.trim();
        }
        return StringUtils.EMPTY;
    }

    @Override
    public String convertToPresentation(String value, Class<? extends String> targetType, Locale locale) throws Converter.ConversionException {
        if (value == null || UIUtil.NULL.equals(value)) {
            return StringUtils.EMPTY;
        } else {
            return value.trim();
        }
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
