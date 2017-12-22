package com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.vaadin.v7.data.util.converter.Converter;

public class GtnUIFrameworkDateFormatConverter implements Converter<String, Date> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2670500024232907566L;
	private String formatString = "";
	private SimpleDateFormat dateFormat = null;

	public GtnUIFrameworkDateFormatConverter(String formatString) {
		this.formatString = formatString;
		dateFormat = new SimpleDateFormat(this.formatString);
	}

	@Override
	public Date convertToModel(String value, Class<? extends Date> targetType, Locale locale)
			throws ConversionException {

		Date date = null;
		if ("".equals(value))
			return date;
		try {

			date = dateFormat.parse(value);
		} catch (Exception ex) {
			throw new ConversionException(ex);
		}
		return date;
	}

	@Override
	public String convertToPresentation(Date value, Class<? extends String> targetType, Locale locale)
			throws ConversionException {
		if (value != null) {
			return dateFormat.format(value);
		}

		return "";
	}

	@Override
	public Class<Date> getModelType() {
		return Date.class;
	}

	@Override
	public Class<String> getPresentationType() {
		return String.class;
	}
}
