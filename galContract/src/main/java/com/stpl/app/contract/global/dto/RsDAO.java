package com.stpl.app.contract.global.dto;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.OrderByColumn;

import com.stpl.app.contract.global.logic.RebateScheduleLogic;
import com.stpl.app.contract.ui.ErrorfulFieldGroup;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;
import com.stpl.portal.kernel.exception.SystemException;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

/**
 * The Class RsDAO.
 */
public class RsDAO implements BeanDAO<RebateScheduleMasterDTO> {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(RsDAO.class);
    /**
     * The rebate schedule logic.
     */
    private final RebateScheduleLogic rebateScheduleLogic = new RebateScheduleLogic();
    /**
     * The binder.
     */
    private ErrorfulFieldGroup binder;
    /**
     * The binder flag.
     */
    private boolean binderFlag;

    /**
     * The Constructor.
     */
    public RsDAO() {
        /**
         * Empty Constructor.
         */
    }

    /**
     * The Constructor.
     *
     * @param binder the binder
     */
    public RsDAO(final ErrorfulFieldGroup binder) {

        LOGGER.info("Entering RsDAO");
        this.binder = binder;
        binderFlag = true;
        LOGGER.info("End of RsDAO");

    }

    /**
     * Returns the count.
     *
     * @param search the search
     * @return the int
     */
    public int count(final BeanSearchCriteria search) {
        LOGGER.info("Entering count()");
        try {
            int count;
            if (binderFlag) {
                count = (int) rebateScheduleLogic.getSearchCount(binder,search);
            } else {
                count = rebateScheduleLogic.getTotalCount();
            }
            LOGGER.info("End of count()");
            return count;
        } catch (SystemException ex) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            LOGGER.error(errorMsg);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        }
        return 0;
    }

    /**
     * Returns the list.
     *
     * @param criteria the criteria
     * @param startIndex the start index
     * @param offset the offset
     * @param columns the columns
     * @return the list
     */
    public List<RebateScheduleMasterDTO> find(final BeanSearchCriteria criteria, final int startIndex, final int offset, final List<OrderByColumn> columns) {

        LOGGER.info("Entering find()");
        if (binderFlag) {
            try {

                LOGGER.info("searchRebateScheduleMaster()");
                final List<RebateScheduleMasterDTO> salesList = rebateScheduleLogic.searchRebateScheduleMaster(binder, criteria,startIndex, startIndex + offset, columns);
                LOGGER.info("returns List<RebateScheduleMasterDTO> size=" + salesList.size());
                return salesList;
            } catch (SystemException ex) {
                final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                LOGGER.error(errorMsg);
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
            }
            return new ArrayList<RebateScheduleMasterDTO>();
        }
        LOGGER.info("End of find()");
        return new ArrayList<RebateScheduleMasterDTO>();
    }

    /**
     * Gets the rebate schedule logic.
     *
     * @return the rebate schedule logic
     */
    public RebateScheduleLogic getRebateScheduleLogic() {
        return rebateScheduleLogic;
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    public ErrorfulFieldGroup getBinder() {
        return binder;
    }

    /**
     * Checks if is binder flag.
     *
     * @return true, if checks if is binder flag
     */
    public boolean isBinderFlag() {
        return binderFlag;
    }

    /**
     * Sets the binder.
     *
     * @param binder the binder
     */
    public void setBinder(final ErrorfulFieldGroup binder) {
        this.binder = binder;
    }

    /**
     * Sets the binder flag.
     *
     * @param binderFlag the binder flag
     */
    public void setBinderFlag(final boolean binderFlag) {
        this.binderFlag = binderFlag;
    }
}
