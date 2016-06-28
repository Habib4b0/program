/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.dto;

import com.stpl.app.contract.common.util.CommonUtil;
import com.stpl.app.contract.contractheader.util.CommonUtils;
import com.stpl.app.contract.contractheader.util.UIUtils;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;

/**
 *
 * @author shrihariharan
 */
public class IfpAvailableTableGenerator implements ExtFilterGenerator{
    CommonUtils commonsUtil = new CommonUtils();
    /** The common util. */
    private CommonUtil commonUtil = CommonUtil.getInstance();    
    
    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
        if ("tradingPartnerName".equals(propertyId)) {

            return new SimpleStringFilter(propertyId, String.valueOf(((HelperDTO) value).getId()), false, false);
        }

        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {

        if (originatingField instanceof ComboBox) {

            if (originatingField.getValue() != null) {

                return new SimpleStringFilter(propertyId, String.valueOf(((HelperDTO) originatingField.getValue()).getId()), false, false);
            } else {
                return null;
            }
        }

        return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        try {
             if ("form".equalsIgnoreCase(String.valueOf(propertyId))) {
               ComboBox form = new ComboBox();
                try {
                    commonUtil.loadComboBox(form, "FORM", true);
                
                } catch (SystemException ex) {
                } catch (Exception ex) {
                    Logger.getLogger(IfpAvailableTableGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
                return form;
            }
            if ("globalitemstatus".equalsIgnoreCase(String.valueOf(propertyId))) {
                ComboBox itemStatus = new ComboBox();
                try {
                    commonUtil.loadComboBox(itemStatus, UIUtils.STATUS, true);

                } catch (SystemException ex) {

                } catch (Exception ex) {
                    Logger.getLogger(IfpAvailableTableGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
                return itemStatus;

            }
             
          if ("packageDesc".equalsIgnoreCase(String.valueOf(propertyId))) {
               ComboBox packageSize = new ComboBox();
                try {
                   commonUtil.loadComboBox(packageSize, "PACKAGE_SIZE", true);

                } catch (SystemException ex) {
                    
                } catch (Exception ex) {
                    Logger.getLogger(IfpAvailableTableGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
                return packageSize;
            }
       if ("itemStatus".equalsIgnoreCase(String.valueOf(propertyId))) {
               ComboBox status = new ComboBox();
                try {
                    commonUtil.loadComboBox(status, "STATUS", true);
                
                } catch (SystemException ex) {
                } catch (Exception ex) {
                    Logger.getLogger(IfpAvailableTableGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
                return status;
            }
       if ("strength".equalsIgnoreCase(String.valueOf(propertyId))) {
               ComboBox strength = new ComboBox();
                try {
                    commonUtil.loadComboBox(strength, "STRENGTH", true);
                
                } catch (SystemException ex) {
                    
                } catch (Exception ex) {
                    Logger.getLogger(IfpAvailableTableGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }

                return strength;
            }
       if ("therapeuticClass".equalsIgnoreCase(String.valueOf(propertyId))) {
               ComboBox therapeutic = new ComboBox();
                try {
                    commonUtil.loadComboBox(therapeutic, "THERAPEUTIC_CLASS", true);
                
                } catch (SystemException ex) {
                   
                } catch (Exception ex) {
                    Logger.getLogger(IfpAvailableTableGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
                return therapeutic;
            }
//           if ("brand".equals(propertyId)) {
//                try {
//                    ComboBox brand = new ComboBox();
//                    commonUtil.loadComboBox(brand, "THERAPEUTIC_CLASS", true);
//                    brand.setDebugId("testing");
//                    return brand;
//                } catch (SystemException ex) {
//                    Logger.getLogger(IfpAvailableTableGenerator.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (PortalException ex) {
//                    Logger.getLogger(IfpAvailableTableGenerator.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (Exception ex) {
//                    Logger.getLogger(IfpAvailableTableGenerator.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
                         final ComboBox comboBox = new ComboBox();
                   Map<Integer, String> userMap = StplSecurity.getUserName();
                    if ("createdBy".equals(propertyId)) {
                            comboBox.addItem(0);
                            comboBox.setItemCaption(0, ConstantsUtils.SHOW_ALL);
                            for (Map.Entry<Integer, String> entry : userMap.entrySet()) {
                                comboBox.addItem(entry.getKey());
                                comboBox.setItemCaption(entry.getKey(), entry.getValue());
                            }
                            comboBox.setNullSelectionAllowed(true);
                            comboBox.setNullSelectionItemId(0);
                            return comboBox;
                    }
                    if ("modifiedBy".equals(propertyId)) {
                            comboBox.addItem(0);
                            comboBox.setItemCaption(0, ConstantsUtils.SHOW_ALL);
                            for (Map.Entry<Integer, String> entry : userMap.entrySet()) {
                                comboBox.addItem(entry.getKey());
                                comboBox.setItemCaption(entry.getKey(), entry.getValue());
                            }
                            comboBox.setNullSelectionAllowed(true);
                            comboBox.setNullSelectionItemId(0);
                            return comboBox;
                    }

            
                  
           
            return null;
        } catch (SystemException ex) {
            Logger.getLogger(IfpAvailableTableGenerator.class.getName()).log(Level.SEVERE, null, ex);
              return null;
        }
    }

    @Override
    public void filterRemoved(Object propertyId) {
    }

    @Override
    public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
    }

    @Override
    public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
    return null;
    }
    
}