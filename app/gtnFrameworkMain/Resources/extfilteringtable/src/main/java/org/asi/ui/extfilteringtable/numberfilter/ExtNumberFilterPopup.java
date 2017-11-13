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
package org.asi.ui.extfilteringtable.numberfilter;


import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.hene.popupbutton.PopupButton.PopupVisibilityEvent;

import com.vaadin.data.util.converter.Converter.ConversionException;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import org.asi.ui.extfilteringtable.ExtFilterDecorator;


/**
 * Produces the number filter popup for the table.
 *
 * @author Abhiram
 */
@SuppressWarnings("serial")
public class ExtNumberFilterPopup extends CustomField<ExtNumberInterval> {

    /** The content. */
    private PopupButton content;
    
    /** The decorator. */
    private ExtFilterDecorator decorator;
    
    /** The setting value. */
    private boolean settingValue;
    
    /** The value marker. */
    private String valueMarker;
    
    /** The interval. */
    private ExtNumberInterval interval;

    /* Input fields */
    /** The lt input. */
    private TextField ltInput;
    
    /** The gt input. */
    private TextField gtInput;
    
    /** The eq input. */
    private TextField eqInput;

    /* Default labels */
    /** The Constant LT. */
    private static final String LT = "<";
    
    /** The Constant GT. */
    private static final String GT = ">";
    
    /** The Constant EQ. */
    private static final String EQ = "=";
    
    /** The Constant DEFAULT_LT_PROMPT. */
    private static final String DEFAULT_LT_PROMPT = "Less than";
    
    /** The Constant DEFAULT_GT_PROMPT. */
    private static final String DEFAULT_GT_PROMPT = "Greater than";
    
    /** The Constant DEFAULT_EQ_PROMPT. */
    private static final String DEFAULT_EQ_PROMPT = "Equal to";
    
    /** The Constant DEFAULT_OK_CAPTION. */
    private static final String DEFAULT_OK_CAPTION = "Set";
    
    /** The Constant DEFAULT_RESET_CAPTION. */
    private static final String DEFAULT_RESET_CAPTION = "Clear";
    
    /** The Constant DEFAULT_VALUE_MARKER. */
    private static final String DEFAULT_VALUE_MARKER = "[x]";

    /* Buttons */
    /** The ok. */
    private Button ok;
    
    /** The reset. */
    private Button reset;

    /**
     * Instantiates a new ext number filter popup.
     *
     * @param decorator the decorator
     */
    public ExtNumberFilterPopup(ExtFilterDecorator decorator) {
        this.decorator = decorator;
        /* This call is needed for the value setting to function before attach */
        getContent();
    }

