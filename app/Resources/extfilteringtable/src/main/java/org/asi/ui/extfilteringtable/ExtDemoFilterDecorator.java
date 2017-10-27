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
package org.asi.ui.extfilteringtable;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;

import org.asi.ui.extfilteringtable.numberfilter.ExtNumberFilterPopupConfig;

import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.datefield.Resolution;


/**
 * The Class ExtDemoFilterDecorator.
 *
 * @author Abhiram
 */
public class ExtDemoFilterDecorator implements ExtFilterDecorator, Serializable {
  
	/**
	 * The Enum State.
	 */
	enum State {
        
        /** The created. */
        CREATED, 
 /** The processing. */
 PROCESSING, 
 /** The processed. */
 PROCESSED, 
 /** The finished. */
 FINISHED;
    }
    
    /**
     * Gets the enum filter display name.
     *
     * @param propertyId the property id
     * @param value the value
     * @return the enum filter display name
     */
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
        
    /**
         * Gets the enum filter icon.
         *
         * @param propertyId the property id
         * @param value the value
         * @return the enum filter icon
         */
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

    /**
     * Gets the boolean filter display name.
     *
     * @param propertyId the property id
     * @param value the value
     * @return the boolean filter display name
     */
    @Override
    public String getBooleanFilterDisplayName(Object propertyId, boolean value) {
        if ("validated".equals(propertyId)) {
            return value ? "Validated" : "Not validated";
        }
        // returning null will output default value
        return null;
    }

    /**
     * Gets the boolean filter icon.
     *
     * @param propertyId the property id
     * @param value the value
     * @return the boolean filter icon
     */
    @Override
    public Resource getBooleanFilterIcon(Object propertyId, boolean value) {
        if ("validated".equals(propertyId)) {
            return value ? new ThemeResource("../runo/icons/16/ok.png")
                    : new ThemeResource("../runo/icons/16/cancel.png");
        }
        return null;
    }

    /**
     * Gets the from caption.
     *
     * @return the from caption
     */
    @Override
    public String getFromCaption() {
        return "Start date:";
    }

    /**
     * Gets the to caption.
     *
     * @return the to caption
     */
    @Override
    public String getToCaption() {
        return "End date:";
    }

    /**
     * Gets the sets the caption.
     *
     * @return the sets the caption
     */
    @Override
    public String getSetCaption() {
        // use default caption
        return null;
    }

    /**
     * Gets the clear caption.
     *
     * @return the clear caption
     */
    @Override
    public String getClearCaption() {
        // use default caption
        return null;
    }

    /**
     * Checks if is text filter immediate.
     *
     * @param propertyId the property id
     * @return true, if is text filter immediate
     */
    @Override
    public boolean isTextFilterImmediate(Object propertyId) {
        // use text change events for all the text fields
        return true;
    }

    /**
     * Gets the text change timeout.
     *
     * @param propertyId the property id
     * @return the text change timeout
     */
    @Override
    public int getTextChangeTimeout(Object propertyId) {
        // use the same timeout for all the text fields
        return 500;
    }

    /**
     * Gets the all items visible string.
     *
     * @return the all items visible string
     */
    @Override
    public String getAllItemsVisibleString() {
        return "Show all";
    }

    /**
     * Gets the date field resolution.
     *
     * @param propertyId the property id
     * @return the date field resolution
     */
    @Override
    public Resolution getDateFieldResolution(Object propertyId) {
        return Resolution.DAY;
    }

    /**
     * Gets the date format.
     *
     * @param propertyId the property id
     * @return the date format
     */
    public DateFormat getDateFormat(Object propertyId) {
        return DateFormat.getDateInstance(DateFormat.SHORT, new Locale("fi",
                "FI"));
    }

    /**
     * Use popup for numeric property.
     *
     * @param propertyId the property id
     * @return true, if successful
     */
    @Override
    public boolean usePopupForNumericProperty(Object propertyId) {
        // TODO Auto-generated method stub
        return true;
    }

    /**
     * Gets the date format pattern.
     *
     * @param propertyId the property id
     * @return the date format pattern
     */
    @Override
    public String getDateFormatPattern(Object propertyId) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Gets the locale.
     *
     * @return the locale
     */
    @Override
    public Locale getLocale() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Gets the number filter popup config.
     *
     * @return the number filter popup config
     */
    @Override
    public ExtNumberFilterPopupConfig getNumberFilterPopupConfig() {
        // TODO Auto-generated method stub
        return null;
    }
}