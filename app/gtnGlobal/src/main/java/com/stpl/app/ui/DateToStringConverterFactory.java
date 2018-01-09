package com.stpl.app.ui;

import java.util.Date;

import org.slf4j.Logger;

import com.vaadin.v7.data.util.converter.Converter;
import com.vaadin.v7.data.util.converter.DefaultConverterFactory;
import org.slf4j.LoggerFactory;

/**
 * Factory Class to create Converter Object.
 *
 * @author
 */
public class DateToStringConverterFactory extends DefaultConverterFactory {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DateToStringConverterFactory.class);
    
    /**
     * Factory method that creates the converter object and returns it.
     *
     * @param <PRESENTATION> the generic type
     * @param <MODEL> the generic type
     * @param presentationType the presentation type
     * @param modelType the model type
     * @return Converter Object
     */
    @Override
    public <PRESENTATION, MODEL> Converter<PRESENTATION, MODEL> createConverter(
            final Class<PRESENTATION> presentationType, final Class<MODEL> modelType) {
        try{
        if (String.class == presentationType && Date.class == modelType) {
            return (Converter<PRESENTATION, MODEL>) new DateToStringConverter();
        }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

        return super.createConverter(presentationType, modelType);
    }
}
