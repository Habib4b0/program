/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.dto;

import com.stpl.app.contract.dashboard.SearchPriceScheduleDTO;
import com.stpl.app.contract.dashboard.logic.DashboardComponentSearchLogic;
import com.stpl.app.contract.ui.ErrorfulFieldGroup;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author Asha
 */
public class PSContainerParentLookup implements BeanDAO<SearchPriceScheduleDTO> {

    /**
     * DTO to hold the search params.
     */
    private SearchPriceScheduleDTO searchDTO;

    /**
     * Logic variable to execute search.
     */
    private final static DashboardComponentSearchLogic PSLOGIC = new DashboardComponentSearchLogic();

    /**
     * LOGGER Field.
     */
    private final static Logger LOGGER = Logger.getLogger(PSContainerParentLookup.class);

    /**
     * Binder Field.
     */
    /**
     * Flag to differentiate default and param search.
     */
    private boolean binderFlag;

    /**
     * The binder.
     */
    private ErrorfulFieldGroup binder;

    /**
     * Gets the search dto.
     *
     * @return the search dto
     */
    public SearchPriceScheduleDTO getSearchDTO() {
        return searchDTO;
    }

    /**
     * Sets the search dto.
     *
     * @param searchDTO the new search dto
     */
    public void setSearchDTO(final SearchPriceScheduleDTO searchDTO) {
        this.searchDTO = searchDTO;
    }

    /**
     * Default Constructor.
     */
    public PSContainerParentLookup() {
        // empty constructor
    }

    /**
     * Constructor with search DTO.
     *
     * @param priceScheduleDTO the price schedule dto
     */
    public PSContainerParentLookup(final SearchPriceScheduleDTO priceScheduleDTO) {
        this.searchDTO = priceScheduleDTO;
    }

    /**
     * Constructor with search binder.
     *
     * @param binder the binder
     */
    public PSContainerParentLookup(final ErrorfulFieldGroup binder) {
        this.binder = binder;
        binderFlag = true;
    }

    /**
     * This method called when scrolling or to get latest search count.
     *
     * @param search the search
     * @return the int
     */
    public int count(final BeanSearchCriteria search) {
        LOGGER.info("Entering count " + search.getFilters());
        try {
            if (binderFlag) {
                LOGGER.info("Entering getDynamicQueryCount");
                LOGGER.info("End of count method");
                return (int) PSLOGIC.getSearchPsCountParentLookup(binder, search);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);

        }
        LOGGER.info(" count method ends ");
        return 0;
    }

    /**
     * This method called to get lazy records of specified size.
     *
     * @param criteria the criteria
     * @param startIndex the start index
     * @param offset the offset
     * @param columns the columns
     * @return the list
     */
    public List<SearchPriceScheduleDTO> find(final BeanSearchCriteria criteria, final int startIndex, final int offset, final List<OrderByColumn> columns) {

        try {

            if (binderFlag) {
                LOGGER.info("Entering find");
                final List<SearchPriceScheduleDTO> salesList1 = PSLOGIC.getSearchPsListParentLookup(binder, startIndex, offset, columns, criteria);
                LOGGER.info("return salesList1 " + ((salesList1 == null) ? salesList1 : salesList1.size()));
                return salesList1;
            }

        } catch (Exception exception) {
            LOGGER.error(exception);

        }

        return new ArrayList<SearchPriceScheduleDTO>();
    }

    /**
     * Checks if is binder flag.
     *
     * @return true, if is binder flag
     */
    public boolean isBinderFlag() {
        return binderFlag;
    }

    /**
     * Sets the binder flag.
     *
     * @param binderFlag the new binder flag
     */
    public void setBinderFlag(final boolean binderFlag) {
        this.binderFlag = binderFlag;
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
     * @param binder the new binder
     */
    public void setBinder(final ErrorfulFieldGroup binder) {
        this.binder = binder;
    }

}
