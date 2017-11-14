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
package org.asi.ui.extfilteringtable.paged;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * The Class ExtPagedFilterControlConfig.
 *
 * @author Abhiram
 */
public class ExtPagedFilterControlConfig {

    /** The items per page. */
    private String itemsPerPage = "Items per page:";
    
    /** The page. */
    private String page = "Page:";

    /** The first. */
    private String first = "<<";
    
    /** The last. */
    private String last = ">>";

    /** The previous. */
    private String previous = "<";
    
    /** The next. */
    private String next = ">";

    /** The page lengths. */
    private List<Integer> pageLengths;

    /**
     * Gets the items per page.
     *
     * @return the items per page
     */
    public String getItemsPerPage() {
        return itemsPerPage;
    }

    /**
     * Sets the items per page.
     *
     * @param itemsPerPage the new items per page
     */
    public void setItemsPerPage(String itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    /**
     * Gets the page.
     *
     * @return the page
     */
    public String getPage() {
        return page;
    }

    /**
     * Sets the page.
     *
     * @param page the new page
     */
    public void setPage(String page) {
        this.page = page;
    }

    /**
     * Gets the first.
     *
     * @return the first
     */
    public String getFirst() {
        return first;
    }

    /**
     * Sets the first.
     *
     * @param first the new first
     */
    public void setFirst(String first) {
        this.first = first;
    }

    /**
     * Gets the last.
     *
     * @return the last
     */
    public String getLast() {
        return last;
    }

    /**
     * Sets the last.
     *
     * @param last the new last
     */
    public void setLast(String last) {
        this.last = last;
    }

    /**
     * Gets the previous.
     *
     * @return the previous
     */
    public String getPrevious() {
        return previous;
    }

    /**
     * Sets the previous.
     *
     * @param previous the new previous
     */
    public void setPrevious(String previous) {
        this.previous = previous;
    }

    /**
     * Gets the next.
     *
     * @return the next
     */
    public String getNext() {
        return next;
    }

    /**
     * Sets the next.
     *
     * @param next the new next
     */
    public void setNext(String next) {
        this.next = next;
    }

    /**
     * Gets the page lengths.
     *
     * @return the page lengths
     */
    public List<Integer> getPageLengths() {
        return pageLengths;
    }

    /**
     * Adds the page length.
     *
     * @param pageLength the page length
     */
    public void addPageLength(int pageLength) {
        if (!pageLengths.contains(pageLength)) {
            pageLengths.add(pageLength);
            Collections.sort(pageLengths);
        }
    }

    /**
     * Sets the page lengths and captions.
     *
     * @param pageLengths the new page lengths and captions
     */
    public void setPageLengthsAndCaptions(List<Integer> pageLengths) {
        this.pageLengths = pageLengths;
        Collections.sort(this.pageLengths);
    }

    /**
     * Instantiates a new ext paged filter control config.
     */
    public ExtPagedFilterControlConfig() {
        pageLengths = new ArrayList<Integer>();
        pageLengths.add(5);
        pageLengths.add(10);
        pageLengths.add(15);
        pageLengths.add(20);
        pageLengths.add(25);
        pageLengths.add(50);
        pageLengths.add(100);
    }
}
