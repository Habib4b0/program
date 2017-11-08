package com.stpl.app.util;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.jboss.logging.Logger;

import com.vaadin.data.util.converter.Converter;

/**
 * The Class DateToStringConverter.
 */
public class DateToStringConverter implements Converter<String, Date> {

	/**
	 * To get Logger information.
	 */
	private static final Logger LOGGER = Logger.getLogger(DateToStringConverter.class);

	/** The parsed value. */
	private static Date parsedValue;

	/**
	 * (non-Javadoc).
	 *
	 * @param value
	 *            the value
	 * @param targetType
	 *            the target type
	 * @param locale
	 *            the locale
	 * @return the date
	 * @see com.vaadin.data.util.converter.Converter#convertToModel(java.lang.Object,
	 *      java.lang.Class, java.util.Locale)
	 */
	public Date convertToModel(final String value, final Class<? extends Date> targetType, final Locale locale) {

		try {

			LOGGER.debug("Enters convertToModel ");
			if (targetType != getModelType()) {
				throw new ConversionException("Converter only supports " + getModelType().getName() + " (targetType was " + targetType.getName() + ")");
			}
			String value1 = value;
			if (value1 == null) {
				return null;
			}

			value1 = value1.trim();

			final ParsePosition parsePosition = new ParsePosition(0);
			parsedValue = getFormat(locale).parse(value1, parsePosition);
			if (parsePosition.getIndex() != value1.length()) {
				throw new ConversionException("Could not convert '" + value1 + "' to " + getModelType().getName());
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
	public String convertToPresentation(final Date value, final Class<? extends String> targetType, final Locale locale) {
		try {
			if (value == null) {
				return null;
			}
			final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			final String dateStr = dateFormat.format(value);
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
	public Class<Date> getModelType() {
		return Date.class;
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
	protected DateFormat getFormat(final Locale locale) {
		Locale locale1 = locale;
		try {
			LOGGER.debug("Entering getFormat with locale");
			if (locale1 == null) {
				locale1 = Locale.getDefault();
			}

			final DateFormat format = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, locale1);
			format.setLenient(false);
			LOGGER.debug("Ends getFormat with format");
			return format;
		} catch (Exception e) {
			LOGGER.error(e);
			return null;
		}
	}
}
