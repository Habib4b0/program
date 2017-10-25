/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.logic;

import com.stpl.app.contract.dashboard.dto.TempPricingDTO;
import com.stpl.app.contract.global.logic.IfpLogic;
import com.stpl.app.contract.ui.ErrorfulFieldGroup;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author karthikraja.k
 */
public class IFPParentLookupContainer implements BeanDAO<TempPricingDTO> {

	/** The logger. */
	private static final Logger LOGGER = Logger.getLogger(IFPParentLookupContainer.class);

	/** The ifp logic. */
	private final IfpLogic ifpLogic = new IfpLogic();

	/** The binder. */
	private ErrorfulFieldGroup binder;

	/** The binder flag. */
	private boolean binderFlag;

	/**
	 * Instantiates a new ifp dao.
	 */
	public IFPParentLookupContainer() {
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
	 * @param binder
	 *            the new binder
	 */
	public void setBinder(final ErrorfulFieldGroup binder) {
		this.binder = binder;
	}

	/**
	 * Sets the binder flag.
	 *
	 * @param binderFlag
	 *            the new binder flag
	 */
	public void setBinderFlag(final boolean binderFlag) {
		this.binderFlag = binderFlag;
	}

	/**
	 * Instantiates a new ifp dao.
	 *
	 * @param binder
	 *            the binder
	 */
	public IFPParentLookupContainer(final ErrorfulFieldGroup binder) {
		this.binder = binder;
		binderFlag = true;
	}

	/**
	 * Method to get the no of count for Searching results.
	 *
	 * @param search
	 *            the search
	 * @return int
	 */
	public int count(final BeanSearchCriteria search) {
		int count = 0;

		try {
			if (binderFlag) {
				count = (int) ifpLogic.getLookUpSearchCount(binder, search);
			} else {
				count = ifpLogic.getTotalCount();
			}

		}  catch (Exception ex) {
			LOGGER.error(ex);
                       
		}
		return count;

	}

	/**
	 * To find the results based on counts.
	 *
	 * @param criteria
	 *            the criteria
	 * @param startIndex
	 *            the start index
	 * @param offset
	 *            the offset
	 * @param columns
	 *            the columns
	 * @return List of results
	 */

	/**
	 * @return the ifpLogic
	 */

    @Override
    public List<TempPricingDTO> find(final BeanSearchCriteria criteria, final int startIndex, final int offset, final List<OrderByColumn> columns) {
        List<TempPricingDTO> salesList = new ArrayList();
        try {

            if (binderFlag) {
                salesList = ifpLogic.lookupSearchIFP(binder, startIndex, offset, columns, criteria);
                return salesList;
            }

        } catch (SystemException ex) {
            LOGGER.error(ex);
        } catch (PortalException ex) {
            LOGGER.error(ex);
        }
        return salesList;
    }

}
