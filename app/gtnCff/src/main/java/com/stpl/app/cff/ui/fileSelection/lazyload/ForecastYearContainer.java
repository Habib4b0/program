/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.cff.ui.fileSelection.lazyload;

import com.stpl.app.cff.ui.fileSelection.logic.FileManagementLogic;
import com.stpl.app.cff.util.AbstractNotificationUtils;
import com.stpl.app.cff.util.ErrorCodeUtil;
import com.stpl.app.cff.util.ErrorCodes;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.DAO;
import org.vaadin.addons.lazycontainer.OrderByColumn;
import org.vaadin.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author Harlin
 */
public class ForecastYearContainer implements DAO<HelperDTO> {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ForecastYearContainer.class);

    /**
     *
     * @param searchCriteria
     * @return
     */
    public int count(final SearchCriteria searchCriteria) {
        try {
            return FileManagementLogic.getForecastYearCount(searchCriteria.getFilter());
        } catch (SystemException ex) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            LOGGER.error(errorMsg);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        } catch (PortalException portException) {
            LOGGER.error(portException);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010));
        }
        return 0;
    }

    /**
     *
     * @param searchCriteria
     * @param startIndex
     * @param offset
     * @param list
     * @return
     */
    public List<HelperDTO> find(final SearchCriteria searchCriteria, final int startIndex, final int offset, final List<OrderByColumn> list) {
        try {
            return FileManagementLogic.getForecastYearResults(startIndex, startIndex + offset, searchCriteria.getFilter());
        } catch (SystemException ex) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            LOGGER.error(errorMsg);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        } catch (PortalException portException) {
            LOGGER.error(portException);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010));
        }
        return null;
    }
}