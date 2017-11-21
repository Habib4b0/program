package com.stpl.ifs.ui;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.vaadin.v7.data.util.converter.Converter;
/**
 * String type Date converter
 * @author shrihariharan
 */
public class DateToStringConverter implements Converter<String, Date> {

	private static final long serialVersionUID = 1L;
        private String formatValue = "MM/dd/yyyy";

	public Date convertToModel(String value, Class<? extends Date> targetType,
			Locale locale)
			throws com.vaadin.v7.data.util.converter.Converter.ConversionException {
		 if (targetType != getModelType()) {
	            throw new ConversionException("Converter only supports "
	                    + getModelType().getName() + " (targetType was "
	                    + targetType.getName() + ")");
	        }

	        if (value == null) {
	            return null;
	        }

	        value = value.trim();

	        ParsePosition parsePosition = new ParsePosition(0);
	        Date parsedValue = getFormat(locale).parse(value, parsePosition);
	        if (parsePosition.getIndex() != value.length()) {
	            throw new ConversionException("Could not convert '" + value
	                    + "' to " + getModelType().getName());
	        }

	        return parsedValue;
	}

	public String convertToPresentation(Date value,
			Class<? extends String> targetType, Locale locale)
			throws com.vaadin.v7.data.util.converter.Converter.ConversionException {
		   if (value == null) {
	            return null;
	        }
		   SimpleDateFormat dateFormat = new SimpleDateFormat(formatValue);
		   String dateStr = dateFormat.format(value);
	        return dateStr;
	}
	   /*
     * (non-Javadoc)
     * 
     * @see com.vaadin.data.util.converter.Converter#getModelType()
     */
 
    public Class<Date> getModelType() {
        return Date.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.vaadin.data.util.converter.Converter#getPresentationType()
     */
   
    public Class<String> getPresentationType() {
        return String.class;
    }
	
	  /**
     * Returns the format used by
     * {@link #convertToPresentation(Date, Class,Locale)} and
     * {@link #convertToModel(String, Class, Locale)}.
     * 
     * @param locale
     *            The locale to use
     * @return A DateFormat instance
     */
    protected DateFormat getFormat(Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }

        DateFormat f = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
                DateFormat.MEDIUM, locale);
        f.setLenient(false);
        return f;
    }
     public void setDateFormat(String formatValue) {
        this.formatValue = formatValue;
    }

}
