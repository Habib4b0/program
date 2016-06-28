package com.stpl.ifs.ui;

import java.util.Date;

import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.util.converter.DefaultConverterFactory;
/**
 * Converter Factory
 * @author shrihariharan
 */
public class DateToStringConverterFactory extends DefaultConverterFactory {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public <PRESENTATION, MODEL> Converter<PRESENTATION, MODEL> createConverter(
			Class<PRESENTATION> presentationType, Class<MODEL> modelType) {
		// Handle one particular type conversion
		if (String.class == presentationType && Date.class == modelType)
			return (Converter<PRESENTATION, MODEL>) new DateToStringConverter();

		// Default to the supertype
		return super.createConverter(presentationType, modelType);
	}
}
