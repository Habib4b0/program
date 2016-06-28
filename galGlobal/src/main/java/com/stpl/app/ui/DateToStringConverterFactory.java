package com.stpl.app.ui;

import java.util.Date;

import org.jboss.logging.Logger;

import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.util.converter.DefaultConverterFactory;

// TODO: Auto-generated Javadoc
/**
 * Factory Class to create Converter Object.
 *
 * @author
 */
public class DateToStringConverterFactory extends DefaultConverterFactory {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(DateToStringConverterFactory.class);
    
    /**
     * Factory method that creates the converter object and returns it.
     *
     * @param <PRESENTATION> the generic type
     * @param <MODEL> the generic type
     * @param presentationType the presentation type
     * @param modelType the model type
     * @return Converter Object
     */
    public <PRESENTATION, MODEL> Converter<PRESENTATION, MODEL> createConverter(
            final Class<PRESENTATION> presentationType, final Class<MODEL> modelType) {
        try{
        if (String.class == presentationType && Date.class == modelType) {
            return (Converter<PRESENTATION, MODEL>) new DateToStringConverter();
        }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

        return super.createConverter(presentationType, modelType);
    }
}
