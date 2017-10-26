/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.cfp.util;

import com.stpl.app.global.cfp.logic.CFPSearchLogic;
import com.stpl.app.global.company.logic.CompanySearchLogic;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.HelperDTO;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import java.util.Map;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.jboss.logging.Logger;

/**
 *
 * @author sibi
 */
public class CFPTestGenerator implements ExtFilterGenerator {

    private CFPSearchLogic cfpLogic = new CFPSearchLogic();
    
    private static Logger LOGGER = Logger.getLogger(CFPTestGenerator.class);
    
    ErrorfulFieldGroup searchResultsBinder;
    
    public CFPTestGenerator(){
        
    }
    
    public CFPTestGenerator(ErrorfulFieldGroup searchResultsBinder){
        this.searchResultsBinder=searchResultsBinder;
    }
    
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
               if (propertyId.toString().equals("createdBy") || propertyId.toString().equals("modifiedBy") || propertyId.toString().equals("cfpcreatedBy") || propertyId.toString().equals("cfpmodifiedBy")) {
                if (originatingField.getValue() != null) {
                    return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
                }
                 else {
                    return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()).replace(GlobalConstants.getPercent(), GlobalConstants.getPercentForEscape()), false, false);
                }
            }
              
            if(ConstantsUtils.PARENT_CFP_ID.equals(propertyId)){
                
              ComboBox box = (ComboBox)originatingField;
              HelperDTO dto = (HelperDTO)box.getValue();
              return new SimpleStringFilter(propertyId,String.valueOf(dto.getId()),false,false);
                
            } else{ 
              
            return new Compare.Equal(propertyId, originatingField.getValue());
            }
          }else{
             return null;  
          }
        } 
    return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        CompanySearchLogic logic = new CompanySearchLogic();
        CommonUtils commonUtils = new CommonUtils();
        try {
            Map<Integer, String> userMap = StplSecurity.userMap;
            ComboBox comboBox;
            if ("companyFamilyPlanStatus".equals(propertyId)) {
                boolean isFiltered = false;
                HelperDTO helperDTO=null;
                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                if (searchResultsBinder != null && searchResultsBinder.getField(ConstantsUtils.COMBO2).getValue() != null && !ConstantsUtils.SELECT_ONE.equals(searchResultsBinder.getField(ConstantsUtils.COMBO2).getValue())) {
                        helperDTO = (HelperDTO) searchResultsBinder.getField(ConstantsUtils.COMBO2).getValue();
                        if (helperDTO != null && helperDTO.getId() != 0 ) {
                            isFiltered = true;
                        }
                }
                
                if(isFiltered){
                commonUtils.getSelectedNativeSelect(comboBox, helperDTO);
                }else{
                commonUtils.getNativeSelectForFilter(comboBox, cfpLogic.getDropDownList(UIUtils.CFP_STATUS));
                }
                
                comboBox.select(0);
                comboBox.setNullSelectionAllowed(true);
                comboBox.setNullSelectionItemId(ConstantsUtils.Show_All);
                comboBox.select(ConstantsUtils.ZERO_INT);
                return comboBox;

            }else if ("companyFamilyPlanDesignation".equals(propertyId)) {

                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                new CommonUtils().getNativeSelectForFilter(comboBox, cfpLogic.getDropDownList(UIUtils.CFP_DESIGNATION));
                comboBox.select(0);
                comboBox.setNullSelectionAllowed(true);
                comboBox.setNullSelectionItemId(ConstantsUtils.Show_All);
                comboBox.select(ConstantsUtils.ZERO_INT);
                return comboBox;

            }else if ("companyFamilyPlanType".equals(propertyId)) {

                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                
                boolean isFiltered = false;
                HelperDTO helperDTO=null;
                  if (searchResultsBinder != null && searchResultsBinder.getField(ConstantsUtils.COMBO1).getValue() != null && !ConstantsUtils.SELECT_ONE.equals(searchResultsBinder.getField(ConstantsUtils.COMBO1).getValue())) {
                        helperDTO = (HelperDTO) searchResultsBinder.getField(ConstantsUtils.COMBO1).getValue();
                        if (helperDTO != null && helperDTO.getId() != 0 ) {
                            isFiltered = true;
                    }
                }
                
                if(isFiltered){
                commonUtils.getSelectedNativeSelect(comboBox, helperDTO);
                }else{
                commonUtils.getNativeSelectForFilter(comboBox, cfpLogic.getDropDownList(UIUtils.CFP_TYPE));
                }
                
                comboBox.select(0);
                comboBox.setNullSelectionAllowed(true);
                comboBox.setNullSelectionItemId(ConstantsUtils.Show_All);
                comboBox.select(ConstantsUtils.ZERO_INT);
                return comboBox;

            } else if ("companyFamilyPlanTradeClass".equals(propertyId)) {

                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                new CommonUtils().getNativeSelectForFilter(comboBox, cfpLogic.getDropDownList(UIUtils.TRADE_CLASS));
                comboBox.select(0);
                comboBox.setNullSelectionAllowed(true);
                comboBox.setNullSelectionItemId(ConstantsUtils.Show_All);
                comboBox.select(ConstantsUtils.ZERO_INT);
                return comboBox;

            } else if ("companyCategoryValue".equals(propertyId)) {

                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                new CommonUtils().getNativeSelectForFilter(comboBox, cfpLogic.getDropDownList(UIUtils.COMPANY_CATEGORY_LIST_NAME));
                comboBox.select(0);
                comboBox.setNullSelectionAllowed(true);
                comboBox.setNullSelectionItemId(ConstantsUtils.Show_All);
                comboBox.select(ConstantsUtils.ZERO_INT);
                return comboBox;

            } else if ("tradeClassValue".equals(propertyId)) {

                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                new CommonUtils().getNativeSelectForFilter(comboBox, cfpLogic.getDropDownList(UIUtils.COMPANY_TRADE_CLASS));
                comboBox.select(0);
                comboBox.setNullSelectionAllowed(true);
                comboBox.setNullSelectionItemId(ConstantsUtils.Show_All);
                comboBox.select(ConstantsUtils.ZERO_INT);
                return comboBox;

            }if("companyGroupValue".equals(propertyId)) {
            ComboBox group = new ComboBox();
            group.addItem(ConstantsUtils.SHOW_ALL);
            try {
                new com.stpl.app.global.company.util.CommonUtils().getNativeSelectForFilter(group, logic.getCompanyGroup());
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
            group.removeItem(0);
            group.setNullSelectionAllowed(true);
            group.setNullSelectionItemId(ConstantsUtils.SHOW_ALL);
            group.select(ConstantsUtils.SHOW_ALL);
            return group;
        }else if ("companyFamilyPlanCategory".equals(propertyId)) {

                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                new CommonUtils().getNativeSelectForFilter(comboBox, cfpLogic.getDropDownList(UIUtils.CFP_CATAGORY));
                comboBox.select(0);
                comboBox.setNullSelectionAllowed(true);
                comboBox.setNullSelectionItemId(ConstantsUtils.Show_All);
                comboBox.select(ConstantsUtils.ZERO_INT);
                return comboBox;

         } else if (ConstantsUtils.COMPANY_STATUS_VALUE.equals(propertyId)) {                
                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                new CommonUtils().getNativeSelectForFilter(comboBox, cfpLogic.getDropDownList(UIUtils.CFP_STATUS));
                comboBox.setNullSelectionAllowed(true);
                comboBox.setNullSelectionItemId(ConstantsUtils.Show_All);
                comboBox.select(ConstantsUtils.ZERO_INT);
                return comboBox;
            } else if (ConstantsUtils.COMPANY_TYPE_VALUE.equals(propertyId)) {                
                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                new CommonUtils().getNativeSelectForFilter(comboBox, cfpLogic.getDropDownList(UIUtils.COMPANY_TYPE_LIST_NAME));
                comboBox.setNullSelectionAllowed(true);
                comboBox.setNullSelectionItemId(ConstantsUtils.Show_All);
                comboBox.select(ConstantsUtils.ZERO_INT);
                return comboBox;
         } else if ("createdBy".equals(propertyId)) {       
                 comboBox = new ComboBox();
                    comboBox.addItem(0);
                    comboBox.setItemCaption(0, ConstantsUtils.SHOW_ALL);
                    for (Map.Entry<Integer, String> entry : userMap.entrySet()) {
                        comboBox.addItem(entry.getKey());
                        comboBox.setItemCaption(entry.getKey(), entry.getValue());
                    }
                    comboBox.setNullSelectionAllowed(true);
                    comboBox.setNullSelectionItemId(0);
                    return comboBox;
         
            } else if ("modifiedBy".equals(propertyId)) {
                comboBox = new ComboBox();
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
             else if ("cfpcreatedBy".equals(propertyId)) {
                comboBox = new ComboBox();
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
            else if ("cfpmodifiedBy".equals(propertyId)) {
                comboBox = new ComboBox();
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
            
        } catch (SystemException | PortalException ex) {
            LOGGER.error(ex);
        }
        return null;
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
