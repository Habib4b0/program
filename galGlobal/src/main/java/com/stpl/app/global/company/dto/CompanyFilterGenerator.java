/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.company.dto;

import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.company.logic.CompanySearchLogic;
import com.stpl.app.global.company.util.UIUtils;
import com.stpl.app.util.ConstantUtil;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.PopupDateField;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;

/**
 *
 * @author Asha
 */
public class CompanyFilterGenerator implements ExtFilterGenerator {
    
    /** The common util. */
    private CommonUtil commonUtil = CommonUtil.getInstance();
    
    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
      
      
        if ("startDate".equals(propertyId)) {
            /* Create an 'equals' filter for the ID field */
            if (value != null && value instanceof String) {
                try {
                    return new Compare.Equal(propertyId,
                            Integer.parseInt((String) value));
                } catch (NumberFormatException ignored) {
                    // If no integer was entered, just generate default filter
                }
            }
        }
        // For other properties, use the default filter
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        
      if (originatingField instanceof ComboBox) {
          if(originatingField.getValue()!=null)
          {
              return new SimpleStringFilter(propertyId, String.valueOf(((HelperDTO)originatingField.getValue()).getId()), false, false);     
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
        CompanySearchLogic companyLogic = new CompanySearchLogic();
        if ("status".equals(propertyId)) {

            ComboBox groupTesting = new ComboBox();
            List<String> result = new ArrayList<String>();
            result.add(ConstantUtil.SHOW_ALL);
            result.add("Active");
            result.add("Discontinued");
            result.add("Divested");
            result.add("Obsolete");
            result.add("Pre-Release");
            groupTesting.setContainerDataSource(new IndexedContainer(result));
            groupTesting.setNullSelectionAllowed(true);
            groupTesting.setNullSelectionItemId(ConstantUtil.SHOW_ALL);
            groupTesting.setValue(ConstantUtil.SHOW_ALL);
            groupTesting.setDebugId("testing");
            groupTesting.setImmediate(true);
            return groupTesting;
        }
        if("companyStartDate".equals(propertyId)) {
            PopupDateField date = new PopupDateField();
            return date;
        }
            if("companyStatus".equals(propertyId)) {
            ComboBox companyStatus = new ComboBox();
            try {
                commonUtil.loadComboBox(companyStatus, UIUtils.STATUS, true);
            } catch (Exception ex) {
            }

            return companyStatus;
        }
        if("companyType".equals(propertyId)) {
            ComboBox companyType = new ComboBox();
            try {
                commonUtil.loadComboBox(companyType, UIUtils.COMPANY_TYPE, true);
            } catch (Exception ex) {
            }

            return companyType;
        }
        if("tradeClass".equals(propertyId)) {
            ComboBox tradeClass1 = new ComboBox();
            try {
                commonUtil.loadComboBox(tradeClass1, UIUtils.TRADE_CLASS, true);
            } catch (Exception ex) {
            }

            return tradeClass1;
        }
        if("companyGroup".equals(propertyId)) {
            ComboBox group = new ComboBox();
            try {
                        commonUtil.loadComboBox(group, UIUtils.COMPANY_GROUP, true);

            } catch (Exception ex) {
            }

            return group;
        }

        if("companyCategory".equals(propertyId)) {
            ComboBox companyCategory = new ComboBox();
            try {
                commonUtil.loadComboBox(companyCategory, UIUtils.COMPANY_CATEGORY, true);
            } catch (Exception ex) {
            }

            return companyCategory;
        }
        if("organizationKey".equals(propertyId)) {
            try {
                ComboBox orgKey = new ComboBox();

                commonUtil.loadComboBox(orgKey, UIUtils.ORGANIZATION_KEY, true);
                return orgKey;
            } catch (Exception ex) {
            }
        }
        if ("udc1".equals(propertyId)) {
            try {
                ComboBox udc1 = new ComboBox();

                commonUtil.loadComboBox(udc1, UIUtils.UDC1, true);
                return udc1;
            } catch (Exception ex) {
            }
        }
        if ("udc2".equals(propertyId)) {
            try {
                ComboBox udc2 = new ComboBox();

                commonUtil.loadComboBox(udc2, UIUtils.UDC2, true);
                return udc2;
            } catch (Exception ex) {
            }
        }
        if ("udc3".equals(propertyId)) {
            try {
                ComboBox udc3 = new ComboBox();

                commonUtil.loadComboBox(udc3, UIUtils.UDC3, true);
                return udc3;
            } catch (Exception ex) {
            }
        }
        if ("udc4".equals(propertyId)) {
            try {
                ComboBox udc4 = new ComboBox();

                commonUtil.loadComboBox(udc4, UIUtils.UDC4, true);
                return udc4;
            } catch (Exception ex) {
            }
        }
        if ("udc5".equals(propertyId)) {
            try {
                ComboBox udc5 = new ComboBox();
                commonUtil.loadComboBox(udc5, UIUtils.UDC5, true);
                return udc5;
            } catch (Exception ex) {
            }
        }
        if ("udc6".equals(propertyId)) {
            try {
                ComboBox udc6 = new ComboBox();

                commonUtil.loadComboBox(udc6, UIUtils.UDC6, true);
                return udc6;
            } catch (Exception ex) {
            }
        }
            
        if ("state".equals(propertyId)) {
            try {
                ComboBox state = new ComboBox();

                  commonUtil.loadComboBox(state, UIUtils.STATE, true);

                return state;
            } catch (Exception ex) {
            }
        }
        if ("country".equals(propertyId)) {
            try {
                ComboBox country = new ComboBox();

                commonUtil.loadComboBox(country, UIUtils.COUNTRY, true);
                return country;
            } catch (Exception ex) {
            }
        }
        if("identifierStatus".equals(propertyId)) {
            ComboBox identifierStatus = new ComboBox();
            try {
                commonUtil.loadComboBox(identifierStatus, UIUtils.STATUS, true);
            } catch (Exception ex) {
            }

            
            return identifierStatus;
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
