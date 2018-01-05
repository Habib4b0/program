package com.stpl.ifs.util.numberfilter;

import com.stpl.ifs.ui.util.NumericConstants;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.hene.popupbutton.PopupButton.PopupVisibilityEvent;

import com.vaadin.v7.data.util.converter.Converter.ConversionException;
import com.vaadin.v7.event.FieldEvents;
import com.vaadin.v7.event.FieldEvents.TextChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.v7.ui.CustomField;
import com.vaadin.ui.GridLayout;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.TextField;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Produces the number filter popup for the table
 * 
 * @author Vimukthi
 * @author Teppo Kurki [adapted for V7]
 */
@SuppressWarnings("serial")
public class NumberFilterPopup extends CustomField<NumberInterval> {

    private PopupButton content;
    private boolean settingValue;
    private String valueMarker;
    private NumberInterval interval;

    /* Input fields */
    private TextField ltInput;
    private TextField gtInput;
    private TextField eqInput;

    /* Default labels */
    private static final String LT = "<";
    private static final String GT = ">";
    private static final String EQ = "=";
    private static final String DEFAULT_LT_PROMPT = "Less than";
    private static final String DEFAULT_GT_PROMPT = "Greater than";
    private static final String DEFAULT_EQ_PROMPT = "Equal to";
    private static final String DEFAULT_OK_CAPTION = "Set";
    private static final String DEFAULT_RESET_CAPTION = "Clear";
    private static final String DEFAULT_VALUE_MARKER = "[x]";
    private static final Logger LOGGER = LoggerFactory.getLogger(NumberFilterPopup.class.getName());

    /* Buttons */
    private Button ok;
    private Button reset;

    public NumberFilterPopup() {
        getContent();
    }

    private void initPopup() {
        final GridLayout content = new GridLayout(2, 4);
        content.setStyleName("numberfilterpopupcontent");
        content.setSpacing(true);
        content.setMargin(true);
        content.setSizeUndefined();

        content.addComponent(new Label(GT), 0, 0);
        content.addComponent(new Label(LT), 0, 1);
        content.addComponent(new Label(EQ), 0, NumericConstants.TWO);

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
        content.addComponent(eqInput, 1, NumericConstants.TWO);

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
                    LOGGER.error(StringUtils.EMPTY,e);
                }
                try {
                    Double.valueOf(gtInput.getValue());
                    gtNow = gtInput.getValue();
                } catch (RuntimeException e) {
                    gtNow = null;
                    LOGGER.error(StringUtils.EMPTY,e);
                }
                try {
                    Double.valueOf(eqInput.getValue());
                    eqNow = eqInput.getValue();
                } catch (RuntimeException e) {
                    LOGGER.error(StringUtils.EMPTY,e);
                    eqNow = null;
                }
                setValue(new NumberInterval(ltNow, gtNow, eqNow));
                NumberFilterPopup.this.content.setPopupVisible(false);
            }
        });

        reset = new Button(DEFAULT_RESET_CAPTION, new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                setValue(null);
                NumberFilterPopup.this.content.setPopupVisible(false);
            }
        });

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setWidth("100%");
        buttons.setSpacing(true);
        buttons.addComponent(ok);
        buttons.addComponent(reset);
        buttons.setExpandRatio(ok, 1);
        buttons.setComponentAlignment(ok, Alignment.MIDDLE_RIGHT);
        content.addComponent(buttons, 0, NumericConstants.THREE, 1, NumericConstants.THREE);

        this.content.setContent(content);
    }

    @Override
    public void setValue(NumberInterval newFieldValue)
            throws com.vaadin.v7.data.Property.ReadOnlyException,
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

    private void updateCaption() {
        if (interval == null) {
            content.setCaption(getAllItemsVisibleString());
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

    private String getAllItemsVisibleString() {
		return "Show All";
	}

	@Override
    public void attach() {
        super.attach();
        setFilterDecorator();
    }
	
	
	public void setFilterDecorator() {

        String eqP = DEFAULT_EQ_PROMPT;
        String ltP = DEFAULT_LT_PROMPT;
        String gtP = DEFAULT_GT_PROMPT;
        valueMarker = DEFAULT_VALUE_MARKER;
        String ok = DEFAULT_OK_CAPTION;
        String reset = DEFAULT_RESET_CAPTION;

        if (getNumberFilterPopupConfig()!=null) {
            NumberFilterPopupConfig conf =getNumberFilterPopupConfig();
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
	
	

    private NumberFilterPopupConfig getNumberFilterPopupConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	
    @Override
    protected Component initContent() {
        if (content == null) {
            content = new PopupButton();
            content.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
            setImmediate(true);
            setStyleName("numberfilterpopup");
            initPopup();
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

    @Override
    public Class<? extends NumberInterval> getType() {
        return NumberInterval.class;
    }

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
