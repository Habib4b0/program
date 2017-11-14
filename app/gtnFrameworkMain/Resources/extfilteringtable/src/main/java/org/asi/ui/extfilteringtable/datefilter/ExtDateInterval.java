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

import java.io.Serializable;
import java.util.Date;


/**
 * Simple date interval providing isBetween comparison for other Date objects.
 * 
 * @author Abhiram
 * 
 */
@SuppressWarnings("serial")
public class ExtDateInterval implements Serializable {
    
    /** The from. */
    private final Date from;
    
    /** The to. */
    private final Date to;

    /**
     * Instantiates a new ext date interval.
     *
     * @param from the from
     * @param to the to
     */
    public ExtDateInterval(Date from, Date to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the from.
     *
     * @return the from
     */
    public Date getFrom() {
        return from;
    }

    /**
     * Gets the to.
     *
     * @return the to
     */
    public Date getTo() {
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
