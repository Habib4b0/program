package com.stpl.app.adminconsole.util;

import java.util.Date;


import com.stpl.ifs.ui.DateToStringConverter;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.util.converter.DefaultConverterFactory;

/**
 * A factory for creating DateToStringConverter objects.
 */
public class DateToStringConverterFactory extends DefaultConverterFactory {


    /**
     * Converting the String and Date model
     *
     * @param <PRESENTATION>
     * @param <MODEL>
     * @param presentationType
     * @param modelType
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public <PRESENTATION, MODEL> Converter<PRESENTATION, MODEL> createConverter(final Class<PRESENTATION> presentationType, final Class<MODEL> modelType) {
        Converter<PRESENTATION, MODEL> converter;

        if (String.class == presentationType && Date.class == modelType) {
            converter = (Converter<PRESENTATION, MODEL>) new DateToStringConverter();
        } else {
            converter = super.createConverter(presentationType, modelType);
        }

        return converter;
    }
}
