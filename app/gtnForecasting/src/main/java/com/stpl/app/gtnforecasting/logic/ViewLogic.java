/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.logic;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.stpl.app.gtnforecasting.dao.DataSelectionDAO;
import com.stpl.app.gtnforecasting.dao.impl.DataSelectionDAOImpl;
import com.stpl.app.gtnforecasting.dto.SaveViewDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.model.ForecastingViewMaster;
import com.stpl.app.service.ForecastingViewMasterLocalServiceUtil;
import com.vaadin.server.VaadinSession;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class ViewLogic.
 *
 * @author lokeshwari
 */
public class ViewLogic {

    /**
     * The data selection.
     */
	private static DataSelectionDAO dataSelection = new DataSelectionDAOImpl();

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
    public boolean isDuplicateView(final String viewName) throws SystemException{
        LOGGER.debug("Entering isDuplicateView method with viewName " + viewName);
        final DynamicQuery dynamicQuery = ForecastingViewMasterLocalServiceUtil.dynamicQuery();
        dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.VIEW_NAME, viewName));
        final long count = dataSelection.getForecastViewMasterdynamicQueryCount(dynamicQuery);
        LOGGER.debug("End of isDuplicateView with size: " + count);
        return count > Constant.ZERO;
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
    public static User getUserById(final String userId) throws SystemException, PortalException {
        LOGGER.debug("Entering getUserById method with userId " + userId);
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
    public int saveForecastViewMaster(final SaveViewDTO saveViewDTO, final int projectionId) throws SystemException, PortalException {
        LOGGER.debug("Entering saveForecastViewMaster method viewBinder and projectionId='" + projectionId + "' and view id: " + String.valueOf(saveViewDTO.getViewId()));
        final String userId = (String) VaadinSession.getCurrent().getAttribute(Constant.USER_ID);
        ForecastingViewMaster viewMaster = ForecastingViewMasterLocalServiceUtil.createForecastingViewMaster(0);
        if (saveViewDTO.getViewId() != 0) {
            viewMaster.setViewId(saveViewDTO.getViewId());
        }
        // Get the saved view if exists
        if (viewMaster.getViewId() == Constant.ZERO) {
            viewMaster.setCreatedBy(userId);
            viewMaster.setCreatedDate(new Date());
        } else {

            viewMaster = dataSelection.getForecastingViewMaster(viewMaster.getViewId());
            viewMaster.setModifiedBy(userId);
            viewMaster.setModifiedDate(new Date());
        }
        if (saveViewDTO.getViewName() != null && !StringUtils.isEmpty(saveViewDTO.getViewName())) {
            viewMaster.setViewName(saveViewDTO.getViewName());
        }
        if (saveViewDTO.getViewType() != null && !StringUtils.isEmpty(saveViewDTO.getViewType())) {
            viewMaster.setViewType(saveViewDTO.getViewType());
        }
        viewMaster.setProjectionId(projectionId);
        if (viewMaster.getViewId() == Constant.ZERO) {
            // Insert Forecast View Master
            viewMaster = dataSelection.addForecastingViewMaster(viewMaster);
        } else {
            // Update Forecast View Master
            viewMaster = dataSelection.updateForecastingViewMaster(viewMaster);
        }
        LOGGER.debug("End of saveForecastViewMaster method with view id: " + viewMaster.getViewId());
        return viewMaster.getViewId();
    }

    /**
     * Method to Update ForecastingViewMaster
     *
     * @param saveViewDTO
     * @param viewId
     * @return true od false.
     */
    public ForecastingViewMaster updateView(SaveViewDTO saveViewDTO, final String viewName, final String viewType) {
        Object[] objects = null;
        ForecastingViewMaster updatedViewMaster = null;
        try {
            int viewId = 0;
            DynamicQuery dynamicQuery = ForecastingViewMasterLocalServiceUtil.dynamicQuery();
            dynamicQuery.add(RestrictionsFactoryUtil.eq("viewName", viewName));
            dynamicQuery.add(RestrictionsFactoryUtil.eq("viewType", viewType));
            final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
            productProjectionList.add(ProjectionFactoryUtil.property("viewId"));
            productProjectionList.add(ProjectionFactoryUtil.property(Constant.PROJECTION_ID));
            dynamicQuery.setProjection(productProjectionList);

            List result = dataSelection.getForecastViewFromName(dynamicQuery);
            if (result == null && result.isEmpty()) {
                return null;
            }
            objects = (Object[]) result.get(0);
            viewId = Integer.parseInt(String.valueOf(objects[0]));
            String userId = (String) VaadinSession.getCurrent().getAttribute(Constant.USER_ID);
            ForecastingViewMaster viewMaster = ForecastingViewMasterLocalServiceUtil.createForecastingViewMaster(0);
            try {
                viewMaster = dataSelection.getForecastingViewMaster(viewId);
                dataSelection.deleteForecastingViewMaster(viewId);
            } catch (PortalException | SystemException ex) {
                LoggerFactory.getLogger(ViewLogic.class.getName()).error( StringUtils.EMPTY, ex);
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

            updatedViewMaster = dataSelection.addForecastingViewMaster(viewMaster);
            return updatedViewMaster;

        } catch (SystemException | NumberFormatException e) {
           LOGGER.error(e.getMessage());
            return updatedViewMaster;
        }

    }
}
