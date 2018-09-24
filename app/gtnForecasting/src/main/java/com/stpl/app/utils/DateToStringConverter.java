package com.stpl.app.utils;

import com.vaadin.v7.data.util.converter.Converter;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * String type Date converter
 *
 * @author shrihariharan
 */
public class DateToStringConverter implements Converter<String, Date> {

    private static final long serialVersionUID = 1L;

    @Override
    public Date convertToModel(String value, Class<? extends Date> targetType,
            Locale locale)
             {
                 String valueMod = value;
        if (targetType != getModelType()) {
            throw new Converter.ConversionException("Converter only supports "
                    + getModelType().getName() + " (targetType was "
                    + targetType.getName() + ")");
        }

        if (valueMod == null) {
            return null;
        }

        valueMod = valueMod.trim();
        ParsePosition parsePosition = new ParsePosition(0);
        Date parsedValue = getFormat(locale).parse(valueMod, parsePosition);
        if (parsePosition.getIndex() != valueMod.length()) {
            throw new Converter.ConversionException("Could not convert '" + valueMod
                    + "' to " + getModelType().getName());
        }

        return parsedValue;
    }

    @Override
    public String convertToPresentation(Date value,
            Class<? extends String> targetType, Locale locale)
             {
        if (value == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        return dateFormat.format(value);
    }
    /*
     * (non-Javadoc)
     * 
     * @see com.vaadin.data.util.converter.Converter#getModelType()
     */

    @Override
    public Class<Date> getModelType() {
        return Date.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.vaadin.data.util.converter.Converter#getPresentationType()
     */
    @Override
    public Class<String> getPresentationType() {
        return String.class;
    }

    /**
     * Returns the format used by
     * {@link #convertToPresentation(Date, Class,Locale)} and
     * {@link #convertToModel(String, Class, Locale)}.
     *
     * @param locale The locale to use
     * @return A DateFormat instance
     */
    protected DateFormat getFormat(Locale locale) {
        Locale localeFormat = locale;
        if (localeFormat == null) {
            localeFormat = Locale.getDefault();
        }

        DateFormat f = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
                DateFormat.MEDIUM, localeFormat);
        f.setLenient(false);
        return f;
    }

}
