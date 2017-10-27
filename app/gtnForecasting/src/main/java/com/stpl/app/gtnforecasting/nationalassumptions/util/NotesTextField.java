package com.stpl.app.gtnforecasting.nationalassumptions.util;

import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.Property;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

/**
 *
 * @author manikanta
 */
public class NotesTextField extends CustomField {

    private static final long serialVersionUID = 1L;
    TextField textField = new TextField();
    TextArea txtArea = new TextArea();
    Property txtAreaProperty;
    Property txtProperty;
    PopupView popup = new PopupView("<img src='/html/icons/notes.png'>",txtArea);
    HorizontalLayout layout = new HorizontalLayout();
    
     public static String alphaNumericChars = "([0-9|a-z|A-Z|\\ |\\*])*";
    public static String notAlphaNumericChars = "([^0-9|a-z|A-Z|\\ |\\*])*";
  
    

    /**
     * Creates the UI Custom Component
     *
     * @return {@link Component} representing the UI of the CustomField
     */
    @Override
    protected Component initContent() {
        layout.addComponent(popup);
        layout.addComponent(textField);
        configureFields();
        return layout;
    }

    /**
     * Returns the type of the Field
     *
     * @return the type of the Field
     */
    @Override
    public Class<? extends String> getType() {
        return String.class;
    }
    
    /**
     * Configure the fields for Customizing
     */
    private void configureFields() {
        layout.addStyleName("borderLayout");
        textField.addStyleName("noBorder");
        textField.setWidth("90px");
        textField.setImmediate(true);
        popup.setHideOnMouseOut(false);
        txtArea.setRows(NumericConstants.THREE);
        txtArea.setColumns(NumericConstants.FIFTEEN);
        txtArea.addStyleName("notesArea");
        txtArea.focus();
        txtArea.setMaxLength(NumericConstants.TWO_HUNDRED);
        txtArea.addValidator(new StringLengthValidator("  Adjustment Tool tip length should be less than 500 characters", 0, NumericConstants.TWO_ZERO_ONE, true));
        textField.addValidator(new StringLengthValidator("  Adjustment field length should be less than 200 characters", 0, NumericConstants.TWO_HUNDRED, true));
        txtArea.addTextChangeListener(new FieldEvents.TextChangeListener() {

            @Override
            public void textChange(FieldEvents.TextChangeEvent event) {
                return;
            }
        });
        txtArea.addBlurListener(new FieldEvents.BlurListener() {
            @Override
            public void blur(FieldEvents.BlurEvent event) {
                popup.setPopupVisible(false);
                }
        });
    }

    /**
     * Sets the specified Property as the data source for the field
     *
     * @param newDataSource
     */
    @Override
    public void setPropertyDataSource(Property newDataSource) {
        super.setPropertyDataSource(newDataSource);
        textField.setPropertyDataSource(txtProperty);
        txtArea.setPropertyDataSource(txtAreaProperty);
    }

    /**
     * Sets the new property to the text Notes Area for the field
     *
     * @param property
     */
    public void setNotesProperty(Property property) {
        txtAreaProperty = property;
    }
     public void seTextProperty(Property property) {
        txtProperty = property;
    }

   
    public void setTextfieldValue(String value) {
        textField.setValue(value);
    }

   
    public String getTextfieldValue() {
        return textField.getValue();
    }
    
     public void setNotesValue(String value) {
        txtArea.setValue(value);
    }

     public String getNotesValue() {
        return txtArea.getValue();
    }
     
    @Override
    public Property getPropertyDataSource() {
        return super.getPropertyDataSource();
    }

    @Override
    public void addStyleName(String style) {
        textField.addStyleName(style);
    }

    @Override
    public void setWidth(float height, Unit unit) {
        if (textField != null) {
            textField.setWidth(height, unit);
        }
    }
    
    public void addTextFieldFocusListener(FocusListener listener) {
        textField.addFocusListener(listener);
    }

    public void addTextFieldBlurListener(BlurListener listener) {
        textField.addBlurListener(listener);
    }

    public void setTextData(Object data) {
        textField.setData(data);
    }

    public void addTextAreaFocusListener(FocusListener listener) {
        txtArea.addFocusListener(listener);
    }

    public void addTextAreaBlurListener(BlurListener listener) {
        txtArea.addBlurListener(listener);
    }

    public void addToolTip(String description) {
        popup.setDescription(description);
         txtArea.setDescription(description);
    }

    public void setTextAreaData(Object data) {
        txtArea.setData(data);
    }
    public void setEnable(boolean flag) {
        txtArea.setEnabled(flag);
        textField.setEnabled(flag);
    }    
    }
