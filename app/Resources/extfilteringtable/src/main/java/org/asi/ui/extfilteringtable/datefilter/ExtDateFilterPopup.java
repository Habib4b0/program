/*
 * Copyright 2014 Abhiram.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.asi.ui.extfilteringtable.datefilter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.hene.popupbutton.PopupButton.PopupVisibilityEvent;
import org.vaadin.hene.popupbutton.PopupButton.PopupVisibilityListener;

import com.vaadin.v7.data.util.converter.Converter.ConversionException;
import com.vaadin.v7.shared.ui.datefield.Resolution;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.v7.ui.CustomField;
import com.vaadin.v7.ui.DateField;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.InlineDateField;
import com.vaadin.v7.ui.VerticalLayout;
import org.asi.ui.extfilteringtable.ExtFilterDecorator;


/**
 * Extension of PopupButton used to implement filter UI for Date properties.
 * Users can select either start date, end date or both. The filter can also be
 * set or cleared via a button in the filter pop-up.
 * 
 * @author Abhiram
 * 
 */
@SuppressWarnings("serial")
public class ExtDateFilterPopup extends CustomField<ExtDateInterval> {
    
    /** The content. */
    private PopupButton content;

    /** The to field. */
    private DateField fromField, toField;
    
    /** The to value. */
    private Date fromValue, toValue;
    
    /** The cancel reset. */
    private boolean cancelReset;
    
    /** The decorator. */
    private ExtFilterDecorator decorator;
    
    /** The clear. */
    private Button set, clear;
    
    /** The property id. */
    private final Object propertyId;
    
    /** The date format pattern. */
    private String dateFormatPattern;

    /** The Constant DEFAULT_FROM_CAPTION. */
    private static final String DEFAULT_FROM_CAPTION = "From";
    
    /** The Constant DEFAULT_TO_CAPTION. */
    private static final String DEFAULT_TO_CAPTION = "To";
    
    /** The Constant DEFAULT_SET_CAPTION. */
    private static final String DEFAULT_SET_CAPTION = "Set";
    
    /** The Constant DEFAULT_CLEAR_CAPTION. */
    private static final String DEFAULT_CLEAR_CAPTION = "Clear";
    
    /** The Constant DEFAULT_RESOLUTION. */
    private static final Resolution DEFAULT_RESOLUTION = Resolution.DAY;

    /**
     * Instantiates a new ext date filter popup.
     *
     * @param decorator the decorator
     * @param propertyId the property id
     */
    public ExtDateFilterPopup(ExtFilterDecorator decorator, Object propertyId) {
        this.decorator = decorator;
        this.propertyId = propertyId;
        /* This call is needed for the value setting to function before attach */
        getContent();
    }

    /**
     * Attach.
     */
    @Override
    public void attach() {
        super.attach();
        setFilterDecorator(decorator);
    }

    /**
     * Sets the value.
     *
     * @param newFieldValue the new value
     * @throws ReadOnlyException the read only exception
     * @throws ConversionException the conversion exception
     */
    @Override
    public void setValue(ExtDateInterval newFieldValue)
            throws com.vaadin.v7.data.Property.ReadOnlyException,
            ConversionException {
        if (newFieldValue == null) {
            newFieldValue = new ExtDateInterval(null, null);
        }
        fromField.setValue(newFieldValue.getFrom());
        toField.setValue(newFieldValue.getTo());
        super.setValue(newFieldValue);
        updateCaption(newFieldValue.isNull());
    }

