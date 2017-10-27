/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.contract.global.lazyload;

import com.stpl.app.contract.global.logic.CFPSearchLogic;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.Collections;
import java.util.List;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.DAO;
import org.vaadin.addons.lazycontainer.OrderByColumn;
import org.vaadin.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author Abhiram
 */
public class CompanyTypeDAO implements DAO<HelperDTO> {
        private static final Logger LOGGER = Logger.getLogger(ManufacturerDAO.class);
    CFPSearchLogic logic=new CFPSearchLogic();
    public int count(SearchCriteria searchCriteria) {
        try {
            LOGGER.debug("Entering CompanyQualifierNameDAO Count method :");
            return logic.getLazyCompanyTypeCount(searchCriteria.getFilter());
        } catch (SystemException ex) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        }
        return 0;
    }

    /**
     * Method used for get the results.
     */
    public List<HelperDTO> find(SearchCriteria searchCriteria, int startIndex, int offset, List<OrderByColumn> list) {
        try {
            LOGGER.debug("Entering CompanyQualifierNameDAO find method :");
            return logic.getLazyCompanyTypeResults(startIndex, startIndex + offset, searchCriteria.getFilter());
        } catch (SystemException ex) {
            LOGGER.error(ex);
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        }
        return  Collections.emptyList();
    }

}
