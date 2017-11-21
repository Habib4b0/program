/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import static com.stpl.app.gtnforecasting.logic.CommonLogic.LOGGER;
import com.vaadin.v7.data.Validator;
import com.vaadin.v7.data.validator.AbstractValidator;
import com.vaadin.v7.ui.PopupDateField;
import org.apache.commons.lang.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class DateValidator.
 *
 * @author soundarrajan
 */
public class DateValidator extends AbstractValidator {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /** The start date. */
    private PopupDateField startDate;
    
    /** The end date. */
    private PopupDateField endDate;
    
    /** The equality error message. */
    private String equalityErrorMessage;
    
    /** The greater error message. */
    private String greaterErrorMessage;
    
    /** The log. */
    /**
     * The Default Constructor.
     */
    public DateValidator() {
        super(StringUtils.EMPTY);
    }

    /**
     * The Constructor with error message.
     *
     * @param errorMessage the error message
     * @param startDate the start date
     * @param endDate the end date
     */
    public DateValidator(final String errorMessage, final PopupDateField startDate, final PopupDateField endDate) {
        super(errorMessage);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    /**
     * Start date, end date validator for equality and greater check.
     *
     * @param errorMessage  Default error message
     * @param greaterErrorMessage Error message when end date is greater than start date
     * @param equalityErrorMessage Error message when both start date and end date are equal
     * @param startDate Start date component
     * @param endDate End date component
     */
    public DateValidator(final String errorMessage, final String greaterErrorMessage, final String equalityErrorMessage, final PopupDateField startDate, final PopupDateField endDate) {
        super(errorMessage);
        this.greaterErrorMessage = greaterErrorMessage;
        this.equalityErrorMessage = equalityErrorMessage;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * To validate the startDate.
     *
     * @param value the value
     * @throws InvalidValueException the invalid value exception
     */
    @Override
    public void validate(final Object value)  {
        if (startDate.getValue() != null && endDate.getValue() != null) {
            if (startDate.getValue().after(endDate.getValue())) {
                throw new Validator.InvalidValueException(greaterErrorMessage);
            } else if (startDate.getValue().equals(endDate.getValue())) {
                throw new Validator.InvalidValueException(equalityErrorMessage);
            }
        }

    }

    /**
     * To check the value is valid.
     *
     * @param value the value
     * @return true, if checks if is valid value
     */
    @Override
    protected boolean isValidValue(final Object value) {
        boolean isValid = true;
        try {
            if (startDate.getValue() != null && endDate.getValue() != null) {
                isValid = startDate.getValue().compareTo(endDate.getValue()) <= 0;
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return isValid;
    }

    /**
     * To get the Type.
     *
     * @return the type
     */
    @Override
    public Class getType() {
        return null;
    }
}