    /**
     * Builds the popup.
     */
    private void buildPopup() {
        VerticalLayout content = new VerticalLayout();
        content.setStyleName("datefilterpopupcontent");
        content.setSpacing(true);
        content.setMargin(true);
        content.setSizeUndefined();

        fromField = new InlineDateField();
        toField = new InlineDateField();
        fromField.setImmediate(true);
        toField.setImmediate(true);

        set = new Button();
        clear = new Button();
        ClickListener buttonClickHandler = new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                updateValue(clear.equals(event.getButton()));
            }
        };
        set.addClickListener(buttonClickHandler);
        clear.addClickListener(buttonClickHandler);

        HorizontalLayout buttonBar = new HorizontalLayout();
        buttonBar.setSizeUndefined();
        buttonBar.setSpacing(true);
        buttonBar.addComponent(set);
        buttonBar.addComponent(clear);

        HorizontalLayout row = new HorizontalLayout();
        row.setSizeUndefined();
        row.setSpacing(true);
        row.addComponent(fromField);
        row.addComponent(toField);

        content.addComponent(row);
        content.addComponent(buttonBar);
        content.setComponentAlignment(buttonBar, Alignment.BOTTOM_RIGHT);

        this.content.setContent(content);
    }

    /**
     * Sets the filter decorator.
     *
     * @param decorator the new filter decorator
     */
    public void setFilterDecorator(ExtFilterDecorator decorator) {
        this.decorator = decorator;

        /* Set DateField Locale */
        fromField.setLocale(getLocaleFailsafe());
        toField.setLocale(getLocaleFailsafe());

        String fromCaption = DEFAULT_FROM_CAPTION;
        String toCaption = DEFAULT_TO_CAPTION;
        String setCaption = DEFAULT_SET_CAPTION;
        String clearCaption = DEFAULT_CLEAR_CAPTION;
        Resolution resolution = DEFAULT_RESOLUTION;
        dateFormatPattern = ((SimpleDateFormat) DateFormat.getDateTimeInstance(
                DateFormat.SHORT, DateFormat.SHORT, getLocaleFailsafe()))
                .toPattern();

        if (decorator != null) {
            if (decorator.getFromCaption() != null) {
                fromCaption = decorator.getFromCaption();
            }
            if (decorator.getToCaption() != null) {
                toCaption = decorator.getToCaption();
            }
            if (decorator.getSetCaption() != null) {
                setCaption = decorator.getSetCaption();
            }
            if (decorator.getClearCaption() != null) {
                clearCaption = decorator.getClearCaption();
            }
            if (decorator.getDateFieldResolution(propertyId) != null) {
                resolution = decorator.getDateFieldResolution(propertyId);
            }
            String dateFormatPattern = decorator
                    .getDateFormatPattern(propertyId);
            if (dateFormatPattern != null) {
                this.dateFormatPattern = dateFormatPattern;
            }
        }
        /* Set captions */
        fromField.setCaption(fromCaption);
        toField.setCaption(toCaption);
        set.setCaption(setCaption);
        clear.setCaption(clearCaption);
        /* Set resolutions and date formats */
        fromField.setResolution(resolution);
        toField.setResolution(resolution);
        fromField.setDateFormat(dateFormatPattern);
        toField.setDateFormat(dateFormatPattern);
    }

    /**
     * Update caption.
     *
     * @param nullTheCaption the null the caption
     */
    private void updateCaption(boolean nullTheCaption) {
        if (nullTheCaption) {
            if (decorator != null
                    && decorator.getAllItemsVisibleString() != null) {
                content.setCaption(decorator.getAllItemsVisibleString());
            } else {
                content.setCaption(null);
            }
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormatPattern);
            content.setCaption((fromField.getValue() == null ? "" : sdf
                    .format(fromField.getValue()))
                    + " - "
                    + (toField.getValue() == null ? "" : sdf.format(toField
                            .getValue())));
        }
    }

    /**
     * Update value.
     *
     * @param nullTheValue the null the value
     */
    private void updateValue(boolean nullTheValue) {
        if (nullTheValue) {
            fromField.setValue(null);
            toField.setValue(null);
        } else {
            cancelReset = true;
        }
        /* Truncate the from and to dates */
        Resolution res = decorator != null ? decorator
                .getDateFieldResolution(propertyId) : DEFAULT_RESOLUTION;
        if (res == null) {
            res = DEFAULT_RESOLUTION;
        }
        fromValue = truncateDate(fromField.getValue(), res, true);
        toValue = truncateDate(toField.getValue(), res, false);
        setValue(new ExtDateInterval(fromValue, toValue));
        ExtDateFilterPopup.this.content.setPopupVisible(false);
    }

    /**
     * Truncate date.
     *
     * @param date the date
     * @param resolution the resolution
     * @param start the start
     * @return the date
     */
    private Date truncateDate(Date date, Resolution resolution, boolean start) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance(getLocaleFailsafe());
        cal.setTime(date);
        cal.set(Calendar.MILLISECOND, start ? 0 : 999);
        for (Resolution res : Resolution.getResolutionsLowerThan(resolution)) {
            if (res == Resolution.SECOND) {
                cal.set(Calendar.SECOND, start ? 0 : 59);
            } else if (res == Resolution.MINUTE) {
                cal.set(Calendar.MINUTE, start ? 0 : 59);
            } else if (res == Resolution.HOUR) {
                cal.set(Calendar.HOUR_OF_DAY, start ? 0 : 23);
            } else if (res == Resolution.DAY) {
                cal.set(Calendar.DAY_OF_MONTH,
                        start ? 1 : cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            } else if (res == Resolution.MONTH) {
                cal.set(Calendar.MONTH,
                        start ? 0 : cal.getActualMaximum(Calendar.MONTH));
            }
        }
        return cal.getTime();
    }

    /**
     * Gets the locale failsafe.
     *
     * @return the locale failsafe
     */
    private Locale getLocaleFailsafe() {
        /* First try the locale provided by the decorator */
        if (decorator != null && decorator.getLocale() != null) {
            return decorator.getLocale();
        }
        /* Then try application locale */
        if (super.getLocale() != null) {
            return super.getLocale();
        }
        /* Finally revert to system default locale */
        return Locale.getDefault();
    }

    /**
     * Inits the content.
     *
     * @return the component
     */
    @Override
    protected Component initContent() {
        if (content == null) {
            content = new PopupButton(null);
            content.setWidth(100, Unit.PERCENTAGE);
            setImmediate(true);
            buildPopup();
            setStyleName("datefilterpopup");
            setFilterDecorator(decorator);
            updateCaption(true);
            content.addPopupVisibilityListener(new PopupVisibilityListener() {
                @Override
                public void popupVisibilityChange(PopupVisibilityEvent event) {
                    if (cancelReset || event.getPopupButton().isPopupVisible()) {
                        fromValue = fromField.getValue();
                        toValue = toField.getValue();
                        cancelReset = false;
                        return;
                    }
                    fromField.setValue(fromValue);
                    toField.setValue(toValue);
                    cancelReset = false;
                }
            });
        }
        return content;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    @Override
    public Class<? extends ExtDateInterval> getType() {
        return ExtDateInterval.class;
    }

    /**
     * Sets the read only.
     *
     * @param readOnly the new read only
     */
    @Override
    public void setReadOnly(boolean readOnly) {
        super.setReadOnly(readOnly);
        set.setEnabled(!readOnly);
        clear.setEnabled(!readOnly);
        fromField.setEnabled(!readOnly);
        toField.setEnabled(!readOnly);
    }
}