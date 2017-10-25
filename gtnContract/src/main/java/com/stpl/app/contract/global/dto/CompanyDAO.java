package com.stpl.app.contract.global.dto;

import com.stpl.app.contract.contractheader.dto.CompanyResultsDTO;
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
 * The Class CompanyDAO.
 */
public class CompanyDAO implements BeanDAO<CompanyResultsDTO> {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CompanyDAO.class);
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
    public CompanyDAO() {
        /**
         * Empty constructor.
         */
    }

    /**
     * The Constructor.
     *
     * @param binder the binder
     */
    public CompanyDAO(final ErrorfulFieldGroup binder) {
        LOGGER.debug("Entering CompanyDAO");
        this.binder = binder;
        binderFlag = true;
        LOGGER.debug("End of CompanyDAO");
    }

    /**
     * Returns the count.
     *
     * @param search the search
     * @return the int
     */
    public int count(final BeanSearchCriteria search) {
        LOGGER.debug("count(SearchCriteria search)");

        try {
            int count;
            LOGGER.debug("Entering count method");
                count = (int) companySearchLogic.getSearchCount(binder,search);
            LOGGER.debug("End of count method with count=" + count);
            return count;
        } catch (SystemException ex) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            LOGGER.error("Exception in count -" + errorMsg);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
            return 0;
        }

    }

    /**
     * Method returns the list of compnay.
     *
     * @param criteria the criteria
     * @param startIndex the start index
     * @param offset the offset
     * @param columns the columns
     * @return the list
     */
    public List<CompanyResultsDTO> find(final BeanSearchCriteria criteria, final int startIndex, final int offset, final List<OrderByColumn> columns) {
        LOGGER.debug("Entering find method");
        try {
            List<CompanyResultsDTO> salesList;
            if (binderFlag) {
                salesList = companySearchLogic.searchCompanyHelperTableSort(binder, criteria,startIndex, startIndex + offset, columns);
                
                LOGGER.debug("End of find method ");
                return salesList;
            } else {
                salesList = new ArrayList<CompanyResultsDTO>();
            }

            LOGGER.debug("End of find method ");
            return salesList;
        } catch (SystemException ex) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            LOGGER.error("Exception in count -" + errorMsg);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        }
        return new ArrayList<CompanyResultsDTO>();
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

    /**
     * Checks if is binder flag.
     *
     * @return true, if checks if is binder flag
     */
    public boolean isBinderFlag() {
        return binderFlag;
    }
}
