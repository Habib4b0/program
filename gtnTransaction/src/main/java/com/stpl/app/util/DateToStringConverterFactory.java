package com.stpl.app.util;

import java.util.Date;

import org.jboss.logging.Logger;

import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.util.converter.DefaultConverterFactory;

/**
 * The Class DateToStringConverterFactory.
 */
public class DateToStringConverterFactory extends DefaultConverterFactory {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(DateToStringConverterFactory.class);

	/**
	 * (non-Javadoc).
	 *
	 * @param <PRESENTATION>
	 *            the generic type
	 * @param <MODEL>
	 *            the generic type
	 * @param presentationType
	 *            the presentation type
	 * @param modelType
	 *            the model type
	 * @return the converter< presentatio n, mode l>
	 * @see com.vaadin.data.util.converter.DefaultConverterFactory#createConverter(java.lang.Class,
	 *      java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <PRESENTATION, MODEL> Converter<PRESENTATION, MODEL> createConverter(final Class<PRESENTATION> presentationType, final Class<MODEL> modelType) {
		try {
			if (String.class == presentationType && Date.class == modelType) {
				return (Converter<PRESENTATION, MODEL>) new DateToStringConverter();
			}
			return super.createConverter(presentationType, modelType);
		} catch (Exception e) {
			LOGGER.error(e);
			return null;
		}
	}
}
