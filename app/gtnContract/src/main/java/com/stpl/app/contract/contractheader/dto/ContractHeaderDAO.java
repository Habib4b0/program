package com.stpl.app.contract.contractheader.dto;

import com.stpl.app.contract.abstractsearch.dto.SearchResultsDTO;
import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.OrderByColumn;

import com.stpl.app.contract.contractheader.logic.ContractHeaderLogic;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

/**
 * DAO for Contract Header.
 *
 * @author sibi
 *
 */
public class ContractHeaderDAO implements BeanDAO<SearchResultsDTO> {

    /**
     * Constant Logger object for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(ContractHeaderDAO.class);
    /**
     * ContractHeaderLogic Object.
     */
    private final ContractHeaderLogic chLogic = new ContractHeaderLogic();
    /**
     * CustomFieldGroup object used to bind the field to DTO.
     */
    private CustomFieldGroup binder;
    /**
     * Flag used for binding fields.
     */
    private boolean binderFlag;
    /**
     * Empty Constructor.
     */
    public int countVal;

    /**
     * Gets the count val.
     *
     * @return the count val
     */
    public int getCountVal() {
        return countVal;
    }

    /**
     * Sets the count val.
     *
     * @param countVal the new count val
     */
    public void setCountVal(final int countVal) {
        this.countVal = countVal;
    }

    /**
     * Instantiates a new contract header dao.
     */
    public ContractHeaderDAO() {
        // Empty Constructor.
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    public CustomFieldGroup getBinder() {
        return binder;
    }

    /**
     * Sets the binder.
     *
     * @param binder the binder
     */
    public void setBinder(final CustomFieldGroup binder) {
        this.binder = binder;
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
     * Sets the binder flag.
     *
     * @param binderFlag the binder flag
     */
    public void setBinderFlag(final boolean binderFlag) {
        this.binderFlag = binderFlag;
    }

    /**
     * Gets the ch logic.
     *
     * @return the ch logic
     */
    public ContractHeaderLogic getChLogic() {
        return chLogic;
    }

    /**
     * Parameterized Constructor.
     *
     * @param binder the binder
     */
    public ContractHeaderDAO(final CustomFieldGroup binder) {

        LOGGER.debug("Enters ContractHeaderDAO ");
        this.binder = binder;
        binderFlag = true;
        LOGGER.debug("ContractHeaderDAO ends");

    }

    /**
     * Returns the number of rows in the Database for the given criteria.
     *
     * @param search the search
     * @return the int
     */
    public int count(final BeanSearchCriteria criteria) {

        LOGGER.debug("Enters count() with parameter search");
        if (binderFlag) {
            try {
                countVal = chLogic.getSearchCount(binder,criteria);
            } catch (SystemException ex) {
                LOGGER.error(ex);
            }
            
        }
        LOGGER.debug("Returns count= " + countVal + " and the method count ends");
        return countVal;
    }

    /**
     * Finds the Contract list based on search criteria,start index and offset.
     *
     * @param criteria the criteria
     * @param startIndex the start index
     * @param offset the offset
     * @param columns the columns
     * @return the list< contract master dto>
     */
    public List<SearchResultsDTO> find(final BeanSearchCriteria criteria, final int startIndex, final int offset, final List<OrderByColumn> columns) {

        try {
            LOGGER.debug("Enters find method with parameters startIndex=" + startIndex + " offset=" + offset + " columns list size =" + columns.size());
            if (binderFlag) {
                return chLogic.searchContractMaster(binder, startIndex, offset, columns,criteria);
            }
            LOGGER.debug("End of  find method after returning contractList ");
        } catch (SystemException e) {
           final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
            LOGGER.error(errorMsg);
        } catch (PortalException e) {
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_2005));
        }
        return new ArrayList<SearchResultsDTO>();
    }
}
