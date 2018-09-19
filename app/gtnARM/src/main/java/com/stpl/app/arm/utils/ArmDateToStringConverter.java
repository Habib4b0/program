package com.stpl.app.arm.utils;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.vaadin.v7.data.util.converter.Converter;

/**
 * String type Date converter
 *
 * @author Vinodhini.Chandrasekar
 */
public class ArmDateToStringConverter implements Converter<String, Date> {

    private static final long serialVersionUID = 1L;

    @Override
    public Date convertToModel(String value, Class<? extends Date> targetType,
            Locale locale) {
        if (targetType != getModelType()) {
            throw new ConversionException("Converter only supports "
                    + getModelType().getName() + " (targetType was "
                    + targetType.getName() + ARMUtils.CLOSE_PARANTHESIS);
        }

        if (value == null) {
            return null;
        }

        String values = value.trim();

        ParsePosition parsePosition = new ParsePosition(0);
        Date parsedValue = getFormat(locale).parse(values, parsePosition);
        if (parsePosition.getIndex() != values.length()) {
            throw new ConversionException("Could not convert '" + values
                    + "' to " + getModelType().getName());
        }

        return parsedValue;
    }

    @Override
    public String convertToPresentation(Date value,
            Class<? extends String> targetType, Locale locale) {
        if (value == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(ARMUtils.MM_DD_YYYY_HH_MM_SS);
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
        Locale locales;
        if (locale == null) {
            locales = Locale.getDefault();
        } else {
            locales = locale;
        }

        DateFormat f = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
                DateFormat.MEDIUM, locales);
        f.setLenient(false);
        return f;
    }

}
