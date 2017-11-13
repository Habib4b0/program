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

import java.io.Serializable;


/**
 * Provides way to set decorative configurations for the
 * {@link ExtNumberFilterPopup}.
 *
 * @author Abhiram
 */
@SuppressWarnings("serial")
public class ExtNumberFilterPopupConfig implements Serializable {
    
    /** The lt prompt. */
    private String ltPrompt;
    
    /** The gt prompt. */
    private String gtPrompt;
    
    /** The eq prompt. */
    private String eqPrompt;
    
    /** The ok caption. */
    private String okCaption;
    
    /** The reset caption. */
    private String resetCaption;
    
    /** The value marker. */
    private String valueMarker;

    /**
     * Gets the lt prompt.
     *
     * @return the lt prompt
     */
    public String getLtPrompt() {
        return ltPrompt;
    }

    /**
     * Sets the lt prompt.
     *
     * @param ltPrompt the new lt prompt
     */
    public void setLtPrompt(String ltPrompt) {
        this.ltPrompt = ltPrompt;
    }

    /**
     * Gets the gt prompt.
     *
     * @return the gt prompt
     */
    public String getGtPrompt() {
        return gtPrompt;
    }

    /**
     * Sets the gt prompt.
     *
     * @param gtPrompt the new gt prompt
     */
    public void setGtPrompt(String gtPrompt) {
        this.gtPrompt = gtPrompt;
    }

    /**
     * Gets the eq prompt.
     *
     * @return the eq prompt
     */
    public String getEqPrompt() {
        return eqPrompt;
    }

    /**
     * Sets the eq prompt.
     *
     * @param eqPrompt the new eq prompt
     */
    public void setEqPrompt(String eqPrompt) {
        this.eqPrompt = eqPrompt;
    }

    /**
     * Gets the ok caption.
     *
     * @return the ok caption
     */
    public String getOkCaption() {
        return okCaption;
    }

    /**
     * Sets the ok caption.
     *
     * @param okCaption the new ok caption
     */
    public void setOkCaption(String okCaption) {
        this.okCaption = okCaption;
    }

    /**
     * Gets the reset caption.
     *
     * @return the reset caption
     */
    public String getResetCaption() {
        return resetCaption;
    }

    /**
     * Sets the reset caption.
     *
     * @param resetCaption the new reset caption
     */
    public void setResetCaption(String resetCaption) {
        this.resetCaption = resetCaption;
    }

    /**
     * Gets the value marker.
     *
     * @return the value marker
     */
    public String getValueMarker() {
        return valueMarker;
    }

    /**
     * Sets the value marker.
     *
     * @param valueMarker the new value marker
     */
    public void setValueMarker(String valueMarker) {
        this.valueMarker = valueMarker;
    }
}
