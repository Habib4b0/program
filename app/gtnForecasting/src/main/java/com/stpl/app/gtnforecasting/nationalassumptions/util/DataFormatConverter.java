package com.stpl.app.gtnforecasting.nationalassumptions.util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.vaadin.v7.data.util.converter.Converter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;
import org.apache.commons.lang.StringUtils;

/**
 *
 * Text field converter to trim
 *
 * @author Vinodhini
 */
public class DataFormatConverter implements Converter<String, String> {

    private static final long serialVersionUID = 1L;
    /**
     * The Constant LOGGER.
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(DataFormatConverter.class);

    private String format = null;
    private String indicator = null;
    public static final String INDICATOR_PERCENT = "%";
    public static final String INDICATOR_DOLLAR = "$";
    private final String numericDashRegex = "[^\\d.-]";

    public DataFormatConverter(String format) {
        this.format = format;
    }

    public DataFormatConverter(String format, String indicator) {
        this.format = format;
        this.indicator = indicator;
    }

    @Override
    public String convertToModel(String value,
            Class<? extends String> targetType, Locale locale)
            {
        if (targetType != getModelType()) {
            throw new Converter.ConversionException("Converter only supports "
                    + getModelType().getName() + " (targetType was "
                    + targetType.getName() + ")");
        }

        if (value == null) {
            return null;
        }
        value = value.trim();
        String parsedValue = StringUtils.EMPTY;
        try {
            parsedValue = value.replaceAll(numericDashRegex, StringUtils.EMPTY);
        } catch (Exception ex) {
           LOGGER.error(ex);
        }
        return parsedValue;
    }

    @Override
    public String convertToPresentation(String value,
            Class<? extends String> targetType, Locale locale)
             {

        if (value == null) {
            return null;
        }
        if (value.contains("-")) {
            return StringUtils.EMPTY;
        }
        if (StringUtils.isBlank(value)) {
            return StringUtils.EMPTY;
        }
        try {
            String stringValue = StringUtils.EMPTY;
            String tempValue;
            tempValue = value.trim().replaceAll(numericDashRegex, StringUtils.EMPTY);
            DecimalFormat df = getFormatter();
            if (!StringUtils.EMPTY.equals(tempValue) && !Constants.NULL.equals(tempValue)
                    && !Constants.NULL.equals(tempValue)
                    && !StringUtils.EMPTY.equals(tempValue)) {
                value = value.trim().replaceAll(numericDashRegex, StringUtils.EMPTY);

                if (df != null) {
                    if (StringUtils.isNotBlank(value)) {
                        BigDecimal bd = new BigDecimal(value);
                        stringValue = df.format(bd);
                    }

                } else {
                    stringValue = value.replaceAll(numericDashRegex, StringUtils.EMPTY);
                }
                if (indicator != null && !StringUtils.EMPTY.equals(indicator) && !Constants.NULL.equals(indicator) && INDICATOR_PERCENT.equals(indicator)) {
                    stringValue += INDICATOR_PERCENT;
                }
            } else {
                stringValue = value.replaceAll(numericDashRegex, StringUtils.EMPTY);
            }
            return stringValue;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return StringUtils.EMPTY;
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.vaadin.data.util.converter.Converter#getModelType()
     */
    @Override
    public Class<String> getModelType() {
        return String.class;
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

    private DecimalFormat getFormatter() {
        DecimalFormat df = null;
        if (getFormat() != null && !StringUtils.EMPTY.equals(getFormat()) && !Constants.NULL.equals(getFormat())) {
            if (indicator != null && !Constants.NULL.equals(indicator)
                    && !StringUtils.EMPTY.equals(indicator) && INDICATOR_DOLLAR.equals(indicator)) {
                df = new DecimalFormat(INDICATOR_DOLLAR + getFormat());
            } else {
                df = new DecimalFormat(getFormat());
            }
        }
        return df;
    }

    private String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

}
