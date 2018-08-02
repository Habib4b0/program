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

import java.io.Serializable;
import java.time.LocalDate;


/**
 * Simple date interval providing isBetween comparison for other Date objects.
 * 
 * @author Abhiram
 * 
 */
@SuppressWarnings("serial")
public class DateInterval implements Serializable {
    
    /** The from. */
    private transient final LocalDate from;
    
    /** The to. */
    private transient final LocalDate to;

    /**
     * Instantiates a new ext date interval.
     *
     * @param from the from
     * @param to the to
     */
    public DateInterval(LocalDate from, LocalDate to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the from.
     *
     * @return the from
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     * Gets the to.
     *
     * @return the to
     */
    public LocalDate getTo() {
        return to;
    }

    /**
     * Checks if is null.
     *
     * @return true, if is null
     */
    public boolean isNull() {
        return from == null && to == null;
    }
}
