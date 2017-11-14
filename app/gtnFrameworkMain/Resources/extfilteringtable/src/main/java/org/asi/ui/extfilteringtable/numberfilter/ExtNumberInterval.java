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
 * The Class ExtNumberInterval.
 *
 * @author Abhiram
 */
@SuppressWarnings("serial")
public class ExtNumberInterval implements Serializable {

    /** The less than value. */
    private final String lessThanValue;
    
    /** The greater than value. */
    private final String greaterThanValue;
    
    /** The equals value. */
    private final String equalsValue;

    /**
     * Instantiates a new ext number interval.
     *
     * @param lessThanValue the less than value
     * @param greaterThanValue the greater than value
     * @param equalsValue the equals value
     */
    public ExtNumberInterval(String lessThanValue, String greaterThanValue,
            String equalsValue) {
        this.lessThanValue = lessThanValue;
        this.greaterThanValue = greaterThanValue;
        this.equalsValue = equalsValue;
    }

    /**
     * Gets the less than value.
     *
     * @return the less than value
     */
    public String getLessThanValue() {
        return lessThanValue;
    }

    /**
     * Gets the greater than value.
     *
     * @return the greater than value
     */
    public String getGreaterThanValue() {
        return greaterThanValue;
    }

    /**
     * Gets the equals value.
     *
     * @return the equals value
     */
    public String getEqualsValue() {
        return equalsValue;
    }
}