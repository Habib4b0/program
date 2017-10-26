/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.itemgroup.ui.lazyload;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.DAO;
import org.vaadin.addons.lazycontainer.OrderByColumn;
import org.vaadin.addons.lazycontainer.SearchCriteria;

import com.stpl.app.adminconsole.itemgroup.util.CommonUtils;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * The Class ContractHolderDAO.
 *
 * @author shrihariharan
 */
public class ContractHolderDAO implements DAO<HelperDTO> {

    private static final Logger LOGGER = Logger.getLogger(ContractHolderDAO.class);

    private String marketType;

    public ContractHolderDAO(final String marketType) {
        this.marketType = marketType;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(final String marketType) {
        this.marketType = marketType;
    }

    public int count(final SearchCriteria search) {
        int counts = 0;
        try {
            counts = CommonUtils.getLazyCustomerNamesCount(marketType, search.getFilter());
        } catch (SystemException e) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        } catch (PortalException e) {
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4005));
        } catch (Exception e) {
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4005));
        }
        return counts;
    }

    /**
     * Gets the list of companies with ids
     *
     * @param criteria
     * @param offset
     * @param startIndex
     * @param columns
     * @return
     */
    public List<HelperDTO> find(final SearchCriteria criteria, final int startIndex, final int offset, final List<OrderByColumn> columns) {
        LOGGER.debug("find method starts with startIndex" + startIndex + "offset " + offset + " columns list size:" + columns.size());
        List<HelperDTO> salesList = new ArrayList<HelperDTO>();
        try {
            salesList = CommonUtils.getCompanyWithIds(startIndex, startIndex + offset, marketType, criteria.getFilter());
        } catch (SystemException e) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        } catch (PortalException e) {
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4005));
        } catch (Exception e) {
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4005));
        }
        LOGGER.debug("Returns list size: " + salesList.size());
        return salesList;
    }
}
