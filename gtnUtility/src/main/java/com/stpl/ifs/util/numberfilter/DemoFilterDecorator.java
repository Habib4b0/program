package com.stpl.ifs.util.numberfilter;

import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.datefield.Resolution;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;
import org.asi.ui.extfilteringtable.ExtFilterDecorator;
import org.asi.ui.extfilteringtable.numberfilter.ExtNumberFilterPopupConfig;

@SuppressWarnings("serial")
public class DemoFilterDecorator implements ExtFilterDecorator, Serializable {
  
	enum State {
        CREATED, PROCESSING, PROCESSED, FINISHED;
    }
    @Override
    public String getEnumFilterDisplayName(Object propertyId, Object value) {
        if ("state".equals(propertyId)) {
            State state = (State) value;
            switch (state) {
            case CREATED:
                return "Order has been created";
            case PROCESSING:
                return "Order is being processed";
            case PROCESSED:
                return "Order has been processed";
            case FINISHED:
                return "Order is delivered";
            }
        }
        if ("graphComponent".equals(propertyId)) {
            
            return null;
        }
        return null;
        }
        // returning null will output default value
        
    @Override
    public Resource getEnumFilterIcon(Object propertyId, Object value) {
        if ("state".equals(propertyId)) {
            State state = (State) value;
            switch (state) {
            case CREATED:
                return new ThemeResource("../runo/icons/16/document.png");
            case PROCESSING:
                return new ThemeResource("../runo/icons/16/reload.png");
            case PROCESSED:
                return new ThemeResource("../runo/icons/16/ok.png");
            case FINISHED:
                return new ThemeResource("../runo/icons/16/globe.png");
            }
        }
        return null;
    }

    @Override
    public String getBooleanFilterDisplayName(Object propertyId, boolean value) {
        if ("validated".equals(propertyId)) {
            return value ? "Validated" : "Not validated";
        }
        // returning null will output default value
        return null;
    }

    @Override
    public Resource getBooleanFilterIcon(Object propertyId, boolean value) {
        if ("validated".equals(propertyId)) {
            return value ? new ThemeResource("../runo/icons/16/ok.png")
                    : new ThemeResource("../runo/icons/16/cancel.png");
        }
        return null;
    }

    @Override
    public String getFromCaption() {
        return "Start date:";
    }

    @Override
    public String getToCaption() {
        return "End date:";
    }

    @Override
    public String getSetCaption() {
        // use default caption
        return null;
    }

    @Override
    public String getClearCaption() {
        // use default caption
        return null;
    }

    @Override
    public boolean isTextFilterImmediate(Object propertyId) {
        // use text change events for all the text fields
        return true;
    }

    @Override
    public int getTextChangeTimeout(Object propertyId) {
        // use the same timeout for all the text fields
        return NumericConstants.FIVE_HUNDRED;
    }

    @Override
    public String getAllItemsVisibleString() {
        return "Show all";
    }

    @Override
    public Resolution getDateFieldResolution(Object propertyId) {
        return Resolution.DAY;
    }

    public DateFormat getDateFormat(Object propertyId) {
        return DateFormat.getDateInstance(DateFormat.SHORT, new Locale("fi",
                "FI"));
    }

    @Override
    public boolean usePopupForNumericProperty(Object propertyId) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public String getDateFormatPattern(Object propertyId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Locale getLocale() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ExtNumberFilterPopupConfig getNumberFilterPopupConfig() {
        // TODO Auto-generated method stub
        return null;
    }
}
