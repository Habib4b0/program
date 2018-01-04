/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.deductioncalendar.dto;

import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.ui.lazyload.BrandContainer;
import com.stpl.app.ui.lazyload.BrandCriteria;
import com.stpl.app.ui.lazyload.ItemQualifierNameContainer;
import com.stpl.app.ui.lazyload.ItemQualifierNameCriteria;
import com.stpl.app.ui.lazyload.ManufactureIdCriteria;
import com.stpl.app.ui.lazyload.ManufacturerIdContainer;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.numberfilter.NumberFilterPopup;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.And;
import com.vaadin.v7.data.util.filter.Compare;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.Field;
import org.slf4j.Logger; 
import org.apache.commons.lang.StringUtils;
import org.asi.ui.addons.lazycontainer.LazyContainer;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sooriya.lakshmanan
 */
public class ItemMasterGenerate implements ExtFilterGenerator {

    final com.stpl.app.global.abstractsearch.util.CommonUtils abstractCommonsUtil = new com.stpl.app.global.abstractsearch.util.CommonUtils();
    private HelperDTO dto = new HelperDTO(ConstantsUtils.SHOW_ALL);
    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemMasterGenerate.class);
    /**
     * The common util.
     */
    private CommonUtil commonUtil = CommonUtil.getInstance();

    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
  
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {

            if (originatingField instanceof ComboBox) {
                if(propertyId.equals("brand")){
                return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
            } else if (originatingField.getValue() != null) {
                return new SimpleStringFilter(propertyId, String.valueOf(((HelperDTO)originatingField.getValue()).getId()), false, false);
            } 
        }
            if (originatingField instanceof NumberFilterPopup) {
                NumberFilterPopup popup=(NumberFilterPopup)originatingField;
                if (originatingField.getValue() != null) {
               if(StringUtils.isNotBlank(popup.getValue().getEqualsValue())){
                 return new Compare.Equal(propertyId,popup.getValue().getEqualsValue());
             }else if(StringUtils.isNotBlank(popup.getValue().getLessThanValue()) && StringUtils.isNotBlank(popup.getValue().getGreaterThanValue())){
                 return new And(new Compare.Less(propertyId,popup.getValue().getLessThanValue()),new Compare.Greater(propertyId,popup.getValue().getGreaterThanValue()));
             }else if(StringUtils.isNotBlank(popup.getValue().getLessThanValue())){
                 return new Compare.Less(propertyId,popup.getValue().getLessThanValue());
             }else if(StringUtils.isNotBlank(popup.getValue().getGreaterThanValue())){
                 return new Compare.Greater(propertyId,popup.getValue().getGreaterThanValue());
             }
                }
            }
        return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        try {
            if ("identifierStatus".equals(propertyId)) {
                final ComboBox identifierStatus = new ComboBox();
                 commonUtil.loadComboBox(identifierStatus, CommonUIUtils.STATUS, true);
                return identifierStatus;
            } else if ("pricingCodeStatus".equals(propertyId)) {
                final ComboBox pricingCodeStatus = new ComboBox();
                  commonUtil.loadComboBox(pricingCodeStatus, CommonUIUtils.STATUS, true);
                return pricingCodeStatus;
            } else {
                if (ConstantsUtils.ITEM_STATUS.equals(propertyId)) {
                          ComboBox itemStatus = new ComboBox();
                          
                          commonUtil.loadComboBox(itemStatus, CommonUIUtils.STATUS, true);

                        return itemStatus;
                }

                if (ConstantsUtils.ITEM_TYPE.equals(propertyId)) {
                    
                        ComboBox itemType = new ComboBox();
                          commonUtil.loadComboBox(itemType, CommonUIUtils.ITEM_TYPE, true);
                        return itemType;
                   
                }
                if ("brand".equals(propertyId)) {
                    ComboBox brandDdlb = new ComboBox();

                    brandDdlb.setPageLength(NumericConstants.SEVEN);
                    brandDdlb.setImmediate(true);
                    brandDdlb.setNullSelectionAllowed(true);
                    brandDdlb.setNullSelectionItemId(new HelperDTO(0, ConstantsUtils.SHOW_ALL));
                    brandDdlb.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
                    brandDdlb.markAsDirty();
                    final LazyContainer identifierTypeDescContainer = new LazyContainer(HelperDTO.class, new BrandContainer(true), new BrandCriteria());
                    identifierTypeDescContainer.setMinFilterLength(0);
                    brandDdlb.setContainerDataSource(identifierTypeDescContainer);
                    brandDdlb.select(new HelperDTO(0, ConstantsUtils.SHOW_ALL));
                    return brandDdlb;
                }
                if (ConstantsUtils.FORM.equals(propertyId)) {
                    ComboBox form = new ComboBox();
                      commonUtil.loadComboBox(form, CommonUIUtils.FORM1, true);
                    return form;
                }

                if (ConstantsUtils.STRENGTH.equals(propertyId)) {
                    ComboBox strength = new ComboBox();
                     commonUtil.loadComboBox(strength, CommonUIUtils.STRENGTH1, true);
                    return strength;
                }

                if (ConstantsUtils.PRIMARY_UOM.equals(propertyId)) {
                    ComboBox primaryUom = new ComboBox();
                        commonUtil.loadComboBox(primaryUom, CommonUIUtils.UOM, true);
                    return primaryUom;
                }

                if (ConstantsUtils.SECONDARY_UOM.equals(propertyId)) {
                    ComboBox secondaryUom = new ComboBox();
                          commonUtil.loadComboBox(secondaryUom, ConstantsUtils.UOM, true);
                    return secondaryUom;
                }

                if (ConstantsUtils.ITEM_CLASS.equals(propertyId)) {
                    ComboBox itemClass = new ComboBox();
                      commonUtil.loadComboBox(itemClass, CommonUIUtils.ITEM_CLASS, true);
                    return itemClass;
                }
                if("manufacturerId".equals(propertyId)){
                    ComboBox manufacturerIdDDLB = new ComboBox();

                    manufacturerIdDDLB.setImmediate(true);
                    manufacturerIdDDLB.setNullSelectionAllowed(true);
                    manufacturerIdDDLB.setNullSelectionItemId(ConstantsUtils.SHOW_ALL);
                    manufacturerIdDDLB.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
                    manufacturerIdDDLB.markAsDirty();
                    final LazyContainer manufactureIdContainer = new LazyContainer(HelperDTO.class, new ManufacturerIdContainer(null,true), new ManufactureIdCriteria());
                    manufactureIdContainer.setMinFilterLength(0);
                    manufacturerIdDDLB.setContainerDataSource(manufactureIdContainer);
                    manufacturerIdDDLB.select(dto);

                    return manufacturerIdDDLB;
                }
                if (ConstantsUtils.THERAPEUTIC_CLASS.equals(propertyId)) {
                    ComboBox therapeuticClass = new ComboBox();
                     commonUtil.loadComboBox(therapeuticClass, CommonUIUtils.THERAPEUTIC_CLASS, true);
                    therapeuticClass.setImmediate(true);

                    return therapeuticClass;
                }
                
                if ("itemSystemID".equals(propertyId)) {
                   NumberFilterPopup popup = new NumberFilterPopup();
                   return popup;
                }
                
                if ("itemIrtQualifierName".equals(propertyId)) {
                    ComboBox combo5 = new ComboBox();
                    final LazyContainer container = new LazyContainer(HelperDTO.class, new ItemQualifierNameContainer(false), new ItemQualifierNameCriteria());
                    abstractCommonsUtil.loadLazyComboBox(combo5, container);
                    return combo5;
                }
            }
        } catch (Exception e) {
            LOGGER.error("",e);
        }
        return null;
    }

    @Override
    public void filterRemoved(Object propertyId) {
        return;
    }

    @Override
    public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
        return;
    }

    @Override
    public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
        return null;
    }

}
