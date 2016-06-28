package com.stpl.app.contract.global.dto;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.OrderByColumn;

import com.stpl.app.contract.global.logic.CFPSearchLogic;
import com.stpl.app.contract.ui.ErrorfulFieldGroup;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;
import com.stpl.portal.kernel.exception.SystemException;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

/**
 * The Class CompanyNameDAO.
 */
public class CompanyNameDAO implements BeanDAO<CompanySearchDto> {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CompanyNameDAO.class);
    /**
     * The company search logic.
     */
    private final CFPSearchLogic companySearchLogic = new CFPSearchLogic();
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
    public CompanyNameDAO() {
        /**
         * Empty Constructor.
         */
    }

    /**
     * The Constructor.
     *
     * @param binder the binder
     */
    public CompanyNameDAO(final ErrorfulFieldGroup binder) {
        this.binder = binder;
        binderFlag = true;
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
                count = (int) companySearchLogic.getCompanySearchCount(binder,search);
            } else {
                count = companySearchLogic.getTotalCount();
            }
            return count;
        } catch (SystemException e) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
            LOGGER.error(errorMsg);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
            return 0;
        }

    }

    /**
     * Finds the Company and returns the list.
     *
     * @param criteria the criteria
     * @param startIndex the start index
     * @param offset the offset
     * @param columns the columns
     * @return the list
     */
    public List<CompanySearchDto> find(final BeanSearchCriteria criteria, final int startIndex, final int offset, final List<OrderByColumn> columns) {
        try {
            LOGGER.info("Entering find()");
            List<CompanySearchDto> salesList;
            if (binderFlag) {
                salesList = companySearchLogic.searchCompanyName(binder,criteria, startIndex, startIndex + offset, columns);
                LOGGER.info("End of find()");
                return salesList;
            } else {
                salesList = new ArrayList<CompanySearchDto>();
            }
            LOGGER.info("End of find()");
            return salesList;
        } catch (SystemException e) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
            LOGGER.error(errorMsg);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        }
        return new ArrayList<CompanySearchDto>();
    }

    /**
     * Gets the company search logic.
     *
     * @return the company search logic
     */
    public CFPSearchLogic getCompanySearchLogic() {
        return companySearchLogic;
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