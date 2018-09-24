/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import com.vaadin.ui.Window;

/**
 *
 * @author Maheshj
 */
public class TotalLivesChart extends Window {

    public static final String QUARTER = "quarterly";
    /**
     * The dto.
     */
    /**
     * The unit dto.
     */
    /**
     * The frequency.
     */
    /**
     * The history.
     */
    private String history;

    /**
     * The Constructor.
     *
     * @param salesDto the sales dto
     * @param unitDto the unit dto
     * @param frequency the frequency
     */
    public TotalLivesChart() {
        super();
    }


    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }
    
}
