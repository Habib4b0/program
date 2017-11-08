/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.util;

import com.stpl.domain.transaction.base.HasItemId;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.util.converter.Converter;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.Locale;
import org.jboss.logging.Logger;

/**
 *
 * @author Gopinath.Mathiyalaga
 */
public class IntegerToStringConverter  implements Converter<String, Integer>,HasItemId {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(DoubleToStringConverter.class);

	/** The parsed value. */
	private static Integer parsedValue;
        
        Object itemId=null;

        @Override
    public Object getItemId() {
        return itemId;
    }

        @Override
    public void setItemId(Object itemId) {
        this.itemId = itemId;
    }

	/**
	 * (non-Javadoc).
	 *
	 * @param value
	 *            the value
	 * @param targetType
	 *            the target type   `   
	 * @param locale
	 *            the locale
	 * @return the double
	 * @see com.vaadin.data.util.converter.Converter#convertToModel(java.lang.Object,
	 *      java.lang.Class, java.util.Locale)
	 */
	public Integer convertToModel(final String value, final Class<? extends Integer> targetType, final Locale locale) {

		try {
			LOGGER.debug("Entering convertToModel ");
			if (targetType != getModelType()) {
				throw new Converter.ConversionException("Converter only supports " + getModelType().getName() + " (targetType was " + targetType.getName() + ")");
			}
			String value1 = value;
			if (value1 == null) {
				return null;
			}

			value1 = value1.trim();
                   
			final ParsePosition parsePosition = new ParsePosition(0);
			parsedValue = (Integer) getFormat(locale).parse(value1, parsePosition);
			if (parsePosition.getIndex() != value1.length()) {
				throw new Converter.ConversionException("Could not convert '" + value1 + "' to " + getModelType().getName());
			}
			LOGGER.debug("Ends convertToModel ");
			return parsedValue;
		} catch (Exception e) {
			LOGGER.error(e);
			return null;
		}
	}

	/**
	 * (non-Javadoc).
	 *
	 * @param value
	 *            the value
	 * @param targetType
	 *            the target type
	 * @param locale
	 *            the locale
	 * @return the string
	 * @see com.vaadin.data.util.converter.Converter#convertToPresentation(java.lang.Object,
	 *      java.lang.Class, java.util.Locale)
	 */
        @Override
    public String convertToPresentation(final Integer value, final Class<? extends String> targetType, final Locale locale) {
        try {
            if (value == null) {
                return null;
            }
             DecimalFormat decimalFormat = new DecimalFormat("####");
            
            final String dateStr = decimalFormat.format(value);
            return dateStr;

        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        }

	}

	/**
	 * (non-Javadoc).
	 *
	 * @return the model type
	 * @see com.vaadin.data.util.converter.Converter#getModelType()
	 */
	public Class<Integer> getModelType() {
		return Integer.class;
	}

	/**
	 * (non-Javadoc).
	 *
	 * @return the presentation type
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
	protected DecimalFormat getFormat(final Locale locale) {
		Locale locale2 = locale;
		try {
			LOGGER.debug("Enters getFormat ");
			if (locale2 == null) {
				locale2 = Locale.getDefault();
}

			final DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance(locale2);
			format.setMaximumFractionDigits(NumericConstants.TWO);
			LOGGER.debug("Enters getFormat ");
			return format;
		} catch (Exception e) {
			LOGGER.error(e);
			return null;
		}

	}
}