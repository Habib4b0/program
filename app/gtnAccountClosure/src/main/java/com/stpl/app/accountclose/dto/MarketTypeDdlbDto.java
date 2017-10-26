/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.dto;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author alok.v
 */
public class MarketTypeDdlbDto {

    private String marketType = StringUtils.EMPTY;

    public MarketTypeDdlbDto() {
    }

    public MarketTypeDdlbDto(String marketType) {
        this.marketType = marketType;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }
}
