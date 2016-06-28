/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.dto;
import com.stpl.app.contract.common.util.CommonUtil;
import static com.stpl.app.contract.dashboard.util.ContractUtils.END_DATE;
import static com.stpl.app.contract.dashboard.util.ContractUtils.START_DATE;
import com.stpl.app.contract.global.logic.IfpLogic;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
/**
 *
 * @author karthikraja.k
 */

public class IfpFilterGenerator implements ExtFilterGenerator {
    
    IfpLogic ifpLogic= new IfpLogic();
           
    public static final Object[] IFP_ITEM_DETAILS_COL = new Object[]{"checkbox","recordType" ,Constants.ITEM_NO, Constants.ITEM_NAME, "itemDesc", "ifpStatus",START_DATE, END_DATE, "globalitemstatus",
        "form", "strength", "therapyClass", "brand" ,Constants.ATTACHED_DATE, "modifiedDate","modifiedBy","createdDate","createdBy"};
    private CommonUtil commonUtil = CommonUtil.getInstance();
    
    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
      if (originatingField instanceof ComboBox) {
          if(originatingField.getValue()!=null)
          {
              if("brand".equals(propertyId.toString())||propertyId.toString().equals("createdBy") || propertyId.toString().equals("modifiedBy")){
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
        try {
            if ("ifpStatus".equals(propertyId) || "itemsStatus".equals(propertyId)
                    || "itemStatus".equals(propertyId) || ConstantsUtils.IFP_STATUS.equals(propertyId)) {
                try {
                    ComboBox itemFamilyplanStatus = new ComboBox();
                    commonUtil.loadComboBox(itemFamilyplanStatus, "STATUS", true);
                    itemFamilyplanStatus.setImmediate(true);
                    return itemFamilyplanStatus;
                } catch (Exception ex) {
                    Logger.getLogger(IfpFilterGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if ("ifpType".equals(propertyId)) {

                try {
                    ComboBox itemFamilyplanType = new ComboBox();
                    commonUtil.loadComboBox(itemFamilyplanType,"IFP_TYPE", true);
                    itemFamilyplanType.setImmediate(true);
                    return itemFamilyplanType;
                } catch (Exception ex) {
                    Logger.getLogger(IfpFilterGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if ("ifpCategory".equals(propertyId)) {

                try {
                    ComboBox itemFamilyplanCategory = new ComboBox();
                    itemFamilyplanCategory.setImmediate(true);
                    commonUtil.loadComboBox(itemFamilyplanCategory,"IFP_CATEGORY", true);
                    itemFamilyplanCategory.setImmediate(true);
                    return itemFamilyplanCategory;
                } catch (Exception ex) {
                    Logger.getLogger(IfpFilterGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if ("ifpDesignation".equals(propertyId)) {

                try {
                    ComboBox itemFamilyplanDesignation = new ComboBox();
                    commonUtil.loadComboBox(itemFamilyplanDesignation, "IFP_DESIGNATION", true);
                    itemFamilyplanDesignation.setImmediate(true);
                    return itemFamilyplanDesignation;
                } catch (Exception ex) {
                    Logger.getLogger(IfpFilterGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
            
            if ("displayForm".equals(propertyId) || "form".equals(propertyId)) {

                try {
                    ComboBox itemFamilyplanDesignation = new ComboBox();
                    commonUtil.loadComboBox(itemFamilyplanDesignation,"FORM", true);
                    itemFamilyplanDesignation.setImmediate(true);
                    return itemFamilyplanDesignation;
                } catch (Exception ex) {
                    Logger.getLogger(IfpFilterGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if ("strength".equals(propertyId)) {

                try {
                    ComboBox itemFamilyplanDesignation = new ComboBox();
                    commonUtil.loadComboBox(itemFamilyplanDesignation,"STRENGTH", true);
                    itemFamilyplanDesignation.setImmediate(true);
                    return itemFamilyplanDesignation;
                } catch (Exception ex) {
                    Logger.getLogger(IfpFilterGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if ("therapeuticClass".equals(propertyId) || "therapyClass".equals(propertyId)) {
                try {
                    ComboBox itemFamilyplanDesignation = new ComboBox();
                    commonUtil.loadComboBox(itemFamilyplanDesignation, "THERAPEUTIC_CLASS", true);
                    itemFamilyplanDesignation.setImmediate(true);
                    return itemFamilyplanDesignation;
                } catch (Exception ex) {
                    Logger.getLogger(IfpFilterGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if ("brand".equals(propertyId)) {
                try {  
                    final ComboBox brand = new ComboBox();
                    brand.setPageLength(7);
                    brand.setNullSelectionAllowed(true);
                    brand.setNullSelectionItemId(0);
                    brand.addItem(0);
                    brand.setItemCaption(0, "Show all");
                    List<HelperDTO> helperList=ifpLogic.getBrandDropDown();
                    for (int i = 0; i <helperList.size() ; i++) {
                        final HelperDTO helperDTO = helperList.get(i);
                        brand.addItem(helperDTO.getId());
                        brand.setItemCaption(helperDTO.getId(), helperDTO.getDescription());
                    }
                    brand.select(0);
                    brand.addValueChangeListener(new Property.ValueChangeListener() {
                        public void valueChange(Property.ValueChangeEvent event) {
                            if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                                brand.select(0);
                            }
                        }
                    });
                    brand.setImmediate(true);
                    return brand;
                } catch (Exception ex) {
                    Logger.getLogger(IfpFilterGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
            Logger.getLogger(IfpFilterGenerator.class.getName()).log(Level.SEVERE, null, ex);
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