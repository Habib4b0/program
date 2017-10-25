/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.fileSelection.dto;

import com.stpl.app.cff.util.ConstantsUtil;
import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Nandhakumar
 */
public class ItemSearchDTO implements Serializable{
    
    private static final long serialVersionUID = 4612301756295249630L;
    
    private String systemId = StringUtils.EMPTY;
    private String itemName = StringUtils.EMPTY;
    private String itemType;
    private String therapyClass;
    private String ndc9=StringUtils.EMPTY;
    private String identifierType;
    private String itemNo=StringUtils.EMPTY;
    private String itemDesc =StringUtils.EMPTY;
    private String itemStatus;
    private String brand;
    private String ndc8=StringUtils.EMPTY;
    private String identifier=StringUtils.EMPTY;
    private HelperDTO helperItemType = new HelperDTO(0, ConstantsUtil.SELECT_ONE);
    private HelperDTO helperTherapyClass = new HelperDTO(0, ConstantsUtil.SELECT_ONE);
    private HelperDTO helperStatus = new HelperDTO(0, ConstantsUtil.SELECT_ONE);
    
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getTherapyClass() {
        return therapyClass;
    }

    public void setTherapyClass(String therapyClass) {
        this.therapyClass = therapyClass;
    }

    public String getNdc9() {
        return ndc9;
    }

    public void setNdc9(String ndc9) {
        this.ndc9 = ndc9;
    }

    public String getIdentifierType() {
        return identifierType;
    }

    public void setIdentifierType(String identifierType) {
        this.identifierType = identifierType;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getNdc8() {
        return ndc8;
    }

    public void setNdc8(String ndc8) {
        this.ndc8 = ndc8;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public HelperDTO getHelperItemType() {
        return helperItemType;
    }

    public void setHelperItemType(HelperDTO helperItemType) {
        this.helperItemType = helperItemType;
    }

    public HelperDTO getHelperStatus() {
        return helperStatus;
    }

    public void setHelperStatus(HelperDTO helperStatus) {
        this.helperStatus = helperStatus;
    }

    public HelperDTO getHelperTherapyClass() {
        return helperTherapyClass;
    }

    public void setHelperTherapyClass(HelperDTO helperTherapyClass) {
        this.helperTherapyClass = helperTherapyClass;
    }
    
}
