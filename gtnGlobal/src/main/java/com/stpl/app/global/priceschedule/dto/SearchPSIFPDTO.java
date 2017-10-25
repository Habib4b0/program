package com.stpl.app.global.priceschedule.dto;

import java.io.Serializable;

/**
 * The Class SearchPsIfpDTO.
 */
public class SearchPSIFPDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 5066647076901111421L;
    /**
     * The item familyplan name.
     */
    private String itemFamilyplanName;
    /**
     * The item familyplan no.
     */
    private String itemFamilyplanNo;

    /**
     * Gets the item familyplan name.
     *
     * @return the itemFamilyplanName
     */
    public String getItemFamilyplanName() {
        return itemFamilyplanName;
    }

    /**
     * Sets the item familyplan name.
     *
     * @param itemFamilyplanName the item familyplan name
     */
    public void setItemFamilyplanName(final String itemFamilyplanName) {
        this.itemFamilyplanName = itemFamilyplanName;
    }

    /**
     * Gets the item familyplan no.
     *
     * @return the item familyplan no
     */
    public String getItemFamilyplanNo() {
        return itemFamilyplanNo;
    }

    /**
     * Sets the item familyplan no.
     *
     * @param itemFamilyplanNo the item familyplan no
     */
    public void setItemFamilyplanNo(final String itemFamilyplanNo) {
        this.itemFamilyplanNo = itemFamilyplanNo;
    }
}
