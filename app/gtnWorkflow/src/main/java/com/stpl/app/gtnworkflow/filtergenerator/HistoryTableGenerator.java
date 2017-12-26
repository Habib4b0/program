/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnworkflow.filtergenerator;

import com.stpl.app.gtnworkflow.util.CommonUtils;
import com.stpl.ifs.util.HelperDTO;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.stpl.app.gtnworkflow.util.ConstantsUtils;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.TextField;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.jboss.logging.Logger;

/**
 *
 * @author Lokeshwari
 */
public class HistoryTableGenerator implements ExtFilterGenerator {

    CommonUtils commonUtils = new CommonUtils();
    private static final Logger LOGGER = Logger.getLogger(HistoryTableGenerator.class);

    public HistoryTableGenerator() {
        LOGGER.debug("HistoryTableGenerator");
    }
    
    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
         try
        {
            if (propertyId.toString().equals("modifiedBy")) {
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
           
            switch (propertyId.toString()) {
                case "modifiedBy":
                    comboBox.addItem(0);
                    comboBox.setItemCaption(0, ConstantsUtils.SHOW_ALL);
                    Map<Integer, String> userMap = getUserName();
                    for (Map.Entry<Integer, String> entry : userMap.entrySet()) {
                        comboBox.addItem(entry.getKey());
                        comboBox.setItemCaption(entry.getKey(), entry.getValue());
                    }
                    comboBox.setNullSelectionAllowed(true);
                    comboBox.setNullSelectionItemId(0);
                    return comboBox;
                case "status":
                    comboBox.focus();
                    commonUtils.loadComboBox(comboBox,CommonUtils.WORKFLOW_STATUS, true);
                    return comboBox;
                case "attachmentLink":
                    TextField iconFilter = new TextField();
                    iconFilter.setReadOnly(true);
                    iconFilter.setWidth("100%");
                    return iconFilter;    
                    
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
    public static Map<Integer, String> getUserName() throws SystemException {
        LOGGER.debug("Enters getUserName method");
        DynamicQuery dynamicQuery = UserLocalServiceUtil.dynamicQuery();
        Map<Integer,String> userMap=new ConcurrentHashMap<Integer,String>(); 
        List<User> userList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
        for (User user : userList) {
            userMap.put(Long.valueOf(user.getUserId()).intValue(), user.getFullName());
        }
        LOGGER.debug("End of getUserName method");
        return userMap;
    } 
}
