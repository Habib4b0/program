/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.security.udc.dto;

import com.stpl.app.security.udc.logic.UdcLogic;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.addons.lazycontainer.BeanDAO;
import org.asi.ui.addons.lazycontainer.BeanSearchCriteria;
import org.asi.ui.addons.lazycontainer.OrderByColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Asha
 */
public class BrandContainer implements BeanDAO<BrandMasterDTO> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BrandContainer.class);

    private UdcLogic brandLogic = new UdcLogic();
    /**
     * The binder.
     */
    private ErrorfulFieldGroup binder;
    /**
     * The binder flag.
     */
    private boolean binderFlag;
    private String category;

    /**
     * Instantiates a new ifp dao.
     * @param categoryValue
     */
    public BrandContainer(Object categoryValue) {
        String categoryVal = String.valueOf(categoryValue);
        this.category = categoryVal;
        // empty method
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
     * Gets the binder flag.
     *
     * @return the binder flag
     */
    public boolean isBinderFlag() {
        return binderFlag;
    }

    /**
     * Sets the binder.
     *
     * @param binder the new binder
     */
    public void setBinder(final ErrorfulFieldGroup binder) {
        this.binder = binder;
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
     * Instantiates a new ifp dao.
     *
     * @param binder the binder
     */
    public BrandContainer(final ErrorfulFieldGroup binder) {
        this.binder = binder;
        binderFlag = true;
    }

    /**
     * Method to get the no of count for Searching results.
     *
     * @param search the search
     * @return int
     */
    @Override
    public int count(final BeanSearchCriteria search) {
        int count = 0;
        try {
            count = brandLogic.brandCount();
        } catch (Exception ex) {
             LOGGER.error(ex.getMessage());
        }
        return count;

    }

    /**
     * To find the results based on counts.
     *
     * @param criteria the criteria
     * @param startIndex the start index
     * @param offset the offset
     * @param columns the columns
     * @return List of results
     */
    @Override
    public List<BrandMasterDTO> find(final BeanSearchCriteria criteria, final int startIndex, final int offset, final List<OrderByColumn> columns) {
        List<BrandMasterDTO> dto = new ArrayList<>();
        try {
            dto = brandLogic.brandFind(category, startIndex, offset,columns);
        } catch (Exception ex) {
             LOGGER.error(ex.getMessage());
        }
        return dto;
    }
}
