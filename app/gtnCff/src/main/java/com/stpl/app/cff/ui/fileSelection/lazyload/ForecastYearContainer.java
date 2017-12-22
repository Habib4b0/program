/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.cff.ui.fileSelection.lazyload;

import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.cff.ui.fileSelection.logic.FileManagementLogic;
import com.stpl.app.cff.util.AbstractNotificationUtils;
import com.stpl.app.cff.util.ErrorCodeUtil;
import com.stpl.app.cff.util.ErrorCodes;
import com.stpl.ifs.util.HelperDTO;
import java.util.Collections;
import java.util.List;
import org.asi.ui.addons.lazycontainer.DAO;
import org.asi.ui.addons.lazycontainer.OrderByColumn;
import org.asi.ui.addons.lazycontainer.SearchCriteria;
import org.jboss.logging.Logger;

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
        }
        return Collections.emptyList();
    }
}