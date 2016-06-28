/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.dto;

import java.io.Serializable;

/**
 * The Class DashBoardSearchDto.
 *
 * @author soundarrajan
 */
public class DashBoardSearchDto implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 4457739662807895721L;
    /**
     * Variable used for Flag.
     */
    private String flag;
    /**
     * Variable used for flagId.
     */
    private String flagId;

    /**
     * Method used for Getting id value.
     *
     * @return the flagId
     */
    public String getFlagId() {
        return flagId;
    }

    /**
     * Method used for Setting id value.
     *
     * @param flagId the flagId
     */
    public void setFlagId(final String flagId) {
        this.flagId = flagId;
    }

    /**
     * Method used for Getting Flag value.
     *
     * @return the flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * Method used for Setting Flag value.
     *
     * @param flag the flag
     */
    public void setFlag(final String flag) {
        this.flag = flag;
    }
}
