package com.stpl.app.global.item.dto;

import com.stpl.app.global.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.global.item.logic.ItemSearchLogic;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemContainer.
 */
public class ItemContainer implements BeanDAO<SearchResultsDTO> {

	/** The logger. */
	private static final Logger LOGGER = Logger.getLogger(ItemContainer.class);

	/** The item search logic. */
	private static final ItemSearchLogic ITEMSEARCHLOGIC = new ItemSearchLogic();

	/** The binder. */
	private ErrorfulFieldGroup binder;

	/** The binder flag. */
	private boolean binderFlag;

	/**
	 * The Constructor.
	 */
	public ItemContainer() {
		/**
		 * Empty Constructor
		 */
	}

	/**
	 * The Constructor.
	 *
	 * @param binder
	 *            the binder
	 */
	public ItemContainer(final ErrorfulFieldGroup binder) {
		this.binder = binder;
		binderFlag = true;
	}

	/**
	 * For Search count.
	 */
    public int count(final BeanSearchCriteria search) {
        LOGGER.debug("Enters count() P1: SearchCriteria");
        int count = 0;
        try {
            if (binderFlag) {
                count = (int) ITEMSEARCHLOGIC.getCount(binder, search);
            } else {
                count = ITEMSEARCHLOGIC.getTotalCount();
            }
            LOGGER.debug("returns count " + count);
        }catch(Exception e)
        {
        LOGGER.error(e);
        
        }
        LOGGER.debug("return count " + count);
        return count;

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
	 * @param binder
	 *            the new binder
	 */
	public void setBinder(final ErrorfulFieldGroup binder) {
		this.binder = binder;
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
	 * @param binderFlag
	 *            the new binder flag
	 */
	public void setBinderFlag(final boolean binderFlag) {
		this.binderFlag = binderFlag;
	}

	/**
	 * (non-Javadoc).
	 * 
	 * @param criteria
	 *            the criteria
	 * @param startIndex
	 *            the start index
	 * @param offset
	 *            the offset
	 * @param columns
	 *            the columns
	 * @return the list
	 * @see org.vaadin.addons.lazycontainer.DAO#find(org.vaadin.addons.lazycontainer.SearchCriteria,
	 *      int, int, java.util.List)
	 */
	public List<SearchResultsDTO> find(final BeanSearchCriteria criteria, final int startIndex, final int offset, final List<OrderByColumn> columns) {
		LOGGER.debug("Enters find() P1: SearchCriteria P2:  startIndex " + startIndex + " P3: offset " + offset + " P4: List<OrderByColumn> size " + columns.size());
        	try {
			if (binderFlag) {
				final List<SearchResultsDTO> salesList = ITEMSEARCHLOGIC.searchItems(binder, startIndex, startIndex + offset, columns, criteria);
				LOGGER.debug("returns salesList size " + salesList.size());
				return salesList;
			}
                }catch (Exception e)
                {
                LOGGER.error(e);
                }

		return new ArrayList<SearchResultsDTO>();
	}

			}
