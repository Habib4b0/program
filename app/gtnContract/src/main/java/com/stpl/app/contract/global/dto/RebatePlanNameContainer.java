/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.global.dto;

import com.stpl.app.contract.global.logic.RebateScheduleLogic;
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
 * @author pvinoth
 */
public class RebatePlanNameContainer implements DAO<HelperDTO>{
    private HelperDTO rebatePlan;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(RebatePlanNameContainer.class);
    
    public RebatePlanNameContainer(final HelperDTO rebatePlan) {
        this.rebatePlan=rebatePlan;
    }
    
    public int count(final SearchCriteria searchCriteria) {
        try {
            LOGGER.debug("Entering CompanyQualifierNameDAO Count method :");
            return RebateScheduleLogic.getRebatePlanCount(searchCriteria.getFilter(), rebatePlan)+1;
        } catch (PortalException ex) {
             LOGGER.debug(ex);
        	AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004));
        } catch (SystemException ex) {
        	final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        }
        return 0;
    }

    /**
     * Method used for get the results.
     */
    public List<HelperDTO> find(final SearchCriteria searchCriteria, final int startIndex, final int offset, final List<OrderByColumn> list) {
        try {
            LOGGER.debug("Entering CompanyQualifierNameDAO find method :");
            return RebateScheduleLogic.getRebatePlanResults(startIndex, startIndex + offset, searchCriteria.getFilter(), rebatePlan);
        } catch (PortalException ex) {
             LOGGER.debug(ex);
        	AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004));
        } catch (SystemException ex) {
        	LOGGER.error(ex);
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        }
        return  Collections.emptyList();
    }
}
