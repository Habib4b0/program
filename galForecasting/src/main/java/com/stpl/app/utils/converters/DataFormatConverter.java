package com.stpl.app.utils.converters;

import com.stpl.app.galforecasting.utils.Constant.CommonConstants;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;

import com.vaadin.data.util.converter.Converter;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author soundarrajan.l
 *
 * Text field converter to trim
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
    private final String numericRegex = "[^\\d.]";
    private final String numericDashRegex = "[^\\d.-]";
    private final String exponentialDashRegex = "[^\\d.Ee-]";

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
            throws com.vaadin.data.util.converter.Converter.ConversionException {
        String regex = StringUtils.EMPTY;
        if (targetType != getModelType()) {
            throw new Converter.ConversionException("Converter only supports "
                    + getModelType().getName() + " (targetType was "
                    + targetType.getName() + ")");
        }

        if (value == null) {
            return null;
        }
        if (value.contains("E-") || value.contains("e-")) {
            regex = exponentialDashRegex;
        } else {
            regex = numericDashRegex;
        }
        value = value.trim();
        String parsedValue = StringUtils.EMPTY;
        try {
            parsedValue = value.replaceAll(regex, StringUtils.EMPTY);
        } catch (Exception ex) {
            LOGGER.error(ex + " in DataFormatConverter - convertToModel");
        }
        return parsedValue;
    }

    @Override
    public String convertToPresentation(String value,
            Class<? extends String> targetType, Locale locale)
            throws com.vaadin.data.util.converter.Converter.ConversionException {
        String regex = StringUtils.EMPTY;
        if (value == null) {
            return null;
        }
        if (value.equals("-")) {
            return "-";
        }
        if (value.contains("E-") || value.contains("e-")) {
            regex = exponentialDashRegex;
        } else {
            regex = numericDashRegex;
        }
        try {
            String stringValue = StringUtils.EMPTY;
            String tempValue = StringUtils.EMPTY;
            tempValue = value.trim().replaceAll(regex, StringUtils.EMPTY);
            DecimalFormat df = getFormatter();
            if (!StringUtils.EMPTY.equals(tempValue) && !CommonConstants.NULL.getConstant().equals(tempValue)
                    && !CommonConstants.NULL.getConstant().equals(tempValue)
                    && !StringUtils.EMPTY.equals(tempValue)) {
                value = value.trim().replaceAll(regex, StringUtils.EMPTY);

                if (df != null) {
                    BigDecimal bd = new BigDecimal(value);

                    stringValue = df.format(bd);

                } else {
                    stringValue = value.replaceAll(regex, StringUtils.EMPTY);
                }
                if (indicator != null && !StringUtils.EMPTY.equals(indicator) && !CommonConstants.NULL.getConstant().equals(indicator) && INDICATOR_PERCENT.equals(indicator)) {
                    stringValue += INDICATOR_PERCENT;
                }
            } else {
                stringValue = value.replaceAll(regex, StringUtils.EMPTY);
            }
            return stringValue;
        } catch (Exception ex) {
            LOGGER.error(ex + "  in DataFormatConverter - convertToPresentation");
            return null;
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
        if (getFormat() != null && !StringUtils.EMPTY.equals(getFormat()) && !CommonConstants.NULL.getConstant().equals(getFormat())) {
            if (indicator != null && !CommonConstants.NULL.getConstant().equals(indicator)
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
