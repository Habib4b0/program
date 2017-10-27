/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.item.dto;

import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.item.logic.ItemSearchLogic;
import com.stpl.app.global.item.ui.lazyload.BrandContainer;
import com.stpl.app.global.item.ui.lazyload.BrandCriteria;
import com.stpl.app.global.item.ui.lazyload.ManufactureIdCriteria;
import com.stpl.app.global.item.ui.lazyload.ManufacturerIdContainer;
import com.stpl.app.global.item.util.CommonUtils;
import com.stpl.app.global.item.util.UIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.HelperDTO;
import com.stpl.ifs.ui.StringExtFilter;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import org.jboss.logging.Logger; 
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.vaadin.addons.lazycontainer.LazyContainer;

/**
 *
 * @author sooriya.lakshmanan
 */
public class IdentifierPricingGenerater implements ExtFilterGenerator {

    /**
     * The item logic.
     */
    private final ItemSearchLogic itemLogic = new ItemSearchLogic();
    final CommonUtils commonsUtil = new CommonUtils();
    private HelperDTO dto = new HelperDTO(ConstantsUtils.SHOW_ALL);
    /** The logger. */
    private static final Logger LOGGER = Logger.getLogger(IdentifierPricingGenerater.class);
    /** The common util. */
    private CommonUtil commonUtil = CommonUtil.getInstance();
    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
        if (ConstantsUtils.ITEM_CLASS.equals(propertyId) || ConstantsUtils.SECONDARY_UOM.equals(propertyId)
                || ConstantsUtils.PRIMARY_UOM.equals(propertyId) || ConstantsUtils.STRENGTH.equals(propertyId)
                || ConstantsUtils.FORM.equals(propertyId) || ConstantsUtils.ITEM_TYPE.equals(propertyId)) {
            if (!value.equals(ConstantsUtils.SHOW_ALL)) {
                return new StringExtFilter(propertyId, CommonUtils.getDescription(Integer.valueOf(String.valueOf(value))), false, false);
           } else {
               return new StringExtFilter(propertyId, "%", false, false); 
            }
        } else if(ConstantsUtils.IDENTIFIER_STATUS.equals(propertyId)){
            if (!ConstantsUtils.SHOW_ALL.equals(String.valueOf(value)) && value!=null ) {
        return new StringExtFilter(propertyId, String.valueOf(value), false, false);
             } else {
             return null;
            }
        }else if (ConstantsUtils.ITEM_STATUS.equals(propertyId) && !String.valueOf(value).equals(ConstantsUtils.SELECT_ALL)) {
            return new StringExtFilter(propertyId, String.valueOf(value), false, false);
        }
       
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        if (ConstantsUtils.ITEM_CLASS.equals(propertyId) || ConstantsUtils.SECONDARY_UOM.equals(propertyId)
                || ConstantsUtils.PRIMARY_UOM.equals(propertyId) || ConstantsUtils.STRENGTH.equals(propertyId)
                || ConstantsUtils.FORM.equals(propertyId) || ConstantsUtils.ITEM_TYPE.equals(propertyId)) {
            if (!String.valueOf(originatingField.getValue()).equals(ConstantsUtils.SELECT_ALL)) {
                return new StringExtFilter(propertyId, CommonUtils.getDescription(Integer.valueOf(String.valueOf(originatingField.getValue()))), false, false);
            } else {
                return new StringExtFilter(propertyId, "%", false, false);
            }
        } else if (ConstantsUtils.IDENTIFIER_STATUS.equals(propertyId)) {
            if (!ConstantsUtils.SHOW_ALL.equals(String.valueOf(originatingField.getValue())) && originatingField.getValue()!=null ) {
                return new StringExtFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
            } else {
                return null;
            }
        } else if (ConstantsUtils.ITEM_STATUS.equals(propertyId) && !String.valueOf(originatingField.getValue()).equals(ConstantsUtils.SELECT_ALL)) {
            return new StringExtFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
        } else if (originatingField instanceof ComboBox && originatingField.getValue() != null) {
                return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
        }

