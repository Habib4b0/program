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
package com.stpl.gtn.gtn2o.ui.framework.component.grid.component;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.ui.datefield.DateResolution;
import java.util.Locale;


import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.hene.popupbutton.PopupButton.PopupVisibilityEvent;
import org.vaadin.hene.popupbutton.PopupButton.PopupVisibilityListener;


import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.VerticalLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Extension of PopupButton used to implement filter UI for Date properties.
 * Users can select either start date, end date or both. The filter can also be
 * set or cleared via a button in the filter pop-up.
 * 
 * @author Abhiram
 * 
 */
@SuppressWarnings("serial")
public class DateFilterPopup extends CustomField<DateInterval> {
    
    /** The content. */
    private PopupButton content;

    /** The From field. */
    private InlineDateField fromField;
    
     /** The to field. */
    private InlineDateField toField;
    
    
    /** The From value. */
    private LocalDate fromValue;
    
     /** The to value. */
    private LocalDate toValue;
    
    /** The cancel reset. */
    private boolean cancelReset;
    
    /** The Set. */
    private Button set;
    
    /** The clear. */
    private Button clear;
    
    
    /** The date format pattern. */
    private String dateFormatPattern="MM/dd/yy";
    
    
    private String showAll="Show all";

    /** The Constant DEFAULT_FROM_CAPTION. */
    private static final String DEFAULT_FROM_CAPTION = "From";
    
    /** The Constant DEFAULT_TO_CAPTION. */
    private static final String DEFAULT_TO_CAPTION = "To";
    
    /** The Constant DEFAULT_SET_CAPTION. */
    private static final String DEFAULT_SET_CAPTION = "Set";
    
    /** The Constant DEFAULT_CLEAR_CAPTION. */
    private static final String DEFAULT_CLEAR_CAPTION = "Clear";
    
    /** The Constant DEFAULT_RESOLUTION. */
    private static final DateResolution DEFAULT_RESOLUTION = DateResolution.DAY;
    
    /**
     * Instantiates a new ext date filter popup.
     *
     * @param propertyId the property id
     */
    public DateFilterPopup() {
        /* This call is needed for the value setting to function before attach */
        getContent();
    }

    /**
     * Attach.
     */
    @Override
    public void attach() {
        super.attach();
        setFilterDecorator();
    }

    /**
     * Builds the popup.
     */
    private void buildPopup() {
        VerticalLayout contentLayout = new VerticalLayout();
        contentLayout.setStyleName("datefilterpopupcontent");
        contentLayout.setSpacing(true);
        contentLayout.setMargin(true);
        contentLayout.setSizeUndefined();
        fromField = new InlineDateField();
        toField = new InlineDateField();


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

        contentLayout.addComponent(row);
        contentLayout.addComponent(buttonBar);
        contentLayout.setComponentAlignment(buttonBar, Alignment.BOTTOM_RIGHT);

        this.content.setContent(contentLayout);
    }

    /**
     * Sets the filter decorator.
     *
     * @param decorator the new filter decorator
     */
    public void setFilterDecorator() {


        /* Set DateField Locale */
        fromField.setLocale(getLocaleFailsafe());
        toField.setLocale(getLocaleFailsafe());

        String fromCaption = DEFAULT_FROM_CAPTION;
        String toCaption = DEFAULT_TO_CAPTION;
        String setCaption = DEFAULT_SET_CAPTION;
        String clearCaption = DEFAULT_CLEAR_CAPTION;
 
        /* Set captions */
        fromField.setCaption(fromCaption);
        toField.setCaption(toCaption);
        set.setCaption(setCaption);
        clear.setCaption(clearCaption);
        /* Set resolutions and date formats */
        fromField.setResolution(DEFAULT_RESOLUTION);
        toField.setResolution(DEFAULT_RESOLUTION);
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
          
                content.setCaption(showAll);
            
        } else {
            content.setCaption((fromField.getValue() == null ? "" : DateTimeFormatter.ofPattern(dateFormatPattern).format(fromField.getValue()))
                    + " - "
                    + (toField.getValue() == null ? "" : DateTimeFormatter.ofPattern(dateFormatPattern).format(toField.getValue())));
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
        fromValue = fromField.getValue();
        toValue = toField.getValue();
        setValue(new DateInterval(fromValue, toValue));
        DateFilterPopup.this.content.setPopupVisible(false);
    }

   
    /**
     * Gets the locale failsafe.
     *
     * @return the locale failsafe
     */
    private Locale getLocaleFailsafe() {
 
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
            content.setCaption(showAll);
            buildPopup();
            setStyleName("datefilterpopup");
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

    @Override
    protected void doSetValue(DateInterval newFieldValue) {
        DateInterval tempFieldValue = newFieldValue;
         if (tempFieldValue == null) {
            tempFieldValue = new DateInterval(null, null);
        }
        fromField.setValue(tempFieldValue.getFrom());
        toField.setValue(tempFieldValue.getTo());
        updateCaption(tempFieldValue.isNull());
    }

    @Override
    public DateInterval getValue() {
        fromValue = fromField.getValue();
        toValue = toField.getValue();
      return new DateInterval(fromValue, toValue);
    }
}