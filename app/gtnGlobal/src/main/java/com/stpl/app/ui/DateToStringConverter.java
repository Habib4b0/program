package com.stpl.app.ui;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.jboss.logging.Logger;

import com.vaadin.v7.data.util.converter.Converter;

// TODO: Auto-generated Javadoc
/**
 * Utility class to convert Date to String format.
 *
 * @author
 */
public class DateToStringConverter implements Converter<String, Date> {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(DateToStringConverter.class);

    private String formatValue = "MM/dd/yyyy";

    /**
     * Converts the String value to formatted Date Object.
     *
     * @param value the value
     * @param targetType the target type
     * @param locale the locale
     * @return the date
     */
    public Date convertToModel(final String value, final Class<? extends Date> targetType,
            final Locale locale) {
        String values = value;
        Date parsedValue = null;
        try {
            if (targetType != getModelType()) {
                throw new ConversionException("Converter only supports "
                        + getModelType().getName() + " (targetType was "
                        + targetType.getName() + ")");
            }

            if (values == null) {
                return null;
            }

            values = values.trim();

            final ParsePosition parsePosition = new ParsePosition(0);
            if (parsePosition.getIndex() != values.length()) {
                throw new ConversionException("Could not convert '" + values
                        + "' to " + getModelType().getName());
            }
            parsedValue = getFormat(locale).parse(values, parsePosition);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

        return parsedValue;
    }

    /**
     * Converts the date to formatted String object.
     *
     * @param value the value
     * @param targetType the target type
     * @param locale the locale
     * @return the string
     */
    public String convertToPresentation(final Date value,
            final Class<? extends String> targetType, final Locale locale) {
        String dateStr = "";
        try {

            if (value == null) {
                return null;
            }
            final SimpleDateFormat dateFormat = new SimpleDateFormat(formatValue);
            dateStr = dateFormat.format(value);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return dateStr;
    }

    /**
     * method returns Date.class
     *
     * @return the model type
     */
    public Class<Date> getModelType() {
        return Date.class;
    }

    /**
     * method returns String.class
     *
     * @return the presentation type
     */
    public Class<String> getPresentationType() {
        return String.class;
    }

    /**
     * Gets the Date Format and returns it.
     *
     * @param locale The locale to use
     * @return A DateFormat instance
     */
    protected DateFormat getFormat(final Locale locale) {
        Locale local = locale;
        DateFormat format = null;
        if (local == null) {
            local = Locale.getDefault();
        }

        format = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
                DateFormat.MEDIUM, local);
        format.setLenient(false);
        LOGGER.debug(" Ends addComponentInGridPosition  ");
        return format;
    }

    public void setDateFormat(String formatValue) {
        this.formatValue = formatValue;
    }
}