        return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
         try {
            if (ConstantsUtils.IDENTIFIER_STATUS.equals(propertyId)) {
                final ComboBox identifierStatus = new ComboBox();
                 commonUtil.loadComboBox(identifierStatus, UIUtils.STATUS, true);
                return identifierStatus;
            } else if ("pricingCodeStatus".equals(propertyId)) {
                final ComboBox pricingCodeStatus = new ComboBox();
                commonUtil.loadComboBox(pricingCodeStatus, UIUtils.STATUS, true);
                return pricingCodeStatus;
            } else if("itemUom".equals(propertyId)){
            
                final ComboBox itemUom = new ComboBox();
                commonUtil.loadComboBox(itemUom, UIUtils.UOM, true);

                return itemUom;
            } else {
                if (ConstantsUtils.ITEM_STATUS.equals(propertyId)) {
               
                        ComboBox itemStatus = new ComboBox();
                         commonUtil.loadComboBox(itemStatus, UIUtils.STATUS, true);
                        return itemStatus;
                   
                }

                if (ConstantsUtils.ITEM_TYPE.equals(propertyId)) {
             
                        ComboBox itemType = new ComboBox();
                        commonUtil.loadComboBox(itemType, UIUtils.ITEM_TYPE, true);

                        return itemType;
                   
                }
                if ("brand".equals(propertyId)) {
                    ComboBox brandDdlb = new ComboBox();

                    brandDdlb.setPageLength(NumericConstants.SEVEN);
                    brandDdlb.setImmediate(true);
                    brandDdlb.setNullSelectionAllowed(true);
                    brandDdlb.setNullSelectionItemId(ConstantsUtils.SHOW_ALL);
                    brandDdlb.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
                    brandDdlb.markAsDirty();
                    final LazyContainer identifierTypeDescContainer = new LazyContainer(HelperDTO.class, new BrandContainer(true), new BrandCriteria());
                    identifierTypeDescContainer.setMinFilterLength(0);
                    brandDdlb.setContainerDataSource(identifierTypeDescContainer);
                    brandDdlb.select(dto);
                    return brandDdlb;
                }
                if (ConstantsUtils.FORM.equals(propertyId)) {
                    ComboBox form = new ComboBox();
                     commonUtil.loadComboBox(form, UIUtils.FORM1, true);

                    return form;
                }

                if (ConstantsUtils.STRENGTH.equals(propertyId)) {
                    ComboBox strength = new ComboBox();
                    strength.setNullSelectionAllowed(true);
                    strength.setNullSelectionItemId(ConstantsUtils.SHOW_ALL);
                    strength.addItem(ConstantsUtils.SHOW_ALL);
                    new CommonUtils().getNativeSelect(strength, itemLogic.getItemType(UIUtils.STRENGTH1));
                    strength.removeItem(0);
                    strength.select(ConstantsUtils.SHOW_ALL);
                    strength.setImmediate(true);
                    return strength;
                }

                if (ConstantsUtils.PRIMARY_UOM.equals(propertyId)) {
                    ComboBox primaryUom = new ComboBox();
                    primaryUom.setNullSelectionAllowed(true);
                    primaryUom.setNullSelectionItemId(ConstantsUtils.SHOW_ALL);
                    primaryUom.addItem(ConstantsUtils.SHOW_ALL);
                    new CommonUtils().getNativeSelect(primaryUom, itemLogic.getItemType(UIUtils.UOM));
                    primaryUom.removeItem(0);
                    primaryUom.select(ConstantsUtils.SHOW_ALL);
                    primaryUom.setImmediate(true);
                    return primaryUom;
                }

                if (ConstantsUtils.SECONDARY_UOM.equals(propertyId)) {
                    ComboBox secondaryUom = new ComboBox();
                    secondaryUom.setNullSelectionAllowed(true);
                    secondaryUom.setNullSelectionItemId(ConstantsUtils.SHOW_ALL);
                    secondaryUom.addItem(ConstantsUtils.SHOW_ALL);
                    new CommonUtils().getNativeSelect(secondaryUom, itemLogic.getItemType(ConstantsUtils.UOM));
                    secondaryUom.removeItem(0);
                    secondaryUom.select(ConstantsUtils.SHOW_ALL);
                    secondaryUom.setImmediate(true);
                    return secondaryUom;
                }

                if (ConstantsUtils.ITEM_CLASS.equals(propertyId)) {
                    ComboBox itemClass = new ComboBox();
                    itemClass.setNullSelectionAllowed(true);
                    itemClass.setNullSelectionItemId(ConstantsUtils.SHOW_ALL);
                    itemClass.addItem(ConstantsUtils.SHOW_ALL);
                    new CommonUtils().getNativeSelect(itemClass, itemLogic.getItemType(UIUtils.ITEM_CLASS));
                    itemClass.removeItem(0);
                    itemClass.select(ConstantsUtils.SHOW_ALL);
                    itemClass.setImmediate(true);
                    return itemClass;
                }
                if("manufacturerId".equals(propertyId)){
                    ComboBox manufacturerIdDDLB = new ComboBox();

                    manufacturerIdDDLB.setPageLength(NumericConstants.SEVEN);
                    manufacturerIdDDLB.setImmediate(true);
                    manufacturerIdDDLB.setNullSelectionAllowed(true);
                    manufacturerIdDDLB.setNullSelectionItemId(ConstantsUtils.SHOW_ALL);
                    manufacturerIdDDLB.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
                    manufacturerIdDDLB.markAsDirty();
                    final LazyContainer manufactureIdContainer = new LazyContainer(HelperDTO.class, new ManufacturerIdContainer(null,false), new ManufactureIdCriteria());
                    manufactureIdContainer.setMinFilterLength(0);
                    manufacturerIdDDLB.setContainerDataSource(manufactureIdContainer);
                    manufacturerIdDDLB.select(dto);

                    return manufacturerIdDDLB;
                }
            }
        } catch (Exception e) {
           LOGGER.error(e);
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
