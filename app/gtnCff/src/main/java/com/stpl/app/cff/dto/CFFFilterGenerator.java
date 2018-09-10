/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.dto;

import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.cff.logic.CFFLogic;
import com.stpl.app.cff.security.StplSecurity;
import com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.ConstantsUtil;
import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.Field;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;

/**
 *
 * @author Manasa
 */
public class CFFFilterGenerator implements ExtFilterGenerator {

    private final CommonUtils commonUtils = new CommonUtils();
   
    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        if (propertyId.toString().equals("approvedBy")) {
                if (originatingField.getValue() != null) {
                    return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
                }
                 else {
                    return null;
                }
            } else if (originatingField instanceof ComboBox) {
            if (originatingField.getValue() != null) {
                HelperDTO dto = (HelperDTO)originatingField.getValue();
                return new SimpleStringFilter(propertyId, String.valueOf(dto.getId()), false, false);
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        
        final ComboBox comboBox = new ComboBox();
        if ("typeDesc".equals(propertyId)) {

            try {
                ComboBox typeDdlb = new ComboBox();
                typeDdlb.addItem(StringConstantsUtil.SHOW_ALL);
                typeDdlb = commonUtils.getNativeSelect(typeDdlb, CFFLogic.getDropDownList(ConstantsUtil.CFF_TYPE), StringUtils.EMPTY);
                typeDdlb.setDebugId("testing");
                typeDdlb.setImmediate(true);
                typeDdlb.setNullSelectionItemId(StringConstantsUtil.SHOW_ALL);
                return typeDdlb;
            } catch (SystemException ex) {
                Logger.getLogger(CFFFilterGenerator.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }

        if ("statusDesc".equals(propertyId)) {

            try {
                ComboBox statusDdlb = new ComboBox();
                statusDdlb.addItem(StringConstantsUtil.SHOW_ALL);
                statusDdlb.addItems(CFFLogic.loadStatusDdlb(CommonUtils.WORKFLOW_STATUS));
                statusDdlb.setDebugId("testing");
                statusDdlb.setImmediate(true);
                statusDdlb.setNullSelectionItemId(StringConstantsUtil.SHOW_ALL);
                return statusDdlb;
             } catch (SystemException ex) {
                Logger.getLogger(CFFFilterGenerator.class.getName()).log(Level.SEVERE, null, ex);
             } 
        }
        if ("approvedBy".equals(propertyId)) {
            try {
                Map<Integer, String> userMap = StplSecurity.getUserMap() ;
                comboBox.addItem(0);
                    comboBox.setItemCaption(0, ConstantsUtils.SHOW_ALL);
                    for (Map.Entry<Integer, String> entry : userMap.entrySet()) {
                        comboBox.addItem(entry.getKey());
                        comboBox.setItemCaption(entry.getKey(), entry.getValue());
                    }
                    comboBox.setNullSelectionAllowed(true);
                    comboBox.setNullSelectionItemId(0);
                    return comboBox;
            } catch (UnsupportedOperationException ex) {
                Logger.getLogger(CFFFilterGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public void filterRemoved(Object propertyId) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
        return null;
    }
//    
   

}
