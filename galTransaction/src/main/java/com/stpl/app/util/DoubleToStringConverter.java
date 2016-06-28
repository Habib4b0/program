package com.stpl.app.util;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.Locale;

import org.jboss.logging.Logger;

import com.vaadin.data.util.converter.Converter;

/**
 * The Class DoubleToStringConverter.
 */
public class DoubleToStringConverter implements Converter<String, Double> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(DoubleToStringConverter.class);

	/** The parsed value. */
	private static Double parsedValue;

	/**
	 * (non-Javadoc).
	 *
	 * @param value
	 *            the value
	 * @param targetType
	 *            the target type
	 * @param locale
	 *            the locale
	 * @return the double
	 * @see com.vaadin.data.util.converter.Converter#convertToModel(java.lang.Object,
	 *      java.lang.Class, java.util.Locale)
	 */
	public Double convertToModel(final String value, final Class<? extends Double> targetType, final Locale locale) {

		try {
			LOGGER.info("Entering convertToModel ");
			if (targetType != getModelType()) {
				throw new ConversionException("Converter only supports " + getModelType().getName() + " (targetType was " + targetType.getName() + ")");
			}
			String value1 = value;
			if (value1 == null) {
				return null;
			}

			value1 = value1.trim();

			final ParsePosition parsePosition = new ParsePosition(0);
			parsedValue = (Double) getFormat(locale).parse(value1, parsePosition);
			if (parsePosition.getIndex() != value1.length()) {
				throw new ConversionException("Could not convert '" + value1 + "' to " + getModelType().getName());
			}
			LOGGER.info("Ends convertToModel ");
			return parsedValue;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
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
	public String convertToPresentation(final Double value, final Class<? extends String> targetType, final Locale locale) {
		try {
			if (value == null) {
				return null;
			}
			final DecimalFormat decimalFormat = new DecimalFormat("#.00");
			final String dateStr = decimalFormat.format(value);
			return dateStr;

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}

	}

	/**
	 * (non-Javadoc).
	 *
	 * @return the model type
	 * @see com.vaadin.data.util.converter.Converter#getModelType()
	 */
	public Class<Double> getModelType() {
		return Double.class;
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
			LOGGER.info("Enters getFormat ");
			if (locale2 == null) {
				locale2 = Locale.getDefault();
			}

			final DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance(locale2);
			format.setMaximumFractionDigits(2);
			LOGGER.info("Enters getFormat ");
			return format;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}

	}
}