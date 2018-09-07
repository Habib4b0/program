package com.stpl.app.utils.converters;

import com.stpl.app.gtnforecasting.utils.Constant.CommonConstants;
import com.vaadin.v7.data.util.converter.Converter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(DataFormatConverter.class);

    private String format = null;
    private String indicator = null;
    public static final String INDICATOR_PERCENT = "%";
    public static final String INDICATOR_DOLLAR = "$";
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
             {
        String regex;
        String valueModel = value;
        if (targetType != getModelType()) {
            throw new Converter.ConversionException("Converter only supports "
                    + getModelType().getName() + " (targetType was "
                    + targetType.getName() + ")");
        }

        if (valueModel == null) {
            return null;
        }
        if (valueModel.contains("E-") || valueModel.contains("e-")) {
            regex = exponentialDashRegex;
        } else {
            regex = numericDashRegex;
        }
        valueModel = valueModel.trim();
        String parsedValue = StringUtils.EMPTY;
        try {
            parsedValue = valueModel.replaceAll(regex, StringUtils.EMPTY);
        } catch (Exception ex) {
            LOGGER.error(" in DataFormatConverter - convertToModel= {}", ex);
        }
        return parsedValue;
    }

    @Override
    public String convertToPresentation(String value,
            Class<? extends String> targetType, Locale locale)
             {
        String regex;
        String valuePresentation = value;
        if (valuePresentation == null) {
            return null;
        }
        if (valuePresentation.equals("-")) {
            return "-";
        }
        if (valuePresentation.contains("E-") || valuePresentation.contains("e-")) {
            regex = exponentialDashRegex;
        } else {
            regex = numericDashRegex;
        }
        try {
            String stringValue;
            String tempValue;
            tempValue = valuePresentation.trim().replaceAll(regex, StringUtils.EMPTY);
            DecimalFormat df = getFormatter();
            if (!StringUtils.EMPTY.equals(tempValue) && !CommonConstants.NULL.getConstant().equals(tempValue)) {
                valuePresentation = valuePresentation.trim().replaceAll(regex, StringUtils.EMPTY);

                if (df != null) {
                    BigDecimal bd = new BigDecimal(valuePresentation);

                    stringValue = df.format(bd);

                } else {
                    stringValue = valuePresentation.replaceAll(regex, StringUtils.EMPTY);
                }
                if (indicator != null && !StringUtils.EMPTY.equals(indicator) && !CommonConstants.NULL.getConstant().equals(indicator) && INDICATOR_PERCENT.equals(indicator)) {
                    stringValue += INDICATOR_PERCENT;
                }
            } else {
                stringValue = valuePresentation.replaceAll(regex, StringUtils.EMPTY);
            }
            return stringValue;
        } catch (Exception ex) {
            LOGGER.error("in DataFormatConverter - convertToPresentation= {}", ex);
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
