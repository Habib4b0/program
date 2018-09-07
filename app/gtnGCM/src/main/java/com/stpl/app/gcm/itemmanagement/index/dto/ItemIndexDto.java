/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.index.dto;

import com.stpl.ifs.util.HelperDTO;
import org.apache.commons.lang.StringUtils;
import com.stpl.app.gcm.util.Constants;

/**
 *
 * @author Srithar
 */
public class ItemIndexDto {
    private Boolean checkRecord = Boolean.FALSE;
    private String systemId;
    private String company = StringUtils.EMPTY;
    private String itemId = StringUtils.EMPTY;
    private String itemNo = StringUtils.EMPTY;
    private String itemName = StringUtils.EMPTY;
    private HelperDTO therapeuticClassDTO;
    private HelperDTO formDTO;
    private HelperDTO identifierTypeDTO;
    private HelperDTO brandDTO;
    private HelperDTO strengthDTO;
    private HelperDTO companyDTO;
    private HelperDTO placeHolderDTO;
    private HelperDTO itemCategoryDTO;
    private HelperDTO itemTypeDTO;
    private String identifier = StringUtils.EMPTY;
    private Integer count = 0;
    private Integer startIndex = 0;
    private Integer endIndex = 0;
    private boolean isCount = false;
    private String itemDesc = StringUtils.EMPTY;
    private String therapeuticClass=Constants.SELECT_ONE ;
    private String brand = StringUtils.EMPTY;
    private String form = StringUtils.EMPTY;
    private String strength = StringUtils.EMPTY;
    private String placeHolder = StringUtils.EMPTY;
    private String ndc9 = StringUtils.EMPTY;
    private String itemCategory = Constants.SELECT_ONE;
    private String itemType = Constants.SELECT_ONE;
    private String itemNameTo = StringUtils.EMPTY;
    private String itemNoTo = StringUtils.EMPTY;
    private String itemIdTo = StringUtils.EMPTY;
    private String placeHolderValue = StringUtils.EMPTY;
    private String fromSid = StringUtils.EMPTY;

    public ItemIndexDto() {
        super();
    }

    public String getPlaceHolderValue() {
        return placeHolderValue;
    }

    public void setPlaceHolderValue(String placeHolderValue) {
        this.placeHolderValue = placeHolderValue;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public HelperDTO getTherapeuticClass_DTO() {
        return therapeuticClassDTO;
    }

    public void setTherapeuticClass_DTO(HelperDTO therapeuticClassDto) {
        this.therapeuticClassDTO = therapeuticClassDto;
    }

    public HelperDTO getForm_DTO() {
        return formDTO;
    }

    public void setForm_DTO(HelperDTO formDto) {
        this.formDTO = formDto;
    }

    public HelperDTO getIdentifierType_DTO() {
        return identifierTypeDTO;
    }

    public void setIdentifierType_DTO(HelperDTO identifierTypeDto) {
        this.identifierTypeDTO = identifierTypeDto;
    }

    public HelperDTO getBrand_DTO() {
        return brandDTO;
    }

    public void setBrand_DTO(HelperDTO brandDto) {
        this.brandDTO = brandDto;
    }

    public HelperDTO getStrength_DTO() {
        return strengthDTO;
    }

    public void setStrength_DTO(HelperDTO strengthDto) {
        this.strengthDTO = strengthDto;
    }

    public HelperDTO getCompany_DTO() {
        return companyDTO;
    }

    public void setCompany_DTO(HelperDTO companyDto) {
        this.companyDTO = companyDto;
    }

    public HelperDTO getPlaceHolder_DTO() {
        return placeHolderDTO;
    }

    public void setPlaceHolder_DTO(HelperDTO placeHolderDto) {
        this.placeHolderDTO = placeHolderDto;
    }

    public HelperDTO getItemCategory_DTO() {
        return itemCategoryDTO;
    }

    public void setItemCategory_DTO(HelperDTO itemCategoryDto) {
        this.itemCategoryDTO = itemCategoryDto;
    }

    public HelperDTO getItemType_DTO() {
        return itemTypeDTO;
    }

    public void setItemType_DTO(HelperDTO itemTypeDto) {
        this.itemTypeDTO = itemTypeDto;
    }

    public Boolean getCheckRecord() {
        return checkRecord;
    }

    public void setCheckRecord(Boolean checkRecord) {
        this.checkRecord = checkRecord;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getTherapeuticClass() {
        return therapeuticClass;
    }

    public void setTherapeuticClass(String therapeuticClass) {
        this.therapeuticClass = therapeuticClass;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
    }

    public String getNdc9() {
        return ndc9;
    }

    public void setNdc9(String ndc9) {
        this.ndc9 = ndc9;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    public boolean isIsCount() {
        return isCount;
    }

    public void setIsCount(boolean isCount) {
        this.isCount = isCount;
    }

    public String getItemNameTo() {
        return itemNameTo;
    }

    public void setItemNameTo(String itemNameTo) {
        this.itemNameTo = itemNameTo;
    }

    public String getItemNoTo() {
        return itemNoTo;
    }

    public void setItemNoTo(String itemNoTo) {
        this.itemNoTo = itemNoTo;
    }

    public String getItemIdTo() {
        return itemIdTo;
    }

    public void setItemIdTo(String itemIdTo) {
        this.itemIdTo = itemIdTo;
    }

    public String getFromSid() {
        return fromSid;
    }

    public void setFromSid(String fromSid) {
        this.fromSid = fromSid;
    }

}
