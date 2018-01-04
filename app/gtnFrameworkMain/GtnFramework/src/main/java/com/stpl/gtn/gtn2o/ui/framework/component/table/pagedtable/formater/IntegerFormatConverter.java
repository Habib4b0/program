/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.formater;

import java.util.Locale;

import com.vaadin.v7.data.util.converter.Converter;
import com.vaadin.v7.data.util.converter.StringToIntegerConverter;

/**
 *
 * @author Abhiram.Giri
 */
public class IntegerFormatConverter extends StringToIntegerConverter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final IntegerFormatConverter _INSTANCE = new IntegerFormatConverter();

	@Override
	public String convertToPresentation(Integer value, Class<? extends String> targetType, Locale locale)
			throws Converter.ConversionException {
		return String.valueOf(value);
	}

	public static IntegerFormatConverter getConverter() {
		return _INSTANCE;
	}
}
