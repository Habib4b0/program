/*
 * 08.08.2017
 * Copyright (c) 2017 STPL. All Rights Reserved
 */
package com.stpl.app.arm.dataselection.dto;

import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.ui.ComboBox;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author saranya.sekar
 */
public class PeriodCheckValidator extends AbstractValidator {

    ComboBox fromPeriod;
    ComboBox toPeriod;

    public PeriodCheckValidator() {
        super("");
    }

    /**
     *
     * @param errorMessage
     */
    public PeriodCheckValidator(ComboBox fromPeriod, ComboBox toPeriod, final String errorMessage) {
        super(errorMessage);
        this.fromPeriod = fromPeriod;
        this.toPeriod = toPeriod;

    }

    /**
     * Validates the value.
     *
     * @param value the value
     * @throws InvalidValueException the invalid value exception
     */
    @Override
    public void validate(final Object value) {
        if (isValidValue(value)) {
            throw new Validator.InvalidValueException("To Period Cannot be Before the From Period");
        }

    }

    @Override
    protected boolean isValidValue(Object value) {
        if (hasValue(fromPeriod.getValue()) && hasValue(toPeriod.getValue())) {
            try {
                Date fromDate = ARMUtils.getInstance().getDbDate().parse(String.valueOf(fromPeriod.getValue()));
                Date toDate = ARMUtils.getInstance().getDbDate().parse(String.valueOf(toPeriod.getValue()));
                return toDate.before(fromDate);
            } catch (ParseException ex) {
                Logger.getLogger(PeriodCheckValidator.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return false;
        }

        return true;
    }

    /**
     * Checks for value.
     *
     * @param obj the obj
     * @return true, if checks for value
     */
    private boolean hasValue(final Object obj) {
        return !(obj == null || obj.equals(NumericConstants.ZERO));
    }

    @Override
    public Class getType() {
        return null;
    }

}
