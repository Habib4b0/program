package com.stpl.app.global.ifp.dto;

import com.stpl.app.global.abstractsearch.dto.SearchResultsDTO;
import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.OrderByColumn;

import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

// TODO: Auto-generated Javadoc
/**
 * The Class IfpDAO.
 */
public class IFPContainer implements BeanDAO<SearchResultsDTO> {

	/** The logger. */
	private static final Logger LOGGER = Logger.getLogger(IFPContainer.class);

	/** The ifp logic. */
	private final IFPLogic ifpLogic = new IFPLogic();

	/** The binder. */
	private ErrorfulFieldGroup binder;

	/** The binder flag. */
	private boolean binderFlag;

	/**
	 * Instantiates a new ifp dao.
	 */
	public IFPContainer() {
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
	public IFPContainer(final ErrorfulFieldGroup binder) {
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
				count = (int) ifpLogic.getSearchCount(binder, search);
			} else {
				count = ifpLogic.getTotalCount();
			}
                }catch (Exception e)
                {
                
                LOGGER.error(e);
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
	public List<SearchResultsDTO> find(final BeanSearchCriteria criteria, final int startIndex, final int offset, final List<OrderByColumn> columns) {

		List<SearchResultsDTO> salesList = new ArrayList();
		try {

			if (binderFlag) {
				salesList = ifpLogic.searchIFP(binder, startIndex, offset, columns, criteria);
                                return salesList;
			}

		
                }catch (Exception e)
                {
                
                LOGGER.error(e);
                
                }
                
                
		return salesList;
	}

	/**
	 * @return the ifpLogic
	 */
	public IFPLogic getIfpLogic() {
		return ifpLogic;
	}

}
