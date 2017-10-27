/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.ui;

import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.global.ifp.util.UIUtils;
import static com.stpl.app.global.item.util.CommonUtils.commonUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import java.util.Map;
import org.jboss.logging.Logger; 
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import java.util.logging.Level;


/**
 *
 * @author pvinoth
 */
public class IFPFilterGenerator implements ExtFilterGenerator {
    
    IFPLogic ifpLogic= new IFPLogic();
    
    private static final Logger LOGGER = Logger.getLogger(ExtFilterGenerator.class);
    private CommonUtil commonUtil = CommonUtil.getInstance();
    
    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
      // For other properties, use the default filter
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
      if (originatingField instanceof ComboBox) {
          if(originatingField.getValue()!=null)
          {       
                 if (propertyId.toString().equals("createdBy") || propertyId.toString().equals("modifiedBy") || propertyId.toString().equals("ifpcreatedBy")) {
                if (originatingField.getValue() != null) {
                    return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
                }
                 else {
                    return null;
                }
            }
              if("brand".equals(propertyId.toString())){
                  return new SimpleStringFilter(propertyId,String.valueOf(originatingField.getValue()),false,false);
              }else{
                return new SimpleStringFilter(propertyId,String.valueOf(((com.stpl.ifs.util.HelperDTO) originatingField.getValue()).getId()),false,false);
              }
          }
          else
          {
             return null;  
          }
        } 
    return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
    
        Map<Integer, String> userMap = null;
        try {
            userMap = StplSecurity.getUserName();
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(IFPFilterGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        ComboBox comboBox1;
        if ("ifpStatus".equals(propertyId) || "displayItemStatus".equals(propertyId)
                || "itemStatus".equals(propertyId) || ConstantsUtils.IFP_STATUS.equals(propertyId)) {
            try {
                ComboBox itemFamilyplanStatus = new ComboBox();

                commonUtil.loadComboBox(itemFamilyplanStatus, UIUtils.STATUS, true);
                itemFamilyplanStatus.setImmediate(true);
                return itemFamilyplanStatus;
            } catch (Exception ex) {
                 LOGGER.error(ex);
            }
        }
        if ("ifpType".equals(propertyId)) {

            try {
                ComboBox itemFamilyplanType = new ComboBox();

                commonUtil.loadComboBox(itemFamilyplanType, UIUtils.IFP_TYPE, true);
                itemFamilyplanType.setImmediate(true);
                return itemFamilyplanType;
            } catch (Exception ex) {
                 LOGGER.error(ex);
            }
        }
        
        if ("ifpCategory".equals(propertyId)) {

            try {
                ComboBox itemFamilyplanCategory = new ComboBox();
                itemFamilyplanCategory.setImmediate(true);

                commonUtil.loadComboBox(itemFamilyplanCategory, UIUtils.IFP_CATEGORY, true);
                itemFamilyplanCategory.setImmediate(true);
                return itemFamilyplanCategory;
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        
        if ("ifpDesignation".equals(propertyId)) {

            try {
                ComboBox itemFamilyplanDesignation = new ComboBox();

                commonUtil.loadComboBox(itemFamilyplanDesignation, UIUtils.IFP_DESIGNATION, true);
                itemFamilyplanDesignation.setImmediate(true);
                return itemFamilyplanDesignation;
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        
        
        
        if ("displayForm".equals(propertyId) || "form".equals(propertyId)) {

            try {
                ComboBox itemFamilyplanDesignation = new ComboBox();

                commonUtil.loadComboBox(itemFamilyplanDesignation, UIUtils.FORM, true);
                itemFamilyplanDesignation.setImmediate(true);
                return itemFamilyplanDesignation;
            } catch (Exception ex) {
                 LOGGER.error(ex);
            }
        }
        
        if ("strength".equals(propertyId)) {

            try {
                ComboBox itemFamilyplanDesignation = new ComboBox();

                commonUtil.loadComboBox(itemFamilyplanDesignation, UIUtils.STRENGTH, true);
                itemFamilyplanDesignation.setImmediate(true);
                return itemFamilyplanDesignation;
            } catch (Exception ex) {
                 LOGGER.error(ex);
            }
        }
        
        if ("therapeuticClass".equals(propertyId) || "therepeuticClass".equals(propertyId)) {
            try {
                ComboBox itemFamilyplanDesignation = new ComboBox();

                commonUtil.loadComboBox(itemFamilyplanDesignation, "THERAPEUTIC_CLASS", true);
                itemFamilyplanDesignation.setImmediate(true);
                return itemFamilyplanDesignation;
            } catch (Exception ex) {
                 LOGGER.error(ex);
            }
        }
        
        if ("brand".equals(propertyId)) {
            try {
                ComboBox brand = new ComboBox();
                commonUtils.getBrandList(brand, ifpLogic.getBrandDropDown());
                brand.setDebugId("testing");
                brand.setImmediate(true);
                return brand;
            } catch (SystemException ex) {
                 LOGGER.error(ex);
            }
        }
        if ("ifpcreatedBy".equals(propertyId)) {       
                 comboBox1 = new ComboBox();
                    comboBox1.addItem(0);
                    comboBox1.setItemCaption(0, ConstantsUtils.SHOW_ALL);
                    for (Map.Entry<Integer, String> entry : userMap.entrySet()) {
                        comboBox1.addItem(entry.getKey());
                        comboBox1.setItemCaption(entry.getKey(), entry.getValue());
                    }
                    comboBox1.setNullSelectionAllowed(true);
                    comboBox1.setNullSelectionItemId(0);
                    return comboBox1;
         
            }
        if ("createdBy".equals(propertyId)) {       
                 comboBox1 = new ComboBox();
                    comboBox1.addItem(0);
                    comboBox1.setItemCaption(0, ConstantsUtils.SHOW_ALL);
                    for (Map.Entry<Integer, String> entry : userMap.entrySet()) {
                        comboBox1.addItem(entry.getKey());
                        comboBox1.setItemCaption(entry.getKey(), entry.getValue());
                    }
                    comboBox1.setNullSelectionAllowed(true);
                    comboBox1.setNullSelectionItemId(0);
                    return comboBox1;
         
            } else if ("modifiedBy".equals(propertyId)) {
                comboBox1 = new ComboBox();
                    comboBox1.addItem(0);
                    comboBox1.setItemCaption(0, ConstantsUtils.SHOW_ALL);
                    for (Map.Entry<Integer, String> entry : userMap.entrySet()) {
                        comboBox1.addItem(entry.getKey());
                        comboBox1.setItemCaption(entry.getKey(), entry.getValue());
                    }
                    comboBox1.setNullSelectionAllowed(true);
                    comboBox1.setNullSelectionItemId(0);
                    return comboBox1;
                    }
        
        return null;
    }

    @Override
    public void filterRemoved(Object propertyId) {
//       empty
    }

    @Override
    public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
//        empty
    }

    @Override
    public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
   return null;
    }

}