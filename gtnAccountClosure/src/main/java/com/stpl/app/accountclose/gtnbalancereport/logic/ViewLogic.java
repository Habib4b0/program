/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.logic;

import com.stpl.app.accountclose.dao.BaseRateDAO;
import com.stpl.app.accountclose.dao.impl.BaseRateDAOImpl;
import com.stpl.app.accountclose.gtnbalancereport.dao.DataSelectionDAO;
import com.stpl.app.accountclose.gtnbalancereport.dao.daoImpl.DataSelectionDaoImpl;
import com.stpl.app.accountclose.gtnbalancereport.dto.ViewDTO;
import com.stpl.app.accountclose.gtnbalancereport.utils.Constants;
import com.stpl.app.accountclose.utils.QueryUtils;
import com.stpl.app.parttwo.model.AccClosureViewMaster;
import com.stpl.app.parttwo.service.AccClosureViewMasterLocalServiceUtil;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.vaadin.server.VaadinSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author santanukumar
 */
public class ViewLogic {

    public static final DataSelectionDAO dataSelection = new DataSelectionDaoImpl();
    private static final Logger LOGGER = Logger.getLogger(ViewLogic.class);
    BaseRateDAO dao = new BaseRateDAOImpl();
    public boolean isDuplicateView(final String viewName, final String viewType) throws SystemException, Exception {
        LOGGER.info("Entering isDuplicateView method with viewName " + viewName);
   List input=new ArrayList();
        input.add(viewName);
       input.add(viewType);
           List resultList = (List<Object[]>)  QueryUtils.executeSelectQuery(input, "viewNameCount");
        Object obj = null;
       if (!resultList.isEmpty()) {
            obj = resultList.get(0);
        }
        int count= obj == null ? 0 : (Integer) obj;
         LOGGER.info("End of isDuplicateView with size: " + count);
        return count > Constants.ZERO;
        
    }

    public int saveForecastViewMaster(final ViewDTO saveViewDTO) {
        LOGGER.info("Entering saveForecastViewMaster method viewBinder and projectionId='" + saveViewDTO.getAccountClosureId());
        final String userId = (String) VaadinSession.getCurrent().getAttribute(Constants.USER_ID);
        AccClosureViewMaster viewMaster = AccClosureViewMasterLocalServiceUtil.createAccClosureViewMaster(0);
        if (saveViewDTO.getViewMasterId()!= 0) {
            viewMaster.setAccClosureViewMasterSid(saveViewDTO.getViewMasterId());
        }

        if (viewMaster.getAccClosureViewMasterSid() == Constants.ZERO) {
            viewMaster.setCreatedBy(Integer.valueOf(userId));
            viewMaster.setCreatedDate(new Date());
        } else {

            try {
                viewMaster = dataSelection.getAcctViewMaster(viewMaster.getAccClosureViewMasterSid());
            } catch (Exception e) {
                LOGGER.error(e);
            }
            viewMaster.setModifiedBy(Integer.valueOf(userId));
            viewMaster.setModifiedDate(new Date());
        }
        if (StringUtils.isNotBlank(saveViewDTO.getViewName())) {
            viewMaster.setViewName(saveViewDTO.getViewName());
        }
        if (StringUtils.isNotBlank(saveViewDTO.getViewType())) {
            viewMaster.setViewType(saveViewDTO.getViewType());
        }
        viewMaster.setAccClosureMasterSid(saveViewDTO.getAccountClosureId());
        if (viewMaster.getAccClosureViewMasterSid() == Constants.ZERO) {
            try {

                viewMaster = dataSelection.addForecastingViewMaster(viewMaster);
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        } else {
            try {

                viewMaster = dataSelection.updateForecastingViewMaster(viewMaster);
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        LOGGER.info("End of saveForecastViewMaster method with view id: " + viewMaster.getAccClosureViewMasterSid());
        return viewMaster.getAccClosureViewMasterSid();
    }


    public static User getUserById(final String userId) {
        LOGGER.info("Entering getUserById method with userId " + userId);
        try {
            return dataSelection.getUser(Long.valueOf(userId));
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }
}