    /**
     * Inits the popup.
     */
    private void initPopup() {
        final GridLayout content = new GridLayout(2, 4);
        content.setStyleName("numberfilterpopupcontent");
        content.setSpacing(true);
        content.setMargin(true);
        content.setSizeUndefined();

        content.addComponent(new Label(GT), 0, 0);
        content.addComponent(new Label(LT), 0, 1);
        content.addComponent(new Label(EQ), 0, 2);

        // greater than input field
        gtInput = new TextField();
        gtInput.setNullRepresentation("");
        content.addComponent(gtInput, 1, 0);

        // less than input field
        ltInput = new TextField();
        ltInput.setNullRepresentation("");
        content.addComponent(ltInput, 1, 1);

        // equals input field
        eqInput = new TextField();
        eqInput.setNullRepresentation("");
        content.addComponent(eqInput, 1, 2);

        // disable gt and lt fields when this activates
        eqInput.addTextChangeListener(new FieldEvents.TextChangeListener() {

            @Override
            public void textChange(TextChangeEvent event) {
                if (event.getText().equals("")) {
                    gtInput.setEnabled(true);
                    ltInput.setEnabled(true);
                } else {
                    gtInput.setEnabled(false);
                    ltInput.setEnabled(false);
                }
            }
        });

        ok = new Button(DEFAULT_OK_CAPTION, new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                // users inputs
                String ltNow;
                String gtNow;
                String eqNow;
                try {
                    Double.valueOf(ltInput.getValue());
                    ltNow = ltInput.getValue();
                } catch (RuntimeException e) {
                    ltNow = null;
                }
                try {
                    Double.valueOf(gtInput.getValue());
                    gtNow = gtInput.getValue();
                } catch (RuntimeException e) {
                    gtNow = null;
                }
                try {
                    Double.valueOf(eqInput.getValue());
                    eqNow = eqInput.getValue();
                } catch (RuntimeException e) {
                    eqNow = null;
                }
                setValue(new ExtNumberInterval(ltNow, gtNow, eqNow));
                ExtNumberFilterPopup.this.content.setPopupVisible(false);
            }
        });

        reset = new Button(DEFAULT_RESET_CAPTION, new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                setValue(null);
                ExtNumberFilterPopup.this.content.setPopupVisible(false);
            }
        });

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setWidth("100%");
        buttons.setSpacing(true);
        buttons.addComponent(ok);
        buttons.addComponent(reset);
        buttons.setExpandRatio(ok, 1);
        buttons.setComponentAlignment(ok, Alignment.MIDDLE_RIGHT);
        content.addComponent(buttons, 0, 3, 1, 3);

        this.content.setContent(content);
    }

    /**
     * Sets the value.
     *
     * @param newFieldValue the new value
     * @throws ReadOnlyException the read only exception
     * @throws ConversionException the conversion exception
     */
    @Override
    public void setValue(ExtNumberInterval newFieldValue)
            throws com.vaadin.data.Property.ReadOnlyException,
            ConversionException {
        settingValue = true;
        boolean nullValue = false;
        if (newFieldValue == null
                || (newFieldValue.getEqualsValue() == null
                        && newFieldValue.getGreaterThanValue() == null && newFieldValue
                        .getLessThanValue() == null)) {
            nullValue = true;
            newFieldValue = null;
            gtInput.setEnabled(true);
            ltInput.setEnabled(true);
        }
        interval = newFieldValue;
        ltInput.setValue(nullValue ? null : newFieldValue.getLessThanValue());
        gtInput.setValue(nullValue ? null : newFieldValue.getGreaterThanValue());
        eqInput.setValue(nullValue ? null : newFieldValue.getEqualsValue());
        super.setValue(newFieldValue);
        updateCaption();
        settingValue = false;
    }

    /**
     * Update caption.
     */
    private void updateCaption() {
        if (interval == null) {
            content.setCaption(decorator != null
                    && decorator.getAllItemsVisibleString() != null ? decorator
                    .getAllItemsVisibleString() : "");
        } else {
            if (interval.getEqualsValue() != null) {
                content.setCaption(valueMarker + " = "
                        + interval.getEqualsValue());
            } else if (interval.getGreaterThanValue() != null
                    && interval.getLessThanValue() != null) {
                content.setCaption(interval.getGreaterThanValue() + " < "
                        + valueMarker + " < " + interval.getLessThanValue());
            } else if (interval.getGreaterThanValue() != null) {
                content.setCaption(valueMarker + " > "
                        + interval.getGreaterThanValue());
            } else if (interval.getLessThanValue() != null) {
                content.setCaption(valueMarker + " < "
                        + interval.getLessThanValue());
            }
        }
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
     * Sets the filter decorator.
     *
     * @param decorator the new filter decorator
     */
    public void setFilterDecorator(ExtFilterDecorator decorator) {
        this.decorator = decorator;

        String eqP = DEFAULT_EQ_PROMPT;
        String ltP = DEFAULT_LT_PROMPT;
        String gtP = DEFAULT_GT_PROMPT;
        valueMarker = DEFAULT_VALUE_MARKER;
        String ok = DEFAULT_OK_CAPTION;
        String reset = DEFAULT_RESET_CAPTION;

        if (decorator != null && decorator.getNumberFilterPopupConfig() != null) {
            ExtNumberFilterPopupConfig conf = decorator
                    .getNumberFilterPopupConfig();
            if (conf.getEqPrompt() != null) {
                eqP = conf.getEqPrompt();
            }
            if (conf.getLtPrompt() != null) {
                ltP = conf.getLtPrompt();
            }
            if (conf.getGtPrompt() != null) {
                gtP = conf.getGtPrompt();
            }
            if (conf.getValueMarker() != null) {
                valueMarker = conf.getValueMarker();
            }
            if (conf.getOkCaption() != null) {
                ok = conf.getOkCaption();
            }
            if (conf.getResetCaption() != null) {
                reset = conf.getResetCaption();
            }
        }

        gtInput.setInputPrompt(gtP);
        ltInput.setInputPrompt(ltP);
        eqInput.setInputPrompt(eqP);
        this.ok.setCaption(ok);
        this.reset.setCaption(reset);
    }

    /**
     * Inits the content.
     *
     * @return the component
     */
    @Override
    protected Component initContent() {
        if (content == null) {
            content = new PopupButton();
            content.setWidth(100, Unit.PERCENTAGE);
            setImmediate(true);
            setStyleName("numberfilterpopup");
            initPopup();
            setFilterDecorator(decorator);
            content.addPopupVisibilityListener(new PopupButton.PopupVisibilityListener() {

                @Override
                public void popupVisibilityChange(PopupVisibilityEvent event) {
                    if (settingValue) {
                        settingValue = false;
                    } else if (interval == null) {
                        ltInput.setValue("");
                        gtInput.setValue("");
                        eqInput.setValue("");
                    } else {
                        ltInput.setValue(interval.getLessThanValue());
                        gtInput.setValue(interval.getGreaterThanValue());
                        eqInput.setValue(interval.getEqualsValue());
                    }
                }
            });
            updateCaption();
        }
        return content;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    @Override
    public Class<? extends ExtNumberInterval> getType() {
        return ExtNumberInterval.class;
    }

    /**
     * Sets the read only.
     *
     * @param readOnly the new read only
     */
    @Override
    public void setReadOnly(boolean readOnly) {
        super.setReadOnly(readOnly);
        ok.setEnabled(!readOnly);
        reset.setEnabled(!readOnly);
        ltInput.setEnabled(!readOnly);
        gtInput.setEnabled(!readOnly);
        eqInput.setEnabled(!readOnly);
    }
}
