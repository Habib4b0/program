/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galworkflow.filtergenerator;

import com.stpl.app.galworkflow.util.CommonUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;

/**
 *
 * @author rohitvignesh.s
 */
public class ViewTableGenerator implements ExtFilterGenerator {

    CommonUtils commonUtils = new CommonUtils();
    public static Map<Integer,String> userMap=new ConcurrentHashMap<Integer,String>();   
    private static final Logger LOGGER = LogManager.getLogger(HistoryTableGenerator.class);

    public ViewTableGenerator() {
    }
    
    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
         try
        {
            if (propertyId.toString().equals("approvedBy")  || propertyId.toString().equals("createdBy") ) {
                if (originatingField.getValue() != null) {
                    return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
                }
                 else {
                    return null;
                }
            } else if (originatingField instanceof ComboBox) {
                if (originatingField.getValue() != null) {
                    return new SimpleStringFilter(propertyId, String.valueOf(((HelperDTO) originatingField.getValue()).getId()), false, false);
                } else {
                    return null;
                }
            }
   
        }
        catch(Exception e){
            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
      
        try {
            final ComboBox comboBox = new ComboBox();
           Map<Integer, String> userMap = getUserName();
            switch (propertyId.toString()) {
                case "createdBy":
                    comboBox.addItem(0);
                    comboBox.setItemCaption(0, ConstantsUtils.SHOW_ALL);
                    for (Map.Entry<Integer, String> entry : userMap.entrySet()) {
                        comboBox.addItem(entry.getKey());
                        comboBox.setItemCaption(entry.getKey(), entry.getValue());
                    }
                    comboBox.setNullSelectionAllowed(true);
                    comboBox.setNullSelectionItemId(0);
                    return comboBox;
                case "approvedBy":
                    comboBox.addItem(0);
                    comboBox.setItemCaption(0, ConstantsUtils.SHOW_ALL);
                    for (Map.Entry<Integer, String> entry : userMap.entrySet()) {
                        comboBox.addItem(entry.getKey());
                        comboBox.setItemCaption(entry.getKey(), entry.getValue());
                    }
                    comboBox.setNullSelectionAllowed(true);
                    comboBox.setNullSelectionItemId(0);
                    return comboBox;
              case "status":
                    commonUtils.loadComboBox(comboBox,CommonUtils.WORKFLOW_STATUS, true);
                    return comboBox;
                default:
                    return null;
            }
        } catch (Exception ex) {
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
    public static Map<Integer, String> getUserName() throws SystemException {
        LOGGER.info("Enters getUserName method");
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
        List<User> userList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
        for (User user : userList) {
            userMap.put(Long.valueOf(user.getUserId()).intValue(), user.getFullName());
        }
        LOGGER.info("End of getUserName method");
        return userMap;
    } 
}
