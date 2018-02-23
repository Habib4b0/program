/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.logic;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.stpl.app.cff.dao.DataSelectionDAO;
import com.stpl.app.cff.dao.impl.DataSelectionDAOImpl;
import com.stpl.app.cff.dto.SaveViewDTO;
import com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils;
import com.stpl.app.cff.util.Constants;
import com.stpl.app.parttwo.model.CffViewMaster;
import com.stpl.app.parttwo.service.CffViewMasterLocalServiceUtil;
import com.vaadin.server.VaadinSession;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mohamed.hameed
 */
public class ViewLogic {

    /**
     * The data selection.
     */
	private static final DataSelectionDAO dataSelection = new DataSelectionDAOImpl();

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewLogic.class);

    /**
     * Checks if is duplicate view.
     *
     * @param viewName the view name
     * @return true, if checks if is duplicate view
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public boolean isDuplicateView(final String viewName) throws SystemException {
        LOGGER.debug("Entering isDuplicateView method with viewName= {}", viewName);
        final DynamicQuery dynamicQuery = CffViewMasterLocalServiceUtil.dynamicQuery();
        dynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.VIEW_NAME, viewName));
        final long count = dataSelection.getForecastViewMasterdynamicQueryCount(dynamicQuery);
        LOGGER.debug("End of isDuplicateView with size: {}", count);
        return count > Constants.ZERO;
    }

    /**
     * Gets the user by id.
     *
     * @param userId the user id
     * @return the user by id
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public static User getUserById(final String userId) throws PortalException, SystemException {
        LOGGER.debug("Entering getUserById method with userId= {}", userId);
        return dataSelection.getUser(Long.valueOf(userId));
    }

    /**
     * This method is used to save record in Forecast View Master.
     *
     * @param saveViewDTO the view dto
     * @param projectionId the projection id
     * @return viewId
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public int saveForecastViewMaster(final SaveViewDTO saveViewDTO, final int projectionId) throws PortalException, SystemException {
        LOGGER.debug("Entering saveForecastViewMaster method viewBinder and projectionId= {} and viewId= {}", projectionId, String.valueOf(saveViewDTO.getViewId()));
        String userId = (String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID);
        CffViewMaster viewMaster = CffViewMasterLocalServiceUtil.createCffViewMaster(0);
        if (saveViewDTO.getViewId() != 0) {
            viewMaster.setCffViewMasterSid(saveViewDTO.getViewId());
        }
        // Get the saved view if exists
        if (viewMaster.getCffViewMasterSid() == Constants.ZERO) {
       
            viewMaster.setCreatedBy(userId);
            viewMaster.setCreatedDate(new Date());
        } else {

            viewMaster = dataSelection.getForecastingViewMaster(viewMaster.getCffViewMasterSid());
            viewMaster.setModifiedBy(userId);
            viewMaster.setModifiedDate(new Date());
        }
        if (saveViewDTO.getViewName() != null && !StringUtils.isEmpty(saveViewDTO.getViewName())) {
            viewMaster.setViewName(saveViewDTO.getViewName());
        }
        if (saveViewDTO.getViewType() != null && !StringUtils.isEmpty(saveViewDTO.getViewType())) {
            viewMaster.setViewType(saveViewDTO.getViewType());
        }
        viewMaster.setCffMasterSid(projectionId);
        if (viewMaster.getCffViewMasterSid() == Constants.ZERO) {
            // Insert Forecast View Master
            viewMaster = dataSelection.addForecastingViewMaster(viewMaster);
        } else {
            // Update Forecast View Master
            viewMaster = dataSelection.updateForecastingViewMaster(viewMaster);
        }
        LOGGER.debug("End of saveForecastViewMaster method with view id= {} ", viewMaster.getCffViewMasterSid());
        return viewMaster.getCffViewMasterSid();
    }

    /**
     * Method to Update ForecastingViewMaster
     *
     * @param saveViewDTO
     * @param viewId
     * @return true od false.
     */
    public CffViewMaster updateView(SaveViewDTO saveViewDTO, final String viewName, final String viewType) {
        Object[] objects = null;
        CffViewMaster updatedViewMaster = null;
        try {
            int viewId = 0;
            DynamicQuery dynamicQuery = CffViewMasterLocalServiceUtil.dynamicQuery();
            dynamicQuery.add(RestrictionsFactoryUtil.eq("viewName", viewName));
            dynamicQuery.add(RestrictionsFactoryUtil.eq("viewType", viewType));
            final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
            productProjectionList.add(ProjectionFactoryUtil.property("cffViewMasterSid"));
            productProjectionList.add(ProjectionFactoryUtil.property("cffMasterSid"));
            dynamicQuery.setProjection(productProjectionList);

            List result = dataSelection.getForecastViewFromName(dynamicQuery);
            if (result == null && result.isEmpty()) {
                return null;
            }
            objects = (Object[]) result.get(0);
            viewId = Integer.parseInt(String.valueOf(objects[0]));
            String userId = (String) VaadinSession.getCurrent().getAttribute(Constants.USER_ID);
            CffViewMaster viewMaster = CffViewMasterLocalServiceUtil.createCffViewMaster(0);
            try {
                viewMaster = dataSelection.getForecastingViewMaster(viewId);
            } catch (PortalException | SystemException ex) {
                java.util.logging.Logger.getLogger(ViewLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (saveViewDTO.getViewName() != null
                    && !StringUtils.isEmpty(saveViewDTO.getViewName())) {
                viewMaster.setViewName(saveViewDTO.getViewName());

            }
            if (saveViewDTO.getViewType() != null
                    && !StringUtils.isEmpty(saveViewDTO.getViewType())) {
                viewMaster.setViewType(saveViewDTO.getViewType());

            }
            viewMaster.setModifiedBy(userId);
            viewMaster.setModifiedDate(new Date());

            updatedViewMaster = dataSelection.updateForecastingViewMaster(viewMaster);
            return updatedViewMaster;

        } catch (SystemException | NumberFormatException e) {
            LOGGER.error(e.getMessage());
            return updatedViewMaster;
        }

    }
}
